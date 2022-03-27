package model;

import java.io.Serializable;
@SuppressWarnings("FieldCanBeLocal")

public class Notes implements Serializable {
    private String titre;
    private String description;
    private int taskID;

    public Notes() {
    }

    public Notes(String titre, String description, int taskID) {
        this.titre = titre;
        this.description = description;
        this.taskID = taskID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public String toString() {
        return description;
    }
}
