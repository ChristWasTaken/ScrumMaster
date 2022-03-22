package model;


import utils.SprintDejaPresentException;

import java.util.ArrayList;
import java.util.Iterator;

public class RegistreSprint extends Registre {
    private ArrayList<Sprint> regSprint;

    public RegistreSprint() {
        this.regSprint = new ArrayList<>();
    }

    public void ajouterSprint(Sprint sprint) throws SprintDejaPresentException {
        if (this.verifierDoublon(sprint)) {
            throw new SprintDejaPresentException("Doublon trouvé!!", sprint);
        } else {
            this.regSprint.add(sprint);
        }
    }

    public boolean verifierDoublon(Sprint sprint) {
        Iterator<Sprint> var2 = this.regSprint.iterator();

        Sprint tmp;
        do {
            if (!var2.hasNext()) {
                return false;
            }
            tmp = (Sprint) var2.next();
        } while (!sprint.equals(tmp));

        return true;
    }

    public ArrayList<Sprint> getRegSprint() {
        return regSprint;
    }


}
