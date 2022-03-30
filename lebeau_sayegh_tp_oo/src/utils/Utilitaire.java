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

    public static void verifierDureeSprint(int duree) throws SaisieInvalideException{
        if(duree < 2 || duree > 12){
            throw new SaisieInvalideException("La durée des sprints doient être entre 2 et 12 semaines.");
        }
    }










}