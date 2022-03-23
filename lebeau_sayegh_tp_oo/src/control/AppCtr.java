package control;

import io.ManipulationFichier;
import model.*;
import ui.FenProjet;
import ui.FenSelectProjet;
import utils.Constante;
import utils.SprintDejaPresentException;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubContrastIJTheme;
import utils.TaskDejaExistException;


import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class AppCtr {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatGitHubContrastIJTheme() );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date projFin = sdf.parse("2022-07-26");
//
//            Projet proj = new Projet("bibi", "blabla", 1,projFin,4);
//            Projet proj2 = new Projet("Flouflou", "teletubies",1,projFin,2);
//
//
//            Task t1 = new Task(1,"blabl11",6);
////            Task t2 = new Task(1,"blabl2",6);
////            Task t3= new Task(1,"blabl3",6);
////            Task t4 = new Task(1,"blabl4",6);
////            Task t5 = new Task(1,"blabl5",6);
////            Task t6 = new Task(1,"blabl6",6);
//            RegistreTask regTask = new RegistreTask();
//
//            regTask.ajouterTask(t1);
////            regTask.ajouterTask(t2);
////            regTask.ajouterTask(t3);
////            regTask.ajouterTask(t4);
////            regTask.ajouterTask(t5);
////            regTask.ajouterTask(t6);
//            ManipulationFichier.lire(Constante.type[2],regTask,2 );
//            for(Task tmp: regTask.getRegistreTasks()){
//                System.out.println(tmp);
//            }
//            ManipulationFichier.ecrire(Constante.type[2],regTask,2 );

//            int[] taskID = {1,2,3,0};
//            int[] taskID2 = {5,4};
//
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
////
//        } catch (ParseException | TaskDejaExistException e) {
//            e.printStackTrace();
//      }// catch(SprintDejaPresentException f){
////            f.printStackTrace();
////        }
//
        FenProjet fenetreProjet = new FenProjet();
        fenetreProjet.setVisible(true);

//        FenSelectProjet fenetreSelect = new FenSelectProjet();
//        fenetreSelect.setVisible(true);


    }
}
