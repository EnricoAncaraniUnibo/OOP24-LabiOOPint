package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.Core.api.Direction;
import labioopint.model.Enemy.impl.Coordinate;

public class TurnPlayer {

    private Player current;

    public Player getCurrentPlayer() {
        return current;
    }

    public void setContext(Player p) {
        current = p;
    }

    public void actionMovement(Direction d) {
        Coordinate newCoordinate = current.move(d);
        // SERVE LA VECCHIA POSIZIONE
        if(actionPredicate.isValid(vecchia pos, newCoordinate)) {
            
        }
    }

    public void actionPowerUp() {

    }

    public void actionShift() {

    }

}
