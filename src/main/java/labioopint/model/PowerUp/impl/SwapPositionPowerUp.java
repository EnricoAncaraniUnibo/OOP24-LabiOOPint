package labioopint.model.PowerUp.impl;

import java.util.Random;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;

public class SwapPositionPowerUp extends PowerUpImpl {

    private boolean condition = true;
	private TurnManager turn;

    public SwapPositionPowerUp(TurnManager tu) {
        super();
        super.setName("Teleport");
		this.turn = tu;
    }

    @Override
    public void activate(PlayerImpl currentPlayer) {
    	if(currentPlayer.getUsablePowerUps().contains(this)) {
    		if (isCollected()) {
	            Coordinate currentPlayerCoordinate = turn.getLab().getPlayerCoordinate(currentPlayer);
	            Random random = new Random();
	            while(this.condition) {
	                PlayerImpl playerSwap = turn.getPlayers().get(random.nextInt(turn.getPlayers().size()));
	                Coordinate playerSwapCoordinate = turn.getLab().getPlayerCoordinate(playerSwap);
	                if(turn.getPlayers().size() < 2) {
	                    this.condition = false;
	                } 
	                else if(turn.getPlayers().size() > 1 && !currentPlayer.equals(playerSwap)){
	                    turn.getLab().PlayerUpdateCoordinate(playerSwap,currentPlayerCoordinate);
	                    turn.getLab().PlayerUpdateCoordinate(currentPlayer,playerSwapCoordinate);
	                    this.condition = false;
	                }
	            }
	        }
    		currentPlayer.removePowerUp(this);
    	}
    }
}