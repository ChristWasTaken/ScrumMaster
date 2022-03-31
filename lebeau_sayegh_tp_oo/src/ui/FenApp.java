/*
     Exception de saisie Invalide pour les formulaires.
     Faire la vérification de saisie et d'exception pour les formulaires.

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
       */

package ui;

import io.ManipulationFichier;
import model.*;
import utils.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static utils.Constante.*;

@SuppressWarnings("FieldCanBeLocal")

public class FenApp extends FenParent {
    private RegistreProjet registreProjet;
    private RegistreEmploye registreEmploye;
    private RegistreTask registreTask;
    private RegistreSprint registreSprint;

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
        setListeners();
    }

    private void setWidget() {

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
        txtTaskPriority = new JTextField(3);
        txtDescTask = new JTextField(50);
        txtEmployeId = new JTextField(20);

        // Initialisation des comboBox
        jcbEmploye = new JComboBox<>();
        jcbEmploye2 = new JComboBox<>();

        // Formats de date
        format = new SimpleDateFormat("yyyy-MM-dd");
        formatDate = new DateFormatter(format);
        ftxtDateDebut = new JFormattedTextField(formatDate);
        ftxtDateFin = new JFormattedTextField(formatDate);


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
        btnEnregistrerProjet = new JButton("Enregistrer", iconSave);
        btnEnregistrerProjet.setToolTipText("Enregistrer le formulaire..");
        btnEnregistrerProjet.setFont(Constante.F3);
        btnEnregistrerProjet.setForeground(Color.GRAY);
        btnEnregistrerTask = new JButton("Enregistrer", iconTaskSave);
        btnEnregistrerTask.setBackground(Color.white);
        btnEnregistrerSprint = new JButton("Enregistrer", iconSprintSave);

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

        scPaneSprint.setPreferredSize(new Dimension(450, 150));
        setTailleColonneTable(tblSprint, Constante.TAILLE_COL_3);

        // *** Formulaires

//        // Formulaire Projet

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
        panProjetForm.add(new JLabel());
        panProjetForm.add(btnEnregistrerProjet);


        // Formulaire Task
        // *** Code pour formulaire panTaskForm

        panTaskForm = new JPanel(new GridLayout(4, 2));
        panTaskForm.setBorder(BorderFactory.createEmptyBorder(10, 50, 500, 200));
        panTaskForm.add(lblTaskPriority);
        panTaskForm.add(txtTaskPriority);
        panTaskForm.add(lblDeskTask);
        panTaskForm.add(txtDescTask);
        panTaskForm.add(lblEmpId);
        panTaskForm.add(jcbEmploye2);
        panTaskForm.add(new JLabel());
        panTaskForm.add(btnEnregistrerTask);

        // Formulaire Sprint
        // *** Code pour formulaire panSprintForm

        panSprintForm = new JPanel(new GridLayout(4, 2));

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
        panCard.add(panSprintCours);

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
        setToolbarActif(1, btnNew, btnCharger, btnDelete, btnAjouterSprint, btnDeleteSprint,
                btnModifierSprint, btnAjouterTask, btnModifierTask, btnDeleteTask);
        // Fonction pour remplir la table par le model
        remplirTableProjet(tableModel, registreProjet, registreEmploye, consoleTxtArea);

        //
        getContentPane().add(panGlobal);
    }


    private void setListeners() {

        // *** Bouton de la toolbar ***

        // Retour à la selection de projet
        btnRetour.addActionListener(e -> {
            if (currentCard != 1) {
                int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.", "Retour au menu précedant?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (currentCard == 2 || currentCard == 3) {
                        consoleTxtArea.append("Retour à la selection de projet.\n");
                        carteSelectionProjet(registreProjet, registreEmploye);
                    } else if (currentCard == 4 || currentCard == 5) {
                        consoleTxtArea.append("Retour à la gestion de projet.\n");
                        carteProjetEnCours(registreProjet, registreTask, registreEmploye, registreSprint);
                    }
                }
            } else {
                consoleTxtArea.append("Aucun retour en arrière possible de cette fenêtre.\n");
            }
        });

        // Créer un nouveau projet - formulaire
        btnNew.addActionListener(a -> {
            if (currentCard != 2) {
                // Initialise la mise en page et les paramètres de la carte
                carteNouveauProjet(registreEmploye);
            } else {
                int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.", "Nouveau projet?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    carteNouveauProjet(registreEmploye);
                }
            }
        });

        // Créer un Nouveau Task
        btnAjouterTask.addActionListener(e -> {
            if (currentCard != 4) {
                carteNouvelleTask(registreEmploye);
            } else {
                int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.",
                        "Retour vers le projet?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    carteNouvelleTask(registreEmploye);
                }
            }
        });
        // Listener de Gestion de Sprint

        // Créer un Nouveau Sprint
        //   *** Code pour btnAjouterSprint
        btnAjouterSprint.addActionListener(e -> {
            if (currentCard != 6) {
                carteNouveauSprint(registreSprint, registreTask);
            } else {
                int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.",
                        "Retour vers le projet?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    carteNouveauSprint(registreSprint, registreTask);
                }
            }
        });

        // Charger un projet
        btnCharger.addActionListener(e -> {
            // Enregistrer l'index de la selection
            indexProjetEnCours = tblProjet.getSelectedRow();
            // Initialise la mise en page et les paramètres de la carte
            carteProjetEnCours(registreProjet, registreTask, registreEmploye, registreSprint);

            consoleTxtArea.append("Chargement du projet complété avec succès.\n");
        });
        // Modifier un Task
        //   *** Code pour
        btnModifierTask.addActionListener(e -> {
            //enregistrer l'index de la tache choisi
            indexTaskEnCours = tblTask.getSelectedRow();
            //Initialise la mise en page et les parametre de la carte
            carteModifierTask(registreTask, registreEmploye);
            consoleTxtArea.append("Chargement de la tache complété avec succès.\n");
        });

        // Supprimer un projet
        btnDelete.addActionListener(e -> {

            int result = Utilitaire.popupOuiNon("La suppression des projets est final. Êtes-vous sur?", "Suppression de projet");
            if (result == JOptionPane.YES_OPTION) {
                try {
                    int i = tblProjet.getSelectedRow();
                    ManipulationFichier.effacerFichiersProjet(registreProjet.getRegistrePro().get(i).getNomProjet(), consoleTxtArea);
                    registreProjet.effacerProjet(i);
                    ManipulationFichier.ecrire(REPERTOIRE_PROJET + nomFichier[0], registreProjet, 1);
                    tableModel.removeRow(i);

                    consoleTxtArea.append("Suppression du projet complété.\n");
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "La ligne du projet doit être correctement selectionné pour pouvoir le supprimer.");
                }
            } else {
                consoleTxtArea.append("Suppression du projet annulée.\n");
            }
        });
        //  Supprimer Task
        //    *** Code pour btnDeleteTask
        btnDeleteTask.addActionListener(e -> {

            int result = Utilitaire.popupOuiNon("La suppression des taches est final. Êtes-vous sur?", "Suppression " +
                    "de tache");
            if (result == JOptionPane.YES_OPTION) {
                try {
                    int i = tblTask.getSelectedRow();

                    registreTask.effacerProjet(i);
                    ManipulationFichier.ecrire(REPERTOIRE_PROJET + nomFichier[1], registreProjet, 1);
                    tableModel2.removeRow(i);

                    consoleTxtArea.append("Suppression du projet complété.\n");
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "La ligne de la tache doit être correctement selectionné pour" +
                            " pouvoir le supprimer.");
                }
            } else {
                consoleTxtArea.append("Suppression de la tache annulée.\n");
            }
        });


        // Modifier un Sprint
        //   *** Code pour btnModifierSprint

        // Supprimer Sprint
        //   *** Code pour btnDeleteSprint


        //***** Enregistrement de formulaire *****

        // Sauvegarder Formulaire Projet
        btnEnregistrerProjet.addActionListener(e -> {

            try {
                Employe temp = (Employe) jcbEmploye.getSelectedItem();
                Projet tempProj = null;

                if (temp != null) {
                    int duree = Integer.parseInt(txtDureeSprint.getText());
                    Utilitaire.verifierDureeSprint(duree);
                    tempProj = new Projet(txtNomProjet.getText(), txtDescProjet.getText(), temp.getEmployeID(), format.parse(ftxtDateDebut.getText()),
                            format.parse(ftxtDateFin.getText()), duree);
                }

                if (currentCard == 2) {
                    try {
                        if (registreProjet.ajouterProjet(tempProj, 0) == -1) {
                            ManipulationFichier.nouveauProjet(txtNomProjet.getText(), consoleTxtArea);
                            ManipulationFichier.ecrire(REPERTOIRE_PROJET + nomFichier[0], registreProjet, 1);
                            consoleTxtArea.append("Nouveau projet créer. Retourner à la page précédente pour lui accèder.\n");
                        }
                    } catch (ProjetDejaPresentException ex) {
                        consoleTxtArea.append("Projet déja présent, Changer le nom du projet pour le sauvegarder.\n");
                    }
                } else if (currentCard == 3) {
                    try {
                        if (registreProjet.ajouterProjet(tempProj, 1) != -1) {
                            ManipulationFichier.ecrire(REPERTOIRE_PROJET + nomFichier[0], registreProjet, 1);
                            consoleTxtArea.append("Les changements au projet: " + txtNomProjet.getText() + " sont sauvegardés.\n");
                        } else {
                            ManipulationFichier.nouveauProjet(txtNomProjet.getText(), consoleTxtArea);
                            ManipulationFichier.ecrire(REPERTOIRE_PROJET + nomFichier[0], registreProjet, 1);
                        }
                    } catch (ProjetDejaPresentException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (NumberFormatException ex2) {
                JOptionPane.showMessageDialog(null, "Les sprints sont des semaines saisie en entier seulement.",
                        "Erreur de saisie.", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (SaisieInvalideException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur de saisie.", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        //sauvegarder le formulaire Task
        btnEnregistrerTask.addActionListener(e -> {

            Task tempTask = new Task(Integer.parseInt(txtTaskPriority.getText()),
                    txtDescTask.getText(), jcbEmploye2.getSelectedIndex());
            try {
                if (currentCard == 4) {

                    if (registreTask.ajouterTask(tempTask, 0) == -1) {
                        ManipulationFichier.ecrire(REPERTOIRE_PROJET + txtNomProjet.getText() + nomFichier[1], registreTask, 2);
                        consoleTxtArea.append("Tache enregistrer dans le registre avec succès.\n");
                    }
                } else if (currentCard == 5) {
                    if (registreTask.ajouterTask(tempTask, 1) != -1) {
                        ManipulationFichier.ecrire(REPERTOIRE_PROJET + txtNomProjet.getText() + nomFichier[1], registreTask, 2);
                        consoleTxtArea.append("Tache enregistrer dans le registre avec succès.\n");
                    }
                }
            } catch (TaskDejaExistException ex) {
                consoleTxtArea.append("Erreur, doublons présent\n");
                ex.printStackTrace();
            }
        });


        //Sauvegarder Formulaire Sprint
        //   *** Code pour btnEnregistrerSprint


        // *** Choix du menu ***
        // SHOULD HAVE
        miSortir.addActionListener(e -> {

            int result = Utilitaire.popupOuiNon("Voulez vous vraiment Quitter?", "Quitter?");
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        });

        miConsole.addActionListener(e -> {
            if (scPaneConsole.isVisible()) {
                scPaneConsole.setVisible(false);
                setVisible(true);
            } else if (!scPaneConsole.isVisible()) {
                scPaneConsole.setVisible(true);
                setVisible(true);
            }
        });

    }


    // ***** Méthodes de la fenêtre *****


    // ***** Gestion des card *****
}