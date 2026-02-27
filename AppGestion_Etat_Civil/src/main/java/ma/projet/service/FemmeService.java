package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {

    @Override
    public void create(Femme femme) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(femme);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void update(Femme femme) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(femme);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void delete(Femme femme) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.delete(session.load(Femme.class, femme.getId()));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Femme findById(int id) {
        Session session = null;
        Femme femme = null;
        try {
            session = HibernateUtil.getSession();
            femme = (Femme) session.get(Femme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femme;
    }

    @Override
    public List<Femme> findAll() {
        Session session = null;
        List<Femme> femmes = null;
        try {
            session = HibernateUtil.getSession();
            femmes = session.createQuery("from Femme").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femmes;
    }

    // Nombre d'enfants d'une femme entre deux dates

    public int getNombreEnfantsEntreDates(int idFemme, Date date1, Date date2) {
        Session session = null;
        Integer total = 0;
        try {
            session = HibernateUtil.getSession();

            // Utilisation de la requête nommée définie dans l'entité Femme
            Long result = (Long) session.getNamedQuery("Femme.nombreEnfantsEntreDates")
                    .setParameter("idFemme", idFemme)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2)
                    .uniqueResult();

            total = (result != null) ? result.intValue() : 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return total;
    }

     // Femmes mariées au moins deux fois

    public List<Femme> getFemmesMarieesDeuxFois() {
        Session session = null;
        List<Femme> femmes = null;
        try {
            session = HibernateUtil.getSession();

            femmes = session.getNamedQuery("Femme.femmesMarieesDeuxFois")
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femmes;
    }

    // Trouver la femme la plus âgée

    public Femme getFemmeLaPlusAgee() {
        Session session = null;
        Femme femme = null;
        try {
            session = HibernateUtil.getSession();

            String hql = "FROM Femme f ORDER BY f.dateNaissance ASC";
            List<Femme> femmes = session.createQuery(hql, Femme.class)
                    .setMaxResults(1)
                    .list();

            if (!femmes.isEmpty()) {
                femme = femmes.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return femme;
    }
}
