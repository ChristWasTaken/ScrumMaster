package ui;

import io.ManipulationFichier;
import model.Projet;
import model.RegistreProjet;
import utils.Constante;
import utils.ProjetDejaPresentException;
import utils.Utilitaire;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FenProjet extends JFrame {

    private RegistreProjet registreProj;

    private FenProjet fenetre;

    private JPanel panGlobal, panProjet, panProjetCourrant, panButton, panProjetForm, panSprint, panTask, panBasDePage;

    private JLabel lblProjet, lblScrum, lblSprint, lblTasks, lblTitre, lblNomProjet, lblDescProjet, lblScrumId, lblDateDebut, lblDateFin, lblDureeSprint;
    private JTextField txtProjet, txtNomProjet, txtDescProjet, txtScrumId, txtDureeSprint;

    private JFormattedTextField ftxtDateDebut, ftxtDateFin;
    private DateFormat formatDate;

    private JToolBar tbMenu;
    private ImageIcon iconProjetSave, iconAjouterTask, iconModifierTask, iconDeleteTask, iconAjouterSprint, iconModifierSprint, iconDeleteSprint;
    private JButton btnProjetSave, btnAjouterTask, btnModifierTask, btnDeleteTask, btnAjouterSprint, btnModifierSprint, btnDeleteSprint;

    private JMenuBar menuBar;
    private JMenuItem miEnregistrerProj, miRetour, miAjouterTask, miModifierTask, miDeleteTask, miAjouterSprint, miModifierSprint, miDeleteSprint;
    private JMenu mnuProjet, mnuSprint, mnuTask;
    private JSeparator sep1, sepVertical, sepVertical2;

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

    };

    public FenProjet(RegistreProjet registre) {
        this.registreProj = registre;
        setSize(800, 1200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setWidget();
//        setAlwaysOnTop(true);
        setListeners();
    }

    private void setWidget() {

        Border brd = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

        //************************************************************
        // Initialisation des objets de la fenetre.

        //initialisation des Labels
        lblProjet = new JLabel("              Projets en cours");
        lblProjet.setFont(Constante.F2);

        lblNomProjet = new JLabel("Nom du projet: ");
        lblDescProjet = new JLabel("Description du projet: ");
        lblScrumId = new JLabel("Charger de projet: ");
        lblDateDebut = new JLabel("Début du projet (yyyy-mm-dd): ");
        lblDateFin = new JLabel("Fin du projet (yyyy-mm-dd): ");
        lblDureeSprint = new JLabel("Nombre de semaine par sprint: ");

        lblSprint = new JLabel("Sprints restant au projet");
        lblSprint.setFont(Constante.F2);

        lblTasks = new JLabel("Taches restantes au projet");
        lblTasks.setFont(Constante.F2);

        lblTitre = new JLabel("Tous droit Réservé. ®");
        lblTitre.setFont(Constante.F4);
        lblScrum = new JLabel("Scrum..Master");
        lblScrum.setFont(Constante.F3);


        //initialisation des textfields
        txtProjet = new JTextField(20);
        txtNomProjet = new JTextField(30);
        txtDescProjet = new JTextField(50);
        txtScrumId = new JTextField(20);


//        formatDate = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//        JFormattedTextField txtDate = new JFormattedTextField(df);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter formatDate = new DateFormatter(format);
//        JFormattedTextField dateField = new JFormattedTextField(df);
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

        //Séparateur de la toolbar
        sep1 = new JSeparator();
        sep1.setForeground(Color.gray);
        sepVertical = new JSeparator(SwingConstants.VERTICAL);
        sepVertical.setForeground(Color.lightGray);
        sepVertical2 = new JSeparator(SwingConstants.VERTICAL);
        sepVertical2.setForeground(Color.lightGray);

        //initiation des icones de boutons
        iconProjetSave = new ImageIcon("src/images/iconNewProjet.png");
        iconAjouterTask= new ImageIcon("src/images/iconNewTask.png");
        iconModifierTask = new ImageIcon("src/images/iconChargerTask.png");
        iconDeleteTask = new ImageIcon("src/images/iconDeleteTask.png");
        iconAjouterSprint = new ImageIcon("src/images/iconNewSprint.png");
        iconModifierSprint = new ImageIcon("src/images/iconChargerSprint.png");
        iconDeleteSprint = new ImageIcon("src/images/iconDeleteSprint.png");

        //initiation des boutonss
        btnProjetSave = new JButton(iconProjetSave);
        btnProjetSave.setToolTipText("Sauvegarder le projet..");
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
        tbMenu.add(btnProjetSave);
        tbMenu.add(sepVertical);
        tbMenu.add(btnAjouterTask);
        tbMenu.add(btnModifierTask);
        tbMenu.add(btnDeleteTask);
        tbMenu.add(sepVertical2);
        tbMenu.add(btnAjouterSprint);
        tbMenu.add(btnModifierSprint);
        tbMenu.add(btnDeleteSprint);
        tbMenu.add(lblProjet);

        //*****************************************
        //initiation item du menu principal
        mnuProjet = new JMenu("Projet");
        mnuSprint = new JMenu("Sprints");
        mnuTask = new JMenu("Taches");

        menuBar.add(mnuProjet);
        menuBar.add(mnuTask);
        menuBar.add(mnuSprint);

        //initiation des items du menu Projet
        miEnregistrerProj = new JMenuItem("Enregistrer le Projet..");
        miRetour = new JMenuItem("Retour à la selection..");
        //initiation des items du menu Task
        miAjouterTask = new JMenuItem("Ajouter une tache..");
        miModifierTask = new JMenuItem("Modifier une tache..");
        miDeleteTask = new JMenuItem("Supprimer une tache..");
        //initiation des items du menu Sprint
        miAjouterSprint = new JMenuItem("Ajouter un sprint..");
        miModifierSprint = new JMenuItem("Modifier un sprint..");
        miDeleteSprint = new JMenuItem("Supprimer un sprint..");

        mnuProjet.add(miEnregistrerProj);
        mnuProjet.add(sep1);
        mnuProjet.add(miRetour);

        mnuSprint.add(miAjouterSprint);
        mnuSprint.add(miModifierSprint);
        mnuSprint.add(miDeleteSprint);

        mnuTask.add(miAjouterTask);
        mnuTask.add(miModifierTask);
        mnuTask.add(miDeleteTask);

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

        scPaneTask.setPreferredSize(new Dimension(765,190));
        colmod = tblTask.getColumnModel();
        tempCol0 = colmod.getColumn(0);
        tempCol0.setPreferredWidth(150);
        tempCol1 = colmod.getColumn(1);
        tempCol1.setPreferredWidth(350);


        //Panneau d'affichage des sprints du projet
        panSprint = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panSprint.add(lblSprint);
        panSprint.add(scPaneSprint);

        //Panneau d'affichage des sprints du projet
        panTask = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panTask.add(lblTasks);
        panTask.add(scPaneTask);

        //Panneau d'affichage du projet selectionné
        panProjetForm = new JPanel(new GridLayout(12,1));
        panProjetForm.add(lblNomProjet);
        panProjetForm.add(txtNomProjet);
        panProjetForm.add(lblDescProjet);
        panProjetForm.add(txtDescProjet);
        panProjetForm.add(lblScrumId);
        panProjetForm.add(txtScrumId);
        panProjetForm.add(lblDateDebut);
        panProjetForm.add(ftxtDateDebut);
        panProjetForm.add(lblDateFin);
        panProjetForm.add(ftxtDateFin);
        panProjetForm.add(lblDureeSprint);
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

    private void setListeners() {

        miRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();


            }
        });
        btnProjetSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ManipulationFichier.nouveauProjet(txtNomProjet.getText());
                DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                Projet tempProj = new Projet(txtNomProjet.getText(), txtDescProjet.getText(), Integer.parseInt(txtScrumId.getText()),
                        Utilitaire.getTodayDate(), Utilitaire.getTodayDate(), Integer.parseInt(txtDureeSprint.getText()));

                try {
                    registreProj.ajouterProjet(tempProj);
                    for (Projet tmp : registreProj.getRegistrePro()){
                        System.out.println(tmp);
                    }
                } catch (ProjetDejaPresentException ex) {
                    ex.printStackTrace();
                }
                ManipulationFichier.effacerFichier(Constante.REPERTOIRE_PROJET +Constante.nomFichier[0]);
                System.out.println(registreProj.getRegistrePro().size());
                ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET +Constante.nomFichier[0], registreProj, 1);
            }
        });
    }
}
