package control;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubContrastIJTheme;
import io.ManipulationFichier;
import model.*;
import ui.FenApp;
import utils.Constante;
import utils.DoublonException;
import utils.Utilitaire;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("FieldCanBeLocal")

public class AppCtr {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatGitHubContrastIJTheme());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        RegistreEmploye employe = new RegistreEmploye();
        RegistreProjet projet = new RegistreProjet();
        RegistreTask regTask = new RegistreTask();
        RegistreSprint registreSprint = new RegistreSprint();

//            Projet proj = new Projet("Scrum..Master", "Gérer des projets avec la méthode Scrum", 1, Utilitaire.getTodayDate(), 2);
//            try {
//                projet.ajouterProjet(proj, 1);
//            } catch (DoublonException e) {
//                e.printStackTrace();
//            }
//            ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[0], projet, 1);


//        try {
//            Task t1 = new Task(1, "Ajouter vos tasks ici - effacer ou modifier ce task.", 6);
//            regTask.ajouterTask(t1, 0);
//            ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET+Constante.nomFichier[1],regTask,2 );
//
//        } catch (DoublonException e) {
//            e.printStackTrace();
//        }


//
//        ArrayList<Integer> taskID = new ArrayList<>();
//        taskID.add(0);
//        taskID.add(1);
//        taskID.add(5);
//        Date projFin = Utilitaire.getTodayDate();
//        Sprint spt = new Sprint(taskID, "Ajouter vos Sprints ici - effacer ou modificer ce sprint", projFin, projFin, 0);
//        taskID.clear();
//        taskID.add(2);
//        taskID.add(7);
//        taskID.add(3);
////        Sprint spt2 = new Sprint(taskID, "Ajouter vos Sprints ici - effacer ou modificer ce sprint", projFin, true);
//        try {
//            registreSprint.ajouterSprint(spt);
//            ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[2] ,registreSprint, 3);
//        } catch (DoublonException e) {
//            e.printStackTrace();
//        }


//
//        Employe emp1 = new Employe("Alain", "Flouflou", Constante.POSTES[0]);
//        Employe emp2 = new Employe("Bibi", "Telebubies", Constante.POSTES[1]);
//        Employe emp3 = new Employe("Caroline", "Lemay", Constante.POSTES[1]);
//        Employe emp4 = new Employe("Christian", "Sayegh", Constante.POSTES[2]);
//        Employe emp5 = new Employe("Yann", "Lebeau", Constante.POSTES[2]);
//        Employe emp6 = new Employe("Toto", "Flouflou", Constante.POSTES[0]);
//        Employe emp8 = new Employe("Nicole", "Desjardins", Constante.POSTES[0]);
//        Employe emp9 = new Employe("Virginie", "Picard", Constante.POSTES[0]);
//        Employe emp0 = new Employe("Pascal", "Lachance", Constante.POSTES[2]);
//
//        try{
//            employe.ajouterEmp(emp1);
//            employe.ajouterEmp(emp2);
//            employe.ajouterEmp(emp3);
//            employe.ajouterEmp(emp4);
//            employe.ajouterEmp(emp5);
//            employe.ajouterEmp(emp6);
//            employe.ajouterEmp(emp8);
//            employe.ajouterEmp(emp9);
//            employe.ajouterEmp(emp0);
//        } catch (DoublonException e) {
//            e.printStackTrace();
//        }
//        ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET+Constante.nomFichier[3],employe,0);

        ManipulationFichier.lire(Constante.REPERTOIRE_PROJET +Constante.nomFichier[3], employe,0);
        ManipulationFichier.lire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[0], projet, 1);
        ManipulationFichier.lire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[1], regTask, 2);
        ManipulationFichier.lire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[2], registreSprint, 3);

        FenApp fenetre;
        fenetre = new FenApp(projet, employe, regTask, registreSprint);
        fenetre.setVisible(true);
    }
}
