package labioopint.model.PowerUp.impl;

import labioopint.model.player.impl.Player;

public class DoubleTurnPowerUp extends PowerUpImpl {
    @Override
    public void activate(Player currentPlayer) {
        if (isCollected()) {
            // Logica specifica per avere un doppio turno
        }
    }
}
