package model;

import utils.DoublonException;

import java.util.ArrayList;

public class RegistreTask extends Registre {
    private ArrayList<Task> registreTasks;

    //constructeur
    public RegistreTask() {
        this.registreTasks = new ArrayList<>();
    }

    //ajouter Task au registre
    public int ajouterTask(Task t, int operation) throws DoublonException {
        int index = verifierDoublons(t);

        if (index!=-1 && operation==0) {
            throw new DoublonException("Doublons trouvé", t,2);
        }else if(index!=-1 &&operation ==1){
            registreTasks.set(index,t);
        } else {
            registreTasks.add(t);
        }
        return index;
    }

    public ArrayList<Task> chercherTaskList(ArrayList<Integer> tmpList, int option){
        ArrayList<Task> tmpReg = new ArrayList<>();

        switch (option) {
            case 0 -> {
                // Ajoute tout les task present dans tmplist au tmpReg
                for (Task tmp : registreTasks) {
                    for (int i : tmpList) {
                        if (tmp.getTaskID() == i) {
                            tmpReg.add(tmp);
                        }
                    }
                }
                for (Task emp : tmpReg) {
                }
                break;
            }
            case 1 -> {

                if (tmpList.size() == 0) {
                    for (Task tmp : registreTasks) {
                        tmpReg.add(tmp);
                    }
                } else {
                    // Ajoute tout les task qui ne sont pas présent dans tmpList au tmpReg
                    for (Task tmp : registreTasks) {

                        boolean flag = false;
                        for (int i : tmpList) {
                            if(tmp.getTaskID() == i){
                                flag = true;
                            }
                        }
                        if(!flag){
                            tmpReg.add(tmp);
                        }
                    }
                }
            }
        }
        return tmpReg;
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
