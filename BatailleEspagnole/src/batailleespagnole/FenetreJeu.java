package batailleespagnole;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe de base servant à créer et afficher la fenêtre principale de jeu.
 *
 */
public class FenetreJeu {

    /* Mode console */
    public static void menu() {
        System.out.println("==========Menu Principal===========");
        System.out.println("-1- Nouvelle Partie");
    }
    
    /**
     * Créé et affiche la fenêtre principale de jeu.
     *
     * @see PanneauJeu
     */
    private static void afficherMaFenetre() {
        // Création de la JFrame
        JFrame jframe = new JFrame();
        jframe.setTitle("Bataille Espagnole");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocation(IConfig.POSITION_X, IConfig.POSITION_Y);

        // Création de la JMenuBar
        JMenuBar menuBar = new JMenuBar();

        JMenu menuJeu = new JMenu("Jeu");
        menuJeu.setMnemonic(KeyEvent.VK_J);
        menuBar.add(menuJeu);
        final JMenuItem itemNew = new JMenuItem("Nouvelle partie");
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ajout du raccourci CTRL + N
        menuJeu.add(itemNew);
        menuJeu.addSeparator();
        /*
        final JMenuItem itemSave = new JMenuItem("Sauvegarder la partie");
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)); // Ajout du raccourci CTRL + S
        menuJeu.add(itemSave);
        final JMenuItem itemLoad = new JMenuItem("Charger une partie");
        itemLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); // Ajout du raccourci CTRL + O
        menuJeu.add(itemLoad);
        menuJeu.addSeparator();
        */
        final JMenuItem itemQuit = new JMenuItem("Quitter");
        itemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK)); // Ajout du raccourci CTRL + Q
        menuJeu.add(itemQuit);

        // Création du JMenu menuAide
        JMenu menuAide = new JMenu("Aide");
        menuAide.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menuAide);
        final JMenuItem itemRegle = new JMenuItem("Règles du jeu");
        itemRegle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK)); // Ajout du raccourci CTRL + R
        menuAide.add(itemRegle);
        final JMenuItem itemCarac = new JMenuItem("Valeurs des cartes");
        itemCarac.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK)); // Ajout du raccourci CTRL + C
        menuAide.add(itemCarac);
        final JMenuItem itemApropos = new JMenuItem("A propos");
        itemApropos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK)); // Ajout du raccourci CTRL + A
        menuAide.add(itemApropos);

        // Création du JPanel englobant
        JPanel jpe = new JPanel();
        jpe.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        /*
        // Création du JPanel contenant le bouton de fin de tour
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
        final JButton finTour = new JButton("Fin du tour");
        finTour.setEnabled(false);
        p1.add(finTour);
        p1.add(Box.createRigidArea(new Dimension(10, 0)));
        final JLabel nbSoldats = new JLabel("Clique sur nouvelle partie pour lancer le jeu");
        p1.add(nbSoldats);
        */
        
        // Création du JPanel contenant le grille de jeu
        final PanneauJeu panneau = new PanneauJeu();

        // Ajout des JPanels dans le JPanel englobant
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 0;
        jpe.add(panneau, c);

        JPanel nbJoueurs = new JPanel();
        JPanel nbIA = new JPanel();
        JPanel nbJeux = new JPanel();
        JPanel nbPts = new JPanel();
        JPanel nom = new JPanel();
           
        
        // Ajout des JPanel dans la JFrame
        jframe.setJMenuBar(menuBar);
        jframe.getContentPane().add(jpe);

        // Action de l'item Nouvelle partie
        itemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int nbJeuxMax, nbPtsMax, nbJoueurs, nbIA;
                do {
                    nbJoueurs = Integer.parseInt(JOptionPane.showInputDialog(panneau, "Combien de joueurs (2-4) au total ?", "Nombre de joueurs", JOptionPane.QUESTION_MESSAGE));
                } while (nbJoueurs < 2 || nbJoueurs > 4);

                do {
                    nbIA = Integer.parseInt(JOptionPane.showInputDialog(panneau, "Combien de joueurs Ordinateur (0-4) ?", "Nombre d'IA", JOptionPane.QUESTION_MESSAGE));
                } while (nbIA < 0 || nbIA > 4);
                do {
                    nbJeuxMax = Integer.parseInt(JOptionPane.showInputDialog(panneau, "Entrez un nombre de jeux maximum (0=infini)", "Nombre de jeux", JOptionPane.QUESTION_MESSAGE));
                    nbPtsMax = Integer.parseInt(JOptionPane.showInputDialog(panneau, "Entrez un nombre de points maximum (0=infini)", "Nombre de points", JOptionPane.QUESTION_MESSAGE));
                } while (nbJeuxMax == 0 && nbPtsMax == 0);

                Partie p = new Partie(nbJoueurs, nbJeuxMax, nbPtsMax);
                int i;
                /* On demande le nom des vrais joueurs et on les ajoute à la partie */
                for (i = 0; i < nbJoueurs - nbIA; i++) {
                    String nom = (JOptionPane.showInputDialog(panneau, "Nom du joueur n°"+ (i+1), "Joueur n°"+(i+1), JOptionPane.QUESTION_MESSAGE));
                    //System.out.println("Nom du joueur n°" + (i + 1));
                    //String nom = LectureClavier.lireChaine();
                    p.ajoutJoueur(new Joueur(nom, false));
                }

                /* On crée le nombre de joueurs IA nécessaires */
                for (int j = i; j < nbJoueurs; j++) {
                    //System.out.println("Nom du joueur ordinateur n°" + (j + 1));
                    p.ajoutJoueur(new Joueur("Joueur " + (j + 1), true));
                }
                p.lancerPartie();

                //panneau.nouvellePartie();
            }
        });

        /*
        // Action de l'item Sauvegarder
        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panneau.sauvegarder();
            }
        });

        // Action de l'item Charger
        itemLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panneau.charger();
            }
        });
        
        
        // Action du bouton de fin de tour
        finTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panneau.finTour();
            }
        });

        // Ajout de action d'un raccourci clavier sur le bouton
        finTour.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    panneau.finTour();
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        */
        
        itemQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        // Action de l'item Règle du jeu
        itemRegle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(panneau, "TODO", "Règles du jeu", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Action de l'item A propos
        itemCarac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(panneau, "TODO");
            }
        });

        // Action de l'item A propos
        itemApropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(panneau, "Jeu développé par :\n-Mickaël Turnel\n-Line Pouvaret\n\nTODO", "A propos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Affichage de la fenêtre
        jframe.pack();
        jframe.setVisible(true);
    }

    /**
     * Méthode appelée lors de l'éxécution du projet.
     *
     * @param args
     */
    public static void main(String[] args) {
        /*
        try {
            Carte.chargementImages();
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement des images !");
        }
                */

        try {
            // Ajouter le look du système d'exploitation utilisé
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erreur lors de la mise en place du look de l'OS courant !");
        } catch (InstantiationException ex) {
            System.out.println("Erreur lors de la mise en place du look de l'OS courant !");
        } catch (IllegalAccessException ex) {
            System.out.println("Erreur lors de la mise en place du look de l'OS courant !");
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Erreur lors de la mise en place du look de l'OS courant !");
        }

        afficherMaFenetre();
        
        /* Mode console */
        // TODO code application logic here
        /*
        menu();
        int choix = LectureClavier.lireEntier("Que voulez-vous faire ?");
        int nbJeuxMax, nbPtsMax, nbJoueurs, nbIA;
        switch (choix) {
            case 1:
                do {
                    nbJoueurs = LectureClavier.lireEntier("Combien de joueurs (2-4) au total?");
                } while (nbJoueurs < 2 || nbJoueurs > 4);

                do {
                    nbIA = LectureClavier.lireEntier("Combien de joueurs Ordinateur (0-4)?");
                } while (nbIA < 0 || nbIA > 4);
                do {
                    nbJeuxMax = LectureClavier.lireEntier("Entrez un nombre de jeux maximum (0=infini)");
                    nbPtsMax = LectureClavier.lireEntier("Entrez un nombre de points maximum (0=infini)");
                } while (nbJeuxMax == 0 && nbPtsMax == 0);

                Partie p = new Partie(nbJoueurs, nbJeuxMax, nbPtsMax);
                int i;
        */
                /* On demande le nom des vrais joueurs et on les ajoute à la partie 
        /*
                for (i = 0; i < nbJoueurs - nbIA; i++) {
                    System.out.println("Nom du joueur n°" + (i + 1));
                    String nom = LectureClavier.lireChaine();
                    p.ajoutJoueur(new Joueur(nom, false));
                }
        */
                /* On crée le nombre de joueurs IA nécessaires */
        /*
                for (int j = i; j < nbJoueurs; j++) {
                    System.out.println("Nom du joueur ordinateur n°" + (j + 1));
                    p.ajoutJoueur(new Joueur("Joueur " + (j + 1), true));
                }
                p.lancerPartie();
            default:
                break;
                
        }
        */
        
    }
}
