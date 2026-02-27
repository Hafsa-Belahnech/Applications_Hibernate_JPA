package ma.projet.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Femme.nombreEnfantsEntreDates",
                query = "SELECT SUM(m.nbrEnfant) FROM Mariage m WHERE m.femme.id = :idFemme " +
                        "AND m.dateDebut BETWEEN :date1 AND :date2"
        ),
        @NamedQuery(
                name = "Femme.femmesMarieesDeuxFois",
                query = "SELECT f FROM Femme f JOIN f.mariages m " +
                        "GROUP BY f.id HAVING COUNT(m.id) >= 2"
        )
})
public class Femme extends Personne implements Serializable {

    @OneToMany(mappedBy = "femme", fetch = FetchType.EAGER)
    private List<Mariage> mariages;

    public Femme() {
        super();
    }

    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
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

