package utils;

import model.Employe;
import model.Sprint;

public class SprintDejaPresentException extends Exception {
    private Sprint sprint;

    public SprintDejaPresentException(String msg, Sprint temp) {
        super(msg);
        this.sprint = temp;
    }

}
