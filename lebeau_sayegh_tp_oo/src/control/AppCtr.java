package control;

import io.ManipulationFichier;
import model.*;
import ui.FenProjet;
import ui.FenSelectProjet;
import ui.FenSprint;
import ui.FenTask;
import utils.Constante;
import utils.SprintDejaPresentException;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubContrastIJTheme;
import utils.TaskDejaExistException;


import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class AppCtr {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatGitHubContrastIJTheme() );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        RegistreProjet projet = new RegistreProjet();

        FenSelectProjet fenetreSelect = new FenSelectProjet(projet);
        fenetreSelect.setVisible(true);

        FenTask fenetreTask = new FenTask();
        fenetreTask.setVisible(true);

        FenProjet fenProjet = new FenProjet(projet);
        fenProjet.setVisible(true);

    }
}
