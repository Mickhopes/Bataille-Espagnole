/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batailleespagnole;

/**
 *
 * @author Lily
 */
public class BatailleEspagnole {

    public static void menu() {
        System.out.println("==========Menu Principal===========");
        System.out.println("-1-Nouvelle Partie");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        menu();
        int choix = LectureClavier.lireEntier("Que voulez-vous faire ?");

        switch (choix) {
            case 1:
                int nbJeuxMax,
                 nbPtsMax,
                 nbJoueurs;
                do {
                    nbJoueurs = LectureClavier.lireEntier("Combien de joueurs (2-4)?");
                } while (nbJoueurs < 2 || nbJoueurs > 4);
                do {
                    nbJeuxMax = LectureClavier.lireEntier("Entrez un nombre de jeux maximum (0=infini)");
                    nbPtsMax = LectureClavier.lireEntier("Entrez un nombre de points maximum (0=infini)");
                } while (nbJeuxMax == 0 && nbPtsMax == 0);

                Partie p = new Partie(nbJoueurs, nbJeuxMax, nbPtsMax);
                for (int i = 0; i < nbJoueurs; i++) {
                    System.out.println("Nom du joueur n°" + i);
                    String nom = LectureClavier.lireChaine();
                    p.ajoutJoueur(new Joueur(nom));
                }
                p.lancerPartie();
            default:
                break;
        }
    }

}
