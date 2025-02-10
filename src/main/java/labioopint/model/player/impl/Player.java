package labioopint.model.player.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.api.Coordinate;
import labioopint.model.api.Movable;
import labioopint.model.maze.api.Direction;

public class Player extends Movable {
    private final Integer id;
    private final List<PowerUp> objectives;
    private final List<PowerUp> usablePowerUps;

    public Player(final Integer id) {
        this.id = id;
        objectives = new ArrayList<>();
        usablePowerUps = new ArrayList<>();
    }

    public void addObjective(final PowerUp pu) {
        objectives.add(pu);
        usablePowerUps.add(pu);
    }

    public Coordinate move(final Coordinate old, final Direction d) {
        if(IsMovable()) {
            switch (d) {
                case UP:
                    return getNewCoordinate(old, -1, 0);
                    break;
                case DOWN:
                    return getNewCoordinate(old, +1, 0);
                    break;
                case RIGHT:
                    return getNewCoordinate(old, 0, +1);
                    break;
                case LEFT:
                    return getNewCoordinate(old, 0, -1);
                    break;
            }
        }
    }

    private Coordinate getNewCoordinate(final Coordinate coor, final Integer newRow, final Integer newColumn) {
        Coordinate newCoordinate = new Coordinate(coor.getRow() + newRow, coor.getColumn() + newColumn);
        return newCoordinate;
    }

    public Integer getID() {
        return id;
    }

    public void usePowerUp(PowerUp p) {
        if (usablePowerUps.contains(p)) {
            p.use();
            usablePowerUps.remove(p);
        }
    }
}
