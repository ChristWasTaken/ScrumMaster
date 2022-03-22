package model;

import utils.MonInter;

import java.util.ArrayList;

public class RegistreNotes implements MonInter {
    private ArrayList<Notes> registreNotes;

    public RegistreNotes(ArrayList<Notes> registreNotes) {
        this.registreNotes = new ArrayList<>();
    }



    public void ajouterNotes(Notes note) {
        this.registreNotes.add(note);
    }
    @Override
    public ArrayList<Notes> getRegistre() {
        return this.registreNotes;
    }
}
