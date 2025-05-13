package labioopint.controller.api;

import java.io.Serializable;
import java.util.Optional;
/**
 * Represents a messenger interface for retrieving various game-related information,
 * such as the current turn, actions, power-ups, and scores.
 */
public interface InformationMessenger extends Serializable {

    /**
     * Retrieves the current turn information.
     *
     * @return a {@link String} representing the current turn
     */
    String getTurn();

    /**
     * Retrieves the current action being performed.
     *
     * @return a {@link String} representing the current action
     */
    String getAction();

    /**
     * Retrieves the list of available power-ups.
     *
     * @return an array of {@link String} containing the power-ups
     */
    String[] getPowerUpsList();

    /**
     * Retrieves the winner of the game, if available.
     *
     * @return an {@link Optional} containing the winner's name as a {@link String}, or empty if no winner exists
     */
    Optional<String> getWinner();

    /**
     * Retrieves the names and scores of players in the game.
     *
     * @return a {@link String} representing the names and scores
     */
    String getNamesScores();

}
