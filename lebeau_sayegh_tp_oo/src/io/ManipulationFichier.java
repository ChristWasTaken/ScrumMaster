package io;

import model.*;
import utils.*;

import javax.swing.*;
import java.io.*;

@SuppressWarnings("FieldCanBeLocal")

public class ManipulationFichier{

    //************ READ/WRITE ***********

    //Lecture d'un fichier binaire
    public static void lire(String fichier, Object registre, int index) {
        //lire du fichier binaire
        File file = new File(fichier);
        FileInputStream fr;
        BufferedInputStream br;
        ObjectInputStream ois = null;
        RegistreTask regTask;
        RegistreEmploye regEmp;
        RegistreProjet regPro;
        RegistreSprint regSpri;
        RegistreNotes regNotes;
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
                        regEmp = (RegistreEmploye) registre;
                        Employe emp = (Employe) ois.readObject();
                        regEmp.ajouterEmp(emp);
                    }
                }
                case 1 -> {
                    for (int i = 0; i < taille; i++) {
                         regPro = (RegistreProjet) registre;
                        Projet projet = (Projet) ois.readObject();
                        regPro.ajouterProjet(projet);
                    }
                }
                case 2 -> {
                    for (int i = 0; i < taille; i++) {
                        regTask = (RegistreTask) registre;
                        Task task = (Task) ois.readObject();
                        regTask.ajouterTask(task);
                        Task.setNbrTask(regTask.getRegistreTasks().size());
                    }
                }
                case 3 -> {
                    for (int i = 0; i < taille; i++) {
                        regSpri = (RegistreSprint) registre;
                        Sprint sprint = (Sprint) ois.readObject();
                        regSpri.ajouterSprint(sprint);
                    }
                }
                case 4 -> {
                    for (int i = 0; i < taille; i++) {
                        regNotes = (RegistreNotes) registre;
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
    //Ecriture d'un fichier binaire
    public static void ecrire(String fichier, Object reg, int index) {
        //ecrire dans un fichier
        File file = new File(fichier);
        FileOutputStream fw ;
        BufferedOutputStream bw = null;
        ObjectOutputStream oos ;

        try {
            fw = new FileOutputStream(file);
            bw = new BufferedOutputStream(fw);
            oos = new ObjectOutputStream(bw);

            switch (index) {
                case 0 -> {
                    RegistreEmploye regEmp = (RegistreEmploye) reg;
                    oos.writeInt(regEmp.getRegistreEmp().size());
                    for (Object objet : regEmp.getRegistreEmp()) {
                        oos.writeObject(objet);
                    }
                }
                case 1 -> {
                    RegistreProjet regPro = (RegistreProjet) reg;
                    oos.writeInt(regPro.getRegistrePro().size());//ecrire la taille de la collection
                    for (Object objet : regPro.getRegistrePro()) {
                        oos.writeObject(objet);
                    }
                }
                case 2 -> {
                    RegistreTask regTask = (RegistreTask) reg;
                    oos.writeInt(regTask.getRegistreTasks().size());
                    for (Object objet : regTask.getRegistreTasks()) {
                        oos.writeObject(objet);
                    }
                }
                case 3 -> {
                    RegistreSprint regSpri = (RegistreSprint) reg;
                    oos.writeInt(regSpri.getRegSprint().size());//ecrire la taille de la collection
                    for (Object objet : regSpri.getRegSprint()) {
                        oos.writeObject(objet);

                    }
                }
                case 4 -> {
                    RegistreNotes regNotes = (RegistreNotes) reg;
                    oos.writeInt(regNotes.getRegistreNotes().size());//ecrire la taille de la collection
                    for (Object objet : regNotes.getRegistreNotes()) {
                        oos.writeObject(objet);
                    }
                }
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

    //************ Manipulation des fichiers/répertoires projets ************

    //Creation des fichiers d'un nouveau projet
    public static void nouveauProjet(String projet, JTextArea console) {
        String dirName = Constante.REPERTOIRE_PROJET +"\\"+projet;
        File newFolder = new File(dirName);
        if(newFolder.mkdir()){
            console.append("Nouveau projet créer. Retourner à la page précédente pour lui accèder.\n");
        }else{
            console.append("Échec de la création du projet\n");
        }

        try {
            File newFile = new File( Constante.REPERTOIRE_PROJET +"\\"+projet+"\\"+"tasks.dat");
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            File newFile = new File( Constante.REPERTOIRE_PROJET +"\\"+projet+"\\"+"sprints.dat");
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException f) {
            System.out.println("An error occurred.");
            f.printStackTrace();
        }
    }

    public static void effacerFichiersProjet(String projet, JTextArea console) {
        String dirName = Constante.REPERTOIRE_PROJET + projet;
        File directory = new File(dirName);
        try {

            if (directory.exists()) {
                System.out.println(dirName);
                effacerRepertoire(directory);
                directory.delete();
                console.append("Répertoire " + projet +" supprimé\n");
            } else {
                console.append("Répertoire inexistant:"+ directory+"\n");
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }


    public static void effacerFichier(String fichier, JTextArea console) {
        File myFile = new File(fichier);
        if (myFile.delete()) {
            console.append("Fichier supprimé: " + myFile.getName()+"\n");
        } else {
            console.append("Erreur, fichier toujours présent.\n");
        }
    }

    public static void effacerRepertoire(File file)
    {
        // store all the paths of files and folders present
        // inside directory
        for (File subfile : file.listFiles()) {

            // if it is a subfolder,e.g Rohan and Ritik,
            // recursiley call function to empty subfolder
            if (subfile.isDirectory()) {
                effacerRepertoire(subfile);
            }

            // delete files and empty subfolders
            subfile.delete();
        }
    }


}
