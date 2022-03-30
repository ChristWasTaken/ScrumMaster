package ui;

import io.ManipulationFichier;
import model.*;
import utils.Constante;
import utils.EmployeDejaPresentException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.Format;
import java.text.SimpleDateFormat;

import static utils.Constante.REPERTOIRE_PROJET;


public class FenParent extends JFrame {


    protected JLabel lblSepProjet, lblSepTask, lblSepSprint, lblProjet, lblTitre, lblRights, lblSprint, lblTasks,
            lblNomProjet, lblDescProjet, lblScrumId, lblTaskPriority, lblDeskTask,lblEmpId,
            lblDateDebut, lblDateFin, lblDureeSprint, lblConsole;
    protected JTextField txtNomProjet, txtDescProjet, txtScrumId, txtDureeSprint,txtTaskPriority,txtDescTask,txtEmployeId;
    protected JTextArea consoleTxtArea;

    protected JFormattedTextField ftxtDateDebut, ftxtDateFin;

    protected JMenuBar menuBar;
    protected JMenu mnuFichier;
    protected JMenuItem miSortir, miConsole;

    JComboBox<Employe> jcbEmploye;
    SimpleDateFormat format;
    DateFormatter formatDate;

    protected JToolBar tbMenu;
    protected ImageIcon iconNew, iconCharger, iconDelete, iconRetour, iconTaskSave, iconSprintSave, iconSave,
            iconAjouterTask, iconModifierTask, iconDeleteTask, iconAjouterSprint, iconModifierSprint, iconDeleteSprint;
    protected JButton btnNew, btnRetour, btnCharger, btnDelete, btnEnregistrer, btnEnregistrerSprint,
            btnEnregistrerTask, btnAjouterTask, btnModifierTask, btnDeleteTask, btnAjouterSprint, btnModifierSprint,
            btnDeleteSprint;

    protected CardLayout cL;

    protected JPanel panGlobal, panCard, panEntete, panProjetForm, panTaskForm, panSprintForm, panButton,
            panProjetEnCours, panProjetCreation, panBasDePage, panTaskCreation, panSprintCreation;

    protected JTable tblProjet, tblSprint, tblTask;
    protected JScrollPane scPaneProjet, scPaneSprint, scPaneTask, scPaneConsole;

    protected TableColumn tempCol0, tempCol1, tempCol2;
    protected TableColumnModel colmod;
    protected DefaultTableModel tableModel, tableModel2, tableModel3;

    protected String[] nomColonnesProjet = Constante.TBL_PROJET;
    protected String[] nomColonnesTask = Constante.TBL_TASK;
    protected String[] nomColonnesSprint = Constante.TBL_SPRINT;

    protected int currentCard = 1;
    protected int indexProjetEnCours;
    // méthode pour remplir un JCombobox avec une liste d'un registre
    public void remplirComboBox(Registre regTmp, JComboBox jcbTemp, int type) {
        switch (type) {
            case 1: {
                RegistreEmploye registre = (RegistreEmploye) regTmp;

                for (Employe emp : registre.getRegistreEmp()) {
                    jcbTemp.addItem(emp);
                }

            }
        }
    }
    // ***** Méthode pour remplir les tables *****
    // méthode remplir Projet
    public static void remplirTableProjet(DefaultTableModel tableModel, RegistreProjet registreProjet, RegistreEmploye registreEmploye,
                                          JTextArea consoleTxtArea) {
        Format formatDate = new SimpleDateFormat("yyyy-MM-dd");
        tableModel.setRowCount(0);
        for (Projet tmp : registreProjet.getRegistrePro()) {
            Object[] row = {tmp.getNomProjet(), tmp.getDescription(), registreEmploye.getRegistreEmp().get(tmp.getScrumMasterId()).getPrenom() + " "
                    + registreEmploye.getRegistreEmp().get(tmp.getScrumMasterId()).getNom(), formatDate.format(tmp.getDateDebut()),
                    formatDate.format(tmp.getDateFin()), Integer.toString(tmp.getDureeSprint())};
            tableModel.addRow(row);
        }
        consoleTxtArea.append("Tableau des projets créé avec succes.\nSelectionner un projet et appuyer sur modifier " +
                "ou  appuyer sur nouveau.\n");
    }

    // *** code pour remplirTableSprint()
    public static void remplirTableSprint(DefaultTableModel tableModel3, RegistreSprint registreSprint,
                                          JTextArea consoleTxtArea) {
        Format formatDate = new SimpleDateFormat("yyyy-MM-dd");
        tableModel3.setRowCount(0);
        for (Sprint tmp : registreSprint.getRegSprint()) {
            Object[] row = {tmp.getDescription(), formatDate.format(tmp.getDateDebut()),
                    formatDate.format(tmp.getDateFin()), tmp.isProgres()};
            tableModel3.addRow(row);
        }
        consoleTxtArea.append("Tableau des sprints créé avec succes");
    }

    // méthode pour remplir les table de task
    public void remplirTableTask(DefaultTableModel tableModel2, RegistreTask registreTask, JTextArea consoleTxtArea) {
        tableModel2.setRowCount(0);
        for (Task tmp : registreTask.getRegistreTasks()) {
            Object[] row = {tmp.getTaskID(), tmp.getTaskPriority(), tmp.getDescription(), tmp.getEmployeID()};
            tableModel2.addRow(row);
        }
        consoleTxtArea.append("Tableau des taches créé avec succes.\n");
    }

    public void setToolbarActif(int i, JButton btnNew, JButton btnCharger, JButton btnDelete,
                                JButton btnAjouterSprint, JButton btnDeleteSprint, JButton btnModifierSprint,
                                JButton btnAjouterTask, JButton btnModifierTask, JButton btnDeleteTask) {
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
            case 4 -> {
                btnNew.setEnabled(false);
                btnCharger.setEnabled(false);
                btnDelete.setEnabled(false);
                btnAjouterSprint.setEnabled(false);
                btnDeleteSprint.setEnabled(false);
                btnModifierSprint.setEnabled(false);
                btnAjouterTask.setEnabled(true);
                btnModifierTask.setEnabled(true);
                btnDeleteTask.setEnabled(true);
            }
        }
    }

    // *** Carte des différentes fenêtres
    // Carte de selection de projet
    public void carteSelectionProjet(RegistreProjet registreProjet, RegistreEmploye registreEmploye) {
        lblProjet.setText("Gestion des projets");
        remplirTableProjet(tableModel, registreProjet, registreEmploye, consoleTxtArea);

        cL.show(panCard, "1");
        setToolbarActif(1, btnNew, btnCharger, btnDelete, btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint, btnAjouterTask, btnModifierTask, btnDeleteTask);

        currentCard = 1;
    }
    // Carte de gestion d'un projet
    public void carteProjetEnCours(RegistreProjet registreProjet, RegistreTask registreTask, RegistreEmploye registreEmploye) {
        try {
            lblProjet.setText("Projet en cours: " + registreProjet.getRegistrePro().get(indexProjetEnCours).getNomProjet());
        } catch (IndexOutOfBoundsException e) {
            consoleTxtArea.append("Erreur de chargement. Selectionner la ligne du projet à charger.\n");
            e.printStackTrace();
        }

        panProjetEnCours.add(panProjetForm, BorderLayout.NORTH);
        panProjetEnCours.add(scPaneTask);

        // Ajout du projet en cours au textFields
        reinitialiserFormProjet(txtNomProjet, txtDescProjet, txtScrumId, ftxtDateDebut, ftxtDateFin, txtDureeSprint);
        txtNomProjet.setText(registreProjet.getRegistrePro().get(indexProjetEnCours).getNomProjet());
        txtNomProjet.setEnabled(false);
        txtDescProjet.setText(registreProjet.getRegistrePro().get(indexProjetEnCours).getDescription());
        ftxtDateDebut.setText(format.format(registreProjet.getRegistrePro().get(indexProjetEnCours).getDateDebut()));
        ftxtDateFin.setText(format.format(registreProjet.getRegistrePro().get(indexProjetEnCours).getDateFin()));
        txtDureeSprint.setText(String.valueOf(registreProjet.getRegistrePro().get(indexProjetEnCours).getDureeSprint()));

        // Créer un régistre des employées de niveau ScrumMaster
        RegistreEmploye regScrumMaster = registreEmploye.listeEmployeParPoste(registreEmploye, 0);
//        // Remplis le combo box avec les objets de type employé
        jcbEmploye.removeAllItems();
        remplirComboBox(regScrumMaster, jcbEmploye, 1);

        // Récupère l'employé du registre employé en utilisant le scrumMasterID du registre projet
        Employe tmp = registreEmploye.getRegistreEmp().get(registreProjet.getRegistrePro().get(indexProjetEnCours).getScrumMasterId());
        System.out.println(tmp);
        // Initialise la valeur par défaut du JCombobox
//        jcbEmploye.setSelectedItem(tmp);


        //Task
        //Vider le registre avant de repopuler avec le contenu du fichier
        registreTask.getRegistreTasks().clear();
        try {
            ManipulationFichier.lire(REPERTOIRE_PROJET + txtNomProjet.getText() + Constante.nomFichier[1], registreTask, 2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        remplirTableTask(tableModel2, registreTask, consoleTxtArea);

        cL.show(panCard, "3");
        setToolbarActif(3, btnNew, btnCharger, btnDelete, btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint, btnAjouterTask, btnModifierTask, btnDeleteTask);
        currentCard = 3;
    }
    // Carte nouveau projet
    public void carteNouveauProjet(RegistreEmploye registreEmploye) {
        lblProjet.setText("Nouveau projet");

        panProjetCreation.add(panProjetForm);

        cL.show(panCard, "2");
        reinitialiserFormProjet(txtNomProjet, txtDescProjet, txtScrumId, ftxtDateDebut, ftxtDateFin, txtDureeSprint);
        txtNomProjet.setEnabled(true);
//         Créer un régistre des employées de niveau ScrumMaster
        RegistreEmploye regScrumMaster = registreEmploye.listeEmployeParPoste(registreEmploye, 0);
//         Remplis le combo box avec les objets de type employé
        jcbEmploye.removeAllItems();
        remplirComboBox(regScrumMaster, jcbEmploye, 1);

        setToolbarActif(2, btnNew, btnCharger, btnDelete, btnAjouterSprint, btnDeleteSprint, btnModifierSprint,
                btnAjouterTask, btnModifierTask, btnDeleteTask);
        currentCard = 2;
        consoleTxtArea.append("Remplir le formulaire et appuyer sur enregistrer.\n");
    }

    public void carteNouvelleTask() {
        lblTasks.setText("Nouvelle Task");
        panTaskCreation.add(panTaskForm);
        cL.show(panCard, "4");
        reinitialiserFormTask(txtTaskPriority,txtDescTask,txtEmployeId);
        setToolbarActif(4, btnNew, btnCharger, btnDelete, btnAjouterSprint, btnDeleteSprint, btnModifierSprint,
                btnAjouterTask, btnModifierTask, btnDeleteTask);
        currentCard =4;
        consoleTxtArea.append("Remplir le formulaire et appuyer sur enregistrer.\n");
    }

    // Méthodes pour reinitialiser les champs des formulaires
    public void reinitialiserFormTask(JTextField txtTaskPriority, JTextField txtDescTask, JTextField txtEmployeId){
        txtTaskPriority.setText("");
        txtDescTask.setText("");
        txtEmployeId.setText("");
    }

    public void reinitialiserFormProjet(JTextField txtNomProjet, JTextField txtDescProjet, JTextField txtScrumId,
                                        JFormattedTextField ftxtDateDebut, JFormattedTextField ftxtDateFin,
                                        JTextField txtDureeSprint) {
        txtNomProjet.setText("");
        txtDescProjet.setText("");
        txtScrumId.setText("");
        ftxtDateDebut.setText("");
        ftxtDateFin.setText("");
        txtDureeSprint.setText("");
    }

}
