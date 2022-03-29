package ui;

import model.*;
import utils.Constante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.text.Format;
import java.text.SimpleDateFormat;

public class FenParent extends JFrame {


    protected JLabel lblSepProjet, lblSepTask, lblSepSprint, lblProjet, lblTitre, lblRights, lblSprint, lblTasks, lblNomProjet, lblDescProjet, lblScrumId,
            lblDateDebut, lblDateFin, lblDureeSprint, lblConsole;
    protected JTextField txtNomProjet, txtDescProjet, txtScrumId, txtDureeSprint;
    protected JTextArea consoleTxtArea;

    protected JFormattedTextField ftxtDateDebut, ftxtDateFin;

    protected JMenuBar menuBar;
    protected JMenu mnuProjet, mnuSprint, mnuTask, mnuView;
    protected JMenuItem miNouveauProj, miChargerProj, miSupprimerProj, miRetourSelect, miSortir, miAjouterTask, miModifierTask, miDeleteTask, miAjouterSprint, miModifierSprint, miDeleteSprint, miConsole;


    protected JToolBar tbMenu;
    protected ImageIcon iconNew, iconCharger, iconDelete, iconRetour, iconTaskSave, iconSprintSave, iconSave, iconAjouterTask
    , iconModifierTask, iconDeleteTask, iconAjouterSprint, iconModifierSprint, iconDeleteSprint;
    protected JButton btnNew, btnRetour, btnCharger, btnDelete, btnEnregistrer, btnEnregistrerSprint, btnEnregistrerTask, btnAjouterTask, btnModifierTask, btnDeleteTask, btnAjouterSprint, btnModifierSprint, btnDeleteSprint;

    protected CardLayout cL;

    protected JPanel panGlobal, panCard, panEntete, panProjetForm, panTaskForm, panSprintForm, panButton, panProjetEnCours, panProjetCreation, panBasDePage;

    protected JTable tblProjet, tblSprint, tblTask;
    protected JScrollPane scPaneProjet, scPaneSprint, scPaneTask, scPaneConsole;

    protected TableColumn tempCol0, tempCol1;
    protected TableColumnModel colmod;
    protected DefaultTableModel tableModel, tableModel2, tableModel3;

    protected String[] nomColonnesProjet = Constante.TBL_PROJET;
    protected String[] nomColonnesTask = Constante.TBL_TASK;
    protected String[] nomColonnesSprint = Constante.TBL_SPRINT;

    protected int currentCard = 1;
    protected int indexProjetEnCours;
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
        consoleTxtArea.append("Tableau des projets créé avec succes.\nSelectionner un projet et appuyer sur modifier ou appuyer sur nouveau.\n");
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
        consoleTxtArea.append("Tableau des sprints créé avec succes");
    }

    //méthode pour remplir les table de task
    public  void remplirTableTask(DefaultTableModel tableModel2, RegistreTask registreTask, JTextArea consoleTxtArea){
        tableModel2.setRowCount(0);
        for (Task tmp : registreTask.getRegistreTasks()){
            Object[] row = {tmp.getTaskID(), tmp.getTaskPriority(), tmp.getDescription(), tmp.getEmployeID()};
            tableModel2.addRow(row);
        }
        consoleTxtArea.append("Tableau des taches créé avec succes.\n");
    }
    public  void setToolbarActif(int i, JButton btnNew, JButton btnCharger, JButton btnDelete,
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
    public  void reinitialiserFormProjet(JTextField txtNomProjet, JTextField txtDescProjet, JTextField txtScrumId,
                                               JFormattedTextField ftxtDateDebut, JFormattedTextField ftxtDateFin, JTextField txtDureeSprint) {
        txtNomProjet.setText("");
        txtDescProjet.setText("");
        txtScrumId.setText("");
        ftxtDateDebut.setText("");
        ftxtDateFin.setText("");
        txtDureeSprint.setText("");
    }


}
