package ui;

import io.ManipulationFichier;
import model.*;
import utils.Constante;
import utils.ProjetDejaPresentException;
import utils.Utilitaire;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

public class FenApp extends JFrame {
    private RegistreProjet registreProjet;
    private RegistreEmploye registreEmploye;
    private RegistreTask registreTask;
    private RegistreSprint registreSprint;

    private JLabel lblProjet, lblTitre, lblScrum, lblSprint, lblTasks, lblNomProjet, lblDescProjet, lblScrumId, lblDateDebut, lblDateFin, lblDureeSprint;
    private JTextField txtProjet, txtNomProjet, txtDescProjet, txtScrumId, txtDureeSprint;

    private JFormattedTextField ftxtDateDebut, ftxtDateFin;
    private DateFormat formatDate;

    private JMenuBar menuBar;
    private JMenu mnuProjet, mnuSprint, mnuTask;
    private JMenuItem miNouveauProj, miChargerProj, miSupprimerProj, miRetourSelect, miSortir, miAjouterTask, miModifierTask, miDeleteTask, miAjouterSprint, miModifierSprint, miDeleteSprint;


    private JToolBar tbMenu, tbMenu2;
    private ImageIcon iconNew, iconCharger, iconDelete, iconSelectProjet, iconTaskSave,iconSprintSave, iconSave, iconAjouterTask, iconModifierTask, iconDeleteTask, iconAjouterSprint, iconModifierSprint, iconDeleteSprint;
    private JButton btnNew, btnSelectProjet, btnCharger, btnDelete, btnEnregistrer, btnEnregistrerSprint, btnEnregistrerTask, btnAjouterTask, btnModifierTask, btnDeleteTask, btnAjouterSprint, btnModifierSprint, btnDeleteSprint;

    private CardLayout cl;

    private JSeparator sep1, sepVertical, sepHorizontal, sepHorizontal2, sepHorizontal3;

    private JPanel panGlobal, panCard, panEntete, panTitre, panProjetForm, panTaskForm, panSprintForm, panButton, panProjetEnCours, panProjetCreation, panBasDePage;

    private JTable tblProjet, tblSprint, tblTask;
    private JScrollPane scPaneProjet, scPaneSprint, scPaneTask;

    private TableColumn tempCol0, tempCol1;
    private TableColumnModel colmod;
    private DefaultTableModel tableModel, tableModel2, tableModel3;

    private String[] nomColonnesProjet = Constante.TBL_PROJET;
    private String[] nomColonnesTask = Constante.TBL_TASK;
    private String[] nomColonnesSprint = Constante.TBL_SPRINT;

    private int currentCard = 1;
    private int indexProjetEnCours;


    public FenApp(RegistreProjet projet, RegistreEmploye employe, RegistreTask task) {
        this.registreProjet = projet;
        this.registreEmploye = employe;
        this.registreTask = task;

        // Paramêtre de la fenêtre de l'application
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setWidget();
        setListeners();
    }

    private void setWidget() {

        // Initialisation des labels
        lblNomProjet = new JLabel("Nom du projet: ");
        lblDescProjet = new JLabel("Description du projet: ");
        lblScrumId = new JLabel("Charger de projet: ");
        lblDateDebut = new JLabel("Début du projet (yyyy-mm-dd): ");
        lblDateFin = new JLabel("Fin du projet (yyyy-mm-dd): ");
        lblDureeSprint = new JLabel("Nombre de semaine par sprint: ");

        lblSprint = new JLabel("Sprints restant au projet");
        lblSprint.setFont(Constante.F2);

        lblTasks = new JLabel("Taches restantes au projet");
        lblTasks.setFont(Constante.F2);

        // Initialisation des textfields
        txtProjet = new JTextField(20);
        txtNomProjet = new JTextField(30);
        txtDescProjet = new JTextField(50);
        txtScrumId = new JTextField(20);
        txtDureeSprint = new JTextField(3);

        // Formats de date
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter formatDate = new DateFormatter(format);
        ftxtDateDebut = new JFormattedTextField(formatDate);
        ftxtDateFin = new JFormattedTextField(formatDate);


        // ***** Entete *****

        // *** menuBar ***

        // Initialisation du menuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        // Initialisation item du menu principal
        mnuProjet = new JMenu("Projet");
        mnuSprint = new JMenu("Sprints");
        mnuTask = new JMenu("Taches");

        menuBar.add(mnuProjet);
        menuBar.add(mnuTask);
        menuBar.add(mnuSprint);

        // Initialisation des items du menu Fichiers
        miNouveauProj = new JMenuItem("Créer un Nouveau Projet..");
        miChargerProj = new JMenuItem("Charger un Projet..");
        miSupprimerProj = new JMenuItem("Supprimer Projet..");
        miRetourSelect = new JMenuItem("Changer de projet..");
        miSortir = new JMenuItem("Sortir");
        // Initialisation des items du menu Task
        miAjouterTask = new JMenuItem("Ajouter une tache..");
        miModifierTask = new JMenuItem("Modifier une tache..");
        miDeleteTask = new JMenuItem("Supprimer une tache..");
        // Initialisation des items du menu Sprint
        miAjouterSprint = new JMenuItem("Ajouter un sprint..");
        miModifierSprint = new JMenuItem("Modifier un sprint..");
        miDeleteSprint = new JMenuItem("Supprimer un sprint..");

        // Séparateur de menu
        sep1 = new JSeparator();
        sep1.setForeground(Color.gray);

        // Ajout des items de menu
        mnuProjet.add(miNouveauProj);
        mnuProjet.add(miChargerProj);
        mnuProjet.add(miSupprimerProj);
        mnuProjet.add(sep1);
        mnuProjet.add(miRetourSelect);
        mnuProjet.add(miSortir);

        mnuSprint.add(miAjouterSprint);
        mnuSprint.add(miModifierSprint);
        mnuSprint.add(miDeleteSprint);

        mnuTask.add(miAjouterTask);
        mnuTask.add(miModifierTask);
        mnuTask.add(miDeleteTask);


        // *** Toolbar ***
        // Initialisation du toolbar
        tbMenu = new JToolBar(JToolBar.VERTICAL);
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);

        // Séparateur de la toolbar
        sep1 = new JSeparator();
        sep1.setForeground(Color.gray);
        sepHorizontal = new JSeparator(SwingConstants.HORIZONTAL);
        sepHorizontal.setForeground(Color.lightGray);
        sepHorizontal2 = new JSeparator(SwingConstants.HORIZONTAL);
        sepHorizontal2.setForeground(Color.lightGray);
        sepHorizontal3 = new JSeparator(SwingConstants.HORIZONTAL);
        sepHorizontal3.setForeground(Color.lightGray);

        // Initialisation des icones de boutons
        iconNew = new ImageIcon("src/images/iconNewProjet.png");
        iconSelectProjet = new ImageIcon("src/images/iconSelectProjet.png");
        iconDelete = new ImageIcon("src/images/iconDeleteProjet.png");
        iconCharger = new ImageIcon("src/images/iconChargerProjet.png");
        iconAjouterTask= new ImageIcon("src/images/iconNewTask.png");
        iconModifierTask = new ImageIcon("src/images/iconChargerTask.png");
        iconDeleteTask = new ImageIcon("src/images/iconDeleteTask.png");
        iconAjouterSprint = new ImageIcon("src/images/iconNewSprint.png");
        iconModifierSprint = new ImageIcon("src/images/iconChargerSprint.png");
        iconDeleteSprint = new ImageIcon("src/images/iconDeleteSprint.png");

        // Initialisation des boutons
        btnSelectProjet = new JButton(iconSelectProjet);
        btnSelectProjet.setToolTipText("Retour à la selection de projet..");
        btnNew = new JButton(iconNew);
        btnNew.setToolTipText("Nouveau Projet..");
        btnCharger = new JButton(iconCharger);
        btnCharger.setToolTipText("Charger le Projet Sélectionné..");
        btnDelete = new JButton(iconDelete);
        btnDelete.setToolTipText("Supprimer le Projet Sélectionné..");
        btnAjouterTask = new JButton(iconAjouterTask);
        btnAjouterTask.setToolTipText("Ajouter une tache..");
        btnModifierTask = new JButton(iconModifierTask);
        btnModifierTask.setToolTipText("Modifier une tache..");
        btnDeleteTask = new JButton(iconDeleteTask);
        btnDeleteTask.setToolTipText("Supprimer une tache..");
        btnAjouterSprint = new JButton(iconAjouterSprint);
        btnAjouterSprint.setToolTipText("Ajouter un sprint..");
        btnModifierSprint = new JButton(iconModifierSprint);
        btnModifierSprint.setToolTipText("Modifier un sprint..");
        btnDeleteSprint = new JButton(iconDeleteSprint);
        btnDeleteSprint.setToolTipText("Supprimer un sprint..");

        // Pnneau du toolbar
        panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButton.add(tbMenu);
        // Objet du toolbar
        tbMenu.add(sepHorizontal3);
        tbMenu.add(btnNew);
        tbMenu.add(btnCharger);
        tbMenu.add(btnDelete);
        tbMenu.add(sepHorizontal);
        tbMenu.add(btnAjouterTask);
        tbMenu.add(btnModifierTask);
        tbMenu.add(btnDeleteTask);
        tbMenu.add(sepHorizontal2);
        tbMenu.add(btnAjouterSprint);
        tbMenu.add(btnModifierSprint);
        tbMenu.add(btnDeleteSprint);

        // Bouton d'enregistrement des formulaires
        iconSave = new ImageIcon("src/images/iconSave.png");
        btnEnregistrer = new JButton("Enregistrer", iconSave);
        btnEnregistrer.setToolTipText("Enregistrer le formulaire..");
        btnEnregistrer.setFont(Constante.F3);
        btnEnregistrer.setForeground(Color.GRAY);

        // ***** Tables *****

        // *** Table Projet ***
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(nomColonnesProjet);
        // Table
        tblProjet = new JTable();
        tblProjet.setModel(tableModel);
        // Permet la selection d'une colonne seulement
        tblProjet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Fonction pour remplir la table par le model
        remplirTableProjet();

        scPaneProjet = new JScrollPane(tblProjet);

        scPaneProjet.setPreferredSize(new Dimension(765, 150));
        colmod = tblProjet.getColumnModel();
        tempCol1 = colmod.getColumn(1);
        tempCol1.setPreferredWidth(350);
        tempCol0 = colmod.getColumn(0);
        tempCol0.setPreferredWidth(150);

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

        scPaneTask.setPreferredSize(new Dimension(300, 150));
        colmod = tblTask.getColumnModel();
        tempCol1 = colmod.getColumn(2);
        tempCol1.setPreferredWidth(250);

        // *** Table Sprint ***
        // *** Code pour scPaneSprint

        // *** Formulaires

        // Formulaire Projet
        panProjetForm = new JPanel(new GridLayout(7,2));

        panProjetForm.add(lblNomProjet);
        panProjetForm.add(txtNomProjet);
        panProjetForm.add(lblDescProjet);
        panProjetForm.add(txtDescProjet);
        panProjetForm.add(lblScrumId);
        panProjetForm.add(txtScrumId);
        panProjetForm.add(lblDateDebut);
        panProjetForm.add(ftxtDateDebut);
        panProjetForm.add(lblDateFin);
        panProjetForm.add(ftxtDateFin);
        panProjetForm.add(lblDureeSprint);
        panProjetForm.add(txtDureeSprint);
        panProjetForm.add(btnEnregistrer);

        // Formulaire Task
        // *** Code pour formulaire panTaskForm

        // Formulaire Sprint
        // *** Code pour formulaire panSprintForm


        // *****Card Panels ****

        // Card Panel # 1 - Selection de projet


        // Card Panel # 2 - Formulaire nouveau projet

        panProjetCreation = new JPanel(new BorderLayout());

        // Card Panel # 3 - Gestion de projet

        panProjetEnCours = new JPanel(new BorderLayout());


        // ***** Configuration de la Fenetre Global *****

        // *** Card Panel ***

        // Initialisation du cardPanel
        panCard = new JPanel();
        cl = new CardLayout();
        panCard.setLayout(cl);

        // Ajout des panneaux au card panel
        panCard.add(scPaneProjet, "1");
        panCard.add(panProjetCreation, "2");
        panCard.add(panProjetEnCours, "3");


        // *** Entete ***
        lblProjet = new JLabel("Selection des projets");
        lblProjet.setFont(Constante.F2);

        panEntete = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panTitre = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        panTitre.add(lblProjet);

        iconSprintSave = new ImageIcon("src/images/iconSave.png");
        iconTaskSave = new ImageIcon("src/images/iconSave.png");

        btnEnregistrerTask = new JButton("Enregistrer", iconTaskSave);
        btnEnregistrer.setBackground(Color.white);
        btnEnregistrerSprint = new JButton("Enregistrer", iconSprintSave);

        panEntete.add(btnSelectProjet);
        panEntete.add(panTitre);
        panEntete.setBorder(BorderFactory.createEmptyBorder(0, 2,0,0));

        // *** Bas de page ***
        lblTitre = new JLabel("Tous droit Réservé. ®");
        lblTitre.setFont(Constante.F4);
        lblScrum = new JLabel("Scrum..Master");
        lblScrum.setFont(Constante.F3);
        panBasDePage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panBasDePage.add(lblTitre);
        panBasDePage.add(lblScrum);

        // Initialisation du Layout Global
        panGlobal = new JPanel(new BorderLayout());
        panGlobal.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
        panGlobal.add(panButton, BorderLayout.WEST);
        panGlobal.add(panEntete, BorderLayout.NORTH);
        panGlobal.add(panCard, BorderLayout.CENTER);
        panGlobal.add(panBasDePage, BorderLayout.SOUTH);

        // Configuration du panneau par defaut
        configPan1();

        //
        getContentPane().add(panGlobal);
    }



    private void setListeners() {

        // *** Bouton de la toolbar ***

        // Retour à la selection de projet
        btnSelectProjet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblProjet.setText("Gestion des projets");

                if(currentCard != 1) {
                    int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.", "Retour à la Sélection de projet?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {

                        tableModel.setRowCount(0);
                        remplirTableProjet();

                        cl.show(panCard, "1");
                        configPan1();
                    }
                }
                currentCard = 1;
            }
        });
        // Créer un nouveau projet - formulaire
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                lblProjet.setText("Nouveau projet");

                panProjetCreation.add(panProjetForm);

                cl.show(panCard, "2");
                reinitialiserFormProjet();
                configPan2();
                currentCard = 2;
            }
        });
        // Charger un projet
        btnCharger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexProjetEnCours = tblProjet.getSelectedRow();
                lblProjet.setText("Projet en cours: " + registreProjet.getRegistrePro().get(indexProjetEnCours).getNomProjet());

                panProjetEnCours.add(panProjetForm, BorderLayout.NORTH);
                panProjetEnCours.add(scPaneTask);

                reinitialiserFormProjet();

                // Ajout du projet en cours au textFields
                txtNomProjet.setText(registreProjet.getRegistrePro().get(indexProjetEnCours).getNomProjet());
                txtDescProjet.setText(registreProjet.getRegistrePro().get(indexProjetEnCours).getDescription());
                txtScrumId.setText(String.valueOf(registreProjet.getRegistrePro().get(indexProjetEnCours).getScrumMasterId()));
                ftxtDateDebut.setText(String.valueOf(registreProjet.getRegistrePro().get(indexProjetEnCours).getDateDebut()));
                ftxtDateFin.setText(String.valueOf(registreProjet.getRegistrePro().get(indexProjetEnCours).getDateFin()));
                txtDureeSprint.setText(String.valueOf(registreProjet.getRegistrePro().get(indexProjetEnCours).getDureeSprint()));

                //Task
                try{
                    ManipulationFichier.lire(Constante.REPERTOIRE_PROJET+txtNomProjet.getText()+Constante.nomFichier[1], registreTask, 2);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                remplirTableTask();

                cl.show(panCard, "3");
                configPan3();
                currentCard = 3;
            }
        });
        // Supprimer un projet
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null,"La suppression set final. Êtes-vous sur?", "Suppression de projet",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    try{
                        int i = tblProjet.getSelectedRow();
                        ManipulationFichier.effacerFichiersProjet(registreProjet.getRegistrePro().get(i).getNomProjet());
                        registreProjet.effacerProjet(i);
                        ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET +Constante.nomFichier[0], registreProjet, 1);
                        tableModel.removeRow(i);

                        JOptionPane.showMessageDialog(null,"Suppression du projet complété.");
                    } catch (IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null,"La ligne du projet doit être correctement selectionné pour pouvoir le supprimer.");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Suppression du projet annulée.");
                }
            }
        });

        // Créer un Nouveau Task
        //   *** Code pour btnAjouterTask

        // Modifier un Task
        //   *** Code pour btnModifierTask

        // Supprimer Task
        //   *** Code pour btnDeleteTask

        // Listener de Gestion de Sprint

        // Créer un Nouveau Sprint
        //   *** Code pour btnAjouterSprint

        // Modifier un Sprint
        //   *** Code pour btnModifierSprint

        // Supprimer Sprint
        //   *** Code pour btnDeleteSprint


        //***** Enregistrement de formulaire *****

        // Sauvegarder Formulaire Projet
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ManipulationFichier.nouveauProjet(txtNomProjet.getText());
                DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                Projet tempProj = new Projet(txtNomProjet.getText(), txtDescProjet.getText(), Integer.parseInt(txtScrumId.getText()),
                        Utilitaire.getTodayDate(), Utilitaire.getTodayDate(), Integer.parseInt(txtDureeSprint.getText()));

                try {
                    registreProjet.ajouterProjet(tempProj);
                    for (Projet tmp : registreProjet.getRegistrePro()){
                    }
                } catch (ProjetDejaPresentException ex) {
                    ex.printStackTrace();
                }
                ManipulationFichier.effacerFichier(Constante.REPERTOIRE_PROJET +Constante.nomFichier[0]);
                ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET +Constante.nomFichier[0], registreProjet, 1);
            }
        });
        // Sauvegarder Formulaire Task
        //   *** Code pour btnEnregistrerTask

        //Sauvegarder Formulaire Sprint
        //   *** Code pour btnEnregistrerSprint

        // *** Choix du menu ***
        // SHOULD HAVE
        miRetourSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentCard != 1){
                    int result = JOptionPane.showConfirmDialog(null,"Les données non sauvegardé seron perdu.", "Retour à la Sélection de projet?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(result == JOptionPane.YES_OPTION) {
                        cl.show(panCard, "1");
                        currentCard = 1;
                        lblProjet.setText("Gestion des projets");
                    }
                }
            }
        });

    }

    // ***** Méthode pour remplir les tables *****

    public void remplirTableProjet(){
        Format formatDate = new SimpleDateFormat("yyyy-MM-dd");
        for (Projet tmp : registreProjet.getRegistrePro()){
            Object[] row = {tmp.getNomProjet(), tmp.getDescription(), Integer.toString(tmp.getScrumMasterId()),
                    formatDate.format(tmp.getDateDebut()), formatDate.format(tmp.getDateFin()),
                    Integer.toString(tmp.getDureeSprint())};
            tableModel.addRow(row);
        }
    }
    public void remplirTableTask(){

        for (Task tmp : registreTask.getRegistreTasks()){
            Object[] row = {tmp.getTaskID(), tmp.getTaskPriority(), tmp.getDescription(), tmp.getEmployeID()};
            tableModel2.addRow(row);
        }
    }

    // *** code pour remplirTableSprint()


    // ***** Gestion des card *****

    public void reinitialiserFormProjet(){
        txtNomProjet.setText("");
        txtDescProjet.setText("");
        txtScrumId.setText("");
        ftxtDateDebut.setText("");
        ftxtDateFin.setText("");
        txtDureeSprint.setText("");
    }
    public void configPan1(){
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
    public void configPan2(){
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
    public void configPan3(){
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