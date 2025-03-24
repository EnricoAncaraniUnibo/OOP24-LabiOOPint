package labioopint.model.Enemy.impl.ais;

import java.util.List;
import java.util.Random;

import labioopint.commons.Coordinate;
import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Enemy.impl.MovementUtilities;
import labioopint.model.Labyrinth.api.Labyrinth;
import labioopint.model.Player.api.Player;

public class RandomAI implements EnemyAI {

    private Random rand = new Random();

    @Override
    public Coordinate getNextPosition(Labyrinth maze, List<Player> players, Coordinate current) {

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
