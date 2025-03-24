package labioopint.controller.api;

import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.maze.impl.Player;
import labioopint.model.PowerUp.api.PowerUp;


public interface GameController {
    /* BLOCK MOVEMENT 
     * Return true if the block can move
     * Return false if the block can't move
     */
    public boolean MoveBlock(Direction dir,Block block);

    /* PLAYER MOVEMENT
     * Return true if the player can move in a specific direction
     * Otherwise it returns false
     */
    public boolean MovePlayer(Direction dir, Player p);

    /* ENEMY MOVEMENT
     * Return true if the enemy can move in a specific direction
     * Otherwise it returns false
     */
    public boolean MoveEnemy(Direction dir);

    /*
     * USE POWER UP
     */
    public boolean UsePowerUp(PowerUp pUP);
}
