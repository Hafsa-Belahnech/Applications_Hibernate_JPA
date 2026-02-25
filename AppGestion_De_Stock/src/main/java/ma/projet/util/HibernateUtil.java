package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Création de la configuration
            Configuration configuration = new Configuration();

            // Chargement des propriétés
            configuration.configure(); // Charge hibernate.cfg.xml si existe

            // Ajout des classes annotées
            configuration.addAnnotatedClass(ma.projet.classes.Categorie.class);
            configuration.addAnnotatedClass(ma.projet.classes.Produit.class);
            configuration.addAnnotatedClass(ma.projet.classes.Commande.class);
            configuration.addAnnotatedClass(ma.projet.classes.LigneCommandeProduit.class);

            // Construction de la SessionFactory
            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //Fermeture de la session
    public static void shutdown() {
        getSessionFactory().close();
    }
}
