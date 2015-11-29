package batailleespagnole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import javax.swing.JOptionPane;

/**
 * Classe qui représente un jeu d'une partie
 * 
 * @author Line
 */
public class Jeu {

    /**
     * Le nombre de plis actuel d'un jeu
     */
    private int nbPlis;

    /**
     * HashMap qui correspond au pli actuel, les Joueurs de la partie sont les clés, les cartes qu'ils ont joué sont les valeurs
     * 
     * @see Joueur
     * @see Carte
     */
    private HashMap<Joueur, Carte> pliActuel;

    /**
     * La famille atout d'un jeu
     * 
     * @see TypeFamille
     */
    private TypeFamille atout;

    /**
     * Le tas de cartes d'un jeu dans lequel les joueurs piochent
     * 
     * @see Carte
     */
    private LinkedList<Carte> tasDeCartes;

    /**
     * Constructeur de Jeu
     * 
     * @param joueursPartie Les joueurs de la partie à inclure dans le jeu
     * @see Joueur
     */
    public Jeu(ArrayList<Joueur> joueursPartie) {
        nbPlis = 0;
        tasDeCartes = new LinkedList<>();
        pliActuel = new HashMap<>();
        for (int i = 0; i < joueursPartie.size(); i++) {
            pliActuel.put(joueursPartie.get(i), null);
        }

    }

    /**
     * Retourne la famille atout du jeu
     * 
     * @return TypeFamille qui correspond à la famille atout
     * @see TypeFamille
     */
    public TypeFamille getAtout() {
        return atout;
    }

    /**
     * Met à jour la famille atout du jeu
     * 
     * @param val La famille à mettre à jour
     * @see TypeFamille
     */
    public void setAtout(TypeFamille val) {
        this.atout = val;
    }

    /**
     * Retourne le nombre de plis actuel du jeu
     * 
     * @return Un entier qui correspond au nombre de plis actuel du jeu
     */
    public int getNbPlis() {
        return nbPlis;
    }

    /**
     * Met à jour le nombre de plis actuel du jeu
     * 
     * @param val L'entier qui correspond au nombre de plis
     */
    public void setNbPlis(int val) {
        this.nbPlis = val;
    }

    /**
     * Retourne le table de hachage du pli actuel du jeu
     * 
     * @return HashMap<Joueur, Carte> La table de hachage du pli actuel
     * @see Joueur
     * @see Carte
     */
    public HashMap<Joueur, Carte> getPliActuel() {
        return pliActuel;
    }

    /**
     * Met à jour la table de hachage qui correspond au pli actuel du jeu
     * 
     * @param pliActuel HashMap<Joueur, Carte> La table de hachage du pli actuel
     * @see Joueur
     * @see Carte
     */
    public void setPliActuel(HashMap<Joueur, Carte> pliActuel) {
        this.pliActuel = pliActuel;
    }

    /**
     * Retourne le tas de cartes du jeu
     * 
     * @return LinkedList<Carte> La liste de cartes qui correspond au tas de cartes du jeu
     * @see Carte
     */
    public LinkedList<Carte> getTasDeCartes() {
        return tasDeCartes;
    }

    /**
     * Met à jour le tas de cartes du jeu
     * 
     * @param tasDeCartes LinkedList<Carte> La liste de cartes à mettre à jour
     * @see Carte
     */
    public void setTasDeCartes(LinkedList<Carte> tasDeCartes) {
        this.tasDeCartes = tasDeCartes;
    }

    /**
     * Méthode qui permet de compter les points de tous les joueurs de la partie pour un jeu
     * 
     * @param joueursPartie Les joueurs de la partie
     * @see Joueur
     */
    public void compterPoints(ArrayList<Joueur> joueursPartie) {
        for (int i = 0; i < joueursPartie.size(); i++) {
            ArrayList<Carte> plisGagnes = joueursPartie.get(i).getPlisGagnes();
            int sum = 0;
            for (int j = 0; j < plisGagnes.size(); j++) {
                sum += plisGagnes.get(j).getValeur();
            }
            joueursPartie.get(i).ajoutPoints(sum);
            joueursPartie.get(i).getPlisGagnes().clear();
        }
    }

    /**
     * Méthode qui crée le paquet de cartes
     */
    public void creerPaquet() {

        /* On crée les 52 cartes */
        for (TypeFamille f : TypeFamille.values()) {
            for (Carte.TypeOrdre o : Carte.TypeOrdre.values()) {
                tasDeCartes.add(new Carte(o, f));
            }
        }
    }

    /**
     * Méthode qui mélange les cartes du tas
     */
    public void melangerCartes() {
        Collections.shuffle(tasDeCartes);
    }

    /**
     * Méthode qui permet de distribuer les cartes du tas au joueur, 3 cartes par joueur
     * 
     * @param joueursPartie ArrayList<Joueur> Les joueurs de la partie
     * @see Joueur
     */
    public void distribuer(ArrayList<Joueur> joueursPartie) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < joueursPartie.size(); j++) {
                joueursPartie.get(j).piocher(tasDeCartes);
            }
        }
    }

    /**
     * Méthode qui permet de déterminer le joueur vainqueur d'un pli
     * 
     * @return Joueur Le joueur qui est le vainqueur du pli
     */
    public Joueur determinerVainqueur() {
        /* Pour toutes les cartes jouées du pli */
        Carte.TypeOrdre max = Carte.TypeOrdre.DEUX;
        Joueur jMax = null;
        boolean maxAtout = false;
        TypeFamille famillePremier = null;

        /* On fait un premier parcours de la table de hachage pour trouver quel est le joueur
         qui a posé la première carte et quelle est la famille de cette carte */
        for (Entry<Joueur, Carte> e : pliActuel.entrySet()) {
            /* On teste que la carte associée au joueur existe bien */
            if (e.getValue() != null) {
                if (e.getKey().isPremier()) {
                    famillePremier = e.getValue().getFamille();
                    /* On remet à false puisqu'on a détecté la famille demandée */
                    // e.getKey().setPremier(false);
                }
            }
        }
        /* Pour chaque couple de la table de hachage du pli actuel */
        for (Entry<Joueur, Carte> e : pliActuel.entrySet()) {
            /* On teste que la carte associée au joueur existe bien */
            if (e.getValue() != null) {
                /* si la carte actuelle est un atout */
                if (e.getValue().getFamille() == this.atout) {
                    /* Si la carte a un ordre plus grand que le max actuel */
                    if (e.getValue().getOrdre().compareTo(max) >= 0) {
                        /* On récupère le joueur potentiellement gagnant et on continue de tester les cartes */
                        max = e.getValue().getOrdre();
                        jMax = e.getKey();
                        /* On indique ici qu'une carte atout a été jouée donc les autres ne comptent pas */
                        maxAtout = true;
                    }
                } /* si la carte actuelle n'est pas un atout */ else {
                    /* Si un atout n'a pas été joué */
                    if (!maxAtout) {
                        /* Si la famille de la carte actuelle qu'on teste correspond à la famille de la première carte jouée */
                        if (e.getValue().getFamille() == famillePremier) {
                            /* Si la carte a un ordre plus grand que le max actuel */
                            if (e.getValue().getOrdre().compareTo(max) >= 0) {
                                /* On récupère le joueur potentiellement gagnant et on continue de tester les cartes */
                                max = e.getValue().getOrdre();
                                jMax = e.getKey();
                            }
                        }
                    }
                }
            }
        }
        return jMax;
    }

    /**
     * Méthode qui permet aux joueurs de la partie de jouer leur carte pour un pli
     * 
     * @param joueursPartie ArrayList<Joueur> Les joueurs de la partie
     * @see Joueur
     */
    public void jouerTour(ArrayList<Joueur> joueursPartie) {
        /* Pour chaque joueur */
        int i;
        int count = 0;

        /* On récupère l'index du premier à jouer */
        for (i = 0; i < joueursPartie.size() && !joueursPartie.get(i).isPremier(); i++) {
        }
        while (count < joueursPartie.size()) {
            System.out.println("");
            /* On affiche le nom du joueur qui doit jouer */
            System.out.println("==Joueur " + joueursPartie.get(i).getNom() + "==");
            /* On affiche le contenu du pli */
            afficherPliActuel();
            System.out.println("");

            ArrayList<Carte> cartesEnMain = joueursPartie.get(i).getCartesEnMain();

            /* Si le joueur actuel est une IA */
            if (joueursPartie.get(i).isIA()) {
                /* A DECOMMENTER SI ON VEUT SAVOIR CE QUE POSSEDE L'IA */
                /*
                 System.out.println("====Cartes actuelles====");
                 /* On affiche les cartes en main du joueur */
                /*
                 for (int j = 0; j < cartesEnMain.size(); j++) {
                 System.out.print(j + 1 + " - ");
                 System.out.println(cartesEnMain.get(j));
                 }
                 System.out.println("=========================");
                 */

                /* On met le tour vrai pour le joueur qui doit jouer */
                joueursPartie.get(i).setTour(true);
                /* On récupère la carte déterminée par l'algo d'IA */
                Carte c = joueursPartie.get(i).jouer(jouerIA(joueursPartie.get(i)));

                /* On ajoute au pli la carte du joueur */
                pliActuel.put(joueursPartie.get(i), c);

                /* On affiche à nouveau le pli pour montrer ce qu'il a joué */
                /* MODE CONSOLE */
                afficherPliActuel();
                /* MODE GRAPHIQUE */
                /* ... */
                
                /* On fait piocher notre joueur ensuite */
                if (!tasDeCartes.isEmpty()) {
                    c = joueursPartie.get(i).piocher(tasDeCartes);
                }
                
                /* Quand il a fini de jouer on met le tour à faux */
                joueursPartie.get(i).setTour(false);
            } /* Sinon */ else {

                /* On met le tour vrai pour le joueur qui doit jouer */
                joueursPartie.get(i).setTour(true);
                
                /* MODE CONSOLE */
                System.out.println("====Cartes actuelles====");
                /* On affiche les cartes en main du joueur */
                for (int j = 0; j < cartesEnMain.size(); j++) {
                    System.out.print(j + 1 + " - ");
                    System.out.println(cartesEnMain.get(j));
                }
                System.out.println("=========================");
                
                /* MODE GRAPHIQUE*/
                /* ... */

                /* On demande au joueur quelle carte il veut jouer */
                int choix;
                /* MODE CONSOLE */
                do {
                    choix = LectureClavier.lireEntier("Quelle carte voulez-vous jouer ?");
                } while (choix < 1 || choix > 3);
                /* MODE GRAPHIQUE */
                /* ... */
                
                /* Appel à la fonction jouer */
                Carte c = joueursPartie.get(i).jouer(cartesEnMain.get(choix - 1));

                /* On ajoute au pli la carte du joueur */
                pliActuel.put(joueursPartie.get(i), c);

                /* On affiche le pli actuel pour voir ce qu'on a joué */
                /* MODE CONSOLE */
                afficherPliActuel();
                /* MODE GRAPHIQUE */
                /* ... */
                
                /* On fait piocher notre joueur ensuite */
                if (!tasDeCartes.isEmpty()) {
                    c = joueursPartie.get(i).piocher(tasDeCartes);
                    /* MODE CONSOLE */
                    System.out.println(joueursPartie.get(i).getNom() + " a pioché : " + c.getOrdre() + " de " + c.getFamille());
                    /* MODE GRAPHIQUE */
                    /* ... */
                }
                
                /* Quand il a fini de jouer on met le tour à faux */
                joueursPartie.get(i).setTour(false);
            } /* Fin du jouer pour vrai joueur */

            i++;
            count++;

            /* On repart de 0 si on est à la fin et qu'on n'a toujours pas fait jouer tout le monde */
            if (i == joueursPartie.size()) {
                i = 0;
            }
        }

    }

    /**
     * Méthode qui permet de déterminer la famille atout d'un jeu en demandant à chaque joueur pour une carte piochée dans le tas
     * 
     * @param joueursPartie ArrayList<Joueur> Les joueurs de la partie
     * @see Joueur
     */
    public void determinerAtout(ArrayList<Joueur> joueursPartie) {
        TypeFamille atout = TypeFamille.Bâton;
        boolean ok;
        Carte c = null;
        System.out.println("");
        /* On tire une carte du tas tant qu'on n'a pas déterminé notre atout */
        do {
            ok = true;
            c = this.tasDeCartes.pop();
            atout = c.getFamille();
            System.out.println("====CHOIX DE L'ATOUT POUR LE JEU====");
            System.out.println("On a pioché la carte suivante : " + c);
            for (int i = 0; i < joueursPartie.size(); i++) {
                System.out.println("<" + joueursPartie.get(i).getNom() + "> Est-ce que l'atout " + atout + " vous convient ?");
                /* Si notre joueur est un vrai joueur on lui demande */
                if (!joueursPartie.get(i).isIA()) {
                    if (!LectureClavier.lireOuiNon("Entrez 'O' pour Oui, 'N' pour Non")) {
                        ok = false;
                        System.out.println("<" + joueursPartie.get(i).getNom() + "> a dit Non");
                        /* ça ne sert à rien de demander aux autres */
                        break;
                    } else {
                        /* Les IA diront toujours oui, elles sont sympas quand même */
                        System.out.println("<" + joueursPartie.get(i).getNom() + "> a dit Oui");
                    }
                } else {
                    System.out.println("<" + joueursPartie.get(i).getNom() + "> a dit Oui");
                }
                System.out.println("");
            }
            this.tasDeCartes.addLast(c);
        } while (!ok);
        System.out.println("");

        this.atout = atout;
    }

    /**
     * Méthode qui affiche le pli actuel
     */
    public void afficherPliActuel() {
        System.out.println("=== Cartes du pli actuel n°" + (getNbPlis() + 1) + " ===");
        for (Entry<Joueur, Carte> e : pliActuel.entrySet()) {
            if (e.getValue() != null) {
                if (e.getKey().isPremier()) {
                    System.out.println("(PREMIER)Joueur " + e.getKey().getNom() + " => : " + e.getValue());
                } else {
                    System.out.println("Joueur " + e.getKey().getNom() + " => : " + e.getValue());
                }
            }
        }
        System.out.println("");
    }

    /**
     * Méthode qui fait jouer un Joueur IA
     * 
     * @param j Joueur Le joueur IA qui doit jouer
     * @return Carte La carte que l'IA désire jouer
     * @see Joueur
     * @see Carte
     */
    public Carte jouerIA(Joueur j) {
        ArrayList<Carte> cartesEnMain = j.getCartesEnMain();
        int nbCartesTapis = 0;

        /* On compte combien il y a de cartes sur le tapis */
        for (Entry<Joueur, Carte> e : pliActuel.entrySet()) {
            if (e.getValue() != null) {
                nbCartesTapis++;
            }
        }

        /* S'il y a 0 cartes sur le tapis */
        if (nbCartesTapis == 0) {
            /* On récupère un nombre aléatoire entre 0 et le nombre de cartes max du joueur */
            int max = j.getCartesEnMain().size();
            int rand = (int) (Math.random() * max);

            return cartesEnMain.get(rand);
        } /* Sinon s'il y a au moins une carte sur le tapis */ else {
            TypeFamille famillePremier = null;
            Carte premier = null;
            /* On fait un premier parcours de la table de hachage pour trouver quel est le joueur
             qui a posé la première carte et quelle est la famille de cette carte */
            for (Entry<Joueur, Carte> e : pliActuel.entrySet()) {
                if (e.getKey().isPremier()) {
                    famillePremier = e.getValue().getFamille();
                    premier = e.getValue();
                }
            }
            /* S'il n'y a qu'une carte dans le pli actuel */
            if (nbCartesTapis == 1) {
                ArrayList<Carte> cartesPlusFortes = new ArrayList<>();
                ArrayList<Carte> cartesPlusFaibles = new ArrayList<>();
                ArrayList<Carte> cartesQuelconques = new ArrayList<>();
                ArrayList<Carte> cartesAtouts = new ArrayList<>();
                for (int i = 0; i < cartesEnMain.size(); i++) {
                    // Si la carte actuelle est de la même famille que la première famille */
                    if (cartesEnMain.get(i).getFamille() == famillePremier) {
                        /* Si la carte actuelle de la même famille est plus forte */
                        if (cartesEnMain.get(i).getOrdre().compareTo(premier.getOrdre()) >= 0) {
                            cartesPlusFortes.add(cartesEnMain.get(i));
                        } /* Sinon c'est une carte plus faible de la même famille */ else {
                            cartesPlusFaibles.add(cartesEnMain.get(i));
                        }
                    } /* Si la carte actuelle n'est pas de la même famille */ else {
                        /* On regarde si c'est un atout */
                        if (cartesEnMain.get(i).getFamille().equals(this.atout)) {
                            cartesAtouts.add(cartesEnMain.get(i));
                        } /* Sinon si c'est une carte quelconque */ else {
                            cartesQuelconques.add(cartesEnMain.get(i));
                        }
                    }
                }

                Carte.TypeOrdre min = Carte.TypeOrdre.AS;
                Carte c = null;
                /* S'il y a des cartes plus fortes à jouer que la première */
                if (!cartesPlusFortes.isEmpty()) {
                    /* On cherche le min des cartes les plus fortes */
                    for (int i = 0; i < cartesPlusFortes.size(); i++) {
                        if (cartesPlusFortes.get(i).getOrdre().compareTo(min) <= 0) {
                            min = cartesPlusFortes.get(i).getOrdre();
                            c = cartesPlusFortes.get(i);
                        }
                    }
                } /* Sinon on prend le min des autres cartes */ else {
                    /* S'il y a des cartes de même famille plus faibles */
                    if (!cartesPlusFaibles.isEmpty()) {
                        for (int i = 0; i < cartesPlusFaibles.size(); i++) {
                            if (cartesPlusFaibles.get(i).getOrdre().compareTo(min) <= 0) {
                                min = cartesPlusFaibles.get(i).getOrdre();
                                c = cartesPlusFaibles.get(i);
                            }
                        }
                    } /* Sinon il n'y a pas de cartes de même famille */ else {
                        /* Est-ce qu'on a des atouts à jouer */
                        if (!cartesAtouts.isEmpty()) {
                            for (int i = 0; i < cartesAtouts.size(); i++) {
                                if (cartesAtouts.get(i).getOrdre().compareTo(min) <= 0) {
                                    min = cartesAtouts.get(i).getOrdre();
                                    c = cartesAtouts.get(i);
                                }
                            }
                        } /* Sinon, on joue une carte quelconque */ else {
                            for (int i = 0; i < cartesQuelconques.size(); i++) {
                                if (cartesQuelconques.get(i).getOrdre().compareTo(min) <= 0) {
                                    min = cartesQuelconques.get(i).getOrdre();
                                    c = cartesQuelconques.get(i);
                                }
                            }
                        }
                    }
                }
                /* On renvoit la carte qu'on veut jouer */
                return c;
            } /* Sinon il y a plus d'une carte sur le pli actuel */ else {
                /* On détermine quel est le joueur qui gagne le pli pour l'instant */
                Joueur jWin = determinerVainqueur();
                Carte cWin = null;
                TypeFamille fPremier = null;
                ArrayList<Carte> cartesPlusFortes = new ArrayList<>();
                ArrayList<Carte> cartesPlusFaibles = new ArrayList<>();
                ArrayList<Carte> cartesQuelconques = new ArrayList<>();
                ArrayList<Carte> cartesAtoutsPlusForts = new ArrayList<>();
                ArrayList<Carte> cartesAtoutsMoinsForts = new ArrayList<>();

                for (Entry<Joueur, Carte> e : pliActuel.entrySet()) {
                    /* Si on trouve le joueur qui a gagné */
                    if (e.getKey().equals(jWin)) {
                        /* On récupère sa carte */
                        cWin = e.getValue();
                    }
                    /* On cherche la carte jouée en premier */
                    if (e.getKey().isPremier()) {
                        /* On récupère la famille de celle-ci */
                        fPremier = e.getValue().getFamille();
                    }
                }

                /* Pour toutes les cartes en main */
                for (int i = 0; i < cartesEnMain.size(); i++) {
                    /* Si on a des cartes de la même famille que la première */
                    if (cartesEnMain.get(i).getFamille() == fPremier) {
                        /* Si en plus de ça elle est plus grande que la meilleure carte potentielle */
                        if (cartesEnMain.get(i).getOrdre().compareTo(cWin.getOrdre()) >= 0 && cartesEnMain.get(i).getFamille() == cWin.getFamille()) {
                            /* On l'ajoute à la liste des cartes plus fortes */
                            cartesPlusFortes.add(cartesEnMain.get(i));
                        } else {
                            cartesPlusFaibles.add(cartesEnMain.get(i));
                        }
                    } /* Sinon la carte n'est pas de la même famille que la première */ else {
                        /* Si notre carte est un atout */
                        if (cartesEnMain.get(i).getFamille() == this.atout) {
                            /* Si la carte potentiellement gagnante est aussi un atout */
                            if (cartesEnMain.get(i).getFamille() == cWin.getFamille()) {
                                /* Si notre atout est plus fort que la carte gagnante */
                                if (cartesEnMain.get(i).getOrdre().compareTo(cWin.getOrdre()) >= 0) {
                                    cartesAtoutsPlusForts.add(cartesEnMain.get(i));
                                } /* Sinon : notre atout est moins fort */ else {
                                    cartesAtoutsMoinsForts.add(cartesEnMain.get(i));
                                }
                            } /* Sinon : la carte potentiellement gagnante n'est pas un atout */ else {
                                cartesAtoutsPlusForts.add(cartesEnMain.get(i));
                            }
                        } /* Sinon ce n'est pas un atout : carte quelconque */ else {
                            cartesQuelconques.add(cartesEnMain.get(i));
                        }
                    }
                }

                Carte.TypeOrdre min = Carte.TypeOrdre.AS;
                Carte c = null;

                /* Maintenant on teste nos listes de cartes */
                /* Si on a des cartes plus fortes que cWin et de la même famille que la première */
                if (!cartesPlusFortes.isEmpty()) {
                    for (int i = 0; i < cartesPlusFortes.size(); i++) {
                        if (cartesPlusFortes.get(i).getOrdre().compareTo(min) <= 0) {
                            min = cartesPlusFortes.get(i).getOrdre();
                            c = cartesPlusFortes.get(i);
                        }
                    }
                } else {
                    /* Si on a des cartes plus faibles que cWin et de la même famille que la première */
                    if (!cartesPlusFaibles.isEmpty()) {
                        for (int i = 0; i < cartesPlusFaibles.size(); i++) {
                            if (cartesPlusFaibles.get(i).getOrdre().compareTo(min) <= 0) {
                                min = cartesPlusFaibles.get(i).getOrdre();
                                c = cartesPlusFaibles.get(i);
                            }
                        }
                    } else {
                        /* Si on a des atouts plus forts que cWin */
                        if (!cartesAtoutsPlusForts.isEmpty()) {
                            for (int i = 0; i < cartesAtoutsPlusForts.size(); i++) {
                                if (cartesAtoutsPlusForts.get(i).getOrdre().compareTo(min) <= 0) {
                                    min = cartesAtoutsPlusForts.get(i).getOrdre();
                                    c = cartesAtoutsPlusForts.get(i);
                                }
                            }
                        } else {
                            /* Si on a des atouts moins forts que cWin */
                            if (!cartesAtoutsMoinsForts.isEmpty()) {
                                for (int i = 0; i < cartesAtoutsMoinsForts.size(); i++) {
                                    if (cartesAtoutsMoinsForts.get(i).getOrdre().compareTo(min) <= 0) {
                                        min = cartesAtoutsMoinsForts.get(i).getOrdre();
                                        c = cartesAtoutsMoinsForts.get(i);
                                    }
                                }
                            } /* Sinon on joue une carte quelconque min */ else {
                                for (int i = 0; i < cartesQuelconques.size(); i++) {
                                    if (cartesQuelconques.get(i).getOrdre().compareTo(min) <= 0) {
                                        min = cartesQuelconques.get(i).getOrdre();
                                        c = cartesQuelconques.get(i);
                                    }
                                }
                            }
                        }
                    }
                }
                return c;
            }
        }
    }

    /**
     * Méthode qui détecte la fin d'un jeu
     * 
     * @param joueursPartie ArrayList<Joueur> Les joueurs de la partie
     * @return boolean True si la partie est finie sinon False
     * @see Joueur
     */
    boolean finJeu(ArrayList<Joueur> joueursPartie) {
        if (tasDeCartes.isEmpty()) {
            for (int i = 0; i < joueursPartie.size(); i++) {
                if (!(joueursPartie.get(i).getCartesEnMain().isEmpty())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    
}
