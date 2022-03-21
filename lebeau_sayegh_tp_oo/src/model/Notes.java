package model;

public class Notes {
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

    @Override
    public String toString() {
        return description;
    }
}
