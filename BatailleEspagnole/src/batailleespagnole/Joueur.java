package batailleespagnole;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Classe servant à représenter un joueur
 *
 * @author Line POUVARET
 * @author Mickaël TURNEL
 */
public class Joueur {

    /**
     * Le nom du joueur
     */
    private String nom;

    /**
     * Le nombre de points du joueur durant la partie
     */
    private int nbPoints;

    /**
     * Attribut qui permet d'indiquer si ce joueur joue en premier
     */
    private boolean premier;

    /**
     * Attribut qui permet d'indiquer si ce joueur est un IA
     */
    private boolean IA;

    /**
     * ArrayList de Carte qui correspondent aux cartes dans la main du joueur
     *
     * @see Carte
     */
    private ArrayList<Carte> cartesEnMain;

    /**
     * ArrayList de Carte qui correspondent aux cartes qu'il a gagné durant le
     * jeu
     *
     * @see Carte
     */
    private ArrayList<Carte> plisGagnes;

    /**
     * Constructeur de joueur
     *
     * @param nom Le nom du joueur
     * @param IA Indique si le joueur est une IA ou pas
     */
    public Joueur(String nom, boolean IA) {
        this.nom = nom;
        this.nbPoints = 0;
        this.premier = false;
        this.cartesEnMain = new ArrayList<>();
        this.plisGagnes = new ArrayList<>();
        this.IA = IA;
    }

    /**
     * Retourne le nombre de points d'un joueur
     *
     * @return Un entier qui correspond au nombre de points d'un joueur
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     * Met à jour le nombre de points du joueur
     *
     * @param val Un entier qui correspond au nombre de points à mettre à jour
     */
    public void setNbPoints(int val) {
        this.nbPoints = val;
    }

    /**
     * Retourne le nom du joueur
     *
     * @return String qui correspond au nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met à jour le nom du joueur
     *
     * @param val String qui correspond au nom du Joueur à mettre à jour
     */
    public void setNom(String val) {
        this.nom = val;
    }

    /**
     * Méthode qui permet d'ajouter un certain nombre de points au joueur
     *
     * @author Line POUVARET
     * @param val Un entier qui correspond au nombre de points à ajouter
     */
    public void ajoutPoints(int val) {
        this.nbPoints += val;
    }

    /**
     * Indique si le joueur est le premier à jouer
     *
     * @return boolean
     */
    public boolean isPremier() {
        return premier;
    }

    /**
     * Met à jour le booléen qui indique si le joueur est le premier à jour
     *
     * @param premier boolean
     */
    public void setPremier(boolean premier) {
        this.premier = premier;
    }

    /**
     * Retourne les cartes dans la main du joueur
     *
     * @return ArrayList(Carte) Les cartes dans la main du joueur
     * @see Carte
     */
    public ArrayList<Carte> getCartesEnMain() {
        return cartesEnMain;
    }

    /**
     * Met à jour les cartes en main du joueur
     *
     * @param cartesEnMain ArrayList(Carte) qui correspond aux cartes dans la
     * main du joueur
     * @see Carte
     */
    public void setCartesEnMain(ArrayList<Carte> cartesEnMain) {
        this.cartesEnMain = cartesEnMain;
    }

    /**
     * Retourne les plis gagnés par le joueur
     *
     * @return ArrayList(Carte) qui correspond aux cartes gagnées par le joueur
     */
    public ArrayList<Carte> getPlisGagnes() {
        return plisGagnes;
    }

    /**
     * Met à jour les plis gagnés par le joueur
     *
     * @param plisGagnes ArrayList(Carte) qui correspond aux cartes gagnées par
     * le joueur
     */
    public void setPlisGagnes(ArrayList<Carte> plisGagnes) {
        this.plisGagnes = plisGagnes;
    }

    /**
     * Indique si le joueur est une IA ou pas
     *
     * @return boolean
     */
    public boolean isIA() {
        return IA;
    }

    /**
     * Met à jour le booléen qui indique si le joueur est une IA
     *
     * @param IA boolean
     */
    public void setIA(boolean IA) {
        this.IA = IA;
    }

    /**
     * Redéfinition de la méthode equals pour Joueur Permet de vérifier que deux
     * joueurs sont bien le même
     *
     * @param o Le deuxième joueur à comparer
     * @return boolean : true si les deux joueurs sont le même, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (((Joueur) o).getNom().equals(this.nom) && ((Joueur) o).isIA() == this.IA) {
            return true;
        }
        return false;
    }

    /**
     * Méthode qui permet à un joueur de jouer une carte et l'enlève de ses
     * cartes en main
     *
     * @author Line POUVARET
     * @param carte Carte qui correspond à la Carte à jouer
     * @return Carte qui correspond à la Carte jouée
     */
    public Carte jouer(Carte carte) {
        cartesEnMain.remove(carte);

        return carte;
    }

    /**
     * Méthode qui permet à un joueur de piocher dans le tas de cartes de la
     * partie
     *
     * @author Line POUVARET
     * @param tasDeCartes LinkedList(Carte) qui correspond au tas de cartes de
     * la partie
     * @return Carte la carte que le joueur a piochée
     */
    public Carte piocher(LinkedList<Carte> tasDeCartes) {
        /* Si le tas est vide */
        if (tasDeCartes.isEmpty()) {
            /* le jeu est fini */
            /* ajouter exceptions */
        }

        /* Si le joueur a déjà trois cartes en main */
        if (cartesEnMain.size() == 3) {
            /* On lui donne rien */
            /* ajouter exceptions */
        }
        Carte c = tasDeCartes.pop();
        cartesEnMain.add(c);
        return c;
    }

}
