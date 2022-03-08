package model;


import java.util.Date;

public class Sprint {
    private int interationId;
    private int[] taskID;
    private Date dateDebut;
    private Date dateFin;
    private boolean progres;

    public Sprint() {
    }

    public Sprint(int[] taskID, Date dateDebut, Date dateFin, boolean progres) {
        Iteration++;
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


}
