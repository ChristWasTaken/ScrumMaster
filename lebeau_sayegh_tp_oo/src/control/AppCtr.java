package control;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubContrastIJTheme;
import io.ManipulationFichier;
import model.*;
import ui.FenApp;
import ui.FenListener;
import utils.Constante;
import javax.swing.*;


@SuppressWarnings("FieldCanBeLocal")

public class AppCtr {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatGitHubContrastIJTheme());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        RegistreEmploye employe = new RegistreEmploye();
        RegistreProjet projet = new RegistreProjet();
        RegistreTask regTask = new RegistreTask();
        RegistreSprint registreSprint = new RegistreSprint();

        ManipulationFichier.lire(Constante.REPERTOIRE_PROJET +Constante.nomFichier[3], employe,0);
        ManipulationFichier.lire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[0], projet, 1);
        ManipulationFichier.lire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[1], regTask, 2);
        ManipulationFichier.lire(Constante.REPERTOIRE_PROJET + Constante.nomFichier[2], registreSprint, 3);

        FenApp fenetre;
        fenetre = new FenListener(projet, employe, regTask, registreSprint);
        fenetre.setVisible(true);
    }
}
