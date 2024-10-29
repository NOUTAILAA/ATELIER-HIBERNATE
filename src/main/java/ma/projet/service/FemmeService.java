package ma.projet.service;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import org.hibernate.Session;

import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

public class FemmeService implements IDao<Femme> {

    @Override
    public boolean create(Femme femme) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(femme);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Femme getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Femme.class, id);
        }
    }

    @Override
    public List<Femme> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Femme", Femme.class).list();
        }
    }
    public int getNombreEnfantsEntreDeuxDates(int femmeId, Date dateDebut, Date dateFin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                "select sum(m.nbrEnfant) from Mariage m where m.femme.id = :femmeId and m.dateDebut >= :dateDebut and m.dateFin <= :dateFin", Long.class);
            query.setParameter("femmeId", femmeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            Long result = query.uniqueResult();
            return result != null ? result.intValue() : 0;
        }
    }
    public List<Femme> getFemmesMarieesDeuxFoisOuPlus() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Femme> query = session.createQuery(
                "select m.femme from Mariage m group by m.femme having count(m) >= 2", Femme.class);
            return query.list();
        }
    }

}
