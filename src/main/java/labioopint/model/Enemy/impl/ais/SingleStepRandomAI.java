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

public class SingleStepRandomAI implements EnemyAI {

    private Random rand = new Random();
    private ActionPredicate ap;
    private TurnManager turn;

    public SingleStepRandomAI(TurnManager tu) {
       turn = tu;
    }

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
