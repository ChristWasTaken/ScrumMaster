package utils;

import model.Employe;

public class EmployeDejaPresentException extends Exception {
    private Employe emp;

    public EmployeDejaPresentException(String msg, Employe temp) {
        super(msg);
        this.emp = temp;
    }
}
