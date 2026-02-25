package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTachePK;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public boolean save(Projet p) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(p);
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
    public boolean update(Projet p) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(p);
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
    public boolean delete(Projet p) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(p);
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
    public Projet findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Projet.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public EmployeTache findById(EmployeTachePK id) {
        return null;
    }

    @Override
    public List<Projet> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Projet", Projet.class).list();
        } finally {
            if (session != null) session.close();
        }
    }

    // Méthodes spécifiques
    public void afficherTachesPlanifiees(int projetId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Projet p = session.get(Projet.class, projetId);
            if (p != null) {
                System.out.println("Projet : " + p.getId() + " | Nom : " + p.getNom());
                for (Tache t : p.getTaches()) {
                    System.out.println(t.getId() + " - " + t.getNom());
                }
            }
        } finally {
            if (session != null) session.close();
        }
    }

    public void afficherTachesRealisees(int projetId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Projet p = session.get(Projet.class, projetId);
            if (p != null) {
                System.out.println("Projet : " + p.getId() + " | Nom : " + p.getNom());
                System.out.println("Num\tNom\tDate Début Réelle\tDate Fin Réelle");
                for (Tache t : p.getTaches()) {
                    if (t.getDateDebutReelle() != null) {
                        System.out.println(t.getId() + "\t" + t.getNom() + "\t" +
                                t.getDateDebutReelle() + "\t" + t.getDateFinReelle());
                    }
                }
            }
        } finally {
            if (session != null) session.close();
        }
    }
}