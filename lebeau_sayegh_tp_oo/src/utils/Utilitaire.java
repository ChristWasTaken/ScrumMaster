package utils;


import java.io.File;
import java.io.IOException;
import java.sql.Date;
@SuppressWarnings("FieldCanBeLocal")

public class Utilitaire {

    public static Date getTodayDate() {
        long miliseconds = System.currentTimeMillis();
        return new Date(miliseconds);
    }


}