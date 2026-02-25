package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTachePK;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeTacheService implements IDao<EmployeTache> {

    @Override
    public boolean save(EmployeTache et) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(et);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(EmployeTache et) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(et);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(EmployeTache et) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(et);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public EmployeTache findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(EmployeTache.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public EmployeTache findById(EmployeTachePK id) {
        return null;
    }

    @Override
    public List<EmployeTache> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<EmployeTache> query = session.createQuery("FROM EmployeTache", EmployeTache.class);
            return query.list();
        } finally {
            if (session != null) session.close();
        }
    }

    // Méthodes spécifiques
    public List<EmployeTache> findByEmploye(int employeId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<EmployeTache> query = session.createQuery(
                    "FROM EmployeTache et WHERE et.employe.id = :id", EmployeTache.class);
            query.setParameter("id", employeId);
            return query.list();
        } finally {
            if (session != null) session.close();
        }
    }

    public List<EmployeTache> findByTache(int tacheId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<EmployeTache> query = session.createQuery(
                    "FROM EmployeTache et WHERE et.tache.id = :id", EmployeTache.class);
            query.setParameter("id", tacheId);
            return query.list();
        } finally {
            if (session != null) session.close();
        }
    }
}