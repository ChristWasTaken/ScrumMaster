package model;

import utils.DoublonException;

import java.util.ArrayList;

public class RegistreProjet extends Registre {
    ArrayList<Projet> registreProjet;

    public RegistreProjet() {
        this.registreProjet= new ArrayList<>();
    }

    //ajouter Projet au registre
    public int ajouterProjet(Projet projet, int operation) throws DoublonException {
        int index = verifierDoublons(projet);
        if (index != -1 && operation == 0) {
            throw new DoublonException("Doublons trouvé", projet,1);
        } else if (index != -1  && operation == 1){
            registreProjet.set(index, projet);
        } else {
            registreProjet.add(projet);
        }
        return index;
    }

    public void effacerProjet(int index){
        registreProjet.remove(index);
    }

    public int verifierDoublons(Projet projet) {
        for (Projet tmp : registreProjet) {
            if (projet.getNomProjet().equals(tmp.getNomProjet())) {
                return getRegistrePro().indexOf(tmp);
            }
        }
        return -1;
    }

    public ArrayList<Projet> getRegistrePro() {
        return registreProjet;
    }
}
