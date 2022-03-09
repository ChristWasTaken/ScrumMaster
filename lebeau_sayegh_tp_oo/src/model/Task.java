package model;

public class Task {
    private int taskID;
    private int taskPriority;
    private String Description;
    private int employeID;

    public Task() {
    }

    public Task(int taskPriority, String description, int employeID) {
        this.taskPriority = taskPriority;

        Description = description;
        this.employeID = employeID;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getEmployeID() {
        return employeID;
    }

    public void setEmployeID(int employeID) {
        this.employeID = employeID;
    }

    @Override
    public String toString() {
        return "Tache\n------\n" +
                "Priorité: " + taskPriority +
                "Description: " + Description + '\'' +
                "Employe: " + employeID ;
    }
}
