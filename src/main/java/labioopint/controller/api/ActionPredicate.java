package labioopint.controller.api;

import java.util.Optional;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.api.DirectionCheck;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;
import labioopint.model.api.Coordinate;

public class ActionPredicate{
    private static Labyrinth lab = TurnManager.GetLab();

    public static boolean PlayerCanMove(Player p, Direction dir) {
        Coordinate playerCoordinate = new Coordinate(lab.getPlayerCoordinate(p));
        if(dir == Direction.LEFT){
            Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(),Integer.valueOf(playerCoordinate.getColumn()-1));
            if(playerCoordinate.getColumn() != 0 && 
               DirectionCheck.checkLeftEntrance(playerCoordinate) && 
               DirectionCheck.checkRightEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.RIGHT){
            Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(),Integer.valueOf(playerCoordinate.getColumn()+1));
            if(playerCoordinate.getColumn() != lab.getGrid().getSize()-1 && 
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
            if(playerCoordinate.getRow() != lab.getGrid().getSize()-1 && 
               DirectionCheck.checkBottomEntrance(playerCoordinate) && 
               DirectionCheck.checkUpperEntrance(targetBlock))
            {return true;}
        }
        return false;
    }

    public static boolean BlockCanMove(Block b, Direction dir) {
        if(b.IsMovable()){
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
            if(blockToMoveCoordinate.getColumn() != lab.getGrid().getSize()-1 && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }else if(dir == Direction.UP){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(Integer.valueOf(blockToMoveCoordinate.getRow()-1),blockToMoveCoordinate.getColumn());
            if(blockToMoveCoordinate.getRow() != 0 && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }else if(dir == Direction.DOWN){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(Integer.valueOf(blockToMoveCoordinate.getRow()+1),blockToMoveCoordinate.getColumn());
            if(blockToMoveCoordinate.getRow() != lab.getGrid().getSize()-1 && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
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
            if(enemyCoordinate.getColumn() != lab.getGrid().getSize()-1 && 
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
            if(enemyCoordinate.getRow() != lab.getGrid().getSize()-1 && 
               DirectionCheck.checkBottomEntrance(enemyCoordinate) && 
               DirectionCheck.checkUpperEntrance(targetBlock))
            {return true;}
        }
        return false;
    }
    
}
