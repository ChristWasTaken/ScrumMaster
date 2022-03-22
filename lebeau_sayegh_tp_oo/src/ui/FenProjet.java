package ui;

import utils.MiseEnPage;
import utils.Utilitaire;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;


public class FenProjet extends JFrame {

    private FenProjet fenetre;


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
        //setListeners();
    }

    private void setWidget() {

        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        //************************************************************
        // Initialisation des objets de la fenetre.

        //initialisation des Labels
        JLabel lblProjet = new JLabel("Projets en cours");
        lblProjet.setFont(MiseEnPage.F1);
        JLabel lblSprint = new JLabel("Sprints restant au projet");
        lblSprint.setFont(MiseEnPage.F2);
        JLabel lblTasks = new JLabel("Taches restantes au projet");
        lblTasks.setFont(MiseEnPage.F2);
        JLabel lblConsigneSprint = new JLabel(" - Affichage du projet sélectionné.");
        lblConsigneSprint.setFont(MiseEnPage.F3);
        JLabel lblConsigneTask = new JLabel(" - Affichage du projet sélectionné.");
        lblConsigneTask.setFont(MiseEnPage.F3);
        JLabel lblTitre = new JLabel("Tous droit Réservé. ®");
        lblTitre.setFont(MiseEnPage.F4);
        JLabel lblScrum = new JLabel("Scrum..Master");
        lblScrum.setFont(MiseEnPage.F3);
//        JLabel lblDateDebut = new JLabel("Projet de Session: ");
//        JLabel lblDateFin = new JLabel("Moyenne: ");
//        JLabel lblResultat = new JLabel("Résultat: ");
//        JLabel lblResult = new JLabel("passe");

        //initialisation des textfields
        JTextField txtProjet = new JTextField(20);
//        JTextField txtIntra = new JTextField(10);
//        JTextField txtFinal = new JTextField(10);
//        JTextField txtTp = new JTextField(10);
//        JTextField txtMoyenne = new JTextField("0", 4);

        //initiation menuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //initialisation du toolbar
        JToolBar tbMenu = new JToolBar();
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);
//        tbMenu.setBorder(brd);

        //initiation des icones de boutons


        ImageIcon icon1 = new ImageIcon("src/images/icon1.png");

        //initiation des boutons
        JButton btn1 = new JButton(icon1);
        JButton btn2 = new JButton(icon1);
        JButton btn3 = new JButton(icon1);
        JButton btn4 = new JButton(icon1);
        JButton btn5 = new JButton(icon1);

        //*****************************************
        //initiation item du menu principal
        JMenu mnuFile = new JMenu("Fichier");
        JMenu mnuEdit = new JMenu("Edit");

        menuBar.add(mnuFile);
        menuBar.add(mnuEdit);

        //initiation des items du menu Fichier
        JMenuItem miFileOpen = new JMenuItem("Ouvrir");
        JMenuItem miFileSave = new JMenuItem("Enregistrer");
        JMenuItem miFileExit = new JMenuItem("Sortir");
        //Séparateur de menu
        JSeparator sep1 = new JSeparator();

        mnuFile.add(miFileOpen);
        mnuFile.add(miFileSave);
        mnuFile.add(sep1);
        mnuFile.add(miFileExit);

        //initiation des items des menus déroulant
        //PLACEHOLDER
        JMenuItem miEditCut = new JMenuItem("1");
        JMenuItem miEditCopy = new JMenuItem("2");
        JMenuItem miEditPaste = new JMenuItem("3");
        mnuEdit.add(miEditCut);
        mnuEdit.add(miEditCopy);
        mnuEdit.add(miEditPaste);

        //****************************************************

        //panneau menu
        JPanel panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButton.add(tbMenu);
        //pammneau toolbar
        tbMenu.add(btn1);
        tbMenu.add(btn2);
        tbMenu.add(btn3);
        tbMenu.add(btn4);
        tbMenu.add(btn5);

        //initiation des tables
        JTable tblProjet = new JTable(tableTest, nomColonnes);
        JTable tblSprint = new JTable(tableTest, nomColonnes);
        JTable tblTask = new JTable(tableTest, nomColonnes);


        //initiation des scrollpanes
        JScrollPane scPaneProjet = new JScrollPane(tblProjet);
        JScrollPane scPaneSprint = new JScrollPane(tblSprint);
        JScrollPane scPaneTask = new JScrollPane(tblTask);

        scPaneProjet.setPreferredSize(new Dimension(765, 125));
        TableColumnModel colmod = tblProjet.getColumnModel();
        TableColumn tempCol1 = colmod.getColumn(1);
        tempCol1.setPreferredWidth(350);
        TableColumn tempCol0 = colmod.getColumn(0);
        tempCol0.setPreferredWidth(150);

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
        JPanel panSprint = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panSprint.add(lblSprint);
        panSprint.add(lblConsigneSprint);
        panSprint.add(scPaneSprint);

        //Panneau d'affichage des sprints du projet
        JPanel panTask = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panTask.add(lblTasks);
        panTask.add(lblConsigneTask);
        panTask.add(scPaneTask);

        //Panneau d'affichage du projet selectionné
        JPanel panProjetSelection = new JPanel(new BorderLayout());

        panProjetSelection.add(panSprint, BorderLayout.NORTH);
        panProjetSelection.add(panTask);

        //Panneau d'affichage des projets en cours
        JPanel panProjetCourrant = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panProjetCourrant.add(lblProjet);
        panProjetCourrant.add(scPaneProjet);

        JPanel panProjet = new JPanel(new BorderLayout());
        panProjet.setBorder(brd);

        panProjet.add(panProjetCourrant, BorderLayout.NORTH);
        panProjet.add(panProjetSelection, BorderLayout.CENTER);

        JPanel panBasDePage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panBasDePage.add(lblTitre);
        panBasDePage.add(lblScrum);

        //panneau Global
        JPanel panGlobal = new JPanel(new BorderLayout());
        panGlobal.setBorder(brd);

        panGlobal.add(panButton, BorderLayout.NORTH);
        panGlobal.add(panProjet, BorderLayout.CENTER);
        panGlobal.add(panBasDePage, BorderLayout.SOUTH);
        this.setContentPane(panGlobal);

    }
}
