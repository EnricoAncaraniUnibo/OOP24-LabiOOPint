package labioopint.controller.api;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.api.DirectionCheck;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;
import labioopint.model.api.Coordinate;

public class ActionPredicate{

    public static boolean PlayerCanMove(Player p, Direction dir, Labyrinth lab) {
        Coordinate playerCoordinate = new Coordinate(lab.getPlayerCoordinate(p));
        if(dir == Direction.LEFT){
            Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(),Integer.valueOf(playerCoordinate.getColumn()-1));
            if(playerCoordinate.getColumn() != 0 && 
               DirectionCheck.checkLeftEntrance(lab, playerCoordinate) && 
               DirectionCheck.checkRightEntrance(lab,targetBlock))
            {return true;}
        }else if(dir == Direction.RIGHT){
            Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(),Integer.valueOf(playerCoordinate.getColumn()+1));
            if(playerCoordinate.getColumn() != lab.getGrid().getSize()-1 && 
               DirectionCheck.checkRightEntrance(lab, playerCoordinate) && 
               DirectionCheck.checkLeftEntrance(lab,targetBlock))
            {return true;}
        }else if(dir == Direction.UP){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(playerCoordinate.getRow()-1),playerCoordinate.getColumn());
            if(playerCoordinate.getRow() != 0 && 
               DirectionCheck.checkUpperEntrance(lab, playerCoordinate) && 
               DirectionCheck.checkBottomEntrance(lab,targetBlock))
            {return true;}
        }else if(dir == Direction.DOWN){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(playerCoordinate.getRow()+1),playerCoordinate.getColumn());
            if(playerCoordinate.getRow() != lab.getGrid().getSize()-1 && 
               DirectionCheck.checkBottomEntrance(lab, playerCoordinate) && 
               DirectionCheck.checkUpperEntrance(lab,targetBlock))
            {return true;}
        }
        return false;
    }

    public static boolean BlockCanMove(Block b, Direction dir, Labyrinth lab) {
        if(dir == Direction.LEFT){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(blockToMoveCoordinate.getRow(),Integer.valueOf(blockToMoveCoordinate.getColumn()-1));
            if(blockToMoveCoordinate.getColumn() != 0 && b.IsMovable() && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }else if(dir == Direction.RIGHT){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(blockToMoveCoordinate.getRow(),Integer.valueOf(blockToMoveCoordinate.getColumn()+1));
            if(blockToMoveCoordinate.getColumn() != lab.getGrid().getSize()-1 && b.IsMovable() && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }else if(dir == Direction.UP){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(Integer.valueOf(blockToMoveCoordinate.getRow()-1),blockToMoveCoordinate.getColumn());
            if(blockToMoveCoordinate.getRow() != 0 && b.IsMovable() && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }else if(dir == Direction.DOWN){
            Coordinate blockToMoveCoordinate = lab.getGrid().getCoordinate(b);
            Coordinate targetBlockCoordinate = new Coordinate(Integer.valueOf(blockToMoveCoordinate.getRow()+1),blockToMoveCoordinate.getColumn());
            if(blockToMoveCoordinate.getRow() != lab.getGrid().getSize()-1 && b.IsMovable() && lab.getGrid().GetBlock(targetBlockCoordinate).IsMovable()){return true;}
        }
        return false;
    }

    public static boolean EnemyCanMove(Enemy e, Direction dir, Labyrinth lab) {
        Coordinate enemyCoordinate = new Coordinate(lab.getEnemyCoordinate(e));
        if(dir == Direction.LEFT){
            Coordinate targetBlock = new Coordinate(enemyCoordinate.getRow(),Integer.valueOf(enemyCoordinate.getColumn()-1));
            if(enemyCoordinate.getColumn() != 0 && 
               DirectionCheck.checkLeftEntrance(lab, enemyCoordinate) && 
               DirectionCheck.checkRightEntrance(lab,targetBlock))
            {return true;}
        }else if(dir == Direction.RIGHT){
            Coordinate targetBlock = new Coordinate(enemyCoordinate.getRow(),Integer.valueOf(enemyCoordinate.getColumn()+1));
            if(enemyCoordinate.getColumn() != lab.getGrid().getSize()-1 && 
               DirectionCheck.checkRightEntrance(lab, enemyCoordinate) && 
               DirectionCheck.checkLeftEntrance(lab,targetBlock))
            {return true;}
        }else if(dir == Direction.UP){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(enemyCoordinate.getRow()-1),enemyCoordinate.getColumn());
            if(enemyCoordinate.getRow() != 0 && 
               DirectionCheck.checkUpperEntrance(lab, enemyCoordinate) && 
               DirectionCheck.checkBottomEntrance(lab,targetBlock))
            {return true;}
        }else if(dir == Direction.DOWN){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(enemyCoordinate.getRow()+1),enemyCoordinate.getColumn());
            if(enemyCoordinate.getRow() != lab.getGrid().getSize()-1 && 
               DirectionCheck.checkBottomEntrance(lab, enemyCoordinate) && 
               DirectionCheck.checkUpperEntrance(lab,targetBlock))
            {return true;}
        }
        return false;
    }
    
}
