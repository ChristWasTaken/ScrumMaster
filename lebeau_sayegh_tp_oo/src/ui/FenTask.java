package ui;

import model.RegistreTask;
import utils.Constante;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class FenTask extends JFrame{

    private FenTask fenetre;

    private JPanel panGlobal, panButton, panTask, panTaskForm, panBasDePage;

    private JLabel lblTaskId, lblTaskPriorite, lblDescription, lblEmploye, lblTitre, lblScrum;
    private JTextField txtTaskId, txtTaskPriorite, txtDescription, txtEmploye;


    private JToolBar tbMenu;
    private ImageIcon iconTaskSave;
    private JButton btnTaskSave;

    private JMenuBar menuBar;
    private JMenuItem miEnregistrerTache, miSortir;
    private JMenu mnuTache;

    public FenTask() {

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setWidget();
//        setAlwaysOnTop(true);
        //setListeners();
    }

    private void setWidget() {

        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        //************************************************************
        // Initialisation des objets de la fenetre.

        //initialisation des Labels
        lblTaskId = new JLabel("ID Tache: ");
        lblTaskPriorite = new JLabel("Priorité de la tache (1-9): ");
        lblDescription = new JLabel("Description: ");
        lblEmploye = new JLabel("ID Employe: ");

        lblTitre = new JLabel("Tous droit Réservé. ®");
        lblTitre.setFont(Constante.F4);
        lblScrum = new JLabel("Scrum..Master");
        lblScrum.setFont(Constante.F3);

        //initialisation des textfields
        txtTaskId = new JTextField(20);
        txtTaskPriorite = new JTextField(4);
        txtDescription = new JTextField(20);
        txtEmploye = new JTextField(20);


        //initiation du ToolBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //initialisation du toolbar
        tbMenu = new JToolBar();
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);

        //initiation des icones de boutons
        iconTaskSave = new ImageIcon("src/images/iconNewTask.png");

        //initiation des boutonss
        btnTaskSave = new JButton(iconTaskSave);
        btnTaskSave.setToolTipText("Sauvegarder la tache..");

        //initiation panneau toolbar
        panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButton.add(tbMenu);
        //ajout objet toolbar
        tbMenu.add(btnTaskSave);

        //*****************************************
        //initiation item du menu principal
        mnuTache = new JMenu("Tache");

        menuBar.add(mnuTache);

        //initiation des items du menu Projet
        miEnregistrerTache= new JMenuItem("Enregistrer la tache..");
        miSortir = new JMenuItem("Sortir");

        mnuTache.add(miEnregistrerTache);
        mnuTache.add(miSortir);

        //****************************************************

        //Panneau d'affichage du projet selectionné
        panTaskForm = new JPanel(new GridLayout(12,2));
        panTaskForm.add(lblTaskId);
        panTaskForm.add(txtTaskId);
        panTaskForm.add(lblTaskPriorite);
        panTaskForm.add(txtTaskPriorite);
        panTaskForm.add(lblDescription);
        panTaskForm.add(txtDescription);
        panTaskForm.add(lblEmploye);
        panTaskForm.add(txtEmploye);


//        private JPanel panGlobal, panButton, panTask, panTaskForm, panBasDePage;

        panTask = new JPanel(new BorderLayout());
        panTask.setBorder(brd);

        panTask.add(panTaskForm, BorderLayout.NORTH);

        panBasDePage = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panBasDePage.add(lblTitre);
        panBasDePage.add(lblScrum);

        //panneau Global
        panGlobal = new JPanel(new BorderLayout());
        panGlobal.setBorder(brd);

        panGlobal.add(panButton, BorderLayout.NORTH);
        panGlobal.add(panTask, BorderLayout.CENTER);
        panGlobal.add(panBasDePage, BorderLayout.SOUTH);
        this.setContentPane(panGlobal);
    }
}
