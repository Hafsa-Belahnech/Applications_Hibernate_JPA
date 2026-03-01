package ma.projet.classes;

import javax.persistence.*;

@Entity
@Table(name = "LigneCommandeProduit")
public class LigneCommandeProduit {

    @EmbeddedId
    private CommandeProduitPK pK;

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

    public LigneCommandeProduit(int quantite, Produit produit, Commande commande) {
        this.quantite = quantite;
        this.produit = produit;
        this.commande = commande;
        pK = new CommandeProduitPK(commande.getId(), produit.getId());
    }

    // Getters et Setters
    public CommandeProduitPK getpK() {
        return pK;
    }

    public void setpK(CommandeProduitPK pK) {
        this.pK = pK;
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
