package control;

import ui.FenGui;

import javax.swing.*;

public class AppCtr {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FenGui fenetre = new FenGui();
        fenetre.setSize(400,300);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);

        fenetre.setVisible(true);
    }
}
