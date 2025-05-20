package labioopint.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import labioopint.controller.api.GameController;
import labioopint.controller.api.InformationMessenger;
import labioopint.model.utilities.impl.ActionType;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;

/**
 * Implementation of the class used to retrive information from the game core
 * and to change them for the view.
 */
public final class InformationMessengerImpl implements InformationMessenger {
    public static final long serialVersionUID = 1L;

    @Override
    public String getTurn(final GameController gameController) {
        return "Player: " + gameController.getCurrentPlayer().getID();
    }

    @Override
    public String getAction(final GameController gameController) {
        if (gameController.getTurnManager().getCurrentAction() == ActionType.BLOCK_PLACEMENT) {
            return "Posizionare il blocco";
        }
        if (gameController.getTurnManager().getCurrentAction() == ActionType.PLAYER_MOVEMENT) {
            return "Muovere il personaggio";
        }
        return "";
    }

    @Override
    public String[] getPowerUpsList(final GameController gameController) {
        final List<PowerUp> powerUpsList = new ArrayList<>();
        powerUpsList.addAll(gameController.getCurrentPlayer().getUsablePowerUps());
        final String[] names = new String[powerUpsList.size()];
        int i = 0;
        for (final PowerUp powerUp : powerUpsList) {
            names[i] = powerUp.getName();
            i++;
        }
        return names;
    }

    @Override
    public Optional<String> getWinner(final GameController gameController) {
        if (gameController.getLabyrinth().checkWinner().isPresent()) {
            return Optional.of("Ha vinto: " + gameController.getLabyrinth().checkWinner().get().getID());
        }
        return Optional.empty();
    }

    @Override
    public String getNamesScores(final GameController gameController) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Player player : gameController.getLabyrinth().getPlayers()) {
            if (player.isInvincibilityStatus()) {
                stringBuilder.append("invincible ").append(player.getID()).append(": ")
                        .append(player.getObjetives().size()).append('\n');
            } else {
                stringBuilder.append(player.getID()).append(": ").append(player.getObjetives().size()).append('\n');
            }
        }
        return stringBuilder.toString();
    }
}
