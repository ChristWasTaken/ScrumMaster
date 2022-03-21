package model;

import utils.ProjetDejaPresentException;
import utils.TaskDejaExistException;

import java.util.ArrayList;

public class RegistreProjet {
    ArrayList<Projet> registre;

    public RegistreProjet(ArrayList<Projet> registre) {
        this.registre = new ArrayList<>();
    }

    //ajouter Projet au registre
    public void ajouterProjet(Projet projet) throws ProjetDejaPresentException {
        if (verifierDoublons(projet)) {
            throw new ProjetDejaPresentException("Doublons trouvé", projet);
        } else {
            registre.add(projet);
        }
    }

    private boolean verifierDoublons(Projet projet) {
        for (Projet tmp : registre) {
            if (projet.getDescription().equals(tmp.getDescription())) {
                return true;
            }
        }
        return false;
    }

}
