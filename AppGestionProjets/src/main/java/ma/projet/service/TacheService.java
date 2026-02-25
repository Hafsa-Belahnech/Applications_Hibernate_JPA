package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTachePK;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TacheService implements IDao<Tache> {

    @Override
    public boolean save(Tache tache) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(tache);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(Tache tache) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(tache);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(Tache tache) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(tache);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Tache findById(int id) {
        Session session = null;
        Tache tache = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tache = session.get(Tache.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return tache;
    }

    @Override
    public EmployeTache findById(EmployeTachePK id) {
        return null;
    }

    @Override
    public List<Tache> findAll() {
        Session session = null;
        List<Tache> taches = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Tache";
            Query<Tache> query = session.createQuery(hql, Tache.class);
            taches = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return taches;
    }


    public void afficherTachesPrixSuperieur(double prixMin) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            // Solution simple avec HQL
            String hql = "FROM Tache t WHERE t.prix > :prix";
            Query<Tache> query = session.createQuery(hql, Tache.class);
            query.setParameter("prix", prixMin);

            List<Tache> taches = query.list();

            System.out.println("Tâches avec prix > " + prixMin + " DH:");
            System.out.println("Num\tNom\t\tPrix");

            for (Tache tache : taches) {
                System.out.println(tache.getId() + "\t" + tache.getNom() + "\t" + tache.getPrix());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public void afficherTachesEntreDeuxDates(Date dateDebut, Date dateFin) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            // La requête nommée
            Query<Tache> query = session.getNamedQuery("Tache.findByDateBetween");
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            List<Tache> taches = query.list();

            System.out.println("Tâches réalisées entre le " + dateDebut +
                    " et le " + dateFin + ":");
            System.out.println("Num\tNom\t\tDate Début Réelle");

            for (Tache tache : taches) {
                System.out.println(tache.getId() + "\t" +
                        tache.getNom() + "\t\t" +
                        tache.getDateDebutReelle());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}
