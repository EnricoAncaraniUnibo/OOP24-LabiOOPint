package labioopint.model.PowerUp.impl;

import java.util.Random;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.api.Coordinate;
import labioopint.model.player.impl.Player;

public class SwapPositionPowerUp extends PowerUpImpl {

    private boolean condition = true;

    public SwapPositionPowerUp() {
        super();
        super.setName("Teleport");
    }

    @Override
    public void activate(Player currentPlayer) {
        if (isCollected()) {
            Coordinate currentPlayerCoordinate = TurnManager.GetLab().getPlayerCoordinate(currentPlayer);
            Random random = new Random();
            while(this.condition) {
                Player playerSwap = TurnManager.GetPlayers().get(random.nextInt(TurnManager.GetPlayers().size()));
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