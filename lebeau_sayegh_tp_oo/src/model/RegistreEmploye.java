package model;

import utils.EmployeDejaPresentException;

import java.util.ArrayList;
import java.util.Iterator;

public class RegistreEmploye {
    private ArrayList<Employe> registreEmp;

    public RegistreEmploye() {
        this.registreEmp = new ArrayList<>();
    }

    public void ajouterEmploye(Employe emp) throws EmployeDejaPresentException {
        if (this.verifierDoublon(emp)) {
            throw new EmployeDejaPresentException("Doublon trouvé!!", emp);
        } else {
            this.registreEmp.add(emp);
        }
    }

    public boolean verifierDoublon(Employe emp) {
        Iterator<Employe> var2 = this.registreEmp.iterator();

        Employe tmp;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            tmp = (Employe) var2.next();
        } while (!emp.equals(tmp));

        return true;
    }

    public ArrayList<Employe> getRegistre() {
        return this.registreEmp;
    }
}
