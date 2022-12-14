package utils;

import java.awt.*;

public class Constante {
    //**** Constantes de manipulation de fichier ****
    public static final String[] nomFichier = {"Projets.dat", "\\tasks.dat", "\\sprints.dat", "Employes.dat"};

    public static final String REPERTOIRE_PROJET = "dataFiles\\Projets\\";
    public static final String[] REPERTOIRE_IMAGE ={"src/images/iconRetour.png","src/images/iconNewProjet.png",
            "src/images/iconDeleteProjet.png","src/images/iconChargerProjet.png","src/images/iconNewTask.png",
            "src/images/iconChargerTask.png","src/images/iconDeleteTask.png","src/images/iconNewSprint.png",
            "src/images/iconChargerSprint.png","src/images/iconDeleteSprint.png","src/images/iconSave.png",
            "src/images/iconAddTask.png", "src/images/iconRemoveTask.png"};

    //Constantes de nom de colonnes des tableaux

    public static final String[] TBL_PROJET = {"Nom du projet", "Description", "ScrumMaster", "Date de début", "Date de fin", "Durée des sprints"};
    public static final String[] TBL_TASK = {"TaskID", "Priorité", "Description", "Assignation"};
    public static final String[] TBL_SPRINT ={"Description", "Date de début", "Date de fin", "Progrès"};


    public static final String[] POSTES = {"Scrum master", "Programmeur senior", "Programmeur junior"};
    public static final String[] PROGRES_SPRINT = {"En cours", "Complété"};

    public static final int[] TAILLE_COL_1 = {40, 350, 50, 20, 20, 20};
    public static final int[] TAILLE_COL_2 = {6, 15, 230, 70};
    public static final int[] TAILLE_COL_3 = {180, 20, 15, 6};
    public static final int[] TAILLE_COL_4 = {6,15, 210, 70};
    public static final int[] PRIORITY ={1,2,3,4,5,6,7,8,9};

    /*  ***************************************************************************************** */

    public static final Font F1 = new Font(java.awt.Font.SANS_SERIF, java.awt.Font.BOLD, 25);
    public static final Font F2 = new Font(java.awt.Font.SANS_SERIF, java.awt.Font.BOLD, 18);
    public static final Font F3 = new Font(java.awt.Font.SANS_SERIF, Font.BOLD, 14);
    public static final Font F4 = new Font(java.awt.Font.SANS_SERIF, Font.PLAIN, 10);
    public static final Font F5 = new Font(java.awt.Font.SANS_SERIF, Font.BOLD, 12);
}

