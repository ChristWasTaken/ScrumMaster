package utils;

import model.Task;

public class TaskDejaExistException extends Exception {
    private Task task;

    public TaskDejaExistException(String msg, Task task) {
        super(msg);
        this.task = task;
    }
}
