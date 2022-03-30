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

    public static void popupErreur(String msg, NumberFormatException e){
        JOptionPane.showMessageDialog(null, msg, e.getMessage(), JOptionPane.ERROR_MESSAGE);
    }










}