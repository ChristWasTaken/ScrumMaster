package model;


import java.io.Serializable;

public class Task implements Serializable {
    private int taskID;
    private int taskPriority;
    private String Description;
    private int employeID;
    private static int nbrTask = 0;

    public Task() {
    }

    public Task(int taskPriority, String description, int employeID) {
        this.taskID = nbrTask;
        nbrTask++;
        this.taskPriority = taskPriority;
        this.Description = description;
        this.employeID = employeID;
    }

    public Task(int taskID, int taskPriority, String description, int employeID) {
        this.taskID = taskID;
        nbrTask=taskID;
        this.taskPriority = taskPriority;
        Description = description;
        this.employeID = employeID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
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

    public static void setNbrTask(int nbrTask) {
        Task.nbrTask = nbrTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", taskPriority=" + taskPriority +
                ", Description='" + Description + '\'' +
                ", employeID=" + employeID +
                '}';
    }

}



