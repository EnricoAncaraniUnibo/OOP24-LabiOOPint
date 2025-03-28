package labioopint.model.impl;

import labioopint.model.player.impl;

public class DoubleTurnPowerUp extends PowerUpImpl {
    @Override
    public void activate(Player currentPlayer) {
        if (isCollected()) {
            // Logica specifica per avere un doppio turno
        }
    }
}