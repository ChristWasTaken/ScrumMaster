package ui;

import javax.swing.*;
import java.awt.*;


public class FenProjet extends JFrame {

    JMenu menu, submenu;
    JMenuItem menuItem;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;

    //Méthode pour création de la fenêtre
    public FenProjet(String title){
        super(title);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

//        Container ControlHost = getContentPane();
//        ControlHost.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Sample 03: Create Menus & add to Menubar
        JMenu mnuFile = new JMenu("File");
        JMenu mnuEdit = new JMenu("Edit");
        menuBar.add(mnuFile);
        menuBar.add(mnuEdit);

        //Sample 04: Create Menu Items
        //4a: Menu Items for File
        JMenuItem miFileOpen = new JMenuItem("Open");
        JMenuItem miFileSave = new JMenuItem("Save");
        JSeparator sep1 = new JSeparator();
        JMenuItem miFileExit = new JMenuItem("Exit");
        mnuFile.add(miFileOpen);
        mnuFile.add(miFileSave);
        mnuFile.add(sep1);
        mnuFile.add(miFileExit);

        //4b: Menu Items for Edit
        JMenuItem miEditCut = new JMenuItem("Cut");
        JMenuItem miEditCopy = new JMenuItem("Copy");
        JMenuItem miEditPaste = new JMenuItem("Paste");
        mnuEdit.add(miEditCut);
        mnuEdit.add(miEditCopy);
        mnuEdit.add(miEditPaste);

        //Sample 05: Handle Clicked Menu Items
//        miFileOpen.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(
//                        JMenuSimple.this, "Opening the File...");
//            }
//        });
    }
}
