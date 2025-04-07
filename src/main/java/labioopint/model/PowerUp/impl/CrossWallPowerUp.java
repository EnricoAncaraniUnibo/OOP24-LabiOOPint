package labioopint.model.PowerUp.impl;

import labioopint.model.player.impl.Player;

public class CrossWallPowerUp extends PowerUpImpl {
    @Override
    public void activate(Player currentPlayer) {
        if (isCollected()) {
            // Logica specifica per attraversare un muro
        }
    }
}
