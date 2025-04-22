package labioopint.controller.impl;

import labioopint.controller.api.DirectionCheck;
import labioopint.model.api.Coordinate;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.maze.impl.MazeImpl;

public class DirectionCheckImpl implements DirectionCheck {
    private LabyrinthImpl lab;

    public DirectionCheckImpl(TurnManager tu) {
        this.lab = tu.getLab();
    }

    /*
     * Every functions check if there are an entrance in the specified direction
     */
    @Override
    public boolean checkRightEntrance(final Coordinate coord) {
        final MazeImpl grid = lab.getGrid();
        final BlockImpl block = grid.getBlock(coord).get();
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
            case BlockType.CROSSING:
                if (rotation == Rotation.ZERO || rotation == Rotation.NINETY
                        || rotation == Rotation.ONE_HUNDRED_EIGHTY) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean checkLeftEntrance(final Coordinate coord) {
        final MazeImpl grid = lab.getGrid();
        final BlockImpl block = grid.getBlock(coord).get();
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
            case BlockType.CROSSING:
                if (rotation == Rotation.ZERO || rotation == Rotation.ONE_HUNDRED_EIGHTY
                        || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean checkBottomEntrance(final Coordinate coord) {
        final MazeImpl grid = lab.getGrid();
        final BlockImpl block = grid.getBlock(coord).get();
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
            case BlockType.CROSSING:
                if (rotation == Rotation.ZERO || rotation == Rotation.NINETY
                        || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean checkUpperEntrance(final Coordinate coord) {
        final MazeImpl grid = lab.getGrid();
        final BlockImpl block = grid.getBlock(coord).get();
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
            case BlockType.CROSSING:
                if (rotation == Rotation.NINETY || rotation == Rotation.ONE_HUNDRED_EIGHTY
                        || rotation == Rotation.TWO_HUNDRED_SEVENTY) {
                    return true;
                }
                break;
        }
        return false;
    }
}
