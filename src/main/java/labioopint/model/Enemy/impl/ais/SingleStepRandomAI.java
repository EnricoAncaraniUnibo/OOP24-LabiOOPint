package labioopint.model.Enemy.impl.ais;

import java.util.List;
import java.util.Random;

import labioopint.commons.Coordinate;
import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Enemy.impl.MovementUtilities;
import labioopint.model.Labyrinth.api.Labyrinth;
import labioopint.model.Player.api.Player;

public class SingleStepRandomAI implements EnemyAI {

    private Random rand = new Random();

    @Override
    public Coordinate getNextPosition(Labyrinth maze, List<Player> players, Coordinate current) {
        int direction = rand.nextInt(4);
        Coordinate newPos = new Coordinate(current.getRow(), current.getColumn());
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
        return newPos;
    }

}
