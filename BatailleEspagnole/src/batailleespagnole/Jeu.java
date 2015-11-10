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
    public Jeu (ArrayList<Joueur> joueursPartie) {
        pliActuel = new HashMap<>();
        for(int i = 0;i<joueursPartie.size();i++){
            pliActuel.put(joueursPartie.get(i), null);
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2BA84C2F-7D3D-E43E-FFE3-8491B1E33686]
    // </editor-fold> 
    public TypeFamille getAtout () {
        return atout;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F1781461-6A98-959E-30DE-4F61DDD67FEA]
    // </editor-fold> 
    public void setAtout (TypeFamille val) {
        this.atout = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8334162B-10E8-A996-E90D-EA277FBD0C1F]
    // </editor-fold> 
    public int getNbPlis () {
        return nbPlis;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.77D0D63E-84B4-1E0A-058C-1A99872C9271]
    // </editor-fold> 
    public void setNbPlis (int val) {
        this.nbPlis = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EA483816-8429-22EF-D30C-6BAC08AFA7AB]
    // </editor-fold> 
    public void comptePoints () {
        
    }
    
    public void creerPaquet(){
        
        /* On crée les 52 cartes */
        for(TypeFamille f : TypeFamille.values()){
            for(Carte.TypeOrdre o : Carte.TypeOrdre.values()){
                tasDeCartes.add(new Carte(o, f));
            }           
        }
    }
    
    public void melangerCartes(){
        Collections.shuffle(tasDeCartes);
    }

    public Joueur determineVainqueur(){
        /* Pour toutes les cartes jouées du pli */
        Carte.TypeOrdre max = Carte.TypeOrdre.DEUX;
        Joueur jMax = null;
        boolean maxAtout = false;
        for(Entry<Joueur, Carte> e : pliActuel.entrySet()){
            /* si la carte actuelle est un atout */
            if(e.getValue().getFamille().equals(atout)){
                
                /* Si la carte a un ordre plus grand que le max actuel */
                if(e.getValue().getOrdre().compareTo(max) == 1){
                    max = e.getValue().getOrdre();
                    jMax = e.getKey();
                    maxAtout = true;
                }
            }
            /* si la carte actuelle n'est pas un atout */
            else{
                if(!maxAtout){
                    if(e.getKey().get)
                    if(e.getValue().getOrdre().compareTo(max) == 1){
                        jMax = e.getKey();
                        max = e.getValue().getOrdre();
                    }
                }
            }
        }
        return jMax;
    }
}

