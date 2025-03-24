package labioopint.controller.api;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Player;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.Labyrinth;

public interface ActionPredicate {
    /*Check if a player can move in a specific direction
     *True -> Can move
     *False -> Cannot move
     */
    boolean PlayerCanMove(Player p, Direction dir, Labyrinth lab);
    /*
     *Check if a block can be moved in a specific direction
     *True -> Can be moved
     *False -> Cannot be moved
     */
    boolean BlockCanMove(Block b, Direction dir, Labyrinth lab);
    /*
     *Check if the enemy can move in a specific direction
     *True -> Can move
     *False -> Cannote move
     */
    boolean EnemyCanMove(Enemy e, Direction dir, Labyrinth lab);
}
