package utils;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
@SuppressWarnings("FieldCanBeLocal")

public class Utilitaire {

    public static Date getTodayDate() {
        long miliseconds = System.currentTimeMillis();
        return new Date(miliseconds);
    }

    public static int popupOuiNon(String msg, String msgTitre){
        int result = JOptionPane.showConfirmDialog(null, msg, msgTitre,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return result;
    }


}