package ma.projet.service;

import ma.projet.classes.*;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeService implements IDao<Employe> {

    @Override
    public boolean save(Employe e) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(e);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(Employe e) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(e);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(Employe e) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(e);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Employe findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Employe.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public EmployeTache findById(EmployeTachePK id) {
        return null;
    }

    @Override
    public List<Employe> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Employe", Employe.class).list();
        } finally {
            if (session != null) session.close();
        }
    }

    // Méthodes spécifiques
    public void afficherTachesEmploye(int employeId) {
        System.out.println("Tâches de l'employé ID: " + employeId);
    }

    public void afficherProjetsEmploye(int employeId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Employe e = session.get(Employe.class, employeId);
            if (e != null) {
                System.out.println("Projets de : " + e.getPrenom() + " " + e.getNom());
                for (Projet p : e.getProjetsGeres()) {
                    System.out.println(p.getId() + " - " + p.getNom());
                }
            }
        } finally {
            if (session != null) session.close();
        }
    }
}