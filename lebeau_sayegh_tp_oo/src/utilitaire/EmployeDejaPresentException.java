package utilitaire;

import model.Employe;

public class EmployeDejaPresentException extends Exception {
    private Employe emp;

    public EmployeDejaPresentException(String msg, Employe emp) {

    }
}
