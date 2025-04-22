package labioopint.controller.api;

import labioopint.model.api.Coordinate;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.maze.impl.MazeImpl;

public class DirectionCheck {
    private LabyrinthImpl lab;

    public DirectionCheck(TurnManager tu){
        this.lab = tu.getLab();
    }

    /*
     * Every functions check if there are an entrance in the specified direction
     */
    public boolean checkRightEntrance(Coordinate coord){
        MazeImpl grid = lab.getGrid();
        BlockImpl block = grid.getBlock(coord).get();
        BlockType BType = block.getType();
        Rotation rotation = block.getRotation();

        switch (BType) {
            case BlockType.CORNER:
                if(rotation == Rotation.ZERO || rotation == Rotation.NINETY){
                    return true;
                }
                break;
            case BlockType.CORRIDOR:
                if(rotation == Rotation.NINETY || rotation == Rotation.TWO_HUNDRED_SEVENTY){
                    return true;
                }
                break;
            case BlockType.CROSSING:
                if(rotation == Rotation.ZERO || rotation == Rotation.NINETY || rotation == Rotation.ONE_HUNDRED_EIGHTY){
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
    public boolean checkLeftEntrance(Coordinate coord){
        MazeImpl grid = lab.getGrid();
        BlockImpl block = grid.getBlock(coord).get();
        BlockType BType = block.getType();
        Rotation rotation = block.getRotation();

        switch (BType) {
            case BlockType.CORNER:
                if(rotation == Rotation.ONE_HUNDRED_EIGHTY || rotation == Rotation.TWO_HUNDRED_SEVENTY){
                    return true;
                }
                break;
            case BlockType.CORRIDOR:
                if(rotation == Rotation.NINETY || rotation == Rotation.TWO_HUNDRED_SEVENTY){
                    return true;
                }
                break;
            case BlockType.CROSSING:
                if(rotation == Rotation.ZERO || rotation == Rotation.ONE_HUNDRED_EIGHTY || rotation == Rotation.TWO_HUNDRED_SEVENTY){
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
    public boolean checkBottomEntrance(Coordinate coord){
        MazeImpl grid = lab.getGrid();
        BlockImpl block = grid.getBlock(coord).get();
        BlockType BType = block.getType();
        Rotation rotation = block.getRotation();

        switch (BType) {
            case BlockType.CORNER:
                if(rotation == Rotation.ZERO || rotation == Rotation.TWO_HUNDRED_SEVENTY){
                    return true;
                }
                break;
            case BlockType.CORRIDOR:
                if(rotation == Rotation.ZERO || rotation == Rotation.ONE_HUNDRED_EIGHTY){
                    return true;
                }
                break;
            case BlockType.CROSSING:
                if(rotation == Rotation.ZERO || rotation == Rotation.NINETY || rotation == Rotation.TWO_HUNDRED_SEVENTY){
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
    public boolean checkUpperEntrance(Coordinate coord){
        MazeImpl grid = lab.getGrid();
        BlockImpl block = grid.getBlock(coord).get();
        BlockType BType = block.getType();
        Rotation rotation = block.getRotation();

        switch (BType) {
            case BlockType.CORNER:
                if(rotation == Rotation.NINETY || rotation == Rotation.ONE_HUNDRED_EIGHTY){
                    return true;
                }
                break;
            case BlockType.CORRIDOR:
                if(rotation == Rotation.ZERO || rotation == Rotation.ONE_HUNDRED_EIGHTY){
                    return true;
                }
                break;
            case BlockType.CROSSING:
                if(rotation == Rotation.NINETY || rotation == Rotation.ONE_HUNDRED_EIGHTY || rotation == Rotation.TWO_HUNDRED_SEVENTY){
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
}
