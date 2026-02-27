package ma.projet.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
public class Homme extends Personne implements Serializable {

    //Relation entre Homme et Mariage
    @OneToMany(mappedBy = "homme", fetch = FetchType.EAGER)
    private List<Mariage> mariages;

    public Homme() {
        super();
    }

    public Homme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }

    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }

    @Override
    public String toString() {
        return getNom() + " " + getPrenom();
    }
}
