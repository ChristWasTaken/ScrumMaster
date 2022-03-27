package utils;

import java.awt.*;

public class Constante {
    //**** Constantes de manipulation de fichier ****
    public static String[] nomFichier = {"Projets.dat", "\\tasks.dat", "\\sprints.dat"};

    public static final String REPERTOIRE_PROJET = "dataFiles\\Projets\\";


    //Constantes de nom de colonnes des tableaux

    public static final String[] TBL_PROJET = {"Nom du projet", "Description", "ScrumMaster", "Date de début", "Date de fin", "Durée des sprints"};
    public static final String[] TBL_TASK = {"TaskID", "Indice de Priorité", "Description", "Assignation"};
    public static final String[] TBL_SPRINT ={""};


    public static final String[] POSTES = {"Scrum master", "Programmeur senior", "Programmeur junior"};



    /*  ***************************************************************************************** */

    public static final Font F1 = new Font(java.awt.Font.SANS_SERIF, java.awt.Font.BOLD, 25);
    public static final Font F2 = new Font(java.awt.Font.SANS_SERIF, java.awt.Font.BOLD, 18);
    public static final Font F3 = new Font(java.awt.Font.SANS_SERIF, Font.BOLD, 14);
    public static final Font F4 = new Font(java.awt.Font.SANS_SERIF, Font.PLAIN, 10);
}

