package io;

import java.io.*;

public class ManipulationFichier {

    public static void lire(String fichier, RegistreArticle registre) {
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
    public static Article parseArticle(String s) {
        //deserialisation
        String[] token = s.split(" ");
        int qte = Integer.parseInt(token[1]);
        double prix = Double.parseDouble(token[2]);
        return new Article(token[0], qte, prix);
    }
    public static void ecrire(String fichier, RegistreArticle registre) {
        //ecrire dans un fichier
        File file = new File(fichier);
        FileWriter fw = null;
        BufferedWriter bw = null;
        String contenu;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for (Article article : registre.getListing()) {
                contenu = formerLigne(article);
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
