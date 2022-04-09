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
    private int progres;

    public Sprint() {
    }

    public Sprint(ArrayList<Integer> taskID, String description,Date dateDebut, Date dateFin, int progres) {
        this.taskID = taskID;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.progres = progres;
    }


    public void ajouterTaskID(int task){
        taskID.add(task);
    }

    public void retirerTaskID(int task){
        int index = 0;
        for (int tmp : taskID){
            if(task == tmp){
                taskID.remove(index);
            }
            index++;
        }
    }

    public ArrayList<Integer> getTaskID() {
        return taskID;
    }

    public void setTaskID(ArrayList<Integer> taskID) {
        this.taskID = taskID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int isProgres() {
        return progres;
    }

    public void setProgres(int progres) {
        this.progres = progres;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "taskID=" + taskID +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", progres=" + progres +
                '}';
    }
}
