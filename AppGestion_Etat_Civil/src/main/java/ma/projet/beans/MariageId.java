package ma.projet.beans;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Embeddable;

@Embeddable //gerer le clé composite
public class MariageId implements Serializable {

    @Column(name = "idHomme")
    private int Homme;

    @Column(name = "idFemme")
    private int Femme;

    // Constructeur sans paramètres requis par JPA
    public MariageId() {
    }

    // Constructeur avec paramètres 
    public MariageId(int Homme, int Femme) {
        this.Homme = Homme;
        this.Femme = Femme;
    }

    // GETTERS et SETTERS
    public int getHomme() {
        return Homme;
    }

    public void setHomme(int Homme) {
        this.Homme = Homme;
    }

    public int getFemme() {
        return Femme;
    }

    public void setFemme(int Femme) {
        this.Femme = Femme;
    }

    // hashCode() & equals()
    @Override
    public int hashCode() {
        return Objects.hash(Homme, Femme);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MariageId that = (MariageId) obj;
        return Homme == that.Homme && Femme == that.Femme;
    }

}

