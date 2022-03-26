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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;

public class FenSelectProjet extends JFrame{
    private FenSelectProjet fenetre;
    private RegistreProjet registreProjet;
    private RegistreEmploye employe;

    private JLabel lblProjet, lblTitre, lblScrum ;
    private JMenuBar menuBar;
    private JToolBar tbMenu;
    private ImageIcon iconNew, iconDelete, iconCharger;
    private JButton btnNew, btnCharger, btnDelete;
    private JMenu mnuFile;
    private JMenuItem miNouveauProj, miChargerProj, miSupprimerProj, miSortir;
    private JSeparator sep1;
    private JPanel panGlobal, panProjet, panButton, panProjetCourrant, panBasDePage;
    private JTable tblProjet;
    private JScrollPane scPaneProjet;
    private TableColumn tempCol0, tempCol1;
    private TableColumnModel colmod;
    private DefaultTableModel tableModel;

    private String[] nomColonnes = { "Nom du projet", "Description", "ScrumMaster", "Date de début", "Date de fin", "Durée des sprints"};


    public FenSelectProjet(RegistreProjet projet, RegistreEmploye employe) {
        this.registreProjet = projet;
        this.employe = employe;

        setSize(800, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setWidget();
        setListeners();
    }

    private void setWidget() {

        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        //*******************************************
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
        iconNew = new ImageIcon("src/images/iconNewProjet.png");
        iconDelete = new ImageIcon("src/images/iconDeleteProjet.png");
        iconCharger = new ImageIcon("src/images/iconChargerProjet.png");

        //initiation des boutons
        btnNew = new JButton(iconNew);
        btnNew.setToolTipText("Nouveau Projet..");
        btnCharger = new JButton(iconCharger);
        btnCharger.setToolTipText("Charger le Projet Sélectionné..");
        btnDelete = new JButton(iconDelete);
        btnDelete.setToolTipText("Supprimer le Projet Sélectionné..");

        //*************** Entete ****************
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
        //*************** Toolbar ****************

        //panneau du toolbar
        panButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panButton.add(tbMenu);
        //objet du toolbar
        tbMenu.add(btnNew);
        tbMenu.add(btnCharger);
        tbMenu.add(btnDelete);
        tbMenu.add(lblProjet);

        //******* initiation da la table *********
        //Modele de la table
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(nomColonnes);
        //table
        tblProjet = new JTable();
        tblProjet.setModel(tableModel);
        // Permet la selection d'une colonne seulement
        tblProjet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Fonction pour remplir la table par le model
        remplirTable();

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

                FenProjet fenProjet = new FenProjet(registreProjet);
                fenProjet.setVisible(true);
                dispose();
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null,"La suppression set final. Êtes-vous sur?", "Suppression de projet",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    try{
                        int i = tblProjet.getSelectedRow();
                        System.out.println(registreProjet.getRegistrePro().size());
                        ManipulationFichier.effacerFichiersProjet(registreProjet.getRegistrePro().get(i).getNomProjet());
                        registreProjet.effacerProjet(i);
                        ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET +Constante.nomFichier[0], registreProjet, 1);
                        System.out.println(registreProjet.getRegistrePro().size());
                        tableModel.removeRow(i);

                        JOptionPane.showMessageDialog(null,"Suppression du projet complété.");
                    } catch (IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null,"La ligne du projet doit être correctement selectionné pour pouvoir le supprimer.");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Suppression du projet annulée.");
                }
            }
        });
    }
    public void remplirTable(){
        Format formatDate = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(registreProjet.getRegistrePro().size());
        System.out.println(nomColonnes.length);
        for (Projet tmp : registreProjet.getRegistrePro()){
            Object[] row = {tmp.getNomProjet(), tmp.getDescription(), Integer.toString(tmp.getScrumMasterId()),
                            formatDate.format(tmp.getDateDebut()), formatDate.format(tmp.getDateFin()),
                            Integer.toString(tmp.getDureeSprint())};
            tableModel.addRow(row);
        }
    }
}
