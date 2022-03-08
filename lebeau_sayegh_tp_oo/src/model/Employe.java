package model;

public class Employe {
    private static int employeID;
    private String nom, prenom, poste;

    public Employe() {
        employeID++;
    }

    public Employe(String nom, String prenom, String poste) {
        employeID++;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }

}
