package labioopint.model.enemy.api;

import java.io.Serializable;
import java.util.List;

import labioopint.controller.api.ActionPredicate;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;

public interface Enemy extends Serializable{

    EnemyAI getEnemyAI();

    List<Coordinate> move(List<Player> players, ActionPredicate actionPredicate, Labyrinth labyrinth);

    void playerHit(List<Player> players, Labyrinth labyrinth);

    Player getLastHit();

    void clearLastHit();

    boolean isPresentLastHit();

}