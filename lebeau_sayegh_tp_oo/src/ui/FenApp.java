/*

     //Could-Have:
     Méthode générique pour remplir les 3 tables.
     Traiter les notes
     Création d'employé
     Ajouter détails à la Carte 1
       */

package ui;

import io.ManipulationFichier;
import model.*;
import utils.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static utils.Constante.*;
import static utils.Utilitaire.calculerMaxSprint;
import static utils.Utilitaire.verifierStringVide;

@SuppressWarnings("FieldCanBeLocal")

public class FenApp extends FenParent {
    protected RegistreProjet registreProjet;
    protected RegistreEmploye registreEmploye;
    protected RegistreTask registreTask;
    protected RegistreSprint registreSprint;

    public FenApp(RegistreProjet projet, RegistreEmploye employe, RegistreTask task, RegistreSprint sprint) {
        this.registreProjet = projet;
        this.registreEmploye = employe;
        this.registreTask = task;
        this.registreSprint = sprint;

        // Paramêtre de la fenêtre de l'application
        setSize(1000, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setWidget();

    }

    private void setWidget() {

        assignedTaskList = new ArrayList<>();

        // Initialisation des labels

        lblNomProjet = new JLabel("Nom du projet: ");
        lblDescProjet = new JLabel("Description du projet: ");
        lblScrumId = new JLabel("Charger de projet: (Niveau: ScrumMaster)");
        lblDateDebut = new JLabel("Début du projet (yyyy-mm-dd): ");
        lblDateFin = new JLabel("Fin du projet (yyyy-mm-dd): ");
        lblDureeSprint = new JLabel("Nombre de semaine par sprint: (2-12 semaines)");
        lblTaskPriority = new JLabel("Définir la priorité");
        lblDeskTask = new JLabel("Description:");
        lblEmpId = new JLabel("Choisir l'employer");
        lblTasks = new JLabel("Taches restantes au projet");
        lblTasks.setFont(Constante.F2);
        lblTaskId = new JLabel("Ajouter/Retiré des tasks de ce sprint en utilisant les flèches.");
        lblDescSprint = new JLabel("Description du sprint: ");
        lblDateDebutSprint = new JLabel(" Date de début: ");
        lblDateFinSprint = new JLabel("Date de fin: ");
        lblSprintProgres = new JLabel(" Progrès: ");
        lblConsigneSprint = new JLabel("           Selectionné les taches et utiliser les fléches pour les assigner.");
        lblConsigneSprint.setFont(Constante.F5);

        lblSprint = new JLabel("");
        lblSprint.setFont(Constante.F2);
        lblNbrSemaine = new JLabel("");
        lblNbreSprint = new JLabel("");

        lblSepProjet = new JLabel("Projet");
        lblSepProjet.setFont(Constante.F4);
        lblSepProjet.setForeground(new Color(204, 123, 31));
        lblSepTask = new JLabel("Tache");
        lblSepTask.setFont(Constante.F4);
        lblSepTask.setForeground(new Color(95, 185, 85));
        lblSepSprint = new JLabel("Sprint");
        lblSepSprint.setFont(Constante.F4);
        lblSepSprint.setForeground(new Color(101, 150, 197));
        lblConsole = new JLabel("Console");
        lblConsole.setFont(Constante.F4);

        // Initialisation des textfields
        txtNomProjet = new JTextField(30);
        txtDescProjet = new JTextField(50);
        txtScrumId = new JTextField(20);
        txtDureeSprint = new JTextField(3);
        txtTaskPriority = new JTextField(3);
        txtDescTask = new JTextField(50);
        txtEmployeId = new JTextField(20);
        txtDescSprint = new JTextField(50);


        // Initialisation des comboBox
        jcbEmploye = new JComboBox<>();
        jcbEmploye2 = new JComboBox<>();
        jcbProgres = new JComboBox<>();
        jcbTask = new JComboBox<>();
        // Formats de date
        format = new SimpleDateFormat("yyyy-MM-dd");
        formatDate = new DateFormatter(format);
        ftxtDateDebut = new JFormattedTextField(formatDate);
        ftxtDateFin = new JFormattedTextField(formatDate);
        ftxtDateDebutSprint = new JFormattedTextField(formatDate);
        ftxtDateFinSprint = new JFormattedTextField(formatDate);
        ftxtDateFinSprint.setEnabled(false);


        // ***** Entete *****

        // *** menuBar ***

        // Initialisation du menuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        // Initialisation item du menu principal
        mnuFichier = new JMenu("Fichier");
        menuBar.add(mnuFichier);

        // Initialisation des items du menu Fichiers
        miSortir = new JMenuItem("Sortir");
        miConsole = new JMenuItem("Cacher/Afficher la Console..");

        // Ajout des items de menu
        mnuFichier.add(miConsole);
        mnuFichier.addSeparator();
        mnuFichier.add(miSortir);

        // *** Toolbar ***
        // Initialisation du toolbar
        tbMenu = new JToolBar(JToolBar.VERTICAL);
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);

        // Initialisation des icones de boutons
        iconRetour = new ImageIcon(REPERTOIRE_IMAGE[0]);
        iconNew = new ImageIcon(REPERTOIRE_IMAGE[1]);
        iconDelete = new ImageIcon(REPERTOIRE_IMAGE[2]);
        iconCharger = new ImageIcon(REPERTOIRE_IMAGE[3]);
        iconAjouterTask = new ImageIcon(REPERTOIRE_IMAGE[4]);
        iconModifierTask = new ImageIcon(REPERTOIRE_IMAGE[5]);
        iconDeleteTask = new ImageIcon(REPERTOIRE_IMAGE[6]);
        iconAjouterSprint = new ImageIcon(REPERTOIRE_IMAGE[7]);
        iconModifierSprint = new ImageIcon(REPERTOIRE_IMAGE[8]);
        iconDeleteSprint = new ImageIcon(REPERTOIRE_IMAGE[9]);
        iconSave = new ImageIcon(REPERTOIRE_IMAGE[10]);
        iconTaskSave = new ImageIcon(REPERTOIRE_IMAGE[10]);
        iconAddTask = new ImageIcon(REPERTOIRE_IMAGE[11]);
        iconRemoveTask = new ImageIcon(REPERTOIRE_IMAGE[12]);

        // Initialisation des boutons
        btnRetour = new JButton(iconRetour);
        btnRetour.setToolTipText("Retour au menu précédant..");
        btnNouveauProjet = new JButton(iconNew);
        btnNouveauProjet.setToolTipText("Nouveau Projet..");
        btnChargerProjet = new JButton(iconCharger);
        btnChargerProjet.setToolTipText("Charger le Projet Sélectionné..");
        btnDeleteProjet = new JButton(iconDelete);
        btnDeleteProjet.setToolTipText("Supprimer le Projet Sélectionné..");
        btnNouveauTask = new JButton(iconAjouterTask);
        btnNouveauTask.setToolTipText("Ajouter une tache..");
        btnModifierTask = new JButton(iconModifierTask);
        btnModifierTask.setToolTipText("Modifier une tache..");
        btnDeleteTask = new JButton(iconDeleteTask);
        btnDeleteTask.setToolTipText("Supprimer une tache..");
        btnNouveauSprint = new JButton(iconAjouterSprint);
        btnNouveauSprint.setToolTipText("Ajouter un sprint..");
        btnModifierSprint = new JButton(iconModifierSprint);
        btnModifierSprint.setToolTipText("Modifier un sprint..");
        btnDeleteSprint = new JButton(iconDeleteSprint);
        btnDeleteSprint.setToolTipText("Supprimer un sprint..");
        btnAjouterTaskSprint = new JButton(iconAddTask);
        btnAjouterTaskSprint.setToolTipText("Ajouter un task au sprint en cours..");
        btnRetirerTaskSprint = new JButton(iconRemoveTask);
        btnRetirerTaskSprint.setToolTipText("Retirer un task au sprint en cours..");

        // Pnneau du toolbar
        panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButton.add(tbMenu);
        // Objet du toolbar
        tbMenu.add(btnRetour);
        tbMenu.addSeparator();
        tbMenu.add(lblSepProjet);
        tbMenu.add(btnNouveauProjet);
        tbMenu.add(btnChargerProjet);
        tbMenu.add(btnDeleteProjet);
        tbMenu.addSeparator();
        tbMenu.add(lblSepTask);
        tbMenu.add(btnNouveauTask);
        tbMenu.add(btnModifierTask);
        tbMenu.add(btnDeleteTask);
        tbMenu.addSeparator();
        tbMenu.add(lblSepSprint);
        tbMenu.add(btnNouveauSprint);
        tbMenu.add(btnModifierSprint);
        tbMenu.add(btnDeleteSprint);
        tbMenu.addSeparator();
        tbMenu.add(lblConsole);

        // Bouton d'enregistrement des formulaires
        btnEnregistrerProjet = new JButton("Enregistrer", iconSave);
        btnEnregistrerProjet.setToolTipText("Enregistrer le formulaire..");
        btnEnregistrerProjet.setFont(Constante.F3);
        btnEnregistrerProjet.setForeground(Color.GRAY);
        btnEnregistrerTask = new JButton("Enregistrer", iconTaskSave);
        btnEnregistrerTask.setBackground(Color.white);
        btnEnregistrerSprint = new JButton("Enregistrer", iconSave);

        // ***** Tables *****

        // *** Table Projet ***
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(nomColonnesProjet);
        // Table
        tblProjet = new JTable();
        tblProjet.setModel(tableModel);
        // Permet la selection d'une colonne seulement
        tblProjet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scPaneProjet = new JScrollPane(tblProjet);

        scPaneProjet.setPreferredSize(new Dimension(765, 150));
        setTailleColonneTable(tblProjet, Constante.TAILLE_COL_1);

        // *** Table Task ***
        tableModel2 = new DefaultTableModel();
        tableModel2.setColumnIdentifiers(nomColonnesTask);
        // Table
        tblTask = new JTable();
        tblTask.setModel(tableModel2);
        // Permet la selection d'une colonne seulement
        tblTask.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Fonction pour remplir la table par le model à faire
        scPaneTask = new JScrollPane(tblTask);

        scPaneTask.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0), BorderFactory.createTitledBorder("Taches")));
        scPaneTask.setPreferredSize(new Dimension(450, 150));
        setTailleColonneTable(tblTask, Constante.TAILLE_COL_2);

        // *** Table Sprint ***
        tableModel3 = new DefaultTableModel();
        tableModel3.setColumnIdentifiers(nomColonnesSprint);
        // Table
        tblSprint = new JTable();
        tblSprint.setModel(tableModel3);
        // Permet la selection d'une colonne seulement
        tblSprint.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scPaneSprint = new JScrollPane(tblSprint);

        scPaneSprint.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0), BorderFactory.createTitledBorder("Projets")));
        scPaneSprint.setPreferredSize(new Dimension(450, 150));
        setTailleColonneTable(tblSprint, Constante.TAILLE_COL_3);

        // ** Table de selection de task (Sprint)

        tableModel4 = new DefaultTableModel();
        tableModel4.setColumnIdentifiers(nomColonnesTask);
        // Table
        tblTaskSelection = new JTable();
        tblTaskSelection.setModel(tableModel4);
        // Permet la selection d'une colonne seulement
        tblTaskSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Fonction pour remplir la table par le model à faire
        scPaneTaskSelection = new JScrollPane(tblTaskSelection);
        scPaneTaskSelection.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0), BorderFactory.createTitledBorder("Taches selectionné pour ce sprint.")));
        scPaneTaskSelection.setPreferredSize(new Dimension(450, 150));


        // *** Formulaires

//        // Formulaire Projet
        panDonneeSprint = new JPanel();

        panProjetForm = new JPanel(new GridLayout(7, 2));
        panProjetForm.setBorder(BorderFactory.createEmptyBorder(10, 50, 250, 150));
        panProjetForm.add(lblNomProjet);
        panProjetForm.add(txtNomProjet);
        panProjetForm.add(lblDescProjet);
        panProjetForm.add(txtDescProjet);
        panProjetForm.add(lblScrumId);
        panProjetForm.add(jcbEmploye);
        panProjetForm.add(lblDateDebut);
        panProjetForm.add(ftxtDateDebut);
        panProjetForm.add(lblDateFin);
        panProjetForm.add(ftxtDateFin);
        panProjetForm.add(lblDureeSprint);
        panProjetForm.add(txtDureeSprint);
        panProjetForm.add(panDonneeSprint);
        panProjetForm.add(btnEnregistrerProjet);
        // Formulaire Task
        // *** Code pour formulaire panTaskForm
        panTaskForm = new JPanel(new GridLayout(4, 2));
        panTaskForm.setBorder(BorderFactory.createEmptyBorder(10, 50, 300, 200));
        panTaskForm.add(lblTaskPriority);
        panTaskForm.add(jcbTask);
        panTaskForm.add(lblDeskTask);
        panTaskForm.add(txtDescTask);
        panTaskForm.add(lblEmpId);
        panTaskForm.add(jcbEmploye2);
        panTaskForm.add(new JLabel());
        panTaskForm.add(btnEnregistrerTask);

        // Formulaire Sprint
        panSprintForm = new JPanel(new GridLayout(5, 2));
        panSprintForm.add(lblDescSprint);
        panSprintForm.add(txtDescSprint);
        panSprintForm.add(lblDateDebutSprint);
        panSprintForm.add(ftxtDateDebutSprint);
        panSprintForm.add(lblDateFinSprint);
        panSprintForm.add(ftxtDateFinSprint);
        panSprintForm.add(lblSprintProgres);
        panSprintForm.add(jcbProgres);
        panSprintForm.add(lblConsigneSprint);
        panSprintForm.add(btnEnregistrerSprint);

        // *****Card Panels ****
        // Card Panel # 1 - Selection de projet (Menu principal COULD HAVE)
        //Utilise seulement le scPaneProjet pour sélectionner un projet pour l'instant. (reste va dans le COULD HAVE)
        // Card Panel # 2 - Formulaire nouveau projet
        panProjetCreation = new JPanel(new BorderLayout());
        // Card Panel # 3 - Gestion de projet
        panProjetEnCours = new JPanel(new BorderLayout());
        // Card Panel #4 panTaskCreation
        panTaskCreation = new JPanel(new BorderLayout());
        // Card Panel #5 panTaskCours
        panTaskCours = new JPanel(new BorderLayout());
        // Card Panel # 6
        panSprintCreation = new JPanel(new BorderLayout());
        panButtonTaskSelect = new JPanel(new BorderLayout());
        // Card Panel # 7
        panSprintCours = new JPanel(new BorderLayout());
        // ***** Configuration de la Fenetre Global *****
        // *** Card Panel ***
        // Initialisation du cardPanel
        panCard = new JPanel();
        cL = new CardLayout();
        panCard.setLayout(cL);
        // Ajout des panneaux au card panel
        panCard.add(scPaneProjet, "1");
        panCard.add(panProjetCreation, "2");
        panCard.add(panProjetEnCours, "3");
        panCard.add(panTaskCreation, "4");
        panCard.add(panTaskCours, "5");
        panCard.add(panSprintCreation, "6");
        panCard.add(panSprintCours, "7");
        // *** Entete ***
        lblProjet = new JLabel("Selection des projets");
        lblProjet.setFont(Constante.F2);
        panEntete = new JPanel(new FlowLayout(FlowLayout.LEFT, 80, 10));
        panEntete.add(lblProjet);
        //  panEntete.add(panTitre);
        //  panEntete.setBorder(BorderFactory.createEmptyBorder(0, 8,0,0));
        // *** Bas de page ***
        lblTitre = new JLabel("Scrum..Master");
        lblTitre.setFont(Constante.F3);
        lblRights = new JLabel("Tout droits réservés. ©");
        lblRights.setFont(Constante.F4);
        consoleTxtArea = new JTextArea(5, 45);
        consoleTxtArea.setEditable(false);
        scPaneConsole = new JScrollPane(consoleTxtArea);
        DefaultCaret caret = (DefaultCaret) consoleTxtArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        panBasDePage = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        panBasDePage.add(scPaneConsole);
        panBasDePage.add(lblTitre);
        panBasDePage.add(lblRights);
        // Initialisation du Layout Global
        panGlobal = new JPanel(new BorderLayout());
        panGlobal.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
        panGlobal.add(panButton, BorderLayout.WEST);
        panGlobal.add(panEntete, BorderLayout.NORTH);
        panGlobal.add(panCard, BorderLayout.CENTER);
        panGlobal.add(panBasDePage, BorderLayout.SOUTH);
        // Configuration du panneau par defaut
        setToolbarActif(1, btnNouveauProjet, btnChargerProjet, btnDeleteProjet, btnNouveauSprint, btnDeleteSprint,
                btnModifierSprint, btnNouveauTask, btnModifierTask, btnDeleteTask);
        // Fonction pour remplir la table par le model
        remplirTableProjet(tableModel, registreProjet, registreEmploye, consoleTxtArea);
        //
        getContentPane().add(panGlobal);
    }
}