package ma.projet.classes
  
import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class CommandeProduitPK implements Serializable{
    private int commande;
    private int produit;

    public CommandeProduitPK() {
    }

    public CommandeProduitPK(int commande, int produit) {
        this.commande = commande;
        this.produit = produit;
    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }
    
    

