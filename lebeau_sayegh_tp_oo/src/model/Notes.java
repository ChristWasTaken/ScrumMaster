package model;

public class Notes {
    private String description;
    private int taskID;

    public Notes() {
    }

    public Notes(String description, int taskID) {
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
