package ma.projet;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Format pour les dates
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Initialisation des services
            FemmeService femmeService = new FemmeService();
            HommeService hommeService = new HommeService();
            MariageService mariageService = new MariageService();

            System.out.println("=== CRÉATION DES DONNÉES DE TEST ===\n");

            // Création de 10 femmes
            Femme f1 = new Femme("RAMI", "Salima", "0601010101", "Casablanca", sdf.parse("15/05/1985"));
            Femme f2 = new Femme("ALI", "Amal", "0602020202", "Rabat", sdf.parse("20/08/1990"));
            Femme f3 = new Femme("ALAOUI", "Wafa", "0603030303", "Fès", sdf.parse("10/03/1988"));
            Femme f4 = new Femme("ALAMI", "Karima", "0604040404", "Marrakech", sdf.parse("05/12/1982"));
            Femme f5 = new Femme("BENALI", "Nadia", "0605050505", "Tanger", sdf.parse("25/07/1992"));
            Femme f6 = new Femme("IDRISI", "Samira", "0606060606", "Agadir", sdf.parse("30/01/1987"));
            Femme f7 = new Femme("TALBI", "Leila", "0607070707", "Oujda", sdf.parse("12/09/1991"));
            Femme f8 = new Femme("HASSANI", "Fatima", "0608080808", "Meknès", sdf.parse("18/04/1989"));
            Femme f9 = new Femme("FILALI", "Zineb", "0609090909", "Tétouan", sdf.parse("22/11/1993"));
            Femme f10 = new Femme("QADIRI", "Houda", "0610101010", "Safi", sdf.parse("08/06/1986"));

            femmeService.create(f1);
            femmeService.create(f2);
            femmeService.create(f3);
            femmeService.create(f4);
            femmeService.create(f5);
            femmeService.create(f6);
            femmeService.create(f7);
            femmeService.create(f8);
            femmeService.create(f9);
            femmeService.create(f10);

            System.out.println("✓ 10 femmes créées");

            // Création de 5 hommes
            Homme h1 = new Homme("SAFI", "Said", "0701010101", "Casablanca", sdf.parse("10/02/1980"));
            Homme h2 = new Homme("KABAJ", "Omar", "0702020202", "Rabat", sdf.parse("25/06/1985"));
            Homme h3 = new Homme("LOUZI", "Karim", "0703030303", "Fès", sdf.parse("14/11/1982"));
            Homme h4 = new Homme("MAZIGH", "Youssef", "0704040404", "Marrakech", sdf.parse("30/03/1988"));
            Homme h5 = new Homme("NAJIB", "Hassan", "0705050505", "Tanger", sdf.parse("05/09/1990"));

            hommeService.create(h1);
            hommeService.create(h2);
            hommeService.create(h3);
            hommeService.create(h4);
            hommeService.create(h5);

            System.out.println(" 5 hommes créés\n");

            // Création des mariages
            System.out.println("=== CRÉATION DES MARIAGES ===\n");

            // Mariages pour h1 (SAFI Said) - 3 mariages en cours + 1 échoué
            Mariage m1 = new Mariage(sdf.parse("03/09/1990"), null, 4, h1, f1); // Salima RAMI
            Mariage m2 = new Mariage(sdf.parse("03/09/1995"), null, 2, h1, f2); // Amal ALI
            Mariage m3 = new Mariage(sdf.parse("04/11/2000"), null, 3, h1, f3); // Wafa ALAOUI
            Mariage m4 = new Mariage(sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0, h1, f4); // Karima ALAMI (échoué)

            // Mariages pour h2 - 2 mariages
            Mariage m5 = new Mariage(sdf.parse("15/06/2010"), null, 2, h2, f5);
            Mariage m6 = new Mariage(sdf.parse("20/01/2015"), null, 1, h2, f6);

            // Mariages pour h3 - 1 mariage
            Mariage m7 = new Mariage(sdf.parse("10/05/2012"), null, 3, h3, f7);

            // Mariages pour h4 - 2 mariages (1 en cours, 1 échoué)
            Mariage m8 = new Mariage(sdf.parse("22/08/2008"), sdf.parse("15/03/2015"), 2, h4, f8);
            Mariage m9 = new Mariage(sdf.parse("20/06/2016"), null, 1, h4, f9);

            // Mariages pour h5 - 1 mariage
            Mariage m10 = new Mariage(sdf.parse("05/12/2018"), null, 0, h5, f10);

            mariageService.create(m1);
            mariageService.create(m2);
            mariageService.create(m3);
            mariageService.create(m4);
            mariageService.create(m5);
            mariageService.create(m6);
            mariageService.create(m7);
            mariageService.create(m8);
            mariageService.create(m9);
            mariageService.create(m10);

            System.out.println(" 10 mariages créés\n");

            // TESTS

            System.out.println("\n========================================");
            System.out.println("1. AFFICHER LA LISTE DES FEMMES");
            System.out.println("===========================================");
            List<Femme> femmes = femmeService.findAll();
            for (Femme f : femmes) {
                System.out.println("- " + f.getNom() + " " + f.getPrenom() +
                        " (née le " + f.getDateNaissance() + ")");
            }

            System.out.println("\n========================================");
            System.out.println("2. AFFICHER LA FEMME LA PLUS ÂGÉE");
            System.out.println("========================================");
            Femme plusAgee = femmeService.getFemmeLaPlusAgee();
            if (plusAgee != null) {
                System.out.println("La femme la plus âgée est: " + plusAgee.getNom() + " " +
                        plusAgee.getPrenom() + " née le " + plusAgee.getDateNaissance());
            }

            System.out.println("\n========================================");
            System.out.println("3. AFFICHER LES ÉPOUSES D'UN HOMME ENTRE DEUX DATES");
            System.out.println("========================================");
            Date date1 = sdf.parse("01/01/1990");
            Date date2 = sdf.parse("31/12/2000");
            hommeService.afficherEpousesEntreDates(h1, date1, date2);

            System.out.println("\n========================================");
            System.out.println("4. NOMBRE D'ENFANTS D'UNE FEMME ENTRE DEUX DATES");
            System.out.println("========================================");
            int nbEnfants = femmeService.getNombreEnfantsEntreDates(f1.getId(),
                    sdf.parse("01/01/1990"), sdf.parse("31/12/2020"));
            System.out.println("Nombre d'enfants de " + f1.getNom() + " " + f1.getPrenom() +
                    " entre 1990 et 2020: " + nbEnfants);

            System.out.println("\n========================================");
            System.out.println("5. FEMMES MARIÉES DEUX FOIS OU PLUS");
            System.out.println("========================================");
            List<Femme> femmesMariees2fois = femmeService.getFemmesMarieesDeuxFois();
            for (Femme f : femmesMariees2fois) {
                System.out.println("- " + f.getNom() + " " + f.getPrenom() +
                        " (nombre de mariages: " + f.getMariages().size() + ")");
            }

            System.out.println("\n========================================");
            System.out.println("6. HOMMES MARIÉS À QUATRE FEMMES ENTRE DEUX DATES");
            System.out.println("========================================");
            int nbHommes4femmes = hommeService.compterHommesMarieds4FemmesEntreDates(
                    sdf.parse("01/01/1980"), sdf.parse("31/12/2025"));
            System.out.println("Nombre d'hommes mariés à 4 femmes entre 1980 et 2025: " + nbHommes4femmes);

            System.out.println("\n========================================");
            System.out.println("7. AFFICHER LES MARIAGES D'UN HOMME AVEC DÉTAILS");
            System.out.println("========================================");
            hommeService.afficherMariagesAvecDetails(h1);

            System.out.println("\n========================================");
            System.out.println("=== TOUS LES TESTS SONT TERMINÉS ===");
            System.out.println("========================================");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture de la SessionFactory
            HibernateUtil.shutDown();
        }
    }
}
