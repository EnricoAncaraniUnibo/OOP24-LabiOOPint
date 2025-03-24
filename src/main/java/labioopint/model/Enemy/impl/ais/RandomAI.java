package labioopint.model.Enemy.impl.ais;

import java.util.List;
import java.util.Random;

import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Enemy.impl.MovementUtilities;
import labioopint.model.api.Coordinate;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

public class RandomAI implements EnemyAI {

    private Random rand = new Random();

    @Override
    public Coordinate getNextPosition(final Labyrinth maze, final List<Player> players, final Coordinate current) {

        Coordinate newPos = new Coordinate(current.getRow(), current.getColumn());
        int direction = rand.nextInt(4);
        int steps = rand.nextInt(4) + 2; // da 2 a 5

        for (int i = 0; i < steps; i++) {
            Boolean success = false;
            while (!success) {
                Coordinate next = MovementUtilities.getNextCoordinate(newPos, direction);
                if (ActionPredicate.isValidPosition(newPos, next)) {
                    newPos = next;
                    success = true;
                } else {
                    direction = rand.nextInt(4);
                }
            }
        }
        return newPos;

    }

}
