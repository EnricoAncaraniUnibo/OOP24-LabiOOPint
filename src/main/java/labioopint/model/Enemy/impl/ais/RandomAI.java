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
 * Implementation of the {@link EnemyAI} interface that moves the enemy in a
 * random
 * direction for a random number of steps.
 */
public class RandomAI implements EnemyAI {

    private Random rand = new Random();
    private TurnManager turn;
    private ActionPredicate ap;

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
        ap = new ActionPredicate(turn);
        Coordinate newPos = new Coordinate(current.getRow(), current.getColumn());
        int direction = rand.nextInt(4);
        int steps = rand.nextInt(4) + 2; // da 2 a 5
        List<Coordinate> ls = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            Boolean success = false;
            while (!success) {
                Direction dir = MovementUtilities.createDirection(direction);
                Coordinate next = MovementUtilities.getNextCoordinate(newPos, dir);
                if (ap.CanMoveFromPosition(newPos, dir)) {
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
