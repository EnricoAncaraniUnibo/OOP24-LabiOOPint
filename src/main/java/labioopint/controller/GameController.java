package labioopint.controller;

import labioopint.model.api.PowerUp;
import labioopint.model.player.Player;
import labioopint.view.GameView;

public class GameController {
    private final GameView view;

    public GameController(GameView view) {
        this.view = view;
    }

    public void activatePowerUp(PowerUp powerUp, Player currentPlayer) {
        if (powerUp.isCollected()) {
            powerUp.activate(currentPlayer);
            view.updatePowerUpStatus(powerUp, currentPlayer);
        } else {
            view.showErrorMessage("Il power-up non è stato raccolto!");
        }
    }

    public void collectPowerUp(PowerUp powerUp) {
        powerUp.collect();
        view.updateCollectedPowerUps(powerUp.getCollectedPowerUps());
    }
}