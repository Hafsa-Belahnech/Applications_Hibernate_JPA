package ma.projet.classes;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "EmployeTache")
public class EmployeTache {

    @EmbeddedId
    private int id;
    private EmployeTachePK pk;

    //Relation entre Employe et EmployeTache
    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    //Relation entre Tache et EmployeTache
    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;

    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;

    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;

    // Constructeurs
    public EmployeTache() {
    }

    public EmployeTache(Employe employe, Tache tache, Date dateDebutReelle, Date dateFinReelle) {
        this.employe = employe;
        this.tache = tache;
        this.dateDebutReelle = dateDebutReelle;
        this.dateFinReelle = dateFinReelle;
        pk = new EmployeTachePK(employe.getId(), tache.getId());
    }

    // Getters et Setters

    public EmployeTachePK getpk() {
        return pk;
    }

    public void setpk(EmployeTachePK pk) {
        this.pk = pk;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    @Override
    public String toString() {
        return "EmployeTache{" +
                "pk=" + pk +
                ", employe=" + employe +
                ", tache=" + tache +
                ", dateDebutReelle=" + dateDebutReelle +
                ", dateFinReelle=" + dateFinReelle +
                '}';
    }
}
