package model;


import utils.Constante;
import utils.EmployeDejaPresentException;

import java.util.ArrayList;
import java.util.Iterator;


public class RegistreEmploye extends Registre {
    private ArrayList<Employe> registreEmp;

    public RegistreEmploye() { this.registreEmp = new ArrayList<>(); }


    public void ajouterEmp(Employe emp) throws EmployeDejaPresentException {
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

    public RegistreEmploye getScrumMaster(RegistreEmploye registreEmploye){
        RegistreEmploye tmpReg = new RegistreEmploye();

        int index = 0;
        for (Employe tmp : registreEmploye.getRegistreEmp()) {

            if(tmp.getPoste().equals(Constante.POSTES[0])){
                try {
                    tmpReg.ajouterEmp(tmp);
                } catch (EmployeDejaPresentException e) {
                    e.printStackTrace();
                }
            }
            index++;
        }
        return tmpReg;
    }


    public ArrayList<Employe> getRegistreEmp() {
        return registreEmp;
    }
}
