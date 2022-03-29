package ui;

import javax.swing.*;

public class FenTest {
    public static JMenu menuFichier(JMenuItem miNouveauProj, JMenuItem miChargerProj , JMenuItem miSupprimerProj,
                                    JMenuItem miRetourSelect, JMenuItem miSortir){
        JMenu tmp = new JMenu("Projet");

        tmp.add(miNouveauProj);
        tmp.add(miChargerProj);
        tmp.add(miSupprimerProj);
        tmp.add(miRetourSelect);
        tmp.add(miSortir);
        return tmp;
    }
}
