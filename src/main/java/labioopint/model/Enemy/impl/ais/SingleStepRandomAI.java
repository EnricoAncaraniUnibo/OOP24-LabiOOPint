package labioopint.model.Enemy.impl.ais;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import labioopint.controller.impl.ActionPredicate;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Enemy.impl.MovementUtilities;
import labioopint.model.Maze.api.Direction;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;

/**
 * Implementation of the {@link EnemyAI} interface that moves the enemy one step
 * in a random direction.
 */
public class SingleStepRandomAI implements EnemyAI {

    private Random rand = new Random();
    private ActionPredicate ap;
    private TurnManager turn;

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
        Direction dir = Direction.UP;
        int result;
        ap = new ActionPredicate(turn);
        Coordinate newPos = new Coordinate(current.getRow(), current.getColumn());
        Boolean success = false;
        while (!success) {
            result = rand.nextInt(4);
            dir = MovementUtilities.createDirection(result);
            Coordinate next = MovementUtilities.getNextCoordinate(newPos, dir);
            if (ap.EnemyCanMove(dir)) {
                newPos = next;
                success = true;
            }
        }
        List<Coordinate> ls = new ArrayList<>();
        ls.add(newPos);
        return ls;
    }
}
