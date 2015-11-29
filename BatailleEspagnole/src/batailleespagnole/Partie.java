package batailleespagnole;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * Classe qui sert à représenter une partie
 *
 * @author Line
 */
public class Partie {

    /**
     * Le nombre de jeux max d'une partie
     */
    private int nbJeux;

    /**
     * Le nombre de points max à atteindre d'une partie
     */
    private int nbPointsMax;

    /**
     * Le nombre de joueur de la partie (ce nombre est considéré fixe pour une
     * partie)
     */
    private final int nbJoueurs;

    /**
     * Le nombre de cartes total du paquet (constante).
     */
    static final int NB_CARTES = 52;

    /**
     * Les joueurs de la partie
     *
     * @see Joueur
     */
    private ArrayList<Joueur> joueursPartie;

    /**
     * Constructeur par défaut de Partie Fixe le nombre de jeux à 10, le nombre
     * de points max à 300
     *
     * @param nbJoueurs Le nombre de joueurs de la partie
     */
    public Partie(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
        this.nbJeux = 10;
        this.nbPointsMax = 300;

        this.joueursPartie = new ArrayList<>();
    }

    /**
     * Deuxième constructeur de Partie
     *
     * @param nbJoueurs Le nombre de joueurs de la partie
     * @param nbJeux Le nombre de jeux max de la partie
     * @param nbPointsMax Le nombre de points max de la partie
     */
    public Partie(int nbJoueurs, int nbJeux, int nbPointsMax) {
        this.nbJoueurs = nbJoueurs;
        this.nbJeux = nbJeux;
        this.nbPointsMax = nbPointsMax;

        this.joueursPartie = new ArrayList<>();
    }

    /**
     * Retourne le nombre de jeux max de la partie
     *
     * @return Un entier qui correspond au nombre de jeux
     */
    public int getNbJeux() {
        return nbJeux;
    }

    /**
     * Met à jour le nombre de jeux max d'une partie
     *
     * @param val Le nombre de jeux max à mettre
     */
    public void setNbJeux(int val) {
        this.nbJeux = val;
    }

    /**
     * Retourne le nombre de points max de la partie
     *
     * @return Un entier qui correspond au nombre de points max de la partie
     */
    public int getNbPointsMax() {
        return nbPointsMax;
    }

    /**
     * Met à jour le nombre de points max de la partie
     *
     * @param val Le nombre de points max à mettre
     */
    public void setNbPointsMax(int val) {
        this.nbPointsMax = val;
    }

    /**
     * Retourne le nombre de joueurs de la partie
     *
     * @return Un entier qui correspond au nombre de joueurs de la partie
     */
    public int getNbJoueurs() {
        return this.nbJoueurs;
    }

    /**
     * Retourne les joueurs de la partie
     *
     * @return ArrayList(Joueur) la liste des joueurs de la partie
     * @see Joueur
     */
    public ArrayList<Joueur> getJoueurs() {
        return joueursPartie;
    }

    /**
     * Met à jour la liste des joueurs de la partie
     *
     * @param val La liste des joueurs à mettre
     * @see Joueur
     */
    public void setJoueurs(ArrayList<Joueur> val) {
        this.joueursPartie = val;
    }

    /**
     * Méthode qui permet d'ajouter un joueur à la partie
     *
     * @param j Joueur Le joueur à ajouter
     * @see Joueur
     */
    public void ajoutJoueur(Joueur j) {
        joueursPartie.add(j);
    }

    /**
     * Méthode qui permet de enlever un joueur de la partie
     *
     * @param j Joueur le jouer à enlever
     * @see Joueur
     */
    public void supprJoueur(Joueur j) {
        joueursPartie.remove(j);
    }

    /**
     * Méthode qui permet de lancer la partie en mode console
     */
    public void lancerPartie() {
        joueursPartie.get(0).setPremier(true);
        int i = 0, max = 0;
        Joueur jWin = null;
        boolean cond;
        if (nbJeux != 0) {
            cond = i < nbJeux;
        } else {
            cond = max < nbPointsMax;
        }
        while (cond) {
            Jeu j = new Jeu(joueursPartie);
            j.creerPaquet();
            j.melangerCartes();
            j.distribuer(joueursPartie);
            j.determinerAtout(joueursPartie);
            /* appel à la fonction qui fait jouer les joueurs */
            while (!j.finJeu(joueursPartie)) {
                j.jouerTour(joueursPartie);
                jWin = j.determinerVainqueur();

                /* On remet les attributs premier des joueurs à false */
                for (int k = 0; k < joueursPartie.size(); k++) {
                    joueursPartie.get(k).setPremier(false);
                }
                jWin.setPremier(true);

                System.out.println("");
                System.out.println("Le vainqueur du pli n°" + ((j.getNbPlis()) + 1) + " est " + jWin.getNom());
                j.setNbPlis(j.getNbPlis() + 1);
                for (Entry<Joueur, Carte> e : j.getPliActuel().entrySet()) {
                    jWin.getPlisGagnes().add(e.getValue());
                }
                /* On vide le pli actuel */
                j.getPliActuel().clear();
            }
            j.compterPoints(joueursPartie);
            jWin = null;
            for (int k = 0; k < joueursPartie.size(); k++) {
                if (joueursPartie.get(k).getNbPoints() > max) {
                    max = joueursPartie.get(k).getNbPoints();
                    jWin = joueursPartie.get(k);
                }
            }
            System.out.println("");
            tableauScores();

            /* Iteration de la boucle */
            i++;

            /* On reteste la condition */
            if (nbJeux != 0) {
                cond = i < nbJeux;
            } else {
                cond = max < nbPointsMax;
            }
        }
        System.out.println("");
        System.out.println("Le vainqueur de la partie est " + jWin.getNom());
    }

    /**
     * Méthode qui permet d'afficher le tableau des scores de la partie
     */
    public void tableauScores() {
        System.out.println("Scores : ");
        for (int i = 0; i < joueursPartie.size(); i++) {
            System.out.println("<" + joueursPartie.get(i).getNom() + "> => " + joueursPartie.get(i).getNbPoints() + " pts");
        }
    }
}
