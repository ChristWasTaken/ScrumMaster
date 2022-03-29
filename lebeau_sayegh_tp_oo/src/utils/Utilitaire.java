package utils;


import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

@SuppressWarnings("FieldCanBeLocal")

public class Utilitaire {

    public static Date getTodayDate() {
        long miliseconds = System.currentTimeMillis();
        return new Date(miliseconds);
    }

    public static int popupOuiNon(String msg, String msgTitre) {
        return JOptionPane.showConfirmDialog(null, msg, msgTitre,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    }

    public static void reinitialiserFormProjet(JTextField txtNomProjet, JTextField txtDescProjet, JTextField txtScrumId,
                                               JFormattedTextField ftxtDateDebut, JFormattedTextField ftxtDateFin, JTextField txtDureeSprint) {
        txtNomProjet.setText("");
        txtDescProjet.setText("");
        txtScrumId.setText("");
        ftxtDateDebut.setText("");
        ftxtDateFin.setText("");
        txtDureeSprint.setText("");
    }

    public static void setToolbarActif(int i, JButton btnNew, JButton btnCharger, JButton btnDelete,
                                 JButton btnAjouterSprint, JButton btnDeleteSprint, JButton btnModifierSprint, JButton btnAjouterTask, JButton btnModifierTask, JButton btnDeleteTask) {
        switch (i) {
            case 1 -> {
                btnNew.setEnabled(true);
                btnCharger.setEnabled(true);
                btnDelete.setEnabled(true);
                btnAjouterSprint.setEnabled(false);
                btnDeleteSprint.setEnabled(false);
                btnModifierSprint.setEnabled(false);
                btnAjouterTask.setEnabled(false);
                btnModifierTask.setEnabled(false);
                btnDeleteTask.setEnabled(false);
            }
            case 2 -> {
                btnNew.setEnabled(true);
                btnCharger.setEnabled(false);
                btnDelete.setEnabled(false);
                btnAjouterSprint.setEnabled(false);
                btnDeleteSprint.setEnabled(false);
                btnModifierSprint.setEnabled(false);
                btnAjouterTask.setEnabled(false);
                btnModifierTask.setEnabled(false);
                btnDeleteTask.setEnabled(false);
            }
            case 3 -> {
                btnNew.setEnabled(false);
                btnCharger.setEnabled(false);
                btnDelete.setEnabled(false);
                btnAjouterSprint.setEnabled(true);
                btnDeleteSprint.setEnabled(true);
                btnModifierSprint.setEnabled(true);
                btnAjouterTask.setEnabled(true);
                btnModifierTask.setEnabled(true);
                btnDeleteTask.setEnabled(true);
            }
        }
    }
    // ***** Méthode pour remplir les tables *****
    //méthode remplir Projet
    public static void remplirTableProjet(DefaultTableModel tableModel, RegistreProjet registreProjet,
                                    JTextArea consoleTxtArea) {
        Format formatDate = new SimpleDateFormat("yyyy-MM-dd");
        tableModel.setRowCount(0);
        for (Projet tmp : registreProjet.getRegistrePro()) {
            Object[] row = {tmp.getNomProjet(), tmp.getDescription(), Integer.toString(tmp.getScrumMasterId()),
                    formatDate.format(tmp.getDateDebut()), formatDate.format(tmp.getDateFin()),
                    Integer.toString(tmp.getDureeSprint())};
            tableModel.addRow(row);
        }
        consoleTxtArea.append("Tableau des projets créé avec success.\nSelectionner un projet et appuyer sur modifier ou appuyer sur nouveau.\n");
    }


    // *** code pour remplirTableSprint()
    public static void  remplirTableSprint(DefaultTableModel tableModel3, RegistreSprint registreSprint,
                                           JTextArea consoleTxtArea){
        Format formatDate = new SimpleDateFormat("yyyy-MM-dd");
        tableModel3.setRowCount(0);
        for(Sprint tmp: registreSprint.getRegSprint()){
            Object[] row = {tmp.getDescription(),formatDate.format(tmp.getDateDebut()),
                    formatDate.format(tmp.getDateFin()), tmp.isProgres()};
            tableModel3.addRow(row);
        }

    }

    //méthode pour remplir les table de task
    public static void remplirTableTask(DefaultTableModel tableModel2, RegistreTask registreTask, JTextArea consoleTxtArea){
        tableModel2.setRowCount(0);
        for (Task tmp : registreTask.getRegistreTasks()){
            Object[] row = {tmp.getTaskID(), tmp.getTaskPriority(), tmp.getDescription(), tmp.getEmployeID()};
            tableModel2.addRow(row);
        }
        consoleTxtArea.append("Tableau des taches créé avec success.\n");
    }




}