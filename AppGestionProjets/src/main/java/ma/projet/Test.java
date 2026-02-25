package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            EmployeService empService = new EmployeService();
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            EmployeTacheService etService = new EmployeTacheService();

            // Création d'un employé
            Employe emp1 = new Employe("Aya", "Salami", "0612345678");
            empService.save(emp1);

            // Création d'un projet
            Projet projet = new Projet("Gestion de stock",
                    sdf.parse("14/01/2013"),
                    sdf.parse("30/07/2013"));
            projet.setChefProjet(emp1);
            projetService.save(projet);

            // Création des tâches
            Tache t1 = new Tache("Analyse", sdf.parse("15/01/2013"),
                    sdf.parse("30/01/2013"), 1500.0);
            t1.setProjet(projet);
            t1.setDateDebutReelle(sdf.parse("10/02/2013"));
            t1.setDateFinReelle(sdf.parse("20/02/2013"));
            tacheService.save(t1);

            Tache t2 = new Tache("Conception", sdf.parse("01/02/2013"),
                    sdf.parse("28/02/2013"), 2000.0);
            t2.setProjet(projet);
            t2.setDateDebutReelle(sdf.parse("10/03/2013"));
            t2.setDateFinReelle(sdf.parse("15/03/2013"));
            tacheService.save(t2);

            // Affectation d'employé à tâche
            EmployeTache et = new EmployeTache(emp1, t1,
                    sdf.parse("16/02/2013"),
                    sdf.parse("20/02/2013"));
            etService.save(et);

            //Affichage
            System.out.println("=== AFFICHAGE PROJET ===");
            projetService.afficherTachesRealisees(projet.getId());

            System.out.println("\n=== TÂCHES PRIX > 1000 DH ===");
            tacheService.afficherTachesPrixSuperieur(1000.0);

            System.out.println("\n=== TOUTES LES AFFECTATIONS ===");
            for (EmployeTache a : etService.findAll()) {
                System.out.println(a.getEmploye().getPrenom() + " - " + a.getTache().getNom());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}