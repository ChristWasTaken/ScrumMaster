package utils;

import model.Employe;
import model.Projet;
import model.Sprint;
import model.Task;

import javax.swing.*;

public class DoublonException extends Exception {
    private Employe emp;
    private Sprint sprint;
    private Task task;
    private Projet projet;

    public DoublonException(String msg, Object objet, int i) {

        switch (i) {
            case 0 -> {
                this.emp = (Employe) objet;
                JOptionPane.showMessageDialog(null, msg, "Employé Existant", JOptionPane.WARNING_MESSAGE);
            }
            case 1 -> {
                this.projet = (Projet) objet;
                JOptionPane.showMessageDialog(null, msg, "Projet Existant", JOptionPane.WARNING_MESSAGE);
            }
            case 2 -> {
                this.task = (Task) objet;
                JOptionPane.showMessageDialog(null, msg, "Tache Existante", JOptionPane.WARNING_MESSAGE);
            }
            case 3 -> {
                this.sprint = (Sprint) objet;
                JOptionPane.showMessageDialog(null, msg, "Sprint Existant", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
