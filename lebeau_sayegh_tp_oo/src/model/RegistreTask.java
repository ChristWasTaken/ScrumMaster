package model;


import utils.TaskDejaExistException;

import java.util.ArrayList;

import static model.Task.getNbrTask;
import static model.Task.setNbrTask;

public class RegistreTask extends Registre {
    private ArrayList<Task> registreTasks;

    //constructeur
    public RegistreTask() {
        this.registreTasks = new ArrayList<>();
    }

    //ajouter Task au registre
    public int ajouterTask(Task t, int operation) throws TaskDejaExistException {
        int index = verifierDoublons(t);

        if (index!=-1 && operation==0) {
            throw new TaskDejaExistException("Doublons trouvé", t);
        }else if(index!=-1 &&operation ==1){

            registreTasks.set(index,t);

        } else {
            registreTasks.add(t);
        }
        return index;
    }

    public ArrayList<Task> trierTask(){
        ArrayList<Task> tmpReg = new ArrayList<>();
        for(Task tmp : registreTasks){
            if(tmp.getTaskPriority() != -1){
                tmpReg.add(tmp);
            }
        }
        return tmpReg;
    }

    private int verifierDoublons(Task t) {
        for (Task tmp : registreTasks) {
            if (t.getDescription().equals(tmp.getDescription())) {
                setNbrTask(getNbrTask()-1);
                return getRegistreTasks().indexOf(tmp);
            }
        }
        return -1;
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


    public void effacerTask(int index) {
        registreTasks.remove(index);
    }
}
