package labioopint.model.enemy.impl.ais;

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
 * Implementation of the {@link EnemyAI} interface that moves the enemy one step
 * in a random direction.
 */
public class SingleStepRandomAI implements EnemyAI {

    private final Random rand = new Random();
    private final TurnManager turn;

    /**
     * Constructs a SingleStepRandomAI with the given TurnManager.
     *
     * @param tu the TurnManager used to manage game state and validate moves
     */
    public SingleStepRandomAI(final TurnManager tu) {
        turn = tu;
    }

    /**
     * Determines the next position for the enemy by randomly selecting a direction
     * and validating the move.
     *
     * @param players the list of players in the game
     * @param current the current position of the enemy
     * @return a list containing the next position of the enemy
     */
    @Override
    public List<Coordinate> getNextPosition(final List<PlayerImpl> players, final Coordinate current) {
        Direction dir;
        int result;
        final ActionPredicate ap = new ActionPredicateImpl(turn);
        Coordinate newPos = new Coordinate(current.getRow(), current.getColumn());
        Boolean success = false;
        while (!success) {
            result = rand.nextInt(4);
            dir = MovementUtilities.createDirection(result);
            final Coordinate next = MovementUtilities.getNextCoordinate(newPos, dir);
            if (ap.EnemyCanMove(dir)) {
                newPos = next;
                success = true;
            }
        }
        final List<Coordinate> ls = new ArrayList<>();
        ls.add(newPos);
        return ls;
    }
}
