package labioopint.model.powerup.impl;

import labioopint.model.player.impl.PlayerImpl;

public class DoubleTurnPowerUp extends PowerUpImpl {
    @Override
    public void activate(PlayerImpl currentPlayer) {
        if (isCollected()) {
            // Logica specifica per avere un doppio turno
        }
    }
}
