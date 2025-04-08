package labioopint.model.PowerUp.impl;

import labioopint.model.Player.impl.PlayerImpl;

public class CrossWallPowerUp extends PowerUpImpl {
    @Override
    public void activate(PlayerImpl currentPlayer) {
        if (isCollected()) {
            // Logica specifica per attraversare un muro
        }
    }
}
