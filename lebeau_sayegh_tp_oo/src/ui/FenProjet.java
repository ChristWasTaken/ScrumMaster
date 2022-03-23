package ui;

import utils.Constante;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;


public class FenProjet extends JFrame {

    private FenProjet fenetre;

    JPanel panGlobal, panProjet, panProjetCourrant, panButton, panProjetForm, panSprint, panTask, panBasDePage;

    JLabel lblProjet, lblScrum, lblSprint, lblTasks, lblTitre, lblConsigneSprint,lblConsigneTask;
    JTextField txtProjet;

    JToolBar tbMenu;
    ImageIcon iconProjetSave, iconAjouterTask, iconModifierTask, iconDeleteTask, iconAjouterSprint, iconModifierSprint, iconDeleteSprint;
    JButton btnProjetSave, btnAjouterTask, btnModifierTask, btnDeleteTask, btnAjouterSprint, btnModifierSprint, btnDeleteSprint;

    JMenuBar menuBar;
    JMenuItem miEnregistrerProj, miSortir, miAjouterTask, miModifierTask, miDeleteTask, miAjouterSprint, miModifierSprint, miDeleteSprint;
    JMenu mnuProjet, mnuSprint, mnuTask;
    JSeparator sep1;

    JTable tblSprint, tblTask;
    TableColumnModel colmod;
    TableColumn tempCol0, tempCol1;
    JScrollPane scPaneSprint, scPaneTask;

    String[] nomColonnes = { "Nom du projet", "Description", "ScrumMaster", "Date de début", "Date de fin"};
    String[][] tableTest = {
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" },
            { "Projet Teletubies", "Regarder la TV le matin", "Bibi", "10-20-30", "10-20-30" }
    };


    private Border brd;

    public FenProjet() {

        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setWidget();
        setAlwaysOnTop(true);
        //setListeners();
    }

    private void setWidget() {

        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        //************************************************************
        // Initialisation des objets de la fenetre.

        //initialisation des Labels
        lblProjet = new JLabel("Projets en cours");
        lblProjet.setFont(Constante.F1);
        lblSprint = new JLabel("Sprints restant au projet");
        lblSprint.setFont(Constante.F2);
        lblTasks = new JLabel("Taches restantes au projet");
        lblTasks.setFont(Constante.F2);
        lblConsigneSprint = new JLabel(" - Affichage du projet sélectionné.");
        lblConsigneSprint.setFont(Constante.F3);
        lblConsigneTask = new JLabel(" - Affichage du projet sélectionné.");
        lblConsigneTask.setFont(Constante.F3);
        lblTitre = new JLabel("Tous droit Réservé. ®");
        lblTitre.setFont(Constante.F4);
        lblScrum = new JLabel("Scrum..Master");
        lblScrum.setFont(Constante.F3);

        //initialisation des textfields
        txtProjet = new JTextField(20);

        //initiation menuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //initialisation du toolbar
        tbMenu = new JToolBar();
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);

        //initiation des icones de boutons
        iconProjetSave = new ImageIcon("src/images/iconNew.png");
        iconAjouterTask= new ImageIcon("src/images/iconNew.png");
        iconModifierTask = new ImageIcon("src/images/icon1.png");
        iconDeleteTask = new ImageIcon("src/images/iconDelete.png");
        iconAjouterSprint = new ImageIcon("src/images/iconNew.png");
        iconModifierSprint = new ImageIcon("src/images/icon1.png");
        iconDeleteSprint = new ImageIcon("src/images/iconDelete.png");

        //initiation des boutonss
        btnProjetSave = new JButton(iconProjetSave);
        btnAjouterTask = new JButton(iconAjouterTask);
        btnModifierTask = new JButton(iconModifierTask);
        btnDeleteTask = new JButton(iconDeleteTask);
        btnAjouterSprint = new JButton(iconAjouterSprint);
        btnModifierSprint = new JButton(iconModifierSprint);
        btnDeleteSprint = new JButton(iconDeleteSprint);

        //*****************************************
        //initiation item du menu principal
        mnuProjet = new JMenu("Projet");
        mnuSprint = new JMenu("Sprints");
        mnuTask = new JMenu("Taches");

        menuBar.add(mnuProjet);
        menuBar.add(mnuTask);
        menuBar.add(mnuSprint);

        //initiation des items du menu Fichier
        miEnregistrerProj = new JMenuItem("Enregistrer le Projet..");
        miSortir = new JMenuItem("Sortir");

        miAjouterTask = new JMenuItem("Enregistrer le Projet..");
        miModifierTask = new JMenuItem("Enregistrer le Projet..");
        miDeleteTask = new JMenuItem("Enregistrer le Projet..");

        miAjouterSprint = new JMenuItem("Enregistrer le Projet..");
        miModifierSprint = new JMenuItem("Enregistrer le Projet..");
        miDeleteSprint = new JMenuItem("Enregistrer le Projet..");

        //Séparateur de menu
        sep1 = new JSeparator();
        sep1.setForeground(Color.gray);

        mnuProjet.add(miEnregistrerProj);
        mnuProjet.add(sep1);
        mnuProjet.add(miSortir);

        mnuSprint.add(miAjouterSprint);
        mnuSprint.add(miModifierSprint);
        mnuSprint.add(miDeleteSprint);

        mnuTask.add(miAjouterTask);
        mnuTask.add(miModifierTask);
        mnuTask.add(miDeleteTask);

        //****************************************************
        //panneau menu
        panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButton.add(tbMenu);
        //pammneau toolbar
        tbMenu.add(btnProjetSave);
        tbMenu.add(btnAjouterTask);
        tbMenu.add(btnModifierTask);
        tbMenu.add(btnDeleteTask);
        tbMenu.add(btnAjouterSprint);
        tbMenu.add(btnModifierSprint);
        tbMenu.add(btnDeleteSprint);

        //initiation des tables
        tblSprint = new JTable(tableTest, nomColonnes);
        tblTask = new JTable(tableTest, nomColonnes);


        //initiation des scrollpanes
        scPaneSprint = new JScrollPane(tblSprint);
        scPaneTask = new JScrollPane(tblTask);

        scPaneSprint.setPreferredSize(new Dimension(765,150));
        colmod = tblSprint.getColumnModel();
        tempCol1 = colmod.getColumn(1);
        tempCol1.setPreferredWidth(350);
        tempCol0 = colmod.getColumn(0);
        tempCol0.setPreferredWidth(150);

        scPaneTask.setPreferredSize(new Dimension(765,200));
        colmod = tblTask.getColumnModel();
        tempCol1 = colmod.getColumn(1);
        tempCol1.setPreferredWidth(350);
        tempCol0 = colmod.getColumn(0);
        tempCol0.setPreferredWidth(150);

        //Panneau d'affichage des sprints du projet
        panSprint = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panSprint.add(lblSprint);
        panSprint.add(lblConsigneSprint);
        panSprint.add(scPaneSprint);

        //Panneau d'affichage des sprints du projet
        panTask = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panTask.add(lblTasks);
        panTask.add(lblConsigneTask);
        panTask.add(scPaneTask);

        //Panneau d'affichage du projet selectionné
        panProjetForm = new JPanel(new BorderLayout());

        panProjetForm.add(panSprint, BorderLayout.NORTH);
        panProjetForm.add(panTask);

        //Panneau d'affichage des projets en cours
        panProjetCourrant = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panProjetCourrant.add(lblProjet);

        panProjet = new JPanel(new BorderLayout());
        panProjet.setBorder(brd);

        panProjet.add(panProjetCourrant, BorderLayout.NORTH);

        panBasDePage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panBasDePage.add(lblTitre);
        panBasDePage.add(lblScrum);

        //panneau Global
        panGlobal = new JPanel(new BorderLayout());
        panGlobal.setBorder(brd);

        panGlobal.add(panButton, BorderLayout.NORTH);
        panGlobal.add(panProjet, BorderLayout.CENTER);
        panGlobal.add(panBasDePage, BorderLayout.SOUTH);
        this.setContentPane(panGlobal);
    }
}
