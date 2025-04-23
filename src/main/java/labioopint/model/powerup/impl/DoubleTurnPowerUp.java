package labioopint.model.powerup.impl;

import labioopint.model.core.impl.TurnManager;
import labioopint.model.player.impl.PlayerImpl;

public class DoubleTurnPowerUp extends PowerUpImpl {

    TurnManager turn;

    public DoubleTurnPowerUp(TurnManager tu) {
        super();
        super.setName("Double Turn");
        this.turn = tu;
    }

    @Override
    public void activate(PlayerImpl currentPlayer) {
        if(currentPlayer.getUsablePowerUps().contains(this)) {
    		if (isCollected()) {
                turn.setNextTurnPlayer();
            }
            currentPlayer.removePowerUp(this);
        }
    }
}
