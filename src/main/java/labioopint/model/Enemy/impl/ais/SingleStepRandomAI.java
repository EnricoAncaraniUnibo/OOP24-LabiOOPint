package labioopint.model.Enemy.impl.ais;

import java.util.List;
import java.util.Random;

import labioopint.controller.api.ActionPredicate;
import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Enemy.impl.MovementUtilities;
import labioopint.model.api.Coordinate;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

public class SingleStepRandomAI implements EnemyAI {

    private Random rand = new Random();

    @Override
    public Coordinate getNextPosition(final Labyrinth maze, final List<Player> players, final Coordinate current) {
        Direction dir = Direction.UP;
        int result;
        
        Coordinate newPos = new Coordinate(current.getRow(), current.getColumn());
        Boolean success = false;
        while (!success) {
            result = rand.nextInt(4);
            switch (result) {
                case 0:
                    dir = Direction.UP;
                    break;
                case 1:
                    dir = Direction.DOWN;
                    break;
                case 2:
                    dir = Direction.LEFT;
                    break;
                case 3:
                    dir = Direction.RIGHT;
                    break;
                default:
                    break;
            }
            Coordinate next = MovementUtilities.getNextCoordinate(newPos, dir);
            if (ActionPredicate.EnemyCanMove(dir, maze)) {
                newPos = next;
                success = true;
            }
        }
        return newPos;
    }
}
