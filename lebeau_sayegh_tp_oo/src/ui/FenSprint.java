package ui;

import model.RegistreSprint;
import utils.Constante;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class FenSprint extends JFrame {
    private JPanel panGlobal, panSprint, panButton, panSprintCourrant, panBasDePage;
    private FenSprint fenetre;
    private RegistreSprint sprint;
    private JLabel lblSprint, lblTitre, lblScrum;//ajouter pour formulaire
    private JMenuBar menuBar;
    private JToolBar tbMenu;
    private ImageIcon iconSprintSave, iconAjouterSprint, iconModifierSprint,
            iconDeleteSprint;

    private JMenu mnuSprint;
    private JMenuItem miNouvSprint, miModSprint, miSortir, miDeleteSprint;
    private JButton btnAjouterSprint, btnModifierSprint, btnDeleteSprint;
    private JTable tblSprint;
    private JScrollPane scPaneSprint;
    private TableColumn tempCol0, tempCol1;
    private TableColumnModel colmod;

    private String[] nomColonnes = {"TaskID", "Date de départ", "Date de Fin", "Terminer"};
    private String[][] sprintTest = {
            {"1,2,3", "10-20-30", "10-20-30", "True"},
            {"1,2,3", "10-20-30", "10-20-30", "True"},
            {"1,2,3", "10-20-30", "10-20-30", "True"},
            {"1,2,3", "10-20-30", "10-20-30", "True"},
            {"1,2,3", "10-20-30", "10-20-30", "True"},
            {"1,2,3", "10-20-30", "10-20-30", "True"},
            {"1,2,3", "10-20-30", "10-20-30", "True"},
            {"1,2,3", "10-20-30", "10-20-30", "True"},
    };
    private Border brd;

    public FenSprint() {
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setWidget();
        //    setListener();
    }


    private void setWidget() {
        brd = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        //initialisation des Labels
        lblSprint = new JLabel("Sprint en cours");
        lblSprint.setFont(Constante.F1);
        lblTitre = new JLabel("Tous droit Réservé. ®");
        lblTitre.setFont(Constante.F4);
        lblScrum = new JLabel("Scrum..Master");
        lblScrum.setFont(Constante.F3);

        //initiation menuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //initialisation du toolbar
        tbMenu = new JToolBar();
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);

        //initiation des icones de boutons
        iconDeleteSprint = new ImageIcon("src/images/iconDeleteSprint.png");
        iconModifierSprint = new ImageIcon("src/images/iconChargerSprint.png");
        iconSprintSave = new ImageIcon("src/images/iconNewProjetSprint.png");

        //initiation des boutons
        btnAjouterSprint = new JButton(iconAjouterSprint);
        btnAjouterSprint.setToolTipText("Ajouter un sprint..");
        btnModifierSprint = new JButton(iconModifierSprint);
        btnModifierSprint.setToolTipText("Modifier un sprint..");
        btnDeleteSprint = new JButton(iconDeleteSprint);
        btnDeleteSprint.setToolTipText("Supprimer un sprint..");

        //*****************************************
        //initiation item du menu principal
        mnuSprint = new JMenu("Sprints");

        menuBar.add(mnuSprint);

        //initiation des items du menu Sprint
        miNouvSprint = new JMenuItem("Ajouter un sprint..");
        miModSprint = new JMenuItem("Modifier un sprint..");
        miDeleteSprint = new JMenuItem("Supprimer un sprint..");
        miSortir = new JMenuItem("Sortir..");
        //ajout dans menu
        mnuSprint.add(miNouvSprint);
        mnuSprint.add(miModSprint);
        mnuSprint.add(miDeleteSprint);
        mnuSprint.add(miSortir);

        /*
         *
         *
         *
         */
        //paneau Icone
        panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tbMenu.add(btnAjouterSprint);
        tbMenu.add(btnModifierSprint);
        tbMenu.add(btnDeleteSprint);
        panButton.add(tbMenu);

        //initialisation des table
        tblSprint = new JTable(sprintTest, nomColonnes);

        //initialisation scrollPane
        scPaneSprint = new JScrollPane(tblSprint);
        scPaneSprint.setPreferredSize(new Dimension(765,150));

        colmod = tblSprint.getColumnModel();
        tempCol0 = colmod.getColumn(0);
        tempCol0.setPreferredWidth(150);
        tempCol1 = colmod.getColumn(1);
        tempCol1.setPreferredWidth(350);

        //Panneau d'affichage des sprints du projet
        panSprint = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panSprint.add(lblSprint);
        panSprint.add(scPaneSprint);


    }

    private void setListener() {
    }
}
