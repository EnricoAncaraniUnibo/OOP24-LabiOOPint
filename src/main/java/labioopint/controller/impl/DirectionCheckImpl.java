package labioopint.controller.impl;

import labioopint.controller.api.DirectionCheck;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.maze.api.Maze;
/**
 * Implementation class used to check the entrance of single blocks.
 */
public final class DirectionCheckImpl implements DirectionCheck {

    @Override
    public boolean checkRightEntrance(final Coordinate coord, final Labyrinth labyrinth) {
        final Maze grid = labyrinth.getGrid();
        final Block block = grid.getBlock(coord).get();
        final BlockType bType = block.getType();
        final Rotation rotation = block.getRotation();

        switch (bType) {
            case BlockType.CORNER:
                if (rotation == Rotation.ZERO || rotation == Rotation.NINETY) {
                    return true;
                }
                break;
            case BlockType.CORRIDOR:
                if (rotation == Rotation.NINETY || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
            default:
                if (rotation == Rotation.ZERO || rotation == Rotation.NINETY
                        || rotation == Rotation.ONE_HUNDRED_EIGHTY) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean checkLeftEntrance(final Coordinate coord, final Labyrinth labyrinth) {
        final Maze grid = labyrinth.getGrid();
        final Block block = grid.getBlock(coord).get();
        final BlockType bType = block.getType();
        final Rotation rotation = block.getRotation();

        switch (bType) {
            case BlockType.CORNER:
                if (rotation == Rotation.ONE_HUNDRED_EIGHTY || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
            case BlockType.CORRIDOR:
                if (rotation == Rotation.NINETY || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
            default:
                if (rotation == Rotation.ZERO || rotation == Rotation.ONE_HUNDRED_EIGHTY
                        || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean checkBottomEntrance(final Coordinate coord, final Labyrinth labyrinth) {
        final Maze grid = labyrinth.getGrid();
        final Block block = grid.getBlock(coord).get();
        final BlockType bType = block.getType();
        final Rotation rotation = block.getRotation();

        switch (bType) {
            case BlockType.CORNER:
                if (rotation == Rotation.ZERO || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
            case BlockType.CORRIDOR:
                if (rotation == Rotation.ZERO || rotation == Rotation.ONE_HUNDRED_EIGHTY) {
                    return true;
                }
                break;
            default:
                if (rotation == Rotation.ZERO || rotation == Rotation.NINETY
                        || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean checkUpperEntrance(final Coordinate coord, final Labyrinth labyrinth) {
        final Maze grid = labyrinth.getGrid();
        final Block block = grid.getBlock(coord).get();
        final BlockType bType = block.getType();
        final Rotation rotation = block.getRotation();

        switch (bType) {
            case BlockType.CORNER:
                if (rotation == Rotation.NINETY || rotation == Rotation.ONE_HUNDRED_EIGHTY) {
                    return true;
                }
                break;
            case BlockType.CORRIDOR:
                if (rotation == Rotation.ZERO || rotation == Rotation.ONE_HUNDRED_EIGHTY) {
                    return true;
                }
                break;
            default:
                if (rotation == Rotation.NINETY || rotation == Rotation.ONE_HUNDRED_EIGHTY
                        || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
        }
        return false;
    }
}
