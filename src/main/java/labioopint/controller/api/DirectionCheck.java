package labioopint.controller.api;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.api.Coordinate;
import labioopint.model.maze.api.BlockType;
import labioopint.model.maze.api.Rotation;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.maze.impl.Maze;

public class DirectionCheck {
    private static Labyrinth lab = TurnManager.GetLab();

    /*
     * Every functions check if there are an entrance in the specified direction
     */
    public static boolean checkRightEntrance(Coordinate coord){
        Maze grid = lab.getGrid();
        Block block = grid.GetBlock(coord);
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
    public static boolean checkLeftEntrance(Coordinate coord){
        Maze grid = lab.getGrid();
        Block block = grid.GetBlock(coord);
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
    public static boolean checkBottomEntrance(Coordinate coord){
        Maze grid = lab.getGrid();
        Block block = grid.GetBlock(coord);
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
    public static boolean checkUpperEntrance(Coordinate coord){
        Maze grid = lab.getGrid();
        Block block = grid.GetBlock(coord);
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
