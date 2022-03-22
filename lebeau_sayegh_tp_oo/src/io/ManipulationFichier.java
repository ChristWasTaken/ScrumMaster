package io;

import com.jgoodies.common.collect.ArrayListModel;
import model.*;
import utils.EmployeDejaPresentException;
import utils.ProjetDejaPresentException;
import utils.SprintDejaPresentException;
import utils.TaskDejaExistException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManipulationFichier {

    public static void lire(String fichier, Object registre, int index) {
        //lire du fichier binaire
        File file = new File(fichier);

        FileInputStream fr = null;
        BufferedInputStream br = null;
        ObjectInputStream ois = null;

        try {
            fr = new FileInputStream(file);
            br = new BufferedInputStream(fr);
            ois = new ObjectInputStream(br);

            //lire le size
            int taille = ois.readInt();
            //Parcourir le fichier selon le size qui a été lu
            switch (index) {
                case 0 -> {
                    for (int i = 0; i < taille; i++) {
                        RegistreEmploye regEmp = (RegistreEmploye) registre;
                        Employe emp = (Employe) ois.readObject();
                        regEmp.ajouterEmp(emp);
                    }
                }
                case 1 -> {
                    for (int i = 0; i < taille; i++) {
                        RegistreProjet regPro = (RegistreProjet) registre;
                        Projet projet = (Projet) ois.readObject();
                        regPro.ajouterProjet(projet);
                    }
                }
                case 2 -> {
                    for (int i = 0; i < taille; i++) {
                        RegistreTask regTask = (RegistreTask) registre;
                        Task task = (Task) ois.readObject();
                        regTask.ajouterTask(task);
                    }
                }
                case 3 -> {
                    for (int i = 0; i < taille; i++) {
                        RegistreSprint regSpri = (RegistreSprint) registre;
                        Sprint sprint = (Sprint) ois.readObject();
                        regSpri.ajouterSprint(sprint);
                    }
                }
                case 4 -> {
                    for (int i = 0; i < taille; i++) {
                        RegistreNotes regNotes = (RegistreNotes) registre;
                        Notes note = (Notes) ois.readObject();
                        regNotes.ajouterNotes(note);
                    }
                }

            }
        } catch (IOException | ClassNotFoundException | EmployeDejaPresentException | ProjetDejaPresentException | TaskDejaExistException | SprintDejaPresentException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void ecrire(String fichier, Registre reg, int index) {
        //ecrire dans un fichier
        File file = new File(fichier);
        FileOutputStream fw = null;
        BufferedOutputStream bw = null;
        ObjectOutputStream oos = null;

        try {
            fw = new FileOutputStream(file);
            bw = new BufferedOutputStream(fw);
            oos = new ObjectOutputStream(bw);

            switch (index) {
                case 0:
                    RegistreEmploye regEmp = (RegistreEmploye) reg;
                    oos.writeInt(regEmp.getRegistreEmp().size());
                    for (Object objet : regEmp.getRegistreEmp()) {
                        oos.writeObject(objet);
                    }
                    break;

                case 1:
                    RegistreProjet regPro = (RegistreProjet) reg;
                    oos.writeInt(regPro.getRegistrePro().size());//ecrire la taille de la collection
                    for (Object objet : regPro.getRegistrePro()) {
                        oos.writeObject(objet);
                    }
                    break;
                case 2:

                    RegistreTask regTask = (RegistreTask) reg;
                    oos.writeInt(regTask.getRegistreTasks().size());
                    System.out.println("oui");//ecrire la taille de la collection
                    for (Object objet : regTask.getRegistreTasks()) {
                        oos.writeObject(objet);
                    }

                    break;
                case 3:
                    RegistreSprint regSpri = (RegistreSprint) reg;
                    oos.writeInt(regSpri.getRegSprint().size());//ecrire la taille de la collection
                    for (Object objet : regSpri.getRegSprint()) {
                        oos.writeObject(objet);

                    }
                    break;
                case 4:
                    RegistreNotes regNotes = (RegistreNotes) reg;
                    oos.writeInt(regNotes.getRegistreNotes().size());//ecrire la taille de la collection
                    for (Object objet : regNotes.getRegistreNotes()) {
                        oos.writeObject(objet);
                    }
                    break;

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
