package control;

import io.ManipulationFichier;
import model.*;
import ui.FenApp;
import utils.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubContrastIJTheme;


import javax.swing.*;

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


//        try {
//            Date projFin = sdf.parse("2022-07-26");
//            Projet proj2 = new Projet("bibi", "blabla", 1,Utilitaire.getTodayDate(),4);
//            Projet proj = new Projet("Scrum..Master", "Gérer des projets avec la méthode Scrum",1, Utilitaire.getTodayDate(),2);
//
//            try{
//                projet.ajouterProjet(proj);
////                projet.ajouterProjet(proj2);
//
//            }catch (ProjetDejaPresentException e) {
//                e.printStackTrace();
//            }
//        System.out.println(projet.getRegistrePro());
////            ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[0], projet, 1);
              ManipulationFichier.lire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[0], projet, 1);
            for (Projet tmp : projet.getRegistrePro()) {
                System.out.println(tmp);
            }
//        try {
//            Task t1 = new Task(1, "blabl1", 6);
//            Task t2 = new Task(1, "blabl2", 6);
//            Task t3 = new Task(1, "blabl3", 6);
//            Task t4 = new Task(1, "blabl4", 6);
//            Task t5 = new Task(1, "blabl5", 6);
//            Task t6 = new Task(1, "blabl6", 6);
//
//
//            regTask.ajouterTask(t1);
//            regTask.ajouterTask(t2);
//            regTask.ajouterTask(t3);
//            regTask.ajouterTask(t4);
//            regTask.ajouterTask(t5);
//            regTask.ajouterTask(t6);
//            ManipulationFichier.lire(Constante.REPERTOIRE_PROJET +Constante.nomFichier[1], regTask, 2);
//            for (Task tmp : regTask.getRegistreTasks()) {
//                System.out.println(tmp);
//
//              ManipulationFichier.ecrire(Constante.REPERTOIRE_PROJET+Constante.nomFichier[1],regTask,2 );
////            int[] taskID = {1,2,3,0};
////            int[] taskID2 = {5,4};
//            }


//        } catch (TaskDejaExistException e) {
//            e.printStackTrace();
//        }
//            Sprint spt = new Sprint(taskID,projFin,false);
//            Sprint spt1 = new Sprint(taskID,projFin,true);
//            Sprint sp2 = new Sprint(taskID,projFin,false);
//            Sprint spt3 = new Sprint(taskID,projFin,true);
//
//            RegistreSprint regSprint = new RegistreSprint();
//            regSprint.ajouterSprint(spt);
//            regSprint.ajouterSprint(spt1);
//
//            for(Sprint tmp: regSprint.getRegSprint()){
//                System.out.println(tmp);
//                for (int j : tmp.getTaskID()) {
//                    Task temp = regTask.getRegistreTasks().get(j);
//                    System.out.println(temp);
//                }
//            }
//
//        } catch ( /*ParseException*/ /*TaskDejaExistException*/ e) {
//            e.printStackTrace();
//        }// catch(SprintDejaPresentException f){
//            f.printStackTrace();

//
//        Employe emp1 = new Employe("Alain", "Flouflou", Constante.POSTES[0]);
//        Employe emp2 = new Employe("Bibi", "Telebubies", Constante.POSTES[1]);
//        Employe emp3 = new Employe("Caroline", "Lemay", Constante.POSTES[1]);
//        Employe emp4 = new Employe("Christian", "Sayegh", Constante.POSTES[2]);
//        Employe emp5 = new Employe("Yann", "Lebeau", Constante.POSTES[2]);
//
//        try{
//            employe.ajouterEmp(emp1);
//            employe.ajouterEmp(emp2);
//            employe.ajouterEmp(emp3);
//            employe.ajouterEmp(emp4);
//            employe.ajouterEmp(emp5);
//        }
//        catch(EmployeDejaPresentException e){
//            e.printStackTrace();
//        }
//        for (Employe tmp : employe.getRegistreEmp()) {
//            System.out.println(tmp);
//        }
//        ManipulationFichier.ecrire(Constante.PROJET_FOLDER+"Employes.dat",employe,0);
//        ManipulationFichier.lire(Constante.REPERTOIRE_PROJET +"Employes.dat", employe,0);
//        for (Employe emp : employe.getRegistreEmp()) {
//            System.out.println(emp);
//        }
        FenApp fenetre;

        fenetre = new FenApp(projet, employe, regTask);
        fenetre.setVisible(true);
    }
}
