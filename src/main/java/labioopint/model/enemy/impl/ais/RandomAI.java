package labioopint.model.enemy.impl.ais;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.impl.ActionPredicateImpl;
import labioopint.model.api.Coordinate;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.EnemyAI;
import labioopint.model.enemy.impl.MovementUtilities;
import labioopint.model.maze.api.Direction;
import labioopint.model.player.impl.PlayerImpl;

/**
 * Implementation of the {@link EnemyAI} interface that moves the enemy in a
 * random
 * direction for a random number of steps.
 */
public class RandomAI implements EnemyAI, Serializable {

    private final Random rand = new Random();
    private final TurnManager turn;

    /**
     * Constructs a RandomAI with the given TurnManager.
     *
     * @param tu the TurnManager used to manage game state and validate moves
     */
    public RandomAI(final TurnManager tu) {
        turn = tu;
    }

    /**
     * Determines the next positions for the enemy by randomly selecting a direction
     * and moving a random number of steps (between 2 and 5).
     *
     * @param players the list of players in the game
     * @param current the current position of the enemy
     * @return a list of coordinates representing the enemy's path
     */
    @Override
    public List<Coordinate> getNextPosition(final List<PlayerImpl> players, final Coordinate current) {
        final ActionPredicate ap = new ActionPredicateImpl(turn);
        Coordinate newPos = new Coordinate(current.getRow(), current.getColumn());
        int direction = rand.nextInt(4);
        final int steps = rand.nextInt(4) + 2; // da 2 a 5
        final List<Coordinate> ls = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            Boolean success = false;
            while (!success) {
                final Direction dir = MovementUtilities.createDirection(direction);
                final Coordinate next = MovementUtilities.getNextCoordinate(newPos, dir);
                if (ap.enemyCanMoveFromPosition(newPos, dir)) {
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
