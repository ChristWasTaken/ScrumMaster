package ui;

import io.ManipulationFichier;
import model.*;
import utils.DoublonException;
import utils.SaisieInvalideException;
import utils.Utilitaire;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static utils.Constante.REPERTOIRE_PROJET;
import static utils.Constante.nomFichier;
import static utils.Utilitaire.calculerMaxSprint;
import static utils.Utilitaire.verifierStringVide;

public class FenListener extends FenApp {

    public FenListener(RegistreProjet projet, RegistreEmploye employe, RegistreTask task, RegistreSprint sprint) {
        super(projet, employe, task, sprint);
        setListeners();
    }

    // *** Bouton de la toolbar ***
    // Retour à la selection de projet

    public void setListeners() {

        btnRetour.addActionListener(e -> {
            if (currentCard != 1) {
                int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.",
                        "Retour au menu précedant?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (currentCard == 2 || currentCard == 3) {
                        consoleTxtArea.append("Retour à la selection de projet.\n");
                        carteSelectionProjet(registreProjet, registreEmploye);
                    } else if (currentCard == 4 || currentCard == 5 || currentCard == 6 || currentCard == 7) {
                        consoleTxtArea.append("Retour à la gestion de projet.\n");
                        carteProjetEnCours(registreProjet, registreTask, registreEmploye, registreSprint);
                    }
                }
            } else {
                consoleTxtArea.append("Aucun retour en arrière possible de cette fenêtre.\n");
            }
        });
        // Créer un nouveau projet - formulaire
        btnNouveauProjet.addActionListener(a -> {
            if (currentCard != 2) {
                // Initialise la mise en page et les paramètres de la carte
                carteNouveauProjet(registreEmploye);
            } else {
                int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.",
                        "Nouveau projet?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    carteNouveauProjet(registreEmploye);
                }
            }
        });
        // Créer un Nouveau Task - formulaire
        btnNouveauTask.addActionListener(e -> {
            if (currentCard != 4) {
                carteNouvelleTask(registreEmploye);
            } else {
                int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.",
                        "Retour vers le projet?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    carteNouvelleTask(registreEmploye);
                }
            }
        });
        // Créer un Nouveau Sprint - formulaire
        btnNouveauSprint.addActionListener(e -> {
            LocalDate dateDebut = LocalDate.parse(ftxtDateDebut.getText());
            LocalDate dateFin = LocalDate.parse(ftxtDateFin.getText());
            // Recoit le nombre de sprint de la durée spécifié
            int nbrSemaine = Utilitaire.calculerNombreSemaine(dateDebut, dateFin);
            if (currentCard != 6) {
                if (calculerMaxSprint(registreSprint.getRegSprint().size(), Utilitaire.calculerNombreSprint(nbrSemaine, Integer.parseInt(txtDureeSprint.getText())))) {
                    reinitialiserFormSprint();
                    carteNouveauSprint(registreSprint, registreTask, registreEmploye);
                } else {
                    JOptionPane.showMessageDialog(null, "Vous avez creer le maximum de Sprint", "Trop de sprint",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                int result = JOptionPane.showConfirmDialog(null, "Les données non sauvegardées seront perdues.",
                        "Retour vers le projet?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    reinitialiserFormSprint();
                    carteNouveauSprint(registreSprint, registreTask, registreEmploye);
                }
            }
        });
        //ajouter un task au sprint courant
        btnAjouterTaskSprint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSelectedtask = tblTask.getSelectedRow();
                int selectedTaskID = (Integer) tblTask.getValueAt(indexSelectedtask, 0);

                if (indexSelectedtask != -1) {
                    boolean flag = false;
                    for (int i = 0; i < assignedTaskList.size(); i++) {
                        if (selectedTaskID == assignedTaskList.get(i)) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        assignedTaskList.add(selectedTaskID);
                        presentSprintTaskList.add(selectedTaskID);
                        reinitialiserTables(registreTask, registreEmploye);
                        consoleTxtArea.append("Tache ajouté au sprint\n");
                    }
                }
            }
        });
        //retirer un task du sprint courant
        btnRetirerTaskSprint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSelectedtask = tblTaskSelection.getSelectedRow();
                int selectedTaskID = (Integer) tblTaskSelection.getValueAt(indexSelectedtask, 0);
                if (selectedTaskID != -1) {
                    for (int i = 0; i < assignedTaskList.size(); i++) {
                        if (selectedTaskID == assignedTaskList.get(i)) {
                            assignedTaskList.remove(i);
                            presentSprintTaskList.remove(indexSelectedtask);
                            tableModel2.setRowCount(0);
                            tableModel4.setRowCount(0);
                            ArrayList<Task> tmpTask = registreTask.chercherTaskList(assignedTaskList, 1);
                            remplirTableTaskSprint(tableModel2, tmpTask, consoleTxtArea, registreEmploye);
                            tmpTask = registreTask.chercherTaskList(presentSprintTaskList, 0);
                            remplirTableTaskSprint(tableModel4, tmpTask, consoleTxtArea, registreEmploye);
                            consoleTxtArea.append("Tache retiré du sprint\n");
                        }
                    }
                }
            }
        });

        // Charger un projet
        btnChargerProjet.addActionListener(e -> {
            int i = tblProjet.getSelectedRow();
            // Enregistrer l'index de la selection
            if (i != -1) {
                indexProjetEnCours = tblProjet.getSelectedRow();
                // Initialise la mise en page et les paramètres de la carte
                carteProjetEnCours(registreProjet, registreTask, registreEmploye, registreSprint);
                consoleTxtArea.append("Chargement du projet complété avec succès.\n");
            } else {
                JOptionPane.showMessageDialog(null, "La ligne du projet doit être correctement selectionné pour " +
                        "pouvoir le charger.\n");
            }
        });
        // Modifier un Task
        btnModifierTask.addActionListener(e -> {
            //enregistrer l'index de la tache choisi
            indexTaskEnCours = tblTask.getSelectedRow();
            //Initialise la mise en page et les parametre de la carte
            if (indexTaskEnCours != -1) {
                carteModifierTask(registreTask, registreEmploye);
                consoleTxtArea.append("Chargement de la tache complété avec succès.\n");
            } else {
                consoleTxtArea.append("La ligne de la tache doit être correctement selectionné pour pouvoir la " +
                        "modifier.\n");
            }
        });
        // Modifier un sprint
        btnModifierSprint.addActionListener(e -> {
            // Enregistre l'index registre du sprint sélectionné
            indexSprintEnCours = tblSprint.getSelectedRow();
            //valide la selection d'un sprint
            if (indexSprintEnCours != -1) {
                carteModifierSprint(registreSprint, registreTask, registreEmploye);
                consoleTxtArea.append("Chargement du sprint complété avec succès. \n");
            } else {
                JOptionPane.showMessageDialog(null, "La ligne du sprint doit être correctement selectionné pour " +
                        "pouvoir le charger.\n");
            }
        });
        // Supprimer un projet
        btnDeleteProjet.addActionListener(e -> {
            int index = tblProjet.getSelectedRow();
            if (index != -1) {
                int result = Utilitaire.popupOuiNon("La suppression des projets est final. Êtes-vous sur?",
                        "Suppression de projet");
                if (result == JOptionPane.YES_OPTION) {

                    int i = tblProjet.getSelectedRow();
                    ManipulationFichier.effacerFichiersProjet(registreProjet.getRegistrePro().get(i).getNomProjet(),
                            consoleTxtArea);
                    registreProjet.effacerProjet(i);
                    ManipulationFichier.ecrire(REPERTOIRE_PROJET + nomFichier[0], registreProjet, 1);
                    tableModel.removeRow(i);
                    consoleTxtArea.append("Suppression du projet complété.\n");
                } else {
                    consoleTxtArea.append("Suppression du projet annulée.\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La ligne du projet doit être correctement selectionné pour " +
                        "pouvoir le supprimer.\n");
            }
        });
        //  Supprimer Task
        btnDeleteTask.addActionListener(e -> {
            int i = tblTask.getSelectedRow();

            if (i != -1) {
                int result = Utilitaire.popupOuiNon("La suppression des taches est final. Êtes-vous sur?",
                        "Suppression de tache");
                if (result == JOptionPane.YES_OPTION) {
                    ArrayList<Task> tmpTask = registreTask.trierTask();
                    tmpTask.get(i).setTaskPriority(-1);
                    tableModel2.removeRow(i);
                    ManipulationFichier.ecrire(REPERTOIRE_PROJET + txtNomProjet.getText() + nomFichier[1],
                            registreTask, 2);
                    consoleTxtArea.append("Suppression de la tache complété.\n");
                } else {
                    consoleTxtArea.append("Suppression de la tache annulée.\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La ligne de la tache doit être correctement selectionné pour" +
                        " pouvoir le supprimer.\n");
            }
        });
        // Supprimer un projet
        btnDeleteSprint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tblSprint.getSelectedRow();

                if (i != -1) {
                    int result = Utilitaire.popupOuiNon("La suppression d'un sprint est final. Êtes-vous sur?",
                            "Suppression de sprint");
                    if (result == JOptionPane.YES_OPTION) {
                        registreSprint.supprimerSprint(i);
                        tableModel3.removeRow(i);
                        ManipulationFichier.ecrire(REPERTOIRE_PROJET + txtNomProjet.getText() + nomFichier[2],
                                registreSprint, 3);
                        consoleTxtArea.append("Suppression du sprint complété.\n");
                    } else {
                        consoleTxtArea.append("Suppression du sprint annulée.\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La ligne du sprint doit être correctement" +
                            " selectionné pour pouvoir le supprimer.\n");
                }
            }
        });

        //***** Enregistrement de formulaire *****

        // Sauvegarder Formulaire Projet
        btnEnregistrerProjet.addActionListener(e -> {
            try {
                Employe temp = (Employe) jcbEmploye.getSelectedItem();
                Projet tempProj = null;
                if (temp != null) {
                    int duree = Integer.parseInt(txtDureeSprint.getText());
                    Utilitaire.verifierIntervalle(duree, 2, 12);
                    tempProj = new Projet(txtNomProjet.getText(), txtDescProjet.getText(), temp.getEmployeID(),
                            format.parse(ftxtDateDebut.getText()), format.parse(ftxtDateFin.getText()), duree);
                }
                if (currentCard == 2) {
                    if (registreProjet.ajouterProjet(tempProj, 0) == -1) {
                        ManipulationFichier.nouveauProjet(txtNomProjet.getText(), consoleTxtArea);
                        ManipulationFichier.ecrire(REPERTOIRE_PROJET + nomFichier[0], registreProjet, 1);
                        consoleTxtArea.append("Nouveau projet créer. Retourner à la page " +
                                "précédente pour lui accèder.\n");
                    }
                } else if (currentCard == 3) {
                    if (registreProjet.ajouterProjet(tempProj, 1) != -1) {
                        ManipulationFichier.ecrire(REPERTOIRE_PROJET + nomFichier[0], registreProjet, 1);
                        consoleTxtArea.append("Les changements au projet: " + txtNomProjet.getText() +
                                " sont sauvegardés.\n");
                    } else {
                        ManipulationFichier.nouveauProjet(txtNomProjet.getText(), consoleTxtArea);
                        ManipulationFichier.ecrire(REPERTOIRE_PROJET + nomFichier[0], registreProjet, 1);
                    }
                }
            } catch (ParseException | DoublonException ex) {
                ex.printStackTrace();
            } catch (SaisieInvalideException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur de saisie.", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

            afficherDonneeSprint();
        });
        // Sauvegarder le formulaire Task
        btnEnregistrerTask.addActionListener(e -> {

            try {
                verifierStringVide(txtDescTask.getText(), 5);
                if (currentCard == 4) {
                    Task tempTask = new Task(Integer.parseInt(String.valueOf(jcbTask.getSelectedItem())),
                            txtDescTask.getText(), jcbEmploye2.getSelectedIndex());
                    if (registreTask.ajouterTask(tempTask, 0) == -1) {
                        ManipulationFichier.ecrire(REPERTOIRE_PROJET + txtNomProjet.getText() + nomFichier[1],
                                registreTask, 2);
                        consoleTxtArea.append("Tache enregistrer dans le registre avec succès.\n");
                    }
                } else if (currentCard == 5) {
                    Task tempTask = registreTask.getRegistreTasks().get(indexTaskEnCours);
                    tempTask.setTaskPriority(Integer.parseInt(String.valueOf(jcbTask.getSelectedItem())));
                    tempTask.setEmployeID(jcbEmploye2.getSelectedIndex());
                    if (registreTask.ajouterTask(tempTask, 1) != -1) {
                        ManipulationFichier.ecrire(REPERTOIRE_PROJET + txtNomProjet.getText() + nomFichier[1],
                                registreTask, 2);
                        consoleTxtArea.append("Tache enregistrer dans le registre avec succès.\n");
                    }
                }
            } catch (DoublonException ex) {
                consoleTxtArea.append("Erreur, doublons présent\n");
                ex.printStackTrace();
            } catch (SaisieInvalideException ex1) {
                JOptionPane.showMessageDialog(null, ex1.getMessage(), "Erreur de saisie.", JOptionPane.ERROR_MESSAGE);
                ex1.printStackTrace();
            }
        });
        // Sauvegarder un projet
        btnEnregistrerSprint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate dateDebut = LocalDate.parse(ftxtDateDebut.getText());
                LocalDate dateFin = LocalDate.parse(ftxtDateFin.getText());
                // Recoit le nombre de sprint de la durée spécifié
                int nbrSemaine = Utilitaire.calculerNombreSemaine(dateDebut, dateFin);
                try {
                    if (currentCard == 6 || currentCard == 7) {
                        if (calculerMaxSprint(registreSprint.getRegSprint().size(), Utilitaire.calculerNombreSprint(nbrSemaine, Integer.parseInt(txtDureeSprint.getText())))) {
                            String tmpDesc = txtDescSprint.getText();
                            Date tmpdateDebut = format.parse(ftxtDateDebutSprint.getText());
                            Calendar tmpCal = Calendar.getInstance();
                            tmpCal.setTime(tmpdateDebut);
                            tmpCal.add(Calendar.WEEK_OF_MONTH, registreProjet.getRegistrePro().get(indexProjetEnCours).getDureeSprint());
                            Date tmpDateFin = tmpCal.getTime();
                            int tmpProg = jcbProgres.getSelectedIndex();
                            Sprint tmpSprint = new Sprint(presentSprintTaskList, tmpDesc, tmpdateDebut, tmpDateFin, tmpProg);
                            registreSprint.ajouterSprint(tmpSprint);
                            ManipulationFichier.ecrire(REPERTOIRE_PROJET + txtNomProjet.getText() + nomFichier[2],
                                    registreSprint, 3);
                        } else {
                            JOptionPane.showMessageDialog(null, "Vous avez creer le maximum de Sprint", "Trop de sprint",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (ParseException | DoublonException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // *** Choix du menu ***
        miSortir.addActionListener(e ->
        {
            int result = Utilitaire.popupOuiNon("Voulez vous vraiment Quitter?", "Quitter?");
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        miConsole.addActionListener(e ->
        {
            if (scPaneConsole.isVisible()) {
                scPaneConsole.setVisible(false);
                setVisible(true);
            } else if (!scPaneConsole.isVisible()) {
                scPaneConsole.setVisible(true);
                setVisible(true);
            }
        });
    }
}
