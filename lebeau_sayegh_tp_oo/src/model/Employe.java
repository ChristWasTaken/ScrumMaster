package model;

import java.io.Serializable;

public class Employe implements Serializable {
    private static int nbrEmploye=0;
    private String nom, prenom, poste;
    private int employeID;

    public Employe() {}
    //
    public Employe(String nom, String prenom, String poste) {
        this.employeID = nbrEmploye;
        nbrEmploye++;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
    @Override
    public String toString() {
        return  " Employe{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", poste='" + poste + '\'' +
                '}';
    }

}
