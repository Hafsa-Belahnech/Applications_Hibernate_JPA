package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) {
        try {
            // Initialisation des services
            CategorieService categorieService = new CategorieService();
            ProduitService produitService = new ProduitService();
            CommandeService commandeService = new CommandeService();
            LigneCommandeService ligneCommandeService = new LigneCommandeService();

            System.out.println("=== TEST DE L'APPLICATION DE GESTION DE STOCK ===\n");

            // 1. Création des catégories
            System.out.println("1. __Création des catégories__");
            Categorie cat1 = new Categorie("CAT001", "Ordinateurs");
            Categorie cat2 = new Categorie("CAT002", "Smartphones");
            Categorie cat3 = new Categorie("CAT003", "Accessoires");

            categorieService.create(cat1);
            categorieService.create(cat2);
            categorieService.create(cat3);
            System.out.println("Catégories créées avec succès!\n");

            // 2. Création des produits
            System.out.println("2. Création des produits...");
            Produit p1 = new Produit("ES12", 120.0f);
            p1.setCategorie(cat1);

            Produit p2 = new Produit("ZR85", 100.0f);
            p2.setCategorie(cat1);

            Produit p3 = new Produit("EE85", 200.0f);
            p3.setCategorie(cat2);

            Produit p4 = new Produit("AB50", 50.0f);
            p4.setCategorie(cat3);

            produitService.create(p1);
            produitService.create(p2);
            produitService.create(p3);
            produitService.create(p4);
            System.out.println("Produits créés avec succès!\n");

            // 3. Création d'une commande
            System.out.println("3. Création d'une commande...");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateCommande = sdf.parse("14/03/2013");

            Commande commande = new Commande(dateCommande);
            commandeService.create(commande);
            System.out.println("Commande créée avec succès!\n");

            // 4. Création des lignes de commande
            System.out.println("4. Création des lignes de commande...");
            LigneCommandeProduit lcp1 = new LigneCommandeProduit(7);
            lcp1.setProduit(p1);
            lcp1.setCommande(commande);

            LigneCommandeProduit lcp2 = new LigneCommandeProduit(14);
            lcp2.setProduit(p2);
            lcp2.setCommande(commande);

            LigneCommandeProduit lcp3 = new LigneCommandeProduit(5);
            lcp3.setProduit(p3);
            lcp3.setCommande(commande);

            ligneCommandeService.create(lcp1);
            ligneCommandeService.create(lcp2);
            ligneCommandeService.create(lcp3);
            System.out.println("Lignes de commande créées avec succès!\n");

            // 5. TEST DES MÉTHODES DEMANDÉES

            System.out.println("=== TEST DES FONCTIONNALITÉS ===\n");

            // Test 1: Produits par catégorie
            System.out.println("TEST 1: Produits par catégorie 'Ordinateurs'");
            System.out.println("----------------------------------------------");
            produitService.afficherProduitsParCategorie("Ordinateurs");
            System.out.println();

            // Test 2: Produits commandés entre deux dates
            System.out.println("TEST 2: Produits commandés entre le 01/03/2013 et 31/03/2013");
            System.out.println("-----------------------------------------------------------");
            Date dateDebut = sdf.parse("01/03/2013");
            Date dateFin = sdf.parse("31/03/2013");
            produitService.afficherProduitsCommandesEntreDeuxDates(dateDebut, dateFin);
            System.out.println();

            // Test 3: Produits d'une commande donnée
            System.out.println("TEST 3: Produits de la commande n°1");
            System.out.println("------------------------------------");
            produitService.afficherProduitsCommande(1);
            System.out.println();

            // Test 4: Produits avec prix > 100 DH
            System.out.println("TEST 4: Produits avec prix supérieur à 100 DH");
            System.out.println("----------------------------------------------");
            List<Produit> produitsChers = produitService.afficherProduitsPrixSuperieur100();
            System.out.printf("%-15s %-10s%n", "Référence", "Prix");
            System.out.println("-------------------------");
            for (Produit p : produitsChers) {
                System.out.printf("%-15s %-10.2f DH%n", p.getReference(), p.getPrix());
            }
            System.out.println();

            System.out.println("=== TOUS LES TESTS ONT RÉUSSI! ===");

        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermeture de la SessionFactory
            HibernateUtil.shutdown();
        }
    }
}
