package labioopint.model.PowerUp.impl;

import java.util.Random;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;

public class SwapPositionPowerUp extends PowerUpImpl {

    private boolean condition = true;

    public SwapPositionPowerUp() {
        super();
        super.setName("Teleport");
    }

    @Override
    public void activate(PlayerImpl currentPlayer) {
        if (isCollected()) {
            Coordinate currentPlayerCoordinate = TurnManager.GetLab().getPlayerCoordinate(currentPlayer);
            Random random = new Random();
            while(this.condition) {
                PlayerImpl playerSwap = TurnManager.GetPlayers().get(random.nextInt(TurnManager.GetPlayers().size()));
                Coordinate playerSwapCoordinate = TurnManager.GetLab().getPlayerCoordinate(playerSwap);
                if(TurnManager.GetPlayers().size() < 2) {
                    this.condition = false;
                } 
                else if(TurnManager.GetPlayers().size() > 1 && !currentPlayer.equals(playerSwap)){
                    TurnManager.GetLab().absoluteUpdateCoordinate(playerSwap,currentPlayerCoordinate);
                    TurnManager.GetLab().absoluteUpdateCoordinate(currentPlayer,playerSwapCoordinate);
                    this.condition = false;
                }
            }
        }
    }
}