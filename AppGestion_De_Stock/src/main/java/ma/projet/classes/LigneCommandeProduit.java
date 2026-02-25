package ma.projet.classes;

import javax.persistence.*;

@Entity
@Table(name = "LigneCommandeProduit")
public class LigneCommandeProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantite")
    private int quantite;

    //Relation entre Produit et LigneCommandeProduit
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    //Relation entre Commande et LigneCommandeProduit
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    // Constructeurs!
    public LigneCommandeProduit() {}

    public LigneCommandeProduit(int quantite) {
        this.quantite = quantite;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
