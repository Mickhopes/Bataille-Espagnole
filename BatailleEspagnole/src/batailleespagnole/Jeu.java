package batailleespagnole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.9F89383C-EBD4-AA66-4B9E-9EBDD505F42E]
// </editor-fold> 
public class Jeu {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B143A8BF-D54D-B324-D01A-BAE760DE3DDA]
    // </editor-fold> 
    private int nbPlis;

    private HashMap<Joueur, Carte> pliActuel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.91CF4B32-C932-208C-D48A-655DA4508BAC]
    // </editor-fold> 
    private TypeFamille atout;

    private LinkedList<Carte> tasDeCartes;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AA2EBD6B-B27A-F705-4B38-19DFF457F2D9]
    // </editor-fold> 
    public Jeu(ArrayList<Joueur> joueursPartie) {
        nbPlis = 0;
        tasDeCartes = new LinkedList<>();
        pliActuel = new HashMap<>();
        for (int i = 0; i < joueursPartie.size(); i++) {
            pliActuel.put(joueursPartie.get(i), null);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2BA84C2F-7D3D-E43E-FFE3-8491B1E33686]
    // </editor-fold> 
    public TypeFamille getAtout() {
        return atout;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F1781461-6A98-959E-30DE-4F61DDD67FEA]
    // </editor-fold> 
    public void setAtout(TypeFamille val) {
        this.atout = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8334162B-10E8-A996-E90D-EA277FBD0C1F]
    // </editor-fold> 
    public int getNbPlis() {
        return nbPlis;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.77D0D63E-84B4-1E0A-058C-1A99872C9271]
    // </editor-fold> 
    public void setNbPlis(int val) {
        this.nbPlis = val;
    }

    public HashMap<Joueur, Carte> getPliActuel() {
        return pliActuel;
    }

    public void setPliActuel(HashMap<Joueur, Carte> pliActuel) {
        this.pliActuel = pliActuel;
    }

    public LinkedList<Carte> getTasDeCartes() {
        return tasDeCartes;
    }

    public void setTasDeCartes(LinkedList<Carte> tasDeCartes) {
        this.tasDeCartes = tasDeCartes;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EA483816-8429-22EF-D30C-6BAC08AFA7AB]
    // </editor-fold> 
    public void compterPoints(ArrayList<Joueur> joueursPartie) {
        for (int i = 0; i < joueursPartie.size(); i++) {
            ArrayList<Carte> plisGagnes = joueursPartie.get(i).getPlisGagnes();
            int sum = 0;
            for (int j = 0; j < plisGagnes.size(); i++) {
                sum += plisGagnes.get(j).getValeur();
            }
            joueursPartie.get(i).ajoutPoints(sum);
        }
    }

    public void creerPaquet() {

        /* On crée les 52 cartes */
        for (TypeFamille f : TypeFamille.values()) {
            for (Carte.TypeOrdre o : Carte.TypeOrdre.values()) {
                tasDeCartes.add(new Carte(o, f));
            }
        }
    }

    public void melangerCartes() {
        Collections.shuffle(tasDeCartes);
    }

    public void distribuer(ArrayList<Joueur> joueursPartie) {
        for(int i = 0;i<3;i++){
            for(int j = 0;j < joueursPartie.size();j++){
                joueursPartie.get(j).piocher(tasDeCartes);
            }
        }
    }

    public Joueur determinerVainqueur() {
        /* Pour toutes les cartes jouées du pli */
        Carte.TypeOrdre max = Carte.TypeOrdre.DEUX;
        Joueur jMax = null;
        boolean maxAtout = false;
        TypeFamille famillePremier = null;

        /* On fait un premier parcours de la table de hachage pour trouver quel est le joueur
         qui a posé la première carte et quelle est la famille de cette carte */
        for (Entry<Joueur, Carte> e : pliActuel.entrySet()) {
            if (e.getKey().isPremier()) {
                famillePremier = e.getValue().getFamille();
                /* On remet à false puisqu'on a détecté la famille demandée */
                e.getKey().setPremier(false);
            }
        }
        /* Pour chaque couple de la table de hachage du pli actuel */
        for (Entry<Joueur, Carte> e : pliActuel.entrySet()) {
            /* si la carte actuelle est un atout */
            if (e.getValue().getFamille().equals(atout)) {
                /* Si la carte a un ordre plus grand que le max actuel */
                if (e.getValue().getOrdre().compareTo(max) >= 0) {
                    /* On récupère le joueur potentiellement gagnant et on continue de tester les cartes */
                    max = e.getValue().getOrdre();
                    jMax = e.getKey();
                    /* On indique ici qu'une carte atout a été jouée donc les autres ne comptent pas */
                    maxAtout = true;
                }
            } /* si la carte actuelle n'est pas un atout */ 
            else {
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
        /* On vide le pli actuel */
        pliActuel.clear();
        
        return jMax;
    }

    /* jouerTour affiche un menu + cartes actuelles (+ cartes posées)
     Il joue une de ses cartes (sauf s'il n'en a plus)
     Il en pioche une autre et on l'affiche
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
            System.out.println("==Joueur "+joueursPartie.get(i).getNom()+"==");
            /* On affiche le contenu du pli */
            afficherPliActuel();
            System.out.println("");
            
            
            ArrayList<Carte> cartesEnMain = joueursPartie.get(i).getCartesEnMain();
            
            /* Si le joueur actuel est une IA */
            if(joueursPartie.get(i).equals(i)){
                /* On récupère la carte déterminée par l'algo d'IA */
                Carte c = joueursPartie.get(i).jouer(jouerIA(joueursPartie.get(i)));
                
                /* On ajoute au pli la carte du joueur */
                pliActuel.put(joueursPartie.get(i), c);

                /* On fait piocher notre joueur ensuite */         
                c = joueursPartie.get(i).piocher(tasDeCartes);
            }
            /* Sinon */
            else{
        
                System.out.println("====Cartes actuelles====");
                /* On affiche les cartes en main du joueur */
                for (int j = 0; j < cartesEnMain.size(); j++) {
                    System.out.print(j + 1 + " - ");
                    System.out.println(cartesEnMain.get(j));
                }
                System.out.println("=========================");

                /* On demande au joueur quelle carte il veut jouer */
                int choix;

                do {
                    choix = LectureClavier.lireEntier("Quelle carte voulez-vous jouer ?");
                } while (choix < 1 && choix > 3);

                /* Appel à la fonction jouer */
                Carte c = joueursPartie.get(i).jouer(cartesEnMain.get(choix - 1));

                /* On ajoute au pli la carte du joueur */
                pliActuel.put(joueursPartie.get(i), c);

                /* On fait piocher notre joueur ensuite */         
                c = joueursPartie.get(i).piocher(tasDeCartes);
                System.out.println(joueursPartie.get(i).getNom()+" a pioché : "+c.getOrdre() + " de "+c.getFamille());
            } /* Fin du jouer pour vrai joueur */
            
            i++;
            count++;
            
            /* On repart de 0 si on est à la fin et qu'on n'a toujours pas fait jouer tout le monde */
            if (i == joueursPartie.size()) {
                i = 0;
            }
        }

    }

    /* determinerAtout
     pop une carte du tas, demander aux joueurs si la famille est un atout souhaité 
     sinon on repop une carte (et on repose celle d'avant dessous (et shuffle?)
     on repose la carte en dessous du tas
     */
    public void determinerAtout(){
        atout = TypeFamille.Bâton;
    }
    
    public void afficherPliActuel(){
        System.out.println("=== Cartes du pli actuel === ");
        for (Entry<Joueur, Carte> e : pliActuel.entrySet()){         
            if(e.getValue() != null){
                if(e.getKey().isPremier()){
                    System.out.println("(PREMIER)Joueur "+e.getKey().getNom() + " => : "+e.getValue());
                }
                else{
                    System.out.println("Joueur "+e.getKey().getNom() + " => : "+e.getValue());
                }
            }
        }
        System.out.println("");
    }
    
    public Carte jouerIA(Joueur j){
        ArrayList<Carte> cartesEnMain = j.getCartesEnMain();
        /* S'il y a 0 cartes sur le tapis */
        if(pliActuel.size() == 0){
            /* On récupère un nombre aléatoire entre 0 et le nombre de cartes max du joueur */
            int max = j.getCartesEnMain().size();
            int rand = 0 + (int)(Math.random() * ((max - 0) + 1));
            
            return cartesEnMain.get(rand);
        }
        /* Sinon s'il y a une carte sur le tapis */
        else{
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
            if(pliActuel.size() == 1){
                for(int i = 0;i<cartesEnMain.size();i++){
                    // Si la carte actuelle est de la même famille que la première famille */
                    if(cartesEnMain.get(i).getFamille() == famillePremier){
                        /* Si la carte actuelle de la même famille est plus forte */
                        if(cartesEnMain.get(i).getOrdre().compareTo(premier.getOrdre()) >= 0){
                            return cartesEnMain.get(i);
                        }
                    }
                }
            }
        }
        
    }
}
