package labioopint.model.enemy.api;

import java.io.Serializable;
import java.util.List;

import labioopint.controller.api.ActionPredicate;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.utilities.api.Coordinate;

public interface EnemyAI extends Serializable{

    List<Coordinate> getNextPosition(List<Player> players, Coordinate current, ActionPredicate actionPredicate,
            Labyrinth labyrinth);

}