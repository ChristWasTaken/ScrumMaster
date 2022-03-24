package ui;

import io.ManipulationFichier;
import model.Projet;
import model.RegistreEmploye;
import model.RegistreProjet;
import utils.Constante;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

public class FenSelectProjet extends JFrame{
    private FenSelectProjet fenetre;
    private RegistreProjet projet;
    private RegistreEmploye employe;

    JLabel lblProjet, lblTitre, lblScrum ;
    JMenuBar menuBar;
    JToolBar tbMenu;
    ImageIcon iconNew, iconDelete, iconCharger;
    JButton btnNew, btnCharger, btnDelete;
    JMenu mnuFile;
    JMenuItem miNouveauProj, miChargerProj, miSupprimerProj, miSortir;
    JSeparator sep1;
    JPanel panGlobal, panProjet, panButton, panProjetCourrant, panBasDePage;
    JTable tblProjet;
    JScrollPane scPaneProjet;
    TableColumn tempCol0, tempCol1;
    TableColumnModel colmod;

    String[] nomColonnes = { "Nom du projet", "Description", "ScrumMaster", "Date de début", "Date de fin"};
    String[][] tableProjet = {{"", "", "", "", "", "" }};

    public FenSelectProjet(RegistreProjet projet, RegistreEmploye employe) {

        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
//        this.projet;
//        projet = ManipulationFichier.lire(Constante.PROJET_FOLDER, projet, 1);
        System.out.println(projet.getRegistrePro());
        setWidget(projet);
        setListeners();
    }

    private void setWidget(RegistreProjet projet) {

        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        //************************************************************
        // Initialisation des objets de la fenetre.

        //initialisation des Labels
        lblProjet = new JLabel("                        Projets en cours");
        lblProjet.setFont(Constante.F1);
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
        iconNew = new ImageIcon("src/images/iconNew.png");
        iconDelete = new ImageIcon("src/images/iconDelete.png");
        iconCharger = new ImageIcon("src/images/iconCharger.png");

        //initiation des boutons
        btnNew = new JButton(iconNew);
        btnNew.setToolTipText("Nouveau Projet..");
        btnCharger = new JButton(iconCharger);
        btnCharger.setToolTipText("Charger le Projet Sélectionné..");
        btnDelete = new JButton(iconDelete);
        btnDelete.setToolTipText("Supprimer le Projet Sélectionné..");

        //*****************************************
        //initiation item du menu principal
        mnuFile = new JMenu("Fichier");

        menuBar.add(mnuFile);

        //initiation des items du menu Fichiers
        miNouveauProj = new JMenuItem("Nouveau Projet..");
        miChargerProj = new JMenuItem("Charger Projet..");
        miSupprimerProj = new JMenuItem("Supprimer Projet..");
        miSortir = new JMenuItem("Sortir");
        //Séparateur de menu
        sep1 = new JSeparator();
        sep1.setForeground(Color.gray);

        mnuFile.add(miNouveauProj);
        mnuFile.add(miChargerProj);
        mnuFile.add(miSupprimerProj);
        mnuFile.add(sep1);

        mnuFile.add(miSortir);

        //****************************************************
        //panneau menu
        panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButton.add(tbMenu);
        //panneau toolbar
        tbMenu.add(btnNew);
        tbMenu.add(btnCharger);
        tbMenu.add(btnDelete);
        tbMenu.add(lblProjet);

        //initiation des table
        Format formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Projet tmp = projet.getRegistrePro().get(0);
            tableProjet[0][0]=tmp.getNomProjet();
            tableProjet[0][1]=tmp.getDescription();
            tableProjet[0][2]=Integer.toString(tmp.getScrumMasterId());
            tableProjet[0][3]= formatDate.format(tmp.getDateDebut());
            tableProjet[0][4]=formatDate.format(tmp.getDateFin());
            tableProjet[0][5]=Integer.toString(tmp.getDureeSprint());


        tblProjet = new JTable(tableProjet, nomColonnes);

        //initiation des scrollpanes
        scPaneProjet = new JScrollPane(tblProjet);

        scPaneProjet.setPreferredSize(new Dimension(765, 150));
        colmod = tblProjet.getColumnModel();
        tempCol1 = colmod.getColumn(1);
        tempCol1.setPreferredWidth(350);
        tempCol0 = colmod.getColumn(0);
        tempCol0.setPreferredWidth(150);

        //Panneau d'affichage des projets en cours
        panProjetCourrant = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panProjetCourrant.add(scPaneProjet);

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

    private void setListeners() {
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {

               ManipulationFichier.nouveauProjet("Projet1");
            }
        });
    }
}
