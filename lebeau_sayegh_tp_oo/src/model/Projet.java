package model;

import utils.Utilitaire;

import java.util.Date;

public class Projet {

    private String description;
    private int scrumMasterId;
    private Date dateDebut;
    private Date dateFin;
    private int dureeSprint;

    public Projet() {
    }

    public Projet(String description/* ,int scrumMasterId*/, Date dateFin, int dureeSprint) {

        this.description = description;
      //  this.scrumMasterId = scrumMasterId;
        this.dateDebut = Utilitaire.getTodayDate();
        this.dateFin = dateFin;
        this.dureeSprint = dureeSprint;
    }

    public static int getProjetId() {
        return projetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScrumMasterId() {
        return scrumMasterId;
    }

    public void setScrumMasterId(int scrumMasterId) {
        this.scrumMasterId = scrumMasterId;
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

    public int getDureeSprint() {
        return dureeSprint;
    }

    public void setDureeSprint(int dureeSprint) {
        this.dureeSprint = dureeSprint;
    }

    @Override
    public String toString() {
        return "Projet\n--------\n" +
                description + "\n" +
                "ScrumMaster: " + scrumMasterId +
                "\nDebut Projet: " + dateDebut +
                "\nFin Projet: " + dateFin +
                "\n Duree des Sprint: " + dureeSprint;
    }
}