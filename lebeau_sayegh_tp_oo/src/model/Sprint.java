package model;


import utils.Utilitaire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Sprint implements Serializable {


    private ArrayList<Integer> taskID;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private boolean progres;

    public Sprint() {
    }

    public Sprint(ArrayList<Integer> taskID, String description, Date dateFin, boolean progres) {
        this.taskID = taskID;
        this.description = description;
        this.dateDebut = Utilitaire.getTodayDate();
        this.dateFin = dateFin;
        this.progres = progres;
    }


    public ArrayList<Integer> getTaskID() {
        return taskID;
    }

    public void setTaskID(ArrayList<Integer> taskID) {
        this.taskID = taskID;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
