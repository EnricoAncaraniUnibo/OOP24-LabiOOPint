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
     * @param gameController the game from which i take the information from
     * @return a {@link String} representing the current turn
     */
    String getTurn(GameController gameController);

    /**
     * Retrieves the current action being performed.
     *
     * @param gameController the game from which i take the information from
     * @return a {@link String} representing the current action
     */
    String getAction(GameController gameController);

    /**
     * Retrieves the list of available power-ups.
     *
     * @param gameController the game from which i take the information from
     * @return an array of {@link String} containing the power-ups
     */
    String[] getPowerUpsList(GameController gameController);

    /**
     * Retrieves the winner of the game, if available.
     *
     * @param gameController the game from which i take the information from
     * @return an {@link Optional} containing the winner's name as a {@link String}, or empty if no winner exists
     */
    Optional<String> getWinner(GameController gameController);

    /**
     * Retrieves the names and scores of players in the game.
     *
     * @param gameController the game from which i take the information from
     * @return a {@link String} representing the names and scores
     */
    String getNamesScores(GameController gameController);

}
