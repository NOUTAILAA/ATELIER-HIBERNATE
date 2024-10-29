package ma.projet.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe employe) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(employe);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Employe getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employe.class, id);
        }
    }

    @Override
    public List<Employe> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employe", Employe.class).list();
        }
    }

    // Méthode pour afficher la liste des tâches réalisées par un employé
    public List<Tache> getTachesParEmploye(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tache> query = session.createQuery(
                "select et.tache from EmployeTache et where et.employe.id = :employeId", Tache.class);
            query.setParameter("employeId", employeId);
            return query.list();
        }
    }

    // Méthode pour afficher la liste des projets gérés par un employé
    public List<Projet> getProjetsParEmploye(int employeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Projet> query = session.createQuery(
                "select p from Projet p where p.chefDeProjet.id = :employeId", Projet.class);
            query.setParameter("employeId", employeId);
            return query.list();
        }
    }
}
