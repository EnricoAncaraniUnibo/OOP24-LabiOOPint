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
    private final LabyrinthImpl labybinth;
    private final Integer mazeSize;
    private final DirectionCheck directionCheck;
    private final Pair<Boolean, Enemy> enemy;
    public static final long serialVersionUID = 4328743;

    public ActionPredicateImpl(final TurnManager turnManager) {
        labyrinth = turnManager.getLab();
        mazeSize = labyrinth.getGrid().getSize();
        directionCheck = new DirectionCheckImpl(turnManager);
        enemy = turnManager.getEnemy();
    }

    @Override
    public boolean playerCanMove(final PlayerImpl player, final Direction dir) {
        final Coordinate playerCoordinate = new Coordinate(labyrinth.getPlayerCoordinate(player));
        if (dir == Direction.LEFT) {
            final Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(), playerCoordinate.getColumn() - 1);
            if (playerCoordinate.getColumn() != 0
                    && directionCheck.checkLeftEntrance(playerCoordinate)
                    && directionCheck.checkRightEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.RIGHT) {
            final Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(), playerCoordinate.getColumn() + 1);
            if (playerCoordinate.getColumn() != mazeSize - 1
                    && directionCheck.checkRightEntrance(playerCoordinate)
                    && directionCheck.checkLeftEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.UP) {
            final Coordinate targetBlock = new Coordinate(playerCoordinate.getRow() - 1, playerCoordinate.getColumn());
            if (playerCoordinate.getRow() != 0
                    && directionCheck.checkUpperEntrance(playerCoordinate)
                    && directionCheck.checkBottomEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.DOWN) {
            final Coordinate targetBlock = new Coordinate(playerCoordinate.getRow() + 1, playerCoordinate.getColumn());
            if (playerCoordinate.getRow() != mazeSize - 1
                    && directionCheck.checkBottomEntrance(playerCoordinate)
                    && directionCheck.checkUpperEntrance(targetBlock)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean blockCanMove(final Coordinate blockCoordinate) {
        final BlockImpl b = labyrinth.getGrid().getBlock(blockCoordinate).get();
        return b.isMovable() && (blockCoordinate.getColumn() == 0 || blockCoordinate.getColumn() == mazeSize - 1
                || blockCoordinate.getRow() == 0 || blockCoordinate.getRow() == mazeSize - 1);
    }

    @Override
    public boolean enemyCanMoveFromPosition(final Coordinate coor, final Direction dir) {
        if (dir == Direction.LEFT) {
            final Coordinate targetBlock = new Coordinate(coor.getRow(), coor.getColumn() - 1);
            if (coor.getColumn() != 0
                    && directionCheck.checkLeftEntrance(coor)
                    && directionCheck.checkRightEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.RIGHT) {
            final Coordinate targetBlock = new Coordinate(coor.getRow(), coor.getColumn() + 1);
            if (coor.getColumn() != mazeSize - 1
                    && directionCheck.checkRightEntrance(coor)
                    && directionCheck.checkLeftEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.UP) {
            final Coordinate targetBlock = new Coordinate(coor.getRow() - 1, coor.getColumn());
            if (coor.getRow() != 0
                    && directionCheck.checkUpperEntrance(coor)
                    && directionCheck.checkBottomEntrance(targetBlock)) {
                return true;
            }
        } else if (dir == Direction.DOWN) {
            final Coordinate targetBlock = new Coordinate(coor.getRow() + 1, coor.getColumn());
            if (coor.getRow() != mazeSize - 1
                    && directionCheck.checkBottomEntrance(coor)
                    && directionCheck.checkUpperEntrance(targetBlock)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean enemyCanMove(final Direction dir) {
        final Coordinate enemyCoordinate = new Coordinate(labyrinth.getEnemyCoordinate(enemy.getSecond()));
        return enemyCanMoveFromPosition(enemyCoordinate, dir);
    }

}
