package ui;

import io.ManipulationFichier;
import model.RegistreProjet;
import utils.Constante;
import utils.MiseEnPage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class FenSelectProjet extends JFrame{
    private FenSelectProjet fenetre;
    private RegistreProjet projet;

    String[] nomColonnes = { "Nom du projet", "Description", "ScrumMaster", "Date de début", "Date de fin"};
    String[][] tableTest = {
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

    public FenSelectProjet() {

        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
//        this.projet
//        projet = ManipulationFichier.lire(Constante.PROJET_FOLDER, projet, 1);

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
        JLabel lblTitre = new JLabel("Tous droit Réservé. ®");
        lblTitre.setFont(MiseEnPage.F4);
        JLabel lblScrum = new JLabel("Scrum..Master");
        lblScrum.setFont(MiseEnPage.F3);

        //initiation menuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //initialisation du toolbar
        JToolBar tbMenu = new JToolBar();
        tbMenu.setFloatable(false);
        tbMenu.setRollover(false);

        //initiation des icones de boutons
        ImageIcon iconNew = new ImageIcon("src/images/iconNew.png");
        ImageIcon iconDelete = new ImageIcon("src/images/iconDelete.png");
        ImageIcon iconCharger = new ImageIcon("src/images/iconCharger.png");

        //initiation des boutons
        JButton btn1 = new JButton(iconNew);
        btn1.setToolTipText("Nouveau Projet..");
        JButton btn2 = new JButton(iconCharger);
        btn2.setToolTipText("Charger le Projet Sélectionné..");
        JButton btn3 = new JButton(iconDelete);
        btn3.setToolTipText("Supprimer le Projet Sélectionné..");

        //*****************************************
        //initiation item du menu principal
        JMenu mnuFile = new JMenu("Fichier");

        menuBar.add(mnuFile);

        //initiation des items du menu Fichier
        JMenuItem miNouveauProj = new JMenuItem("Nouveau Projet..");
        JMenuItem miChargerProj = new JMenuItem("Charger Projet..");
        JMenuItem miSupprimerProj = new JMenuItem("Supprimer Projet..");
        JMenuItem miSortir = new JMenuItem("Sortir");
        //Séparateur de menu
        JSeparator sep1 = new JSeparator();
        sep1.setForeground(Color.gray);

        mnuFile.add(miNouveauProj);
        mnuFile.add(miChargerProj);
        mnuFile.add(miSupprimerProj);
        mnuFile.add(sep1);

        mnuFile.add(miSortir);

        //****************************************************
        //panneau menu
        JPanel panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButton.add(tbMenu);
        //panneau toolbar
        tbMenu.add(btn1);
        tbMenu.add(btn2);
        tbMenu.add(btn3);

        //initiation des tables
        JTable tblProjet = new JTable(tableTest, nomColonnes);

        //initiation des scrollpanes
        JScrollPane scPaneProjet = new JScrollPane(tblProjet);

        scPaneProjet.setPreferredSize(new Dimension(765, 75));
        TableColumnModel colmod = tblProjet.getColumnModel();
        TableColumn tempCol1 = colmod.getColumn(1);
        tempCol1.setPreferredWidth(350);
        TableColumn tempCol0 = colmod.getColumn(0);
        tempCol0.setPreferredWidth(150);

        //Panneau d'affichage des projets en cours
        JPanel panProjetCourrant = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panProjetCourrant.add(lblProjet);
        panProjetCourrant.add(scPaneProjet);

        JPanel panProjet = new JPanel(new BorderLayout());
        panProjet.setBorder(brd);

        panProjet.add(panProjetCourrant, BorderLayout.NORTH);

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
