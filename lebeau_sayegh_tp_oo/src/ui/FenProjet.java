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


    String[] nomColonnes = { "Nom du projet", "Description", "ScrumMaster", "Date de début", "Date de fin"};

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
        JLabel lblProjet = new JLabel("Projets en cours");
        lblProjet.setFont(Constante.F1);
        JLabel lblSprint = new JLabel("Sprints restant au projet");
        lblSprint.setFont(Constante.F2);
        JLabel lblTasks = new JLabel("Taches restantes au projet");
        lblTasks.setFont(Constante.F2);
        JLabel lblConsigneSprint = new JLabel(" - Affichage du projet sélectionné.");
        lblConsigneSprint.setFont(Constante.F3);
        JLabel lblConsigneTask = new JLabel(" - Affichage du projet sélectionné.");
        lblConsigneTask.setFont(Constante.F3);
        JLabel lblTitre = new JLabel("Tous droit Réservé. ®");
        lblTitre.setFont(Constante.F4);
        JLabel lblScrum = new JLabel("Scrum..Master");
        lblScrum.setFont(Constante.F3);

        //initialisation des textfields
        JTextField txtProjet = new JTextField(20);

        //initiation menuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //initialisation du toolbar
        JToolBar tbMenu = new JToolBar();
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);

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

        JPanel panProjet = new JPanel(new BorderLayout());
        panProjet.setBorder(brd);

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
