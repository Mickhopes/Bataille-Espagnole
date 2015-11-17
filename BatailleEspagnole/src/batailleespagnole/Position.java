package batailleespagnole;

import java.io.Serializable;

/**
 * Classe de base servant à représenter une position sur la carte.
 *
 * Elle est représentée par ses coordonées en 2 dimensions, x t y.
 *
 * @see Carte
 */
public class Position implements Serializable {

    /**
     * La coordonée x de la position sur la carte.
     *
     * @see Position#getX()
     * @see Position#setX(int)
     */
    private int x;
    /**
     * La coordonnée y de la position sur la carte.
     *
     * @see Position#getY()
     * @see Position#setY(int)
     */
    private int y;

    /**
     * Constructeur de la classe Position.
     *
     * @param x La coordonnée x de la position sur la carte.
     * @param y La coordonnée y de la position sur la carte.
     */
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la coordonnée x de la position.
     *
     * @return Un entier qui correspond à la coordonnée x sur la carte.
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la coordonnée y de la position.
     *
     * @return Un entier qui correspond à la coordonnée y sur la carte.
     */
    public int getY() {
        return y;
    }

    /**
     * Met à jour la coordonnée x de la position.
     *
     * @param x La coordonée x de la position sur la carte.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Met à jour la coordonnée y de la position.
     *
     * @param y La coordonée y de la position sur la carte.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Indique si la position est valide.
     *
     * @return Un booléen qui correspond à la validité de la position, true si
     * la position est bien sur la carte, false sinon.
     */
    public boolean estValide() {
        if (x < 0 || x >= IConfig.LARGEUR || y < 0 || y >= IConfig.HAUTEUR) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Indique si la position est voisine à celle envoyée en paramètre.
     *
     * @param pos La position à tester.
     * @return Un booléen, true si la position pos est voisine à la position
     * courante, false sinon.
     */
    public boolean estVoisine(Position pos) {
        return ((Math.abs(x - pos.x) <= 1) && (Math.abs(y - pos.y) <= 1));
    }

    /**
     * Indique si la position est égale à celle envoyée en paramètre.
     *
     * @param o L'objet à tester. Si l'objet n'est pas une position, renvoi
     * false.
     * @return Un booléen, true si les coordonnées des deux positions sont
     * égales, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            if (this.x == ((Position) o).getX() && this.y == ((Position) o).getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne la position sous forme d'une chaîne de caractères.
     *
     * @return Un String
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
