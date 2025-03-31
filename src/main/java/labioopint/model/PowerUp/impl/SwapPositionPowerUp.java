package labioopint.model.PowerUp.impl;

import java.util.List;
import java.util.Random;
import labioopint.model.Core.impl.TurnMenager;
import labioopint.model.api.Coordinate;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

public class SwapPositionPowerUp extends PowerUpImpl {

    private final Labyrinth labyrinth = TurnMenager.GetLab();
    private final List<Player> players = TurnMenager.GetPlayers();
    private boolean condition = true;

    @Override
    public void activate(Player currentPlayer) {
        if (isCollected()) {
            Coordinate currentPlayerCoordinate = this.labyrinth.getPlayerCoordinate(currentPlayer);
            Random random = new Random();
            while(this.condition) {
                Player playerSwap = this.players.get(random.nextInt(this.players.size()));
                Coordinate playerSwapCoordinate = this.labyrinth.getPlayerCoordinate(playerSwap);
                if(this.players.size() < 2) {
                    this.condition = false;
                } 
                else if(this.players.size() > 1 && !currentPlayer.equals(playerSwap)){
                    this.labyrinth.updateCoordinate(playerSwap,currentPlayerCoordinate);
                    this.labyrinth.updateCoordinate(currentPlayer,playerSwapCoordinate);
                    this.condition = false;
                }
            }
        }
    }
}