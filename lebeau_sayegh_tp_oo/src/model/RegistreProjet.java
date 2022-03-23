package model;


import utils.ProjetDejaPresentException;

import java.util.ArrayList;

public class RegistreProjet extends Registre {
    ArrayList<Projet> registreProjet;

    public RegistreProjet() {
        this.registreProjet= new ArrayList<>();
    }

    //ajouter Projet au registre
    public void ajouterProjet(Projet projet) throws ProjetDejaPresentException {
        if (verifierDoublons(projet)) {
            throw new ProjetDejaPresentException("Doublons trouvé", projet);
        } else {
            registreProjet.add(projet);
        }
    }

    private boolean verifierDoublons(Projet projet) {
        for (Projet tmp : registreProjet) {
            if (projet.getDescription().equals(tmp.getDescription())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Projet> getRegistrePro() {
        return registreProjet;
    }
}
