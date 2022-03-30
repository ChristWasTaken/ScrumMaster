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
    // Filtre le registre employé par poste et retourne un registre temporaire avec la nouvelle liste
    public RegistreEmploye listeEmployeParPoste(RegistreEmploye registreEmploye, int posteId){
        RegistreEmploye tmpReg = new RegistreEmploye();

        for (Employe tmp : registreEmploye.getRegistreEmp()) {

            if(tmp.getPoste().equals(Constante.POSTES[posteId])){
                try {
                    tmpReg.ajouterEmp(tmp);
                } catch (EmployeDejaPresentException e) {
                    e.printStackTrace();
                }
            }
        }
        return tmpReg;
    }


    public ArrayList<Employe> getRegistreEmp() {
        return registreEmp;
    }
}
