package batailleespagnole;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6384B198-9A51-156D-F380-21276E467CE3]
// </editor-fold> 
/**
 * Classe servant à représenter une carte de jeu
 * 
 * @author Line
 */
public class Carte implements Comparable<Carte> {

    /**
     * Enumération correspondant aux différents ordres possibles d'une carte de jeu
     */
    static enum TypeOrdre {

        DEUX, QUATRE, CINQ, SIX, SEPT, HUIT, NEUF, DIX, CAVALIER, DAME, ROI, TROIS, AS;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.94E54F8B-922C-A362-FFC7-336B808D8B63]
    // </editor-fold> 
    /** 
     * L'ordre d'une carte de jeu
     * 
     * @see TypeOrdre
     */
    private TypeOrdre ordre;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EB3D496D-A648-B074-ECDB-8BFBD8AA17D5]
    // </editor-fold> 
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
     * @see TypeOrdre
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

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4B0892EA-7CCC-BD9C-0057-36BCE26BA1F5]
    // </editor-fold> 
    /**
     * Retourne l'ordre d'une carte.
     * 
     * @return Une énumération TypeOrdre qui correspond à l'ordre de la carte
     */
    public TypeOrdre getOrdre() {
        return ordre;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.AFF1BB55-8CB5-1724-8640-68D544841CB7]
    // </editor-fold> 
    /**
     * Met à jour l'ordre d'une carte.
     * 
     * @param val L'ordre à mettre à jour sur la carte
     */
    public void setOrdre(TypeOrdre val) {
        this.ordre = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B1A3DC70-FE6B-0E95-DB5D-D9472652FCAF]
    // </editor-fold> 
    /**
     * Retourne la valeur en points d'une carte.
     * 
     * @return Un entier qui correspond à la valeur de la carte
     */
    public int getValeur() {
        return valeur;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1F2E7405-F0A5-31D5-43FD-4A200F91DABE]
    // </editor-fold> 
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
     * @return Une énumération TypeFamille qui correspond à la famille d'une carte
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
     * @return Un entier : 1 si c est plus grande en ordre que l'objet actuel,0 si elles sont de mêmes ordre et -1 sinon. 
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
