package labioopint.controller.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.ActionType;

/**
 * The InformationMessenger class provides utility methods to retrieve
 * information
 * about the current game state.
 * The informations provided by this class are transformed to permit the output
 * on the view.
 */
public class InformationMessenger {
    private TurnManager turn;

     /**
     * Constructs an InformationMessenger with the specified TurnManager.
     *
     * @param tu the TurnManager instance to manage game turns
     */
    public InformationMessenger(final TurnManager tu) {
        turn = tu;
    }

    /**
     * Retrieves the current player's turn information and create a string to see in
     * the view.
     *
     * @return a string representing the current player's ID(that is the player name
     *         too).
     */
    public String getTurn() {
        return "Player: " + turn.GetCurrentPlayer().getID();
    }

    /**
     * Retrieves the current action that the player needs to perform.
     *
     * @return a string describing the current action, or an empty string if no
     *         action is set.
     */
    public String getAction() {
        if (turn.GetCurrentAction() == ActionType.BLOCK_PLACEMENT) {
            return "Posizionare il blocco";
        }
        if (turn.GetCurrentAction() == ActionType.PLAYER_MOVEMENT) {
            return "Muovere il personaggio";
        }
        return "";
    }

    /**
     * Retrieves the list of usable power-ups for the current player.
     *
     * @return an array of strings containing the names of the usable power-ups.
     */
    public String[] getPowerUpsList() {
        List<PowerUp> lpu = new ArrayList<>();
        lpu.addAll(turn.GetCurrentPlayer().getUsablePowerUps());
        String[] names = new String[lpu.size()];
        int i = 0;
        for (PowerUp pu : lpu) {
            names[i] = (pu.getName());
            i++;
        }
        return names;
    }
}
