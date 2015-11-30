/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batailleespagnole;

import exception.NotEnoughPlayersException;

/**
 * Classe principale du programme
 *
 * @author Line POUVARET
 * @author Mickaël TURNEL
 */
public class BatailleEspagnole {

    /**
     * Méthode qui affiche le menu du programme
     *
     * @author Line POUVARET
     * @author Mickaël TURNEL
     */
    public static void menu() {
        System.out.println("");
        System.out.println("==========Menu Principal===========");
        System.out.println("-1- Nouvelle Partie");
        System.out.println("-0- Quitter");
        System.out.println("");
    }

    /**
     * @author Mickaël TURNEL
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choix;

        do {
            menu();
            choix = LectureClavier.lireEntier("Que voulez-vous faire ?");
            int nbJeuxMax, nbPtsMax, nbJoueurs, nbIA;
            switch (choix) {
                case 1:
                    do {
                        nbJoueurs = LectureClavier.lireEntier("\nCombien de joueurs (2-4) au total?");
                    } while (nbJoueurs < 2 || nbJoueurs > 4);

                    do {
                        nbIA = LectureClavier.lireEntier("\nCombien de joueurs Ordinateur (0-4)?");
                    } while (nbIA < 0 || nbIA > 4);
                    do {
                        nbJeuxMax = LectureClavier.lireEntier("\nEntrez un nombre de jeux maximum (0=infini)");
                        nbPtsMax = LectureClavier.lireEntier("\nEntrez un nombre de points maximum (0=infini)");
                    } while (nbJeuxMax == 0 && nbPtsMax == 0);

                    Partie p = new Partie(nbJoueurs, nbJeuxMax, nbPtsMax);
                    int i;
                    /* On demande le nom des vrais joueurs et on les ajoute à la partie */
                    for (i = 0; i < nbJoueurs - nbIA; i++) {
                        System.out.println("\nNom du joueur n°" + (i + 1));
                        String nom = LectureClavier.lireChaine();
                        p.ajoutJoueur(new Joueur(nom, false));
                    }

                    /* On crée le nombre de joueurs IA nécessaires */
                    for (int j = i; j < nbJoueurs; j++) {
                        System.out.println("\nNom de l'ordinateur n°" + (j + 1));
                        System.out.println("Joueur " + (j + 1));
                        p.ajoutJoueur(new Joueur("Joueur " + (j + 1), true));
                    }
                    
                    try {
                        p.lancerPartie();
                    } catch (NotEnoughPlayersException ex) {
                        System.err.println(ex.getMessage());
                    }
                default:
                    break;
            }
        } while (choix != 0);
    }

}
