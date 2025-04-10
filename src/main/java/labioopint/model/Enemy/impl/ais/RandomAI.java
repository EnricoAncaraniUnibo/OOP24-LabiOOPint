package labioopint.model.Enemy.impl.ais;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import labioopint.controller.impl.ActionPredicate;
import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Enemy.impl.MovementUtilities;
import labioopint.model.Maze.api.Direction;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;

public class RandomAI implements EnemyAI {

    private Random rand = new Random();

    @Override
    public List<Coordinate> getNextPosition(final List<PlayerImpl> players, final Coordinate current) {

        Coordinate newPos = new Coordinate(current.getRow(), current.getColumn());
        int direction = rand.nextInt(4);
        int steps = rand.nextInt(4) + 2; // da 2 a 5
        List<Coordinate> ls = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            Boolean success = false;
            while (!success) {
                Direction dir = MovementUtilities.createDirection(direction);
                Coordinate next = MovementUtilities.getNextCoordinate(newPos, dir);
                if (ActionPredicate.CanMoveFromPosition(newPos,dir)) {
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
