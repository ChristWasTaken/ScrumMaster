package io;

import com.jgoodies.common.collect.ArrayListModel;
import model.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManipulationFichier {

    public static void lire(String fichier, Object registre) {
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
            for (int i=0;i<taille;i++){
                Article article = (Article)ois.readObject();
                registre.ajouterArticle(article);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
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

    public static void ecrire(String fichier, Object reg, int type) {
        //ecrire dans un fichier
        File file = new File(fichier);
        FileOutputStream fw = null;
        BufferedOutputStream bw = null;
        ObjectOutputStream oos = null;
        String contenu;
        try {
            fw = new FileOutputStream(file);
            bw = new BufferedOutputStream(fw);
            oos = new ObjectOutputStream(bw);

            switch (type) {
                case '1':
                    RegistreEmploye regEmp = (RegistreEmploye) reg;
                    break;
                case '2':
                    RegistreProjet regPro = (RegistreProjet) reg;
                    break;
                case '3':
                    RegistreTask regTask = (RegistreTask) reg;
                    break;
                case '4':
                    RegistreSprint regSpri = (RegistreSprint) reg;
                    break;
                case '5':
                    RegistreNotes regNotes = (RegistreNotes) reg;
                    break;

                oos.writeInt(registre.getRegistre().size());//ecrire la taille de la collection
                    for (Object objet : registre.getRegistre()) {
                        oos.writeObject(objet);
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



}
