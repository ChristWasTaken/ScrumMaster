package model;


import utils.Utilitaire;

import java.io.Serializable;
import java.util.Date;

public class Sprint implements Serializable {


    private int[] taskID;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private boolean progres;

    public Sprint() {
    }

    public Sprint(int[] taskID,String description,  Date dateFin, boolean progres) {
        this.taskID = taskID;
        this.description = description;
        this.dateDebut = Utilitaire.getTodayDate();
        this.dateFin = dateFin;
        this.progres = progres;
    }

    public Sprint(int[] taskID, Date dateDebut, Date dateFin, boolean progres) {
        this.taskID = taskID;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.progres = progres;
    }

    public int[] getTaskID() {
        return taskID;
    }

    public void setTaskID(int[] taskID) {
        this.taskID = taskID;
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

    public boolean isProgres() {
        return progres;
    }

    public void setProgres(boolean progres) {
        this.progres = progres;
    }

    @Override
    public String toString() {
        return "Sprint: " + description +
                "dateDebut= " + dateDebut +
                ", dateFin= " + dateFin +
                ", progres= " + progres

               ;
    }
}
