package ma.projet.beans;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable //gerer le clé composite
public class MariageId implements Serializable {

    @Column(name = "idHomme")
    private int idHomme;

    @Column(name = "idFemme")
    private int idFemme;

    // Constructeur sans paramètres requis par JPA
    public MariageId() {
    }

    // Constructeur avec paramètres 
    public MariageId(int idHomme, int idFemme) {
        this.idHomme = idHomme;
        this.idFemme = idFemme;
    }

    // GETTERS et SETTERS
    public int getIdHomme() {
        return idHomme;
    }

    public void setIdHomme(int idHomme) {
        this.idHomme = idHomme;
    }

    public int getIdFemme() {
        return idFemme;
    }

    public void setIdFemme(int idFemme) {
        this.idFemme = idFemme;
    }

    // hashCode() & equals()
    @Override
    public int hashCode() {
        return Objects.hash(idHomme, idFemme);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MariageId that = (MariageId) obj;
        return idHomme == that.idHomme && idFemme == that.idFemme;
    }

}
