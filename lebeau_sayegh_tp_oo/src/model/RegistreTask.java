package model;


import utils.TaskDejaExistException;

import java.io.Serializable;
import java.util.ArrayList;

public class RegistreTask extends Registre {
    private ArrayList<Task> registreTasks;

    //constructeur
    public RegistreTask() {
        this.registreTasks = new ArrayList<>();
    }

    //ajouter Task au registre
    public void ajouterTask(Task t) throws TaskDejaExistException {
        if (verifierDoublons(t)) {
            throw new TaskDejaExistException("Doublons trouvé", t);
        } else {
            registreTasks.add(t);
        }
    }

    private boolean verifierDoublons(Task t) {
        for (Task tmp : registreTasks) {
            if (t.getDescription().equals(tmp.getDescription())) {
                return true;
            }
        }
        return false;
    }

    //afficher registre
    public void afficherRegistreTask() {
        for (Task tmp : registreTasks) {
            System.out.println(tmp);
        }
    }

    public ArrayList<Task> getRegistreTasks() {
        return registreTasks;
    }

    public void setRegistreTasks(ArrayList<Task> registreTasks) {
        this.registreTasks = registreTasks;
    }



}
