package utils;

import javax.swing.*;

import java.sql.Date;


@SuppressWarnings("FieldCanBeLocal")

public class Utilitaire {

    public static Date getTodayDate() {
        long miliseconds = System.currentTimeMillis();
        return new Date(miliseconds);
    }

    public static int popupOuiNon(String msg, String msgTitre) {
        return JOptionPane.showConfirmDialog(null, msg, msgTitre,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    }

    public static void verifierIntervalle(int nombre, int min, int max) throws SaisieInvalideException{
        if(nombre < min || nombre > max){
            throw new SaisieInvalideException("L'intervalle doient être entre "+ min+" et "+ max+" semaines.");
        }
    }
    public static void verifierStringVide(String s, int longueur) throws SaisieInvalideException{
        if(s.length()<longueur){
            throw new SaisieInvalideException("Le champs dois contenir au minimum "+ longueur+ " caractere");
        }
    }










}