package labioopint.model.player.impl;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.Movable;
import labioopint.model.maze.api.Direction;

public class Player extends Movable {
    private final Integer id;
    private final List<PowerUp> objectives;
    private final List<PowerUp> usablePowerUps;
    private final Image image;
    private final Image imageTurn;

    public Player(final Integer id, final List<Image> im) {
        this.id = id;
        objectives = new ArrayList<>();
        usablePowerUps = new ArrayList<>();
        this.image = im.get(0);
        this.imageTurn = im.get(1);
    }

    public void addObjective(final PowerUp pu) {
        objectives.add(pu);
        usablePowerUps.add(pu);
    }

    public Image getImage() {
        if(TurnManager.GetCurrentPlayer()!=this) {
            return image;
        } else {
            return imageTurn;
        }
    }

    public Coordinate move(final Coordinate old, final Direction d) {
        if(IsMovable()) {
            switch (d) {
                case UP:
                    return getNewCoordinate(old, -1, 0);
                case DOWN:
                    return getNewCoordinate(old, +1, 0);
                case RIGHT:
                    return getNewCoordinate(old, 0, +1);
                case LEFT:
                    return getNewCoordinate(old, 0, -1);
            }
        }
        return old;
    }

    private Coordinate getNewCoordinate(final Coordinate coor, final Integer newRow, final Integer newColumn) {
        Coordinate newCoordinate = new Coordinate(coor.getRow() + newRow, coor.getColumn() + newColumn);
        return newCoordinate;
    }

    public Integer getID() {
        return id;
    }

    public void removeFirst() {
        objectives.remove(0);
    }
}
