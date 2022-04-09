package utils;

import model.Projet;
import model.RegistreProjet;
import model.RegistreSprint;
import model.Sprint;

import javax.swing.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;


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

    public static int calculerNombreSemaine(LocalDate dateDebut, LocalDate dateFin){

        return (int)(ChronoUnit.DAYS.between(dateDebut, dateFin))/7;
    }

    public static int calculerNombreSprint(int nbrSem, int dureeSprint){
        return nbrSem/dureeSprint;
    }

    public static boolean calculerMaxSprint(int size,int nombreSemaine ){
        boolean flag= size <nombreSemaine;
        System.out.println(size +"size AND nombresemaine"+nombreSemaine);
        return flag  ;

    }










}