package ma.projet.classes;

import java.io.Serializable;
import java.util.Objects;

//Classe pour la clé primaire composite de EmployeTache
public class EmployeTachePK implements Serializable {

    private int employe;  // ID de l'employé
    private int tache;    // ID de la tâche

    public EmployeTachePK() {
    }

    public EmployeTachePK(int employe, int tache) {
        this.employe = employe;
        this.tache = tache;
    }


    public int getEmploye() {
        return employe;
    }

    public void setEmploye(int employe) {
        this.employe = employe;
    }

    public int getTache() {
        return tache;
    }

    public void setTache(int tache) {
        this.tache = tache;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeTachePK that = (EmployeTachePK) o;
        return employe == that.employe && tache == that.tache;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employe, tache);
    }
}
