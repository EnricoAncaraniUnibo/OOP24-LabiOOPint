package labioopint.controller.impl;

import java.util.Optional;

import labioopint.controller.api.DirectionCheck;
import labioopint.controller.impl.ActionPredicate;
import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.api.Direction;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;

public class ActionPredicate{
    private static LabyrinthImpl lab = TurnManager.GetLab();
    private static Integer mazeSize = lab.getGrid().getSize();

    public static boolean PlayerCanMove(PlayerImpl p, Direction dir) {
        Coordinate playerCoordinate = new Coordinate(lab.getPlayerCoordinate(p));
        if(dir == Direction.LEFT){
            Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(),Integer.valueOf(playerCoordinate.getColumn()-1));
            if(playerCoordinate.getColumn() != 0 && 
               DirectionCheck.checkLeftEntrance(playerCoordinate) && 
               DirectionCheck.checkRightEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.RIGHT){
            Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(),Integer.valueOf(playerCoordinate.getColumn()+1));
            if(playerCoordinate.getColumn() != mazeSize-1 && 
               DirectionCheck.checkRightEntrance(playerCoordinate) && 
               DirectionCheck.checkLeftEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.UP){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(playerCoordinate.getRow()-1),playerCoordinate.getColumn());
            if(playerCoordinate.getRow() != 0 && 
               DirectionCheck.checkUpperEntrance(playerCoordinate) && 
               DirectionCheck.checkBottomEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.DOWN){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(playerCoordinate.getRow()+1),playerCoordinate.getColumn());
            if(playerCoordinate.getRow() != mazeSize-1 && 
               DirectionCheck.checkBottomEntrance(playerCoordinate) && 
               DirectionCheck.checkUpperEntrance(targetBlock))
            {return true;}
        }
        return false;
    }

    public static boolean BlockCanMove(Coordinate blockCoordinate) {
        BlockImpl b = lab.getGrid().GetBlock(blockCoordinate);
        if(b.IsMovable() && (blockCoordinate.getColumn() == 0 || blockCoordinate.getColumn() == mazeSize-1
                         ||  blockCoordinate.getRow() == 0 || blockCoordinate.getRow() == mazeSize-1)){
            return true;
        }
        return false;
        /*
        if(dir == Direction.LEFT){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(blockToMoveCoordinate.getRow(),Integer.valueOf(blockToMoveCoordinate.getColumn()-1));
            if(blockToMoveCoordinate.getColumn() != 0 && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }else if(dir == Direction.RIGHT){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(blockToMoveCoordinate.getRow(),Integer.valueOf(blockToMoveCoordinate.getColumn()+1));
            if(blockToMoveCoordinate.getColumn() != mazeSize-1 && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }else if(dir == Direction.UP){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(Integer.valueOf(blockToMoveCoordinate.getRow()-1),blockToMoveCoordinate.getColumn());
            if(blockToMoveCoordinate.getRow() != 0 && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }else if(dir == Direction.DOWN){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(Integer.valueOf(blockToMoveCoordinate.getRow()+1),blockToMoveCoordinate.getColumn());
            if(blockToMoveCoordinate.getRow() != mazeSize-1 && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }
        return false;
         */
    }

    public static boolean EnemyCanMove(Direction dir) {
        Optional<Enemy> e = TurnManager.GetEnemy();
        Coordinate enemyCoordinate = new Coordinate(lab.getEnemyCoordinate(e.get()));
        if(dir == Direction.LEFT){
            Coordinate targetBlock = new Coordinate(enemyCoordinate.getRow(),Integer.valueOf(enemyCoordinate.getColumn()-1));
            if(enemyCoordinate.getColumn() != 0 && 
               DirectionCheck.checkLeftEntrance(enemyCoordinate) && 
               DirectionCheck.checkRightEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.RIGHT){
            Coordinate targetBlock = new Coordinate(enemyCoordinate.getRow(),Integer.valueOf(enemyCoordinate.getColumn()+1));
            if(enemyCoordinate.getColumn() != mazeSize-1 && 
               DirectionCheck.checkRightEntrance(enemyCoordinate) && 
               DirectionCheck.checkLeftEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.UP){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(enemyCoordinate.getRow()-1),enemyCoordinate.getColumn());
            if(enemyCoordinate.getRow() != 0 && 
               DirectionCheck.checkUpperEntrance(enemyCoordinate) && 
               DirectionCheck.checkBottomEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.DOWN){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(enemyCoordinate.getRow()+1),enemyCoordinate.getColumn());
            if(enemyCoordinate.getRow() != mazeSize-1 && 
               DirectionCheck.checkBottomEntrance(enemyCoordinate) && 
               DirectionCheck.checkUpperEntrance(targetBlock))
            {return true;}
        }
        return false;
    }
    
}
