package labioopint.model.powerup.impl;

import labioopint.model.player.impl.PlayerImpl;

public class CrossWallPowerUp extends PowerUpImpl {

    public CrossWallPowerUp() {
        super();
        super.setName("Cross Wall");
    }
    
    @Override
    public void activate(PlayerImpl currentPlayer) {
        if (isCollected()) {
            // Logica specifica per attraversare un muro
        }
    }
}
