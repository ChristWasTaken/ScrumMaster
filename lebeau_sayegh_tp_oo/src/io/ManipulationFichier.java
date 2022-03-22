package io;

import model.Employe;
import model.Notes;
import model.Projet;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManipulationFichier {

    public static void lire(String fichier, ArrayList registre) {
        //lire du fichier
        File file = new File(fichier);

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            //lecture du fichier
            String c;
            while ((c = br.readLine()) != null) {
                //System.out.println(c);
                Article article = parseArticle(c);
                //Ajouter objet dans registre
                registre.ajouterArticle(article);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static Object parseObject(String s, int type) throws ParseException {
        //deserialisation
        String[] token = s.split("|");
        switch (type) {
            case '1':
                return new Employe(token[0], token[1], token[2]);
            break;
            case '2':
                int taskID = Integer.parseInt(token[2]);
                return new Notes(token[0], token[1], taskID);
            break;
            case '3':
                int scrumMasterId = Integer.parseInt(token[2]);
                Date dateDebut = new SimpleDateFormat("dd/MM/yyyy").parse(token[3]);
                Date dateFin = new SimpleDateFormat("dd/MM/yyyy").parse(token[4]);
                int dureeSprint = Integer.parseInt(token[5]);
                return new Projet(token[0], token[1],scrumMasterId,dateDebut,dateFin, dureeSprint );
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }


    }

    private static String formerLigne(Object objet) {
        //Serialisation
        return objet.toString();
    }


    public static void ecrire(String fichier, ArrayList registre) {
        //ecrire dans un fichier
        File file = new File(fichier);
        FileWriter fw = null;
        BufferedWriter bw = null;
        String contenu;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for (Object objet : registre.getListing()) {
                contenu = formerLigne(objet);
                bw.write(contenu);
                bw.newLine();
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
