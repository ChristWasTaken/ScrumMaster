package model;


import utils.DoublonException;

import java.util.ArrayList;
import java.util.Iterator;

public class RegistreSprint extends Registre {
    private ArrayList<Sprint> regSprint;

    public RegistreSprint() {
        this.regSprint = new ArrayList<>();
    }

    public void ajouterSprint(Sprint sprint) throws DoublonException {
        int index = verifierDoublon(sprint);
        if ( index != -1) {
            this.regSprint.set(index, sprint);
        } else {
            this.regSprint.add(sprint);
        }
    }

    public void supprimerSprint(int index){
        regSprint.remove(index);
    }

    public int verifierDoublon(Sprint sprint) {
        for(Sprint tmp : regSprint) {
            if (sprint.getDateDebut().equals(tmp.getDateDebut())){
                return getRegSprint().indexOf(tmp);
            }
        }
        return -1;
    }

    public ArrayList<Integer> rechercheTasksSprint(){
        ArrayList<Integer> tmpList = new ArrayList<>();
        for(Sprint tmp : regSprint){
            for(int emp : tmp.getTaskID()){
                tmpList.add(emp);
            }
        }
        return tmpList;
    }

    public ArrayList<Sprint> getRegSprint() {
        return regSprint;
    }


}
