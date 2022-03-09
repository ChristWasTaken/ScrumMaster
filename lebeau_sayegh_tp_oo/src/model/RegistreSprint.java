package model;

import java.util.ArrayList;

public class RegistreSprint {
    private ArrayList<Sprint> regSprint;

    public RegistreSprint() {
        this.regSprint = new ArrayList<Sprint>();
    }
    public void ajouterSprint(Sprint sprt){
        this.regSprint.add(sprt);
    }

    public ArrayList<Sprint> getRegSprint() {
        return regSprint;
    }

    public void setRegSprint(ArrayList<Sprint> regSprint) {
        this.regSprint = regSprint;
    }
}
