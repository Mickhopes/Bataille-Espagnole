package batailleespagnole;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe de base servant à créer et afficher la carte.
 *
 * @see Carte
 */
public class PanneauJeu extends JPanel implements MouseListener, MouseMotionListener {

    /**
     * L'énumération servant à définir l'état du panneau.
     */
    private enum Etat {

        EN_ATTENTE, EN_COURS, POSITION
    };
    /**
     * L'état du panneau.
     */
    private Etat etat;
    /**
     * La position sur laquelle l'utilisateur clique.
     */
    private Position clicked;
    /**
     * La position sur laquelle la souris se trouve.
     */
    private Position over;
     /**
     * L'image du tapis.
     */
    private static BufferedImage tapis;

    /**
     * Constructeur par défaut de PanneauJeu.
     */
    public PanneauJeu() throws IOException {
        setPreferredSize(new Dimension(IConfig.LARGEUR, IConfig.HAUTEUR));
        addMouseListener(this);
        addMouseMotionListener(this);
        over = null;
        etat = Etat.EN_ATTENTE;
        chargementImages();
    }
    
    /**
     * Charge toutes les images nécessaires au jeu.
     *
     * @throws IOException
     */
    public static void chargementImages() throws IOException {
        tapis = ImageIO.read(new File("images/Tapis.png"));
    }

    /**
     * Relance une nouvelle partie.
     */
    public void nouvellePartie() {
        etat = Etat.EN_COURS;
        
        int nbJeuxMax, nbPtsMax, nbJoueurs, nbIA;
        do {
            nbJoueurs = Integer.parseInt(JOptionPane.showInputDialog(this, "Combien de joueurs (2-4) au total ?", "Nombre de joueurs", JOptionPane.QUESTION_MESSAGE));
        } while (nbJoueurs < 2 || nbJoueurs > 4);

        do {
            nbIA = Integer.parseInt(JOptionPane.showInputDialog(this, "Combien de joueurs Ordinateur (0-4) ?", "Nombre d'IA", JOptionPane.QUESTION_MESSAGE));
        } while (nbIA < 0 || nbIA > 4);
        do {
            nbJeuxMax = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez un nombre de jeux maximum (0=infini)", "Nombre de jeux", JOptionPane.QUESTION_MESSAGE));
            nbPtsMax = Integer.parseInt(JOptionPane.showInputDialog(this, "Entrez un nombre de points maximum (0=infini)", "Nombre de points", JOptionPane.QUESTION_MESSAGE));
        } while (nbJeuxMax == 0 && nbPtsMax == 0);

        Partie p = new Partie(nbJoueurs, nbJeuxMax, nbPtsMax);
        int i;
        /* On demande le nom des vrais joueurs et on les ajoute à la partie */
        for (i = 0; i < nbJoueurs - nbIA; i++) {
            String nom = (JOptionPane.showInputDialog(this, "Nom du joueur n°"+ (i+1), "Joueur n°"+(i+1), JOptionPane.QUESTION_MESSAGE));
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
        
        this.over = null;
        refresh();
    }

    /**
     * Termine la partie en cours.
     */
    public void finPartie() {
        etat = Etat.EN_ATTENTE;
        
        /* Finit partie */
        
        repaint();
        over = null;
    }

    /**
     * Sauvegarde la partie actuelle.
     *
     * Cette méthode ouvre une fenêtre de dialogue pour demander à l'utilisateur
     * ou enregistrer le fichier et à quel nom.
     */
    /*
    public void sauvegarder() {
        try {
            JFileChooser browse = new JFileChooser(new File("."));
            browse.setDialogTitle("Sauvegarder une partie");
            browse.setFileFilter(new FileNameExtensionFilter("Fichiers du Wargame", "ser"));
            int choix = browse.showSaveDialog(this);
            if (choix == JFileChooser.APPROVE_OPTION) {
                File f = browse.getSelectedFile();
                if (etat != Etat.EN_ATTENTE) {
                    if (!f.getAbsolutePath().endsWith(".ser")) {
                        carte.sauvegarder(f.getAbsolutePath() + ".ser");
                    } else {
                        carte.sauvegarder(f.getAbsolutePath());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Il faut d'abord lancer une partie pour pouvoir sauvegarder !", "Attention !", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Impossible de sauvegarder la partie !", "Attention !", JOptionPane.ERROR_MESSAGE);
        }
    }
    */
    /**
     * Charge une partie sauvegardé ultérieurement.
     *
     * Cette méthode ouvre une fenêtre de dialogue pour demande à l'utilisateur
     * quel est le fichier à chargé.
     */
    /*
    public void charger() {
        try {
            JFileChooser browse = new JFileChooser(new File("."));
            browse.setDialogTitle("Charger une partie");
            browse.setFileFilter(new FileNameExtensionFilter("Fichiers du Wargame", "ser"));
            int choix = browse.showOpenDialog(this);
            if (choix == JFileChooser.APPROVE_OPTION) {
                File f = browse.getSelectedFile();
                carte.charger(f.getAbsolutePath());
                refreshNbSoldats();
                refresh();
                finTour.setEnabled(true);
                etat = Etat.EN_COURS;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Impossible de charger la partie !", "Attention !", JOptionPane.ERROR_MESSAGE);
            if (etat == Etat.EN_ATTENTE) {
                finTour.setEnabled(false);
            }
        }
    }
    */
    /**
     * Met à jour la carte.
     */
    public void refresh() {
        repaint();
        /* TESTER SI VICTOIRE DE PARTIE */
    }

    /**
     * Termine le tour actuel.
     */
    public void finTour() {
        etat = Etat.EN_COURS;
        over = null;
        refresh();
    }

    /**
     * Dessinne le panneau sur le fenêtre principale.
     *
     * @param g Le Graphics permettant de dessiner.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(tapis, 0, 0, null);
    }

    /**
     * Méthode appelée lors du clic de la souris sur la carte.
     *
     * @param e Le MouseEvent.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (int) (e.getX());
        int y = (int) (e.getY());
        if (etat != Etat.EN_ATTENTE) {
            if (etat == Etat.EN_COURS) {
                /* TODO */
            } else if (etat == Etat.POSITION) {
                /* TODO */
                over = null;
                etat = Etat.EN_COURS;
                refresh();
            }
        }
    }

    /**
     * Méthode appelée lors de l'appuie d'un bouton de la souris sur la carte.
     *
     * @param e Le MouseEvent.
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Méthode appelée lors du relachement d'un bouton de la souris sur la
     * carte.
     *
     * @param e Le MouseEvent.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Méthode appelée lorsque la souris entre sur la carte.
     *
     * @param e Le MouseEvent.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Méthode appelée lorsque la souris sort de la carte.
     *
     * @param e Le MouseEvent.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Méthode appelée lorsque la souris entre sur la carte.
     *
     * @param e Le MouseEvent.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Méthode appelée lorsque la souris bouge sur la carte et qu'un bouton est
     * appuyé.
     *
     * @param e Le MouseEvent.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}