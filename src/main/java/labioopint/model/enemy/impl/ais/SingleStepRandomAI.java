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

public class SingleStepRandomAI implements EnemyAI{

    public static final long serialVersionUID = 1L;
    private final Random rand = new Random();

    @Override
    public List<Coordinate> getNextPosition(final List<Player> players, final Coordinate current, ActionPredicate actionPredicate, Labyrinth labyrinth) {
        Direction dir;
        int result;
        Coordinate newPos = new CoordinateImpl(current.getRow(), current.getColumn());
        Boolean success = false;
        while (!success) {
            result = rand.nextInt(4);
            dir = MovementUtilities.createDirection(result);
            final Coordinate next = MovementUtilities.getNextCoordinate(newPos, dir);
            if (actionPredicate.enemyCanMove(dir, labyrinth.getEnemy().getSecond())) {
                newPos = next;
                success = true;
            }
        }
        final List<Coordinate> ls = new ArrayList<>();
        ls.add(newPos);
        return ls;
    }
}
