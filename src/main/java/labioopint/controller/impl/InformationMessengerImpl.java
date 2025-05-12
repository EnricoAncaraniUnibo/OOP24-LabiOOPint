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
    public static final long serialVersionUID = 1L;
    private final TurnManager turnManager;

    /**
     * Constructs an InformationMessenger with the specified TurnManager.
     *
     * @param tu the TurnManager instance to manage game turns
     */
    public InformationMessengerImpl(final TurnManager tu) {
        turnManager = tu;
    }

    @Override
    public String getTurn() {
        return "Player: " + turnManager.getCurrentPlayer().getID();
    }

    @Override
    public String getAction() {
        if (turnManager.getCurrentAction() == ActionType.BLOCK_PLACEMENT) {
            return "Posizionare il blocco";
        }
        if (turnManager.getCurrentAction() == ActionType.PLAYER_MOVEMENT) {
            return "Muovere il personaggio";
        }
        return "";
    }

    @Override
    public String[] getPowerUpsList() {
        final List<PowerUp> powerUpsList = new ArrayList<>();
        powerUpsList.addAll(turnManager.getCurrentPlayer().getUsablePowerUps());
        final String[] names = new String[powerUpsList.size()];
        int i = 0;
        for (final PowerUp powerUp : powerUpsList) {
            names[i] = powerUp.getName();
            i++;
        }
        return names;
    }

    @Override
    public Optional<String> getWinner() {
        if (turnManager.getLab().getWinner().isPresent()) {
            return Optional.of("Ha vinto: " + turnManager.getLab().getWinner().get().getID());
        }
        return Optional.empty();
    }

    @Override
    public String getNamesScores() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Player player : turnManager.getPlayers()) {
            stringBuilder.append(player.getID()).append(": ").append(player.getObjetives().size()).append('\n');
            //stringBuilder.append(player.getID() + ": " + player.getObjetives().size() + '\n');
        }
        return stringBuilder.toString();
    }
}
