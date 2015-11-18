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
                JOptionPane.showMessageDialog(panneau, "----Bataille Espagnole----\n"
                        + "La bataille espagnole est un jeu de carte qui se joue de 2 à 4 joueurs. Les cartes sont de 4\n" +
"familles différentes : l’or, l’épée, le bâton et la coupe. L’ordre des cartes dans une famille\n" +
"est : As, 3, Roi, Dame, Cavalier, 10, 9, 8, 7, 6, 5, 4, 3, 2\n\n"
                        + "Une partie est constituée de plusieurs “jeux” qui permettent aux joueurs de marquer des\n" +
"points. Le gagnant étant celui qui a le plus de points lorsque la partie se termine. Une partie\n" +
"se joue soit en un nombre de jeux fixé par les joueurs, soit par un nombre de points à\n" +
"atteindre (exemple : fin de la partie lorsqu’un joueur a atteint les 300 points).\n"
                        + "A chaque jeu, une famille est désignée comme étant l’atout. Cette famille a pour particularité\n" +
"d’être plus forte que les autres, c’est à dire que n’importe quelle carte de la famille atout bat\n" +
"n’importe quelle carte d’une des trois autres familles.\n"
                        + "Le choix de la famille atout d’un jeu se passe de la manière suivante : après distribution de 3\n" +
"cartes à chaque joueur, la première carte du tas de cartes restantes est retournée, la famille\n" +
"à laquelle elle appartient est désignée comme atout si l’un des joueurs (interrogés dans le\n" +
"sens des aiguilles d’une montre) le souhaite. Si personne ne souhaite la famille de la carte\n" +
"comme atout, les joueurs peuvent en choisir une autre (là aussi dans l’ordre des aiguilles\n" +
"d’une montre). La carte retournée est placée sous le tas pour être la dernière carte piochée.\n\n"
                        + "Chaque joueur possède 3 cartes (qu’il voit). A chaque tour, il joue une de ses 3 cartes et en\n" +
"pioche une autre dans le tas de cartes non distribuées (tant qu’il en reste dans le tas). Une\n" +
"fois que tous les joueurs ont joué leur tour, la carte la plus forte remporte la bataille et donc\n" +
"le joueur qui l’a jouée prend les cartes du pli (sa carte et celles des autres joueurs) et\n" +
"commencera le tour suivant. Toutes les cartes remportées sont comptabilisée à la fin du jeu\n" +
"(quand toutes les cartes auront été jouées).\n\n"
                        + "Pour définir la carte la plus forte d’un pli :\n"
                        + "\t- s’il" +
"n’y a aucune carte de la famille atout, la carte la plus forte est celle la plus haute\n" +
"dans l’ordre des cartes jouées parmi les cartes de la même famille que la 1ère carte\n" +
"jouée\n"
                        + "\t- s’il" +
"y a au moins une carte de la famille atout, c’est la carte la plus haute dans l’ordre\n" +
"parmi les cartes de la famille atout.", "Règles du jeu", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Action de l'item A propos
        itemCarac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(panneau, "\t\t----Valeurs des cartes----\n\n"
                        + "As : 11 points\n"
                        + "3 : 10 points\n"
                        + "Roi : 4 points\n"
                        + "Dame : 3 points\n"
                        + "Cavalier : 2 points\n"
                        + "10 à 2 : 0 points");
            }
        });

        // Action de l'item A propos
        itemApropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(panneau, "Jeu développé par :\n-Mickaël Turnel\n-Line Pouvaret\n\n", "A propos", JOptionPane.INFORMATION_MESSAGE);
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
