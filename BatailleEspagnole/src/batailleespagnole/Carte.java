package batailleespagnole;

/**
 * Classe servant à représenter une carte de jeu
 *
 * @author Line POUVARET
 * @author Mickaël TURNEL
 */
public class Carte implements Comparable<Carte> {

    /**
     * Enumération correspondant aux différents ordres possibles d'une carte de
     * jeu
     */
    static enum TypeOrdre {

        DEUX, QUATRE, CINQ, SIX, SEPT, HUIT, NEUF, DIX, CAVALIER, DAME, ROI, TROIS, AS;
    }

    /**
     * L'ordre d'une carte de jeu
     *
     * @see TypeOrdre
     */
    private TypeOrdre ordre;

    /**
     * Valeur d'une carte en terme de points
     */
    private int valeur;

    /**
     * Famille de la carte
     *
     * @see TypeFamille
     */
    private TypeFamille famille;

    /**
     * Constructeur de Carte.
     *
     * @param ordre L'ordre de la carte
     * @param famille La famille de la carte
     *
     * @see TypeFamille
     */
    public Carte(TypeOrdre ordre, TypeFamille famille) {
        this.ordre = ordre;
        this.famille = famille;
        switch (ordre) {
            case AS:
                this.valeur = 11;
                break;
            case TROIS:
                this.valeur = 10;
                break;
            case ROI:
                this.valeur = 4;
                break;
            case DAME:
                this.valeur = 3;
                break;
            case CAVALIER:
                this.valeur = 2;
                break;
            case DIX:
            case NEUF:
            case HUIT:
            case SEPT:
            case SIX:
            case CINQ:
            case QUATRE:
            case DEUX:
                this.valeur = 0;
                break;
            default:
                System.err.println("Erreur : ordre de carte inexistant");
        }
    }

    /**
     * Retourne l'ordre d'une carte.
     *
     * @return Une énumération TypeOrdre qui correspond à l'ordre de la carte
     */
    public TypeOrdre getOrdre() {
        return ordre;
    }

    /**
     * Met à jour l'ordre d'une carte.
     *
     * @param val L'ordre à mettre à jour sur la carte
     */
    public void setOrdre(TypeOrdre val) {
        this.ordre = val;
    }

    /**
     * Retourne la valeur en points d'une carte.
     *
     * @return Un entier qui correspond à la valeur de la carte
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * Met à jour la valeur d'une carte en points.
     *
     * @param val Un entier pour mettre à jour la valeur
     */
    public void setValeur(int val) {
        this.valeur = val;
    }

    /**
     * Retourne la famille d'une carte.
     *
     * @return Une énumération TypeFamille qui correspond à la famille d'une
     * carte
     */
    public TypeFamille getFamille() {
        return famille;
    }

    /**
     * Met à jour la famille d'une carte.
     *
     * @param famille Le type énumération TypeFamille
     */
    public void setFamille(TypeFamille famille) {
        this.famille = famille;
    }

    /**
     * Permet de comparer deux Cartes entre elles
     *
     * @param c Une carte
     * @return Un entier : 1 si c est plus grande en ordre que l'objet actuel,0
     * si elles sont de mêmes ordre et -1 sinon.
     */
    @Override
    public int compareTo(Carte c) {
        if (c.getFamille() == this.famille) {
            return this.getOrdre().compareTo(c.getOrdre());
        }
        return -1;
    }

    /**
     * Permet de formater l'affichage d'une Carte.
     *
     * @return La chaine qui affichera l'objet Carte
     */
    @Override
    public String toString() {
        String s = "";
        s = ordre.toString() + " de " + famille.toString();

        return s;
    }
}
