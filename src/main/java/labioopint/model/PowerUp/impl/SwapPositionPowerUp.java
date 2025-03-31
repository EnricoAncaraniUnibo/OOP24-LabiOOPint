package labioopint.model.PowerUp.impl;

import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.api.Coordinate;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

public class SwapPositionPowerUp extends PowerUpImpl {

    private final Labyrinth labyrinth = TurnManager.GetLab();
    private final List<Player> players = TurnManager.GetPlayers();
    private boolean condition = true;
    private Image image;

    public SwapPositionPowerUp() {
        super();
        try {
            image = ImageIO.read(new File("src/main/java/labioopint/resources/PowerUps/teleport.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
                    this.labyrinth.absoluteUpdateCoordinate(playerSwap,currentPlayerCoordinate);
                    this.labyrinth.absoluteUpdateCoordinate(currentPlayer,playerSwapCoordinate);
                    this.condition = false;
                }
            }
        }
    }

    @Override
    public Image getImage() {
        return image;
    }
}