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

    public void effacerProjet(int index){
        registreProjet.remove(index);
    }

    private boolean verifierDoublons(Projet projet) {
        for (Projet tmp : registreProjet) {
            if (projet.getNomProjet().equals(tmp.getNomProjet())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Projet> getRegistrePro() {
        return registreProjet;
    }
}
