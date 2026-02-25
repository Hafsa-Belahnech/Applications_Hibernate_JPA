package ma.projet.service;

import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    @Override
    public boolean create(Produit produit) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(produit);
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
    public boolean update(Produit produit) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(produit);
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
    public boolean delete(Produit produit) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(produit);
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
    public Produit findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Produit.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Produit> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Produit", Produit.class).list();
        } finally {
            if (session != null) session.close();
        }
    }

    // Méthode 1: Afficher les produits par catégorie
    public void afficherProduitsParCategorie(String nomCategorie) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String hql = "SELECT p FROM Produit p WHERE p.categorie.libelle = :nomCategorie";
            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("nomCategorie", nomCategorie);

            List<Produit> produits = query.list();

            System.out.println("Produits de la catégorie '" + nomCategorie + "':");
            System.out.println("------------------------------------------------");
            for (Produit p : produits) {
                System.out.println("Référence: " + p.getReference() +
                        " | Prix: " + p.getPrix() + " DH");
            }
        } finally {
            if (session != null) session.close();
        }
    }

    // Méthode 2: Afficher les produits commandés entre deux dates
    public void afficherProduitsCommandesEntreDeuxDates(Date dateDebut, Date dateFin) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String hql = "SELECT DISTINCT p FROM Produit p " +
                    "JOIN p.ligneCommandeProduits lcp " +
                    "JOIN lcp.commande c " +
                    "WHERE c.date BETWEEN :dateDebut AND :dateFin";

            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            List<Produit> produits = query.list();

            System.out.println("Produits commandés entre le " + dateDebut + " et " + dateFin);
            System.out.println("------------------------------------------------------------");
            for (Produit p : produits) {
                System.out.println("Référence: " + p.getReference() +
                        " | Prix: " + p.getPrix() + " DH");
            }
        } finally {
            if (session != null) session.close();
        }
    }

    // Méthode 3: Afficher les produits commandés dans une commande donnée
    public void afficherProduitsCommande(int idCommande) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String hql = "SELECT lcp FROM LigneCommandeProduit lcp " +
                    "JOIN FETCH lcp.produit p " +
                    "WHERE lcp.commande.id = :idCommande";

            Query query = session.createQuery(hql);
            query.setParameter("idCommande", idCommande);

            List<Object[]> results = query.list();

            System.out.println("Commande n°: " + idCommande);
            System.out.println("Liste des produits:");
            System.out.println("------------------------------------------------");
            System.out.printf("%-15s %-10s %-10s%n", "Référence", "Prix", "Quantité");
            System.out.println("------------------------------------------------");

            for (Object obj : results) {
                // Si c'est une LigneCommandeProduit
                if (obj instanceof ma.projet.classes.LigneCommandeProduit) {
                    ma.projet.classes.LigneCommandeProduit lcp =
                            (ma.projet.classes.LigneCommandeProduit) obj;
                    Produit p = lcp.getProduit();
                    System.out.printf("%-15s %-10.2f %-10d%n",
                            p.getReference(), p.getPrix(), lcp.getQuantite());
                }
            }
        } finally {
            if (session != null) session.close();
        }
    }

    // Méthode 4: Produits dont le prix > 100 DH (requête nommée)
    public List<Produit> afficherProduitsPrixSuperieur100() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            // Requête nommée
            String hql = "SELECT p FROM Produit p WHERE p.prix > :prix";
            Query<Produit> query = session.createQuery(hql, Produit.class);
            query.setParameter("prix", 100.0f);

            return query.list();
        } finally {
            if (session != null) session.close();
        }
    }
}
