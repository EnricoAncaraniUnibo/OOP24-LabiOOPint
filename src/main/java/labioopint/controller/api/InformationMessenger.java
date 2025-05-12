package labioopint.controller.api;

import java.util.Optional;

public interface InformationMessenger {

    /**
     * Retrieves the current player's turn information and create a string to see in
     * the view.
     *
     * @return a string representing the current player's ID(that is the player name
     *         too).
     */
    String getTurn();

    /**
     * Retrieves the current action that the player needs to perform.
     *
     * @return a string describing the current action, or an empty string if no
     *         action is set.
     */
    String getAction();

    /**
     * Retrieves the list of usable power-ups for the current player.
     *
     * @return an array of strings containing the names of the usable power-ups.
     */
    String[] getPowerUpsList();

    /**
     * Set the winner string if there is a winner
     * 
     * @return an optional of a string if there is a winner, an empty optional otherwise
     */
    Optional<String> getWinner();

    /**
     * 
     * @return a String with
     * player 1 : powerUp taken
     * player 2 : powerUp taken
     * ...
     */
    String getNamesScores();

}
