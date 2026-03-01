package ma.projet.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Mariage implements Serializable {

    @EmbeddedId
    private MariageId id;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin; // null si mariage en cours

    private int nbrEnfant;

    @ManyToOne
    @JoinColumn(name = "idHomme")
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "idFemme")
    private Femme femme;

    public Mariage() {
    }

    public Mariage(Date dateDebut, Date dateFin, int nbrEnfant, Homme homme, Femme femme) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfant = nbrEnfant;
        this.homme = homme;
        this.femme = femme;
        id = new MariageId(homme.getId(), femme.getId());
    }

    // Getters & Setters
    public MariageId getid() {
        return id;
    }

    public void setid(MariageId id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbrEnfant() {
        return nbrEnfant;
    }

    public void setNbrEnfant(int nbrEnfant) {
        this.nbrEnfant = nbrEnfant;
    }

    public Homme getHomme() {
        return homme;
    }

    public void setHomme(Homme homme) {
        this.homme = homme;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    @Override
    public String toString() {
        return "Mariage entre " + homme + " et " + femme +
                " le " + dateDebut + " avec " + nbrEnfant + " enfant(s)";
    }
}

