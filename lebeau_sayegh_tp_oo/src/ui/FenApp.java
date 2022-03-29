/*

     //Must-Have:
     Terminer formulaire de Gestion de projet

     Formulaire de Task + carte 4 + settoolbar pour la carte
     Code pour btnEnregistrerTask

     Formulaire de Sprint + carte 5 + settoolbar pour la carte
     Code pour btnEnregistrerSprint

     Créer la table sprint + methode pour la remplir

     Exception de saisie Invalide pour les formulaires.
     Faire la vérification de saisie et d'exception pour les formulaires.

     Code pour btnAjouterTask
     Code pour btnModifierTask
     Code pour btnDeleteTask
     Code pour btnAjouterSprint
     Code pour btnModifierSprint
     Code pour btnDeleteSprint

     //Should-Have:
     Exception générique pour les doublons au lieu de 4 différentes.



     //Could-Have:
     Menu de haut de page pour les cartes. (J'ai juste configurer sortir et view)
     Méthode générique pour remplir les 3 tables.
     Traiter les notes
     Création d'employé
     Ajouter détails à la Carte 1
*       */


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
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import static utils.Utilitaire.reinitialiserFormProjet;
import static utils.Utilitaire.setToolbarActif;

@SuppressWarnings("FieldCanBeLocal")

public class FenApp extends JFrame {
    private RegistreProjet registreProjet;
    private RegistreEmploye registreEmploye;
    private RegistreTask registreTask;
    private RegistreSprint registreSprint;

    private JLabel lblSepProjet, lblSepTask, lblSepSprint, lblProjet, lblTitre, lblRights, lblSprint, lblTasks, lblNomProjet, lblDescProjet, lblScrumId,
            lblDateDebut, lblDateFin, lblDureeSprint, lblConsole;
    private JTextField txtNomProjet, txtDescProjet, txtScrumId, txtDureeSprint;
    private JTextArea consoleTxtArea;

    private JFormattedTextField ftxtDateDebut, ftxtDateFin;

    private JMenuBar menuBar;
    private JMenu mnuProjet, mnuSprint, mnuTask, mnuView;
    private JMenuItem miNouveauProj, miChargerProj, miSupprimerProj, miRetourSelect, miSortir, miAjouterTask, miModifierTask, miDeleteTask, miAjouterSprint, miModifierSprint, miDeleteSprint, miConsole;


    private JToolBar tbMenu;
    private ImageIcon iconNew, iconCharger, iconDelete, iconRetour, iconTaskSave,iconSprintSave, iconSave, iconAjouterTask, iconModifierTask, iconDeleteTask, iconAjouterSprint, iconModifierSprint, iconDeleteSprint;
    private JButton btnNew, btnRetour, btnCharger, btnDelete, btnEnregistrer, btnEnregistrerSprint, btnEnregistrerTask, btnAjouterTask, btnModifierTask, btnDeleteTask, btnAjouterSprint, btnModifierSprint, btnDeleteSprint;

    private CardLayout cl;

    private JPanel panGlobal, panCard, panEntete, panProjetForm, panTaskForm, panSprintForm, panButton, panProjetEnCours, panProjetCreation, panBasDePage;

    private JTable tblProjet, tblSprint, tblTask;
    private JScrollPane scPaneProjet, scPaneSprint, scPaneTask, scPaneConsole;

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
        setSize(800, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        lblSepProjet = new JLabel("Projet");
        lblSepProjet.setFont(Constante.F4);
        lblSepProjet.setForeground(new Color(204, 123, 31));
        lblSepTask = new JLabel("Task");
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
        mnuView = new JMenu("View");

        menuBar.add(mnuProjet);
        menuBar.add(mnuTask);
        menuBar.add(mnuSprint);
        menuBar.add(mnuView);

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
        // Initialisation des items du menu View
        miConsole = new JMenuItem("Cacher/Afficher la Console..");

        // Ajout des items de menu
        mnuProjet.add(miRetourSelect);
        mnuProjet.add(miNouveauProj);
        mnuProjet.add(miChargerProj);
        mnuProjet.add(miSupprimerProj);
        mnuProjet.add(miSortir);
        mnuProjet.insertSeparator(1);
        mnuProjet.insertSeparator(4);

        mnuSprint.add(miAjouterSprint);
        mnuSprint.add(miModifierSprint);
        mnuSprint.add(miDeleteSprint);

        mnuTask.add(miAjouterTask);
        mnuTask.add(miModifierTask);
        mnuTask.add(miDeleteTask);

        mnuView.add(miConsole);


        // *** Toolbar ***
        // Initialisation du toolbar
        tbMenu = new JToolBar(JToolBar.VERTICAL);
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);

        // Initialisation des icones de boutons
        iconRetour = new ImageIcon("src/images/iconRetour.png");
        iconNew = new ImageIcon("src/images/iconNewProjet.png");
        iconDelete = new ImageIcon("src/images/iconDeleteProjet.png");
        iconCharger = new ImageIcon("src/images/iconChargerProjet.png");
        iconAjouterTask= new ImageIcon("src/images/iconNewTask.png");
        iconModifierTask = new ImageIcon("src/images/iconChargerTask.png");
        iconDeleteTask = new ImageIcon("src/images/iconDeleteTask.png");
        iconAjouterSprint = new ImageIcon("src/images/iconNewSprint.png");
        iconModifierSprint = new ImageIcon("src/images/iconChargerSprint.png");
        iconDeleteSprint = new ImageIcon("src/images/iconDeleteSprint.png");

        // Initialisation des boutons
        btnRetour = new JButton(iconRetour);
        btnRetour.setToolTipText("Retour au menu précédant..");
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
        tbMenu.add(btnRetour);
        tbMenu.addSeparator();
        tbMenu.add(lblSepProjet);
        tbMenu.add(btnNew);
        tbMenu.add(btnCharger);
        tbMenu.add(btnDelete);
        tbMenu.addSeparator();
        tbMenu.add(lblSepTask);
        tbMenu.add(btnAjouterTask);
        tbMenu.add(btnModifierTask);
        tbMenu.add(btnDeleteTask);
        tbMenu.addSeparator();
        tbMenu.add(lblSepSprint);
        tbMenu.add(btnAjouterSprint);
        tbMenu.add(btnModifierSprint);
        tbMenu.add(btnDeleteSprint);
        tbMenu.addSeparator();
        tbMenu.add(lblConsole);

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
        panProjetForm.setBorder(BorderFactory.createEmptyBorder(10,50,300,200));
        panProjetForm.add(lblNomProjet);
        panProjetForm.add(txtNomProjet);
        panProjetForm.add(lblDescProjet);
        panProjetForm.add(txtDescProjet);
        // Besoin d'un combo box pour scrumid, date, date https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
        panProjetForm.add(lblScrumId);
        panProjetForm.add(txtScrumId);
        panProjetForm.add(lblDateDebut);
        panProjetForm.add(ftxtDateDebut);
        panProjetForm.add(lblDateFin);
        panProjetForm.add(ftxtDateFin);
        panProjetForm.add(lblDureeSprint);
        panProjetForm.add(txtDureeSprint);
        panProjetForm.add(new JLabel());
        panProjetForm.add(btnEnregistrer);

        // Formulaire Task
        // *** Code pour formulaire panTaskForm

        panTaskForm = new JPanel(new GridLayout(4,2));

        // Formulaire Sprint
        // *** Code pour formulaire panSprintForm

        panSprintForm = new JPanel(new GridLayout(4,2));


        iconSprintSave = new ImageIcon("src/images/iconSave.png");
        iconTaskSave = new ImageIcon("src/images/iconSave.png");

        btnEnregistrerTask = new JButton("Enregistrer", iconTaskSave);
        btnEnregistrer.setBackground(Color.white);
        btnEnregistrerSprint = new JButton("Enregistrer", iconSprintSave);


        // *****Card Panels ****

        // Card Panel # 1 - Selection de projet (Menu principal COULD HAVE)

        //Utilise seulement le scPaneProjet pour sélectionner un projet pour l'instant. (reste va dans le COULD HAVE)

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

        panEntete = new JPanel(new FlowLayout(FlowLayout.LEFT, 80, 10));
        panEntete.add(lblProjet);

//        panEntete.add(panTitre);
//        panEntete.setBorder(BorderFactory.createEmptyBorder(0, 8,0,0));

        // *** Bas de page ***
        lblTitre = new JLabel("Scrum..Master");
        lblTitre.setFont(Constante.F3);
        lblRights = new JLabel("Tout droits réservés. ©");
        lblRights.setFont(Constante.F4);



        consoleTxtArea = new JTextArea(3,45);
        consoleTxtArea.setEditable(false);
        scPaneConsole = new JScrollPane(consoleTxtArea);

        DefaultCaret caret = (DefaultCaret)consoleTxtArea.getCaret();
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
        setToolbarActif(1, btnNew, btnCharger, btnDelete, btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint, btnAjouterTask, btnModifierTask, btnDeleteTask);
        // Fonction pour remplir la table par le model
        remplirTableProjet();

        //
        getContentPane().add(panGlobal);
    }



    private void setListeners() {

        // *** Bouton de la toolbar ***

        // Retour à la selection de projet
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentCard != 1) {
                    int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.", "Retour au menu précedant?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        if(currentCard == 2 || currentCard == 3) {
                            consoleTxtArea.append("Retour à la selection de projet.\n");
                            carteSelectionProjet();
                        }else if(currentCard == 4 || currentCard == 5){
                            consoleTxtArea.append("Retour à la gestion de projet.\n");
                            carteProjetEnCours();
                        }
                    }
                }else{
                    consoleTxtArea.append("Aucun retour en arrière possible de cette fenêtre.\n");
                }
            }
        });

        // Créer un nouveau projet - formulaire
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if(currentCard != 2){
                    carteNouveauProjet();
                }else{
                    int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.", "Nouveau projet?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        carteNouveauProjet();
                    }
                }
            }
        });

        // Charger un projet
        btnCharger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enregistrer l'index de la selection
                indexProjetEnCours = tblProjet.getSelectedRow();
                carteProjetEnCours();
                consoleTxtArea.append("Chargement complété avec succès.\n");
            }
        });
        // Supprimer un projet
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = Utilitaire.popupOuiNon("La suppression set final. Êtes-vous sur?", "Suppression de projet");
                if(result == JOptionPane.YES_OPTION){
                    try{
                        int i = tblProjet.getSelectedRow();
                        ManipulationFichier.effacerFichiersProjet(registreProjet.getRegistrePro().get(i).getNomProjet(), consoleTxtArea);
                        registreProjet.effacerProjet(i);
                        ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET +Constante.nomFichier[0], registreProjet, 1);
                        tableModel.removeRow(i);

                        consoleTxtArea.append("Suppression du projet complété.\n");
                    } catch (IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null,"La ligne du projet doit être correctement selectionné pour pouvoir le supprimer.");
                    }
                }else{
                    consoleTxtArea.append("Suppression du projet annulée.\n");
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

                ManipulationFichier.nouveauProjet(txtNomProjet.getText(), consoleTxtArea);
                DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                Projet tempProj = new Projet(txtNomProjet.getText(), txtDescProjet.getText(), Integer.parseInt(txtScrumId.getText()),
                        Utilitaire.getTodayDate(), Utilitaire.getTodayDate(), Integer.parseInt(txtDureeSprint.getText()));

                try {
                    registreProjet.ajouterProjet(tempProj);
                    for (Projet tmp : registreProjet.getRegistrePro()){
                        
                    }
                    consoleTxtArea.append("Projet charger dans le registre avec succès.\n");
                } catch (ProjetDejaPresentException ex) {
                    consoleTxtArea.append("Erreur, doublons présent\n");
                    ex.printStackTrace();
                }
                ManipulationFichier.effacerFichier(Constante.REPERTOIRE_PROJET +Constante.nomFichier[0], consoleTxtArea);
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
                    int result = Utilitaire.popupOuiNon("Les données du formulaire seron perdu.", "Nouveau formulaire?");
                    if(result == JOptionPane.YES_OPTION) {
                        cl.show(panCard, "1");
                        currentCard = 1;
                        lblProjet.setText("Gestion des projets");
                    }
                }
            }
        });

        miConsole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scPaneConsole.isVisible()){
                    scPaneConsole.setVisible(false);
                    setVisible(true);
                }else if(!scPaneConsole.isVisible()){
                    scPaneConsole.setVisible(true);
                    setVisible(true);
                }
            }
        });

    }


    // ***** Méthodes de la fenêtre *****


    // *** Carte des différentes fenêtres
    // Carte de selection de projet
    public void carteSelectionProjet(){
        lblProjet.setText("Gestion des projets");
        remplirTableProjet();

        cl.show(panCard, "1");
        setToolbarActif(1, btnNew,  btnCharger,  btnDelete,  btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint,btnAjouterTask, btnModifierTask,  btnDeleteTask);

        currentCard = 1;
    }

    // Carte de gestion d'un projet
    public void carteProjetEnCours() {
        try{
            lblProjet.setText("Projet en cours: " + registreProjet.getRegistrePro().get(indexProjetEnCours).getNomProjet());
        } catch (IndexOutOfBoundsException e) {
            consoleTxtArea.append("Erreur de chargement. Selectionner la ligne du projet à charger.\n");
            e.printStackTrace();
        }


        panProjetEnCours.add(panProjetForm, BorderLayout.NORTH);
        panProjetEnCours.add(scPaneTask);

        // Ajout du projet en cours au textFields
        reinitialiserFormProjet(txtNomProjet,txtDescProjet,txtScrumId,ftxtDateDebut,ftxtDateFin,txtDureeSprint);
        txtNomProjet.setText(registreProjet.getRegistrePro().get(indexProjetEnCours).getNomProjet());
        txtDescProjet.setText(registreProjet.getRegistrePro().get(indexProjetEnCours).getDescription());
        txtScrumId.setText(String.valueOf(registreProjet.getRegistrePro().get(indexProjetEnCours).getScrumMasterId()));
        ftxtDateDebut.setText(String.valueOf(registreProjet.getRegistrePro().get(indexProjetEnCours).getDateDebut()));
        ftxtDateFin.setText(String.valueOf(registreProjet.getRegistrePro().get(indexProjetEnCours).getDateFin()));
        txtDureeSprint.setText(String.valueOf(registreProjet.getRegistrePro().get(indexProjetEnCours).getDureeSprint()));

        //Task
        //Vider le registre avant de repopuler avec le contenu du fichier
        registreTask.getRegistreTasks().clear();
        try{
            ManipulationFichier.lire(Constante.REPERTOIRE_PROJET+txtNomProjet.getText()+Constante.nomFichier[1], registreTask, 2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        remplirTableTask();

        cl.show(panCard, "3");
        setToolbarActif(3, btnNew,  btnCharger,  btnDelete,  btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint, btnAjouterTask, btnModifierTask,  btnDeleteTask);
        currentCard = 3;
    }
    // Carte nouveau projet
    public void carteNouveauProjet(){
        lblProjet.setText("Nouveau projet");

        panProjetCreation.add(panProjetForm);

        cl.show(panCard, "2");
        reinitialiserFormProjet(txtNomProjet,txtDescProjet,txtScrumId,ftxtDateDebut,ftxtDateFin,txtDureeSprint);
        setToolbarActif(2, btnNew,  btnCharger,  btnDelete,  btnAjouterSprint, btnDeleteSprint,  btnModifierSprint,
                btnAjouterTask, btnModifierTask,  btnDeleteTask);
        currentCard = 2;
        consoleTxtArea.append("Remplir le formulaire et appuyer sur enregistrer.\n");
    }

    // ***** Méthode pour remplir les tables *****

    public void remplirTableProjet(){
        Format formatDate = new SimpleDateFormat("yyyy-MM-dd");
        tableModel.setRowCount(0);
        for (Projet tmp : registreProjet.getRegistrePro()){
            Object[] row = {tmp.getNomProjet(), tmp.getDescription(), Integer.toString(tmp.getScrumMasterId()),
                    formatDate.format(tmp.getDateDebut()), formatDate.format(tmp.getDateFin()),
                    Integer.toString(tmp.getDureeSprint())};
            tableModel.addRow(row);
        }
        consoleTxtArea.append("Tableau des projets créé avec success.\nSelectionner un projet et appuyer sur modifier ou appuyer sur nouveau.\n");
    }
    public void remplirTableTask(){
        tableModel2.setRowCount(0);
        for (Task tmp : registreTask.getRegistreTasks()){
            Object[] row = {tmp.getTaskID(), tmp.getTaskPriority(), tmp.getDescription(), tmp.getEmployeID()};
            tableModel2.addRow(row);
        }
        consoleTxtArea.append("Tableau des taches créé avec success.\n");
    }

    // *** code pour remplirTableSprint()


    // ***** Gestion des card *****



}