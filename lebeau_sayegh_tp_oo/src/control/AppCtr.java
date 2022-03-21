package control;

import model.*;
import utils.TaskDejaExistException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class AppCtr {
    public static void main(String[] args) {
      /*  try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FenGui fenetre = new FenGui();
        fenetre.setSize(400,300);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);

        fenetre.setVisible(true);*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date projFin = sdf.parse("2022-07-26");

            Projet proj = new Projet("bibi", "blabla",projFin,4);
            Projet proj2 = new Projet("Flouflou", "teletubies",projFin,2);

            Map<Integer, Projet> listeProjet = new TreeMap<>();
            listeProjet.put(1, proj);
            listeProjet.put(4, proj2);
            for (int key : listeProjet.keySet()) {
                System.out.println(key);
                System.out.println(listeProjet.get(key));
            }


            Task t1 = new Task(1,"blabl1",6);
            Task t2 = new Task(1,"blabl2",6);
            Task t3= new Task(1,"blabl3",6);
            Task t4 = new Task(1,"blabl4",6);
            Task t5 = new Task(1,"blabl5",6);
            Task t6 = new Task(1,"blabl6",6);
            RegistreTask regTask = new RegistreTask();
            regTask.ajouterTask(t1);
            regTask.ajouterTask(t2);
            regTask.ajouterTask(t3);
            regTask.ajouterTask(t4);
            regTask.ajouterTask(t5);
            regTask.ajouterTask(t6);
            for(Task tmp: regTask.getRegistreTasks()){
                System.out.println(tmp);
            }
            int[] taskID = {1,2,3,4};
            int[] taskID2 = {5, 6};

            Sprint spt = new Sprint(taskID,projFin,false);
            Sprint spt1 = new Sprint(taskID2,projFin,false);

            RegistreSprint regSprint = new RegistreSprint();
            regSprint.ajouterSprint(spt);
            regSprint.ajouterSprint(spt1);
            for(Sprint tmp: regSprint.getRegSprint()){
                System.out.println(tmp);
                for (int j : taskID) {
                    Task temp = regTask.getRegistreTasks().get(j);
                }
            }

        } catch (ParseException | TaskDejaExistException e) {
            e.printStackTrace();
        }


    }
}
