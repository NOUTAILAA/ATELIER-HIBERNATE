package ma.projet.service;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme homme) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(homme);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Homme getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Homme.class, id);
        }
    }

    @Override
    public List<Homme> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Homme", Homme.class).list();
        }
    }

    public List<Femme> getEpousesParDate(int hommeId, Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Femme> query = session.createQuery(
                "select m.femme from Mariage m where m.homme.id = :hommeId and m.dateDebut >= :dateDebut and m.dateFin <= :dateFin", Femme.class);
            query.setParameter("hommeId", hommeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.list();
        }
    }
    public int getNombreHommesMarieesQuatreFois(Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Mariage> mariage = cq.from(Mariage.class);
            
            cq.select(cb.countDistinct(mariage.get("homme")))
              .where(cb.greaterThanOrEqualTo(mariage.get("dateDebut"), cb.literal(dateDebut)),
                     cb.lessThanOrEqualTo(mariage.get("dateFin"), cb.literal(dateFin)))
              .groupBy(mariage.get("homme"))
              .having(cb.greaterThanOrEqualTo(cb.count(mariage.get("femme")), cb.literal(4L)));
            
            Long result = session.createQuery(cq).uniqueResult();
            return result != null ? result.intValue() : 0;
        }
    }
    public void afficherDetailsMariages(int hommeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Homme homme = session.get(Homme.class, hommeId);
            System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());

            List<Mariage> mariages = session.createQuery(
                "from Mariage m where m.homme.id = :hommeId", Mariage.class)
                .setParameter("hommeId", hommeId)
                .list();

            System.out.println("Mariages En Cours :");
            for (Mariage mariage : mariages) {
                if (mariage.getDateFin() == null) {
                    System.out.println("  Femme : " + mariage.getFemme().getNom() + " "
                            + mariage.getFemme().getPrenom() + " Date Début : "
                            + mariage.getDateDebut() + " Nbr Enfants : " + mariage.getNbrEnfant());
                }
            }

            System.out.println("\nMariages échoués :");
            for (Mariage mariage : mariages) {
                if (mariage.getDateFin() != null) {
                    System.out.println("  Femme : " + mariage.getFemme().getNom() + " "
                            + mariage.getFemme().getPrenom() + " Date Début : "
                            + mariage.getDateDebut() + " Date Fin : " + mariage.getDateFin()
                            + " Nbr Enfants : " + mariage.getNbrEnfant());
                }
            }
        }
    }


}
