package ma.projet.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

public class ProjetService implements IDao<Projet> {

    @Override
    public boolean create(Projet projet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(projet);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Projet getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Projet.class, id);
        }
    }

    @Override
    public List<Projet> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Projet", Projet.class).list();
        }
    }

    // Méthode pour afficher la liste des tâches planifiées pour un projet
    public List<Tache> getTachesParProjet(int projetId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tache> query = session.createQuery(
                "select t from Tache t where t.projet.id = :projetId", Tache.class);
            query.setParameter("projetId", projetId);
            return query.list();
        }
    }
}
