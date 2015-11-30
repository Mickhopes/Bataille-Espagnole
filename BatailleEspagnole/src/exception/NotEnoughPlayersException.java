/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * Classe qui sert à définir une exception générée lors 
 * du lancement d'une partie avec un nombre insuffisant de joueurs
 * 
 * @author Mickaël TURNEL
 */
public class NotEnoughPlayersException extends Exception {
    /**
     * Message de l'exception
     */
    private String message;
    
    /**
     * Constructeur par défaut de l'exception
     */
    public NotEnoughPlayersException() {
            this("Pas assez de joueurs dans la partie");
    }

    /**
     * Constructeur de l'exception
     * 
     * @param message Le message de l'exception
     */
    public NotEnoughPlayersException(String message) {
            this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
