package labioopint.controller.impl;

import java.io.Serializable;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.api.DirectionCheck;
import labioopint.model.api.Coordinate;
import labioopint.model.api.Pair;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.impl.PlayerImpl;

public class ActionPredicateImpl implements ActionPredicate, Serializable {
    private final LabyrinthImpl lab;
    private final Integer mazeSize;
    private final DirectionCheck dc;
    private final Pair<Boolean, Enemy> e;

    public ActionPredicateImpl(final TurnManager tu) {
        lab = tu.getLab();
        mazeSize = lab.getGrid().getSize();
        dc = new DirectionCheckImpl(tu);
        e = tu.getEnemy();
    }

    @Override
    public boolean playerCanMove(final PlayerImpl p, final Direction dir) {
        final Coordinate playerCoordinate = new Coordinate(lab.getPlayerCoordinate(p));
        if (dir == Direction.LEFT) {
            final Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(), playerCoordinate.getColumn() - 1);
            if (playerCoordinate.getColumn() != 0
                    && dc.checkLeftEntrance(playerCoordinate)
                    && dc.checkRightEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.RIGHT) {
            final Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(), playerCoordinate.getColumn() + 1);
            if (playerCoordinate.getColumn() != mazeSize - 1
                    && dc.checkRightEntrance(playerCoordinate)
                    && dc.checkLeftEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.UP) {
            final Coordinate targetBlock = new Coordinate(playerCoordinate.getRow() - 1, playerCoordinate.getColumn());
            if (playerCoordinate.getRow() != 0
                    && dc.checkUpperEntrance(playerCoordinate)
                    && dc.checkBottomEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.DOWN) {
            final Coordinate targetBlock = new Coordinate(playerCoordinate.getRow() + 1, playerCoordinate.getColumn());
            if (playerCoordinate.getRow() != mazeSize - 1
                    && dc.checkBottomEntrance(playerCoordinate)
                    && dc.checkUpperEntrance(targetBlock)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean blockCanMove(final Coordinate blockCoordinate) {
        final BlockImpl b = lab.getGrid().getBlock(blockCoordinate).get();
        return b.isMove() && (blockCoordinate.getColumn() == 0 || blockCoordinate.getColumn() == mazeSize - 1
                || blockCoordinate.getRow() == 0 || blockCoordinate.getRow() == mazeSize - 1);
    }

    @Override
    public boolean canMoveFromPosition(final Coordinate coor, final Direction dir) {
        if (dir == Direction.LEFT) {
            final Coordinate targetBlock = new Coordinate(coor.getRow(), coor.getColumn() - 1);
            if (coor.getColumn() != 0
                    && dc.checkLeftEntrance(coor)
                    && dc.checkRightEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.RIGHT) {
            final Coordinate targetBlock = new Coordinate(coor.getRow(), coor.getColumn() + 1);
            if (coor.getColumn() != mazeSize - 1
                    && dc.checkRightEntrance(coor)
                    && dc.checkLeftEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.UP) {
            final Coordinate targetBlock = new Coordinate(coor.getRow() - 1, coor.getColumn());
            if (coor.getRow() != 0
                    && dc.checkUpperEntrance(coor)
                    && dc.checkBottomEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.DOWN) {
            final Coordinate targetBlock = new Coordinate(coor.getRow() + 1, coor.getColumn());
            if (coor.getRow() != mazeSize - 1
                    && dc.checkBottomEntrance(coor)
                    && dc.checkUpperEntrance(targetBlock)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean enemyCanMove(final Direction dir) {
        final Coordinate enemyCoordinate = new Coordinate(lab.getEnemyCoordinate(e.getSecond()));
        return canMoveFromPosition(enemyCoordinate, dir);
    }

}
