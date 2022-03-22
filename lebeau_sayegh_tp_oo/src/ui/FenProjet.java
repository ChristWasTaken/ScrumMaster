package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class FenProjet extends JFrame {

    private FenProjet fenetre;


    private String nomColonnes[] = {"Nom", "Description", "ScrumMaster", "dateDebut", "dateFin"};
    private String [][] tableTest = {
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"},
            {"Bibi", "Flouflou", "Scrummaster", "05-06-10", "09-12-11"}
    };


    private Border brd;
    private double moyenne = 0;
    private Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 25);


    public FenProjet(String title) {

        super(title);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setWidget();
        //setListeners();
    }

    private void setWidget() {

        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        //************************************************************
        // Initialisation des objets de la fenetre.

        //initialisation des Labels
        JLabel lblProjet = new JLabel("Projet en cours");
//        JLabel lblNomProj= new JLabel("                    Nom: ");
//        JLabel lblDescProj = new JLabel("       Examen Intra: ");
//        JLabel lblScrumMaster = new JLabel("       Examen Final: ");
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
        tbMenu.setBorder(brd);

        //initiation des icones de boutons
        ImageIcon icon1 = new ImageIcon("lebeau_sayegh_tp_oo/images/icon1.png");

        //initiation des boutons
        JButton btn1 = new JButton(icon1);
        JButton btn2 = new JButton(icon1);
        JButton btn3 = new JButton(icon1);
        JButton btn4 = new JButton(icon1);
        JButton btn5 = new JButton(icon1);

        JTable tblProjet = new JTable(tableTest, nomColonnes);
        tblProjet.setBounds(0, 0, 100, 50);

        JScrollPane scPaneProjet = new JScrollPane();
        tblProjet.add(scPaneProjet);
        tblProjet.setSize(50,50);


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

        //**************************************************************************************//

        //Panneau d'affichage du projet en cours
        JPanel panProjet = new JPanel(new BorderLayout());
        panProjet.setBorder(brd);
        JPanel panProjetCourrant = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panProjetCourrant.setBorder(brd);
        JPanel panProjetSelection = new JPanel(new FlowLayout());
        panProjetSelection.setBorder(brd);

        panProjet.add(panProjetCourrant, BorderLayout.NORTH);
        panProjet.add(panProjetSelection, BorderLayout.SOUTH);

        panProjetCourrant.add(tblProjet);

        panProjetCourrant.add(lblProjet);

        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" }
        };

        // Column Names
        String[] columnNames = { "Name", "Roll Number", "Department" };

        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);


        //panneau Global
        JPanel panGlobal = new JPanel(new BorderLayout());
        panGlobal.setBorder(brd);
        panGlobal.add(j, BorderLayout.SOUTH);
        panGlobal.add(panButton, BorderLayout.NORTH);
        panGlobal.add(panProjet, BorderLayout.CENTER);
        this.setContentPane(panGlobal);


//        JPanel panSaisie = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        panSaisie.setPreferredSize(new Dimension(400, 170));
//        panSaisie.add(panNom);
//        panSaisie.add(panIntra);
//        panSaisie.add(panFinal);
//        panSaisie.add(panProjet);
//        panSaisie.setBorder(brd);

//        //panneau des résultats
//        JPanel panResultat = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        panResultat.setPreferredSize(new Dimension(400, 125));
//        panResultat.add(lblMoyenne);
//        panResultat.add(txtMoyenne);
//        panResultat.add(lblResultat);
//        panResultat.add(lblResult);
//        panResultat.setBorder(brd);

//        //panneau Main (saisie + résultats)
//        JPanel panMain = new JPanel();
//        panMain.add(panSaisie);
//        panMain.add(panResultat);
    }
}
