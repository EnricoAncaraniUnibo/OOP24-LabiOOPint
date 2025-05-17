package labioopint.model.enemy.impl.ais;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import labioopint.controller.api.ActionPredicate;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.enemy.api.EnemyAI;
import labioopint.model.enemy.impl.MovementUtilities;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
/**
 * The enemyAi that moves x steps random with random Directions.
 */
public final class RandomAI implements EnemyAI {

    public static final long serialVersionUID = 1L;
    private final Random rand = new Random();

    @Override
    public List<Coordinate> getNextPosition(final List<Player> players, final Coordinate current,
            final ActionPredicate actionPredicate, final Labyrinth labyrinth) {
        Coordinate newPos = new CoordinateImpl(current.getRow(), current.getColumn());
        int direction = rand.nextInt(4);
        final int steps = rand.nextInt(4) + 2; // da 2 a 5
        final List<Coordinate> ls = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            Boolean success = false;
            while (!success) {
                final Direction dir = MovementUtilities.createDirection(direction);
                final Coordinate next = MovementUtilities.getNextCoordinate(newPos, dir);
                if (actionPredicate.enemyCanMoveFromPosition(newPos, dir, labyrinth)) {
                    newPos = next;
                    success = true;
                } else {
                    direction = rand.nextInt(4);
                }
            }
            ls.add(newPos);
        }
        return ls;
    }
}
