package ma.projet.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



 //Singleton: Une seule instance de SessionFactory pour toute l'application

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Chargement de la configuration depuis hibernate.cfg.xml
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Erreur lors de la création de SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    //Ouvrir une nouvelle session

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    // Ferme la SessionFactory
    public static void shutDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
