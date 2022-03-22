package utils;

import model.Projet;

public class ProjetDejaPresentException extends Exception {
    private Projet tmp;

    public ProjetDejaPresentException(String msg, Projet temp) {
        super(msg);
        this.tmp = temp;
    }
}
