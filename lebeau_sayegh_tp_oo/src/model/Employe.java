package model;

public class Employe {
    private static int employeID;
    private String nom, prenom, poste;

    public Employe() {
        employeID++;
    }
    //
    public Employe(String nom, String prenom, String poste) {
        employeID++;
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
        return "Employe{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", poste='" + poste + '\'' +
                '}';
    }

}
