package labioopint.controller.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import labioopint.controller.api.InformationMessenger;
import labioopint.model.api.ActionType;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;

/**
 * The InformationMessenger class provides utility methods to retrieve
 * information
 * about the current game state.
 * The informations provided by this class are transformed to permit the output
 * on the view.
 */
public final class InformationMessengerImpl implements InformationMessenger, Serializable {
    private final TurnManager turn;

    /**
     * Constructs an InformationMessenger with the specified TurnManager.
     *
     * @param tu the TurnManager instance to manage game turns
     */
    public InformationMessengerImpl(final TurnManager tu) {
        turn = tu;
    }

    /**
     * Retrieves the current player's turn information and create a string to see in
     * the view.
     *
     * @return a string representing the current player's ID(that is the player name
     *         too).
     */
    @Override
    public String getTurn() {
        return "Player: " + turn.getCurrentPlayer().getID();
    }

    /**
     * Retrieves the current action that the player needs to perform.
     *
     * @return a string describing the current action, or an empty string if no
     *         action is set.
     */
    @Override
    public String getAction() {
        if (turn.getCurrentAction() == ActionType.BLOCK_PLACEMENT) {
            return "Posizionare il blocco";
        }
        if (turn.getCurrentAction() == ActionType.PLAYER_MOVEMENT) {
            return "Muovere il personaggio";
        }
        return "";
    }

    /**
     * Retrieves the list of usable power-ups for the current player.
     *
     * @return an array of strings containing the names of the usable power-ups.
     */
    @Override
    public String[] getPowerUpsList() {
        final List<PowerUp> lpu = new ArrayList<>();
        lpu.addAll(turn.getCurrentPlayer().getUsablePowerUps());
        final String[] names = new String[lpu.size()];
        int i = 0;
        for (final PowerUp pu : lpu) {
            names[i] = pu.getName();
            i++;
        }
        return names;
    }

    @Override
    public Optional<String> getWinner() {
        if (turn.getLab().getWinner().isPresent()) {
            return Optional.of("Ha vinto: " + turn.getLab().getWinner().get().getID());
        }
        return Optional.empty();
    }

    public String getNamesScores() {
        StringBuilder sb = new StringBuilder();
        for (Player p : turn.getPlayers()) {
            sb.append(p.getID()).append(": ").append(p.getObjetives().size()).append("\n");
        }
        return sb.toString();
    }
}
