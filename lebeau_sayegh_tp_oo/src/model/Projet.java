package model;

import utils.Utilitaire;

import java.io.Serializable;
import java.util.Date;

public class Projet implements Serializable {

    private String nomProjet;
    private String description;
    private int scrumMasterId;
    private Date dateDebut;
    private Date dateFin;
    private int dureeSprint;

    public Projet() {
    }

    public Projet(String nomProjet, String description ,int scrumMasterId, Date dateFin, int dureeSprint) {

        this.nomProjet = nomProjet;
        this.description = description;
        this.scrumMasterId = scrumMasterId;
        this.dateDebut = Utilitaire.getTodayDate();
        this.dateFin = dateFin;
        this.dureeSprint = dureeSprint;
    }

    public Projet(String nomProjet, String description, int scrumMasterId, Date dateDebut, Date dateFin, int dureeSprint) {
        this.nomProjet = nomProjet;
        this.description = description;
        this.scrumMasterId = scrumMasterId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dureeSprint = dureeSprint;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
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
        return "Projet{" +
                "nomProjet='" + nomProjet + '\'' +
                ", description='" + description + '\'' +
                ", scrumMasterId=" + scrumMasterId +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", dureeSprint=" + dureeSprint +
                '}';
    }
}