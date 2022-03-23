package model;



import java.util.ArrayList;

public class RegistreNotes extends Registre {
    private ArrayList<Notes> registreNotes;

    public RegistreNotes() {
        this.registreNotes = new ArrayList<>();
    }



    public void ajouterNotes(Notes note) {
        this.registreNotes.add(note);
    }

    public ArrayList<Notes> getRegistreNotes() {
        return registreNotes;
    }

}
