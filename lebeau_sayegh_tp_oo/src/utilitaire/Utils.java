package utilitaire;


import java.sql.Date;

public class Utils {

    public static Date getTodayDate() {
        long miliseconds = System.currentTimeMillis();
        return new Date(miliseconds);
    }
}