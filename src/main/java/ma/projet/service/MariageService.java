package ma.projet.service;

import java.util.List;

import org.hibernate.Session;

import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

public class MariageService implements IDao<Mariage> {

    @Override
    public boolean create(Mariage mariage) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(mariage);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Mariage getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Mariage.class, id);
        }
    }

    @Override
    public List<Mariage> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Mariage", Mariage.class).list();
        }
    }
}
