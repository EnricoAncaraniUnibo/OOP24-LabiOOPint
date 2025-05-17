package labioopint.controller.impl;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.api.DirectionCheck;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
/**
 * Implementation of the class used as a predicate to indicate 
 * if an action can be performed or not.
 */
public final class ActionPredicateImpl implements ActionPredicate {
    private final Integer mazeSize;
    private final DirectionCheck directionCheck;
    public static final long serialVersionUID = 4328743;
    /**
     * Constructs an {@code ActionPredicateImpl} with the specified labyrinth.
     *
     * @param labyrinth the {@link Labyrinth} instance representing the game coordinate manager
     */
    public ActionPredicateImpl(final Labyrinth labyrinth) {
        mazeSize = labyrinth.getGrid().getSize();
        directionCheck = new DirectionCheckImpl();
    }

    @Override
    public boolean playerCanMove(final Player player, final Direction dir, final Labyrinth labyrinth) {
        final Coordinate playerCoordinate = new CoordinateImpl(labyrinth.getPlayerCoordinate(player));
        if (dir == Direction.LEFT) {
            final Coordinate targetBlock = new CoordinateImpl(playerCoordinate.getRow(), playerCoordinate.getColumn() - 1);
            if (playerCoordinate.getColumn() != 0
                    && directionCheck.checkLeftEntrance(playerCoordinate, labyrinth)
                    && directionCheck.checkRightEntrance(targetBlock, labyrinth)) {
                return true;
            }
        } else if (dir == Direction.RIGHT) {
            final Coordinate targetBlock = new CoordinateImpl(playerCoordinate.getRow(), playerCoordinate.getColumn() + 1);
            if (playerCoordinate.getColumn() != mazeSize - 1
                    && directionCheck.checkRightEntrance(playerCoordinate, labyrinth)
                    && directionCheck.checkLeftEntrance(targetBlock, labyrinth)) {
                return true;
            }
        } else if (dir == Direction.UP) {
            final Coordinate targetBlock = new CoordinateImpl(playerCoordinate.getRow() - 1, playerCoordinate.getColumn());
            if (playerCoordinate.getRow() != 0
                    && directionCheck.checkUpperEntrance(playerCoordinate, labyrinth)
                    && directionCheck.checkBottomEntrance(targetBlock, labyrinth)) {
                return true;
            }
        } else if (dir == Direction.DOWN) {
            final Coordinate targetBlock = new CoordinateImpl(playerCoordinate.getRow() + 1, playerCoordinate.getColumn());
            if (playerCoordinate.getRow() != mazeSize - 1
                    && directionCheck.checkBottomEntrance(playerCoordinate, labyrinth)
                    && directionCheck.checkUpperEntrance(targetBlock, labyrinth)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean blockCanMove(final Coordinate blockCoordinate, final Labyrinth labyrinth) {
        final BlockImpl b = (BlockImpl) labyrinth.getGrid().getBlock(blockCoordinate).get();
        return b.isMovable() && (blockCoordinate.getColumn() == 0 || blockCoordinate.getColumn() == mazeSize - 1
                || blockCoordinate.getRow() == 0 || blockCoordinate.getRow() == mazeSize - 1);
    }

    @Override
    public boolean enemyCanMoveFromPosition(final Coordinate coor, final Direction dir, Labyrinth labyrinth) {
        if (dir == Direction.LEFT) {
            final Coordinate targetBlock = new CoordinateImpl(coor.getRow(), coor.getColumn() - 1);
            if (coor.getColumn() != 0
                    && directionCheck.checkLeftEntrance(coor, labyrinth)
                    && directionCheck.checkRightEntrance(targetBlock, labyrinth)) {
                return true;
            }
        } else if (dir == Direction.RIGHT) {
            final Coordinate targetBlock = new CoordinateImpl(coor.getRow(), coor.getColumn() + 1);
            if (coor.getColumn() != mazeSize - 1
                    && directionCheck.checkRightEntrance(coor, labyrinth)
                    && directionCheck.checkLeftEntrance(targetBlock, labyrinth)) {
                return true;
            }
        } else if (dir == Direction.UP) {
            final Coordinate targetBlock = new CoordinateImpl(coor.getRow() - 1, coor.getColumn());
            if (coor.getRow() != 0
                    && directionCheck.checkUpperEntrance(coor, labyrinth)
                    && directionCheck.checkBottomEntrance(targetBlock, labyrinth)) {
                return true;
            }
        } else if (dir == Direction.DOWN) {
            final Coordinate targetBlock = new CoordinateImpl(coor.getRow() + 1, coor.getColumn());
            if (coor.getRow() != mazeSize - 1
                    && directionCheck.checkBottomEntrance(coor, labyrinth)
                    && directionCheck.checkUpperEntrance(targetBlock, labyrinth)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean enemyCanMove(final Direction dir, final Enemy enemy, final Labyrinth labyrinth) {
        final Coordinate enemyCoordinate = new CoordinateImpl(labyrinth.getEnemyCoordinate(enemy));
        return enemyCanMoveFromPosition(enemyCoordinate, dir, labyrinth);
    }

}
