package ui;

import model.RegistreSprint;
import model.RegistreTask;
import utils.Constante;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FenSprint extends JFrame {
    private RegistreSprint registreSprint;
    private RegistreTask registreTask;

//    private FenProjet fenetre;

    private JPanel panGlobal, panProjet, panProjetCourrant, panButton, panProjetForm, panSprint, panTask, panBasDePage;

    private JLabel lblSprintTitre, lblScrum, lblTaskSprint, lblTasksProjet, lblTitre, lblNomProjet, lblDescSprint, lblScrumId, lblDateDebut, lblDateFin, lblProgres;
    private JTextField  txtNomProjet, txtDescProjet, txtScrumId, txtDureeSprint;

    private JFormattedTextField ftxtDateDebut, ftxtDateFin;


    private JToolBar tbMenu;
    private ImageIcon iconProjetSave, iconAjouterTask, iconModifierTask, iconDeleteTask, iconAjouterSprint, iconModifierSprint, iconDeleteSprint;
    private JButton btnProjetSave, btnAjouterTask, btnModifierTask, btnDeleteTask, btnAjouterSprint, btnModifierSprint, btnDeleteSprint;

    private JMenuBar menuBar;
    private JMenuItem miRetourProjet, miSortir,  miAjouterSprint, miModifierSprint, miDeleteSprint;
    private JMenu mnuProjet, mnuSprint;
    private JSeparator sep1, sepVertical, sepVertical2;
    private DateFormat formatDate;
    private JTable tblSprint, tblTask;
    private TableColumnModel colmod;
    private TableColumn tempCol0, tempCol1;
    private JScrollPane scPaneSprint, scPaneTask;


    
    private String[] nomColonnes = { "Nom du projet", "Description", "ScrumMaster", "Date de début", "Date de fin"};
    private String[][] tableTest = {
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

    public FenSprint() {
        setSize(800, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setWidget();
        //    setListener();
    }


    private void setWidget() {
        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        //************************************************************
        // Initialisation des objets de la fenetre.

        //initialisation des Labels
        lblSprintTitre = new JLabel("              Sprint Sélectionner");
        lblSprintTitre.setFont(Constante.F2);


        lblDescSprint = new JLabel("Description du sprint: ");
        lblDateDebut = new JLabel("Début du projet (yyyy-mm-dd): ");
        lblDateFin = new JLabel("Fin du projet (yyyy-mm-dd): ");
        lblProgres = new JLabel("Nombre de semaine par sprint: ");

        lblTaskSprint = new JLabel("Task sélectionner pour ce Sprint");
        lblTaskSprint.setFont(Constante.F2);

        lblTasksProjet = new JLabel("Taches restantes au projet");
        lblTasksProjet.setFont(Constante.F2);

        lblTitre = new JLabel("Tous droit Réservé. ®");
        lblTitre.setFont(Constante.F4);
        lblScrum = new JLabel("Scrum..Master");
        lblScrum.setFont(Constante.F3);
        new SimpleDateFormat("yyyy-MM-dd");

        //initialisation des textfields

        txtNomProjet = new JTextField(30);
        txtDescProjet = new JTextField(50);
        txtScrumId = new JTextField(20);
        ftxtDateDebut = new JFormattedTextField(formatDate);
        ftxtDateFin = new JFormattedTextField(formatDate);
        txtDureeSprint = new JTextField(3);


        //initiation du ToolBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //initialisation du toolbar
        tbMenu = new JToolBar();
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);

        sepVertical2 = new JSeparator(SwingConstants.VERTICAL);
        sepVertical2.setForeground(Color.lightGray);
        //initiation des icones de boutons
        iconAjouterTask= new ImageIcon("src/images/iconNewTask.png");
        iconModifierTask = new ImageIcon("src/images/iconChargerTask.png");
        iconDeleteTask = new ImageIcon("src/images/iconDeleteTask.png");
        iconAjouterSprint = new ImageIcon("src/images/iconNewSprint.png");
        iconModifierSprint = new ImageIcon("src/images/iconChargerSprint.png");
        iconDeleteSprint = new ImageIcon("src/images/iconDeleteSprint.png");

        //initiation des boutonss

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

        //initiation panneau toolbar
        panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButton.add(tbMenu);
        //ajout objet toolbar

        tbMenu.add(btnAjouterTask);
        tbMenu.add(btnModifierTask);
        tbMenu.add(btnDeleteTask);
        tbMenu.add(sepVertical2);
        tbMenu.add(btnAjouterSprint);
        tbMenu.add(btnModifierSprint);
        tbMenu.add(btnDeleteSprint);
        tbMenu.add(lblSprintTitre);

        //*****************************************
        //initiation item du menu principal
        mnuProjet = new JMenu("Projet");
        mnuSprint = new JMenu("Sprints");

        menuBar.add(mnuProjet);
        menuBar.add(mnuSprint);

        //initiation des items du menu Projet
        miRetourProjet = new JMenuItem("Enregistrer le Projet..");
        miSortir = new JMenuItem("Sortir");

        //initiation des items du menu Sprint
        miAjouterSprint = new JMenuItem("Ajouter un sprint..");
        miModifierSprint = new JMenuItem("Modifier un sprint..");
        miDeleteSprint = new JMenuItem("Supprimer un sprint..");

        mnuProjet.add(miRetourProjet);

        mnuProjet.add(miSortir);

        mnuSprint.add(miAjouterSprint);
        mnuSprint.add(miModifierSprint);
        mnuSprint.add(miDeleteSprint);

        //****************************************************

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
        tempCol0 = colmod.getColumn(0);
        tempCol0.setPreferredWidth(150);
        tempCol1 = colmod.getColumn(1);
        tempCol1.setPreferredWidth(350);


        //Panneau d'affichage des sprints du projet
        panSprint = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panSprint.add(lblTaskSprint);
        panSprint.add(scPaneSprint);

        //Panneau d'affichage des sprints du projet
        panTask = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panTask.add(lblTasksProjet);
        panTask.add(scPaneTask);

        //Panneau d'affichage du projet selectionné
        panProjetForm = new JPanel(new GridLayout(8,1));
        panProjetForm.add(lblDescSprint);
        panProjetForm.add(txtDescProjet);
        panProjetForm.add(lblDateDebut);
        panProjetForm.add(ftxtDateDebut);
        panProjetForm.add(lblDateFin);
        panProjetForm.add(ftxtDateFin);
        panProjetForm.add(lblProgres);
        panProjetForm.add(txtDureeSprint);


        //Panneau d'affichage des projets en cours
        panProjetCourrant = new JPanel(new BorderLayout());
        panProjetCourrant.add(panProjetForm, BorderLayout.NORTH);
        panProjetCourrant.add(panSprint, BorderLayout.CENTER);
        panProjetCourrant.add(panTask, BorderLayout.SOUTH);

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

    private void setListener() {
    }
}
