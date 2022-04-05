package ui;

import io.ManipulationFichier;
import model.*;
import utils.Constante;
import utils.Utilitaire;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import static utils.Constante.*;


public class FenParent extends JFrame {
    protected JLabel lblSepProjet, lblSepTask, lblSepSprint, lblProjet, lblTitre, lblRights, lblTasks,
            lblNomProjet, lblDescProjet, lblScrumId, lblTaskPriority, lblDeskTask, lblEmpId, lblTaskId, lblDescSprint,
            lblDateDebut, lblDateFin, lblDureeSprint, lblConsole, lblDateDebutSprint, lblDateFinSprint, lblSprintProgres,
            lblSprint, lblNbrSemaine, lblNbreSprint;
    protected JTextField txtNomProjet, txtDescProjet, txtScrumId, txtDureeSprint, txtTaskPriority, txtDescTask, txtEmployeId,
            txtDescSprint;
    protected JTextArea consoleTxtArea;

    protected JFormattedTextField ftxtDateDebut, ftxtDateFin, ftxtDateDebutSprint, ftxtDateFinSprint;

    protected JMenuBar menuBar;
    protected JMenu mnuFichier;
    protected JMenuItem miSortir, miConsole;

    JComboBox<Employe> jcbTask, jcbEmploye, jcbEmploye2, jcbProgres;
    SimpleDateFormat format;
    DateFormatter formatDate;

    protected JToolBar tbMenu;
    protected ImageIcon iconNew, iconCharger, iconDelete, iconRetour, iconTaskSave, iconSprintSave, iconSave,
            iconAjouterTask, iconModifierTask, iconDeleteTask, iconAjouterSprint, iconModifierSprint, iconDeleteSprint;
    protected JButton btnNouveauProjet, btnRetour, btnChargerProjet, btnDelete, btnEnregistrerProjet, btnEnregistrerSprint,
            btnEnregistrerTask, btnAjouterTask, btnModifierTask, btnDeleteTask, btnAjouterSprint, btnModifierSprint,
            btnDeleteSprint;

    protected CardLayout cL;

    protected JPanel panGlobal, panCard, panEntete, panProjetForm, panTaskForm, panSprintForm, panButton, panButtonTaskSelect,
            panProjetEnCours, panProjetCreation, panBasDePage, panTaskCreation, panTaskCours, panSprintCreation, panSprintCours, panDonneeSprint;

    protected JTable tblProjet, tblSprint, tblTask, tblTaskSelection;
    protected JScrollPane scPaneProjet, scPaneSprint, scPaneTask, scPaneConsole, scPaneTaskSelection;

    protected TableColumn tempCol0, tempCol1, tempCol2;
    protected TableColumnModel colmod;
    protected DefaultTableModel tableModel, tableModel2, tableModel3, tableModel4;

    protected String[] nomColonnesProjet = Constante.TBL_PROJET;
    protected String[] nomColonnesTask = Constante.TBL_TASK;
    protected String[] nomColonnesSprint = Constante.TBL_SPRINT;

    protected int currentCard = 1;
    protected int indexProjetEnCours, indexTaskEnCours;


    // méthode pour remplir un JCombobox avec une liste d'un registre
    public void remplirComboBox(Object o, JComboBox jcbTemp, int type) {
        switch (type) {
            case 1 -> {
                RegistreEmploye registre = (RegistreEmploye) o;

                for (Employe emp : registre.getRegistreEmp()) {
                    jcbTemp.addItem(emp);
                }
                break;
            }
            case 2 -> {
                String[] tableau = (String[]) o;

                for (String emp : tableau) {
                    jcbTemp.addItem(emp);
                }
                break;
            }
            case 3 -> {
                int[] intTab = (int[]) o;
                for (int tmp : intTab) {
                    jcbTemp.addItem(tmp);
                }
            }
        }

    }


    // ***** Méthode pour remplir les tables *****
    // Méthode pour configurere les champs de tables
    public static void setTailleColonneTable(JTable table, int[] tmpColTable) {
        int nbrCol = table.getColumnCount();
        TableColumn column = null;
        for (int i = 0; i < nbrCol; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(tmpColTable[i]);
        }
    }

    public void afficherDonneeSprint() {
        //Transforme les dates en LocalDate
        LocalDate dateDebut = LocalDate.parse(ftxtDateDebut.getText());
        LocalDate dateFin = LocalDate.parse(ftxtDateFin.getText());
        // Recoit le nombre de sprint de la durée spécifié
        int nbrSemaine = Utilitaire.calculerNombreSemaine(dateDebut, dateFin);

        try {
            int nbrSprint = Utilitaire.calculerNombreSprint(nbrSemaine, Integer.parseInt(txtDureeSprint.getText()));
            lblNbrSemaine.setText("Nombre de semaines au projet: " + nbrSemaine + " - Sprints: " +
                    tblSprint.getRowCount() + "/" + nbrSprint);
            lblNbrSemaine.setFont(Constante.F5);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Les sprints sont des semaines saisie en entier seulement.",
                    "Erreur de saisie.", JOptionPane.ERROR_MESSAGE);
        }
    }

    // méthode remplir Projet
    public void remplirTableProjet(DefaultTableModel tableModel, RegistreProjet registreProjet,
                                   RegistreEmploye registreEmploye, JTextArea consoleTxtArea) {
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
        String progres;
        for (Sprint tmp : registreSprint.getRegSprint()) {
            if (tmp.isProgres() == true) {
                progres = PROGRES_SPRINT[1];
            } else {
                progres = PROGRES_SPRINT[0];
            }
            Object[] row = {tmp.getDescription(), formatDate.format(tmp.getDateDebut()),
                    formatDate.format(tmp.getDateFin()), progres};
            tableModel3.addRow(row);
        }
        consoleTxtArea.append("Tableau des sprints créé avec succes\n");
    }

    // méthode pour remplir les table de task
    public void remplirTableTask(DefaultTableModel tableModel2, RegistreTask registreTask, JTextArea consoleTxtArea,
                                 RegistreEmploye registreEmploye) {
        tableModel2.setRowCount(0);
        for (Task tmp : registreTask.getRegistreTasks()) {
            if (tmp.getTaskPriority() != -1) {
                String employe = registreEmploye.getRegistreEmp().get(tmp.getEmployeID()).getNom() + registreEmploye.getRegistreEmp().get(tmp.getEmployeID()).getPrenom();
                Object[] row = {tmp.getTaskID(), tmp.getTaskPriority(), tmp.getDescription(), employe};
                tableModel2.addRow(row);
            }
        }
        consoleTxtArea.append("Tableau des taches créé avec succes.\n");
    }

    // *** Carte des différentes fenêtres
    // Carte de selection de projet
    public void carteSelectionProjet(RegistreProjet registreProjet, RegistreEmploye registreEmploye) {
        lblProjet.setText("Gestion des projets");
        remplirTableProjet(tableModel, registreProjet, registreEmploye, consoleTxtArea);

        cL.show(panCard, "1");
        setToolbarActif(1, btnNouveauProjet, btnChargerProjet, btnDelete, btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint, btnAjouterTask, btnModifierTask, btnDeleteTask);

        currentCard = 1;
    }

    // Carte de gestion d'un projet
    public void carteProjetEnCours(RegistreProjet registreProjet, RegistreTask registreTask,
                                   RegistreEmploye registreEmploye, RegistreSprint registreSprint) {
        int i = tblProjet.getSelectedRow();
        if (i != -1) {
            lblProjet.setText("Projet en cours: " + registreProjet.getRegistrePro().get(indexProjetEnCours).getNomProjet());
        } else {
            consoleTxtArea.append("Erreur de chargement. Selectionner la ligne du projet à charger.\n");

        }

        panProjetEnCours.add(panProjetForm, BorderLayout.NORTH);
        panProjetForm.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 150));
        panProjetEnCours.add(scPaneTask, BorderLayout.EAST);
        panProjetEnCours.add(scPaneSprint, BorderLayout.WEST);

        scPaneTask.setPreferredSize(new Dimension(450, 150));
        setTailleColonneTable(tblTask, Constante.TAILLE_COL_2);

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
        // Remplis le combo box avec les objets de type employé
        jcbEmploye.removeAllItems();
        remplirComboBox(regScrumMaster, jcbEmploye, 1);

        // Récupère l'employé du registre employé en utilisant le scrumMasterID du registre projet
        Employe tmp = registreEmploye.getRegistreEmp().get(registreProjet.getRegistrePro().get(indexProjetEnCours).getScrumMasterId());
        // Initialise la valeur par défaut du JCombobox
        jcbEmploye.setSelectedItem(tmp);

        //Task
        //Vider le registre avant de repopuler avec le contenu du fichier
        registreTask.getRegistreTasks().clear();
        try {
            ManipulationFichier.lire(REPERTOIRE_PROJET + txtNomProjet.getText() + Constante.nomFichier[1], registreTask, 2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        remplirTableTask(tableModel2, registreTask, consoleTxtArea, registreEmploye);

        registreSprint.getRegSprint().clear();
        try {
            ManipulationFichier.lire(REPERTOIRE_PROJET + txtNomProjet.getText() + Constante.nomFichier[2], registreSprint, 3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        remplirTableSprint(tableModel3, registreSprint, consoleTxtArea);

        afficherDonneeSprint();

        panDonneeSprint.add(lblNbrSemaine);

        cL.show(panCard, "3");
        setToolbarActif(3, btnNouveauProjet, btnChargerProjet, btnDelete, btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint, btnAjouterTask, btnModifierTask, btnDeleteTask);
        currentCard = 3;
    }

    // Carte nouveau projet
    public void carteNouveauProjet(RegistreEmploye registreEmploye) {
        lblProjet.setText("Nouveau projet");

        panProjetCreation.add(panProjetForm);
        panProjetForm.setBorder(BorderFactory.createEmptyBorder(10, 50, 250, 150));

        cL.show(panCard, "2");
        reinitialiserFormProjet(txtNomProjet, txtDescProjet, txtScrumId, ftxtDateDebut, ftxtDateFin, txtDureeSprint);
        txtNomProjet.setEnabled(true);
//         Créer un régistre des employées de niveau ScrumMaster
        RegistreEmploye regScrumMaster = registreEmploye.listeEmployeParPoste(registreEmploye, 0);
//         Remplis le combo box avec les objets de type employé
        jcbEmploye.removeAllItems();
        remplirComboBox(regScrumMaster, jcbEmploye, 1);

        setToolbarActif(2, btnNouveauProjet, btnChargerProjet, btnDelete, btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint, btnAjouterTask, btnModifierTask, btnDeleteTask);
        currentCard = 2;
        consoleTxtArea.append("Remplir le formulaire et appuyer sur enregistrer.\n");
    }

    public void carteNouvelleTask(RegistreEmploye registreEmploye) {
        lblTasks.setText("Nouvelle Task");
        panTaskCreation.add(panTaskForm);

        cL.show(panCard, "4");
        txtDescTask.setEnabled(true);
        reinitialiserFormTask(txtDescTask);
        remplirComboBox(PRIORITY, jcbTask, 3);
        remplirComboBox(registreEmploye, jcbEmploye2, 1);
        Employe tmp = registreEmploye.getRegistreEmp().get(indexProjetEnCours);
        jcbEmploye2.setSelectedItem(tmp);
        setToolbarActif(4, btnNouveauProjet, btnChargerProjet, btnDelete, btnAjouterSprint,
                btnDeleteSprint, btnModifierSprint, btnAjouterTask, btnModifierTask, btnDeleteTask);
        currentCard = 4;
        consoleTxtArea.append("Remplir le formulaire et appuyer sur enregistrer.\n");
    }

    //methode pour creer un sprint
    public void carteNouveauSprint(RegistreSprint registreSprint, RegistreTask registreTask,
                                   RegistreEmploye registreEmploye) {
        cL.show(panCard, "6");
        panSprintCreation.add(panSprintForm, BorderLayout.NORTH);
        panSprintCreation.add(scPaneTaskSelection, BorderLayout.EAST);
        panSprintCreation.add(scPaneTask, BorderLayout.WEST);
        panSprintCreation.add(panButtonTaskSelect, BorderLayout.CENTER);
        panButtonTaskSelect.add(btnAjouterTask, BorderLayout.NORTH);
        panButtonTaskSelect.add(btnDeleteTask, BorderLayout.CENTER);

        scPaneTaskSelection.setPreferredSize(new Dimension(425, 150));
        setTailleColonneTable(tblTaskSelection, TAILLE_COL_4);

        scPaneTask.setPreferredSize(new Dimension(425, 150));
        setTailleColonneTable(tblTask, Constante.TAILLE_COL_4);

        jcbProgres.removeAllItems();

        remplirComboBox(Constante.PROGRES_SPRINT, jcbProgres, 2);

        //populer le tableau des Tasks du projet
        registreTask.getRegistreTasks().clear();
        try {
            ManipulationFichier.lire(REPERTOIRE_PROJET + txtNomProjet.getText() + Constante.nomFichier[1],
                    registreTask, 2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        remplirTableTask(tableModel2, registreTask, consoleTxtArea, registreEmploye);

        currentCard = 6;
        consoleTxtArea.append("Remplir le formulaire de sprint et appuyer sur enregistrer\n");
    }

    // methode pour carte modifier Task
    public void carteModifierTask(RegistreTask registreTask, RegistreEmploye registreEmploye) {
        int i = tblTask.getSelectedRow();
        if (i != -1) {
            lblTasks.setText("Task en cours: " + registreTask.getRegistreTasks().get(indexProjetEnCours).getDescription());
        } else {
            consoleTxtArea.append("Erreur de chargement. Selectionner la ligne du projet à charger.\n");
        }
        panTaskCours.add(panTaskForm);
        panTaskForm.setBorder((BorderFactory.createEmptyBorder(10, 50, 300, 150)));
        cL.show(panCard, "5");
        //ajout du task en cours
        reinitialiserFormTask(txtDescTask);
        txtDescTask.setEnabled(false);
        remplirComboBox(PRIORITY, jcbTask, 3);
        jcbTask.setSelectedItem(registreTask.getRegistreTasks().get(indexProjetEnCours).getTaskPriority());
        ArrayList<Task> tmpTsk = registreTask.trierTask();
        txtDescTask.setText(tmpTsk.get(indexTaskEnCours).getDescription());
        remplirComboBox(registreEmploye, jcbEmploye2, 1);
        Employe tmp = registreEmploye.getRegistreEmp().get(registreTask.getRegistreTasks().get(indexTaskEnCours).getEmployeID());
        jcbEmploye2.setSelectedItem(tmp);
        setToolbarActif(4, btnNouveauProjet, btnChargerProjet, btnDelete, btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint, btnAjouterTask, btnModifierTask, btnDeleteTask);
        currentCard = 5;
        consoleTxtArea.append("Modifier la tache et appuyer sur Enregistrer\n");
    }

    // Méthodes pour reinitialiser les champs des formulaires
    public void reinitialiserFormTask(JTextField txtDescTask) {
        jcbTask.removeAllItems();
        txtDescTask.setText("");
        jcbEmploye2.removeAllItems();
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

}
