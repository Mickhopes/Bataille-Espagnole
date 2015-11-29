package batailleespagnole;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Classe servant à représenter un joueur
 * 
 * @author Line
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.CC54FE1A-D482-F1F8-4BC2-0DB57B57C53A]
// </editor-fold> 
public class Joueur {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D6EBBA9C-A2DF-2EC7-ADDC-140CC30BCF12]
    // </editor-fold> 
    /**
     * Le nom du joueur
     */
    private String nom;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.078D6297-7935-837D-41DE-9C3839B0AA87]
    // </editor-fold> 
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
     * Attribut qui permet de savoir si c'est au tour du joueur
     */
    private boolean tour;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BE6D8EFC-C7C8-5E18-76BD-752E80A886A7]
    // </editor-fold> 
    /**
     * ArrayList de Carte qui correspondent aux cartes dans la main du joueur
     * 
     * @see Carte
     */
    private ArrayList<Carte> cartesEnMain;

    /**
     * ArrayList de Carte qui correspondent aux cartes qu'il a gagné durant le jeu
     *
     * @see Carte
     */
    private ArrayList<Carte> plisGagnes;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B1CC00A2-ECB6-67A4-6437-8C82AF977CFB]
    // </editor-fold> 
    /**
     * Attribut qui correspond à la partie à laquelle le joueur participe
     * 
     * @see Partie
     */
    private Partie mPartie;

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
        this.tour = false;
        this.cartesEnMain = new ArrayList<>();
        this.plisGagnes = new ArrayList<>();
        this.IA = IA;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5B763AA0-337C-FCC8-7B08-FF7C4E142B78]
    // </editor-fold> 
    /** 
     * Retourne la partie à laquelle le joueur participe
     * 
     * @return Partie
     */
    public Partie getPartie() {
        return mPartie;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4348DC49-88EC-2A08-1E73-F709231262CF]
    // </editor-fold> 
    /**
     * Met à jour la partie à laquelle le joueur participe
     * 
     * @param val La Partie à lui attribuer
     */
    public void setPartie(Partie val) {
        this.mPartie = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A75E7EE8-52B8-B972-69C2-271AB5C0C194]
    // </editor-fold> 
    /**
     * Retourne le nombre de points d'un joueur
     * 
     * @return Un entier qui correspond au nombre de points d'un joueur
     */
    public int getNbPoints() {
        return nbPoints;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2CD0510C-C057-A5CB-1A97-01D965F2BFE2]
    // </editor-fold> 
    /**
     * Met à jour le nombre de points du joueur
     * 
     * @param val Un entier qui correspond au nombre de points à mettre à jour
     */
    public void setNbPoints(int val) {
        this.nbPoints = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7DCDEA3A-8EFA-3FCC-6BA3-F7E2B34A9FE9]
    // </editor-fold> 
    /**
     * Retourne le nom du joueur
     * 
     * @return String qui correspond au nom du joueur
     */
    public String getNom() {
        return nom;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E0A9BC8A-DD14-8DD5-6AED-1506876777BE]
    // </editor-fold> 
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
     * Indique si c'est au tour du joueur de jouer
     * 
     * @return boolean
     */
    public boolean isTour() {
        return tour;
    }

    /**
     * Met à jour le booléen qui indique si c'est au joueur de jouer
     * 
     * @param tour boolean
     */
    public void setTour(boolean tour) {
        this.tour = tour;
    }

    /**
     * Retourne les cartes dans la main du joueur
     * 
     * @return ArrayList<Carte> Les cartes dans la main du joueur
     * @see Carte
     */
    public ArrayList<Carte> getCartesEnMain() {
        return cartesEnMain;
    }

    /**
     * Met à jour les cartes en main du joueur
     * 
     * @param cartesEnMain ArrayList<Carte> qui correspond aux cartes dans la main du joueur
     * @see Carte
     */
    public void setCartesEnMain(ArrayList<Carte> cartesEnMain) {
        this.cartesEnMain = cartesEnMain;
    }

    /**
     * Retourne les plis gagnés par le joueur
     * 
     * @return ArrayList<Carte> qui correspond aux cartes gagnées par le joueur
     */
    public ArrayList<Carte> getPlisGagnes() {
        return plisGagnes;
    }

    /**
     * Met à jour les plis gagnés par le joueur
     * 
     * @param plisGagnes ArrayList<Carte> qui correspond aux cartes gagnées par le joueur
     */
    public void setPlisGagnes(ArrayList<Carte> plisGagnes) {
        this.plisGagnes = plisGagnes;
    }

    /**
     * Retourne la partie à laquelle le joueur participe
     * 
     * @return Partie 
     */
    public Partie getmPartie() {
        return mPartie;
    }

    /**
     * Met à jour la partie à laquelle le joueur participe
     * 
     * @param mPartie Partie
     */
    public void setmPartie(Partie mPartie) {
        this.mPartie = mPartie;
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
     * Redéfinition de la méthode equals pour Joueur
     * Permet de vérifier que deux joueurs sont bien le même
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

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1DAF6847-A28B-2D0E-5550-AA504F96E002]
    // </editor-fold> 
    /**
     * Méthode qui permet à un joueur de jouer une carte et l'enlève de ses cartes en main
     * 
     * @param carte Carte qui correspond à la Carte à jouer
     * @return Carte qui correspond à la Carte jouée
     */
    public Carte jouer(Carte carte) {
        cartesEnMain.remove(carte);

        return carte;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D5451F0E-CF8C-0CD3-7590-145DAA5B4521]
    // </editor-fold> 
    /**
     * Méthode qui permet à un joueur de piocher dans le tas de cartes de la partie
     * 
     * @param tasDeCartes LinkedList<Carte> qui correspond au tas de cartes de la partie
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
