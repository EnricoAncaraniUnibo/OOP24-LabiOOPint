package labioopint.model.PowerUp.impl;

import labioopint.model.Player.impl.PlayerImpl;

public class DoubleTurnPowerUp extends PowerUpImpl {
    @Override
    public void activate(PlayerImpl currentPlayer) {
        if (isCollected()) {
            // Logica specifica per avere un doppio turno
        }
    }
}
