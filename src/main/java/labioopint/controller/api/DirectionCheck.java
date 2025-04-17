package labioopint.controller.api;

import labioopint.model.Block.api.BlockType;
import labioopint.model.Block.api.Rotation;
import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Maze.impl.MazeImpl;
import labioopint.model.api.Coordinate;

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
        BlockImpl block = grid.GetBlock(coord).get();
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
        BlockImpl block = grid.GetBlock(coord).get();
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
        BlockImpl block = grid.GetBlock(coord).get();
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
        BlockImpl block = grid.GetBlock(coord).get();
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
