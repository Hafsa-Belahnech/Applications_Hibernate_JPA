package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {

    @Override
    public void create(Homme homme) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(homme);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void update(Homme homme) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(homme);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void delete(Homme homme) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.delete(session.load(Homme.class, homme.getId()));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Homme findById(int id) {
        Session session = null;
        Homme homme = null;
        try {
            session = HibernateUtil.getSession();
            homme = (Homme) session.get(Homme.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return homme;
    }

    @Override
    public List<Homme> findAll() {
        Session session = null;
        List<Homme> hommes = null;
        try {
            session = HibernateUtil.getSession();
            hommes = session.createQuery("from Homme").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return hommes;
    }

    //Les épouses d'un homme entre deux dates (mariages commencés entre date1 et date2)

    public void afficherEpousesEntreDates(Homme homme, Date date1, Date date2) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();

            String hql = "SELECT m.femme FROM Mariage m " +
                    "WHERE m.homme.id = :idHomme " +
                    "AND m.dateDebut BETWEEN :date1 AND :date2";

            List<Femme> epouses = session.createQuery(hql, Femme.class)
                    .setParameter("idHomme", homme.getId())
                    .setParameter("date1", date1)
                    .setParameter("date2", date2)
                    .list();

            System.out.println("Épouses de " + homme + " mariées entre " + date1 + " et " + date2 + ":");
            for (Femme f : epouses) {
                System.out.println("- " + f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    //API Criteria pour compter les hommes mariés à 4 femmes entre deux dates

    public int compterHommesMarieds4FemmesEntreDates(Date date1, Date date2) {
        Session session = null;
        int count = 0;
        try {
            session = HibernateUtil.getSession();

            Criteria criteria = session.createCriteria(Mariage.class, "m");
            criteria.add(Restrictions.between("m.dateDebut", date1, date2));
            criteria.setProjection(
                    Projections.projectionList()
                            .add(Projections.groupProperty("m.homme.id"), "homme")
                            .add(Projections.countDistinct("m.femme.id"), "nbFemmes")
            );

            List<Object[]> results = criteria.list();

            for (Object[] result : results) {
                Long nbFemmes = (Long) result[1];
                if (nbFemmes == 4) {
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return count;
    }

   // Les mariages d'un homme avec détails

    public void afficherMariagesAvecDetails(Homme homme) {
        System.out.println("\nNom : " + homme.getNom() + " " + homme.getPrenom().toUpperCase());

        Session session = null;
        try {
            session = HibernateUtil.getSession();

            String hql = "FROM Mariage m WHERE m.homme.id = :idHomme ORDER BY m.dateDebut";
            List<Mariage> mariages = session.createQuery(hql, Mariage.class)
                    .setParameter("idHomme", homme.getId())
                    .list();

            List<Mariage> enCours = new java.util.ArrayList<>();
            List<Mariage> echoues = new java.util.ArrayList<>();

            for (Mariage m : mariages) {
                if (m.getDateFin() == null) {
                    enCours.add(m);
                } else {
                    echoues.add(m);
                }
            }

            // Affichage mariages en cours
            System.out.println("Mariages En Cours :");
            int i = 1;
            for (Mariage m : enCours) {
                System.out.println(i + ". Femme : " + m.getFemme().getNom() + " " +
                        m.getFemme().getPrenom() +
                        "\tDate Début : " + m.getDateDebut() +
                        "\tNbr Enfants : " + m.getNbrEnfant());
                i++;
            }

            // Affichage mariages échoués
            System.out.println("\nMariages échoués :");
            i = 1;
            for (Mariage m : echoues) {
                System.out.println(i + ". Femme : " + m.getFemme().getNom() + " " +
                        m.getFemme().getPrenom() +
                        "\tDate Début : " + m.getDateDebut() +
                        "\tDate Fin : " + m.getDateFin() +
                        "\tNbr Enfants : " + m.getNbrEnfant());
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}
