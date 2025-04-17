package labioopint.controller.impl;

import java.util.Optional;

import labioopint.controller.api.DirectionCheck;
import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.api.Direction;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;

public class ActionPredicate{
    private LabyrinthImpl lab;
    private Integer mazeSize;
    private DirectionCheck dc;
    private Optional<Enemy> e;

    public ActionPredicate(TurnManager tu) {
        lab = tu.getLab();
        mazeSize = lab.getGrid().getSize();
        dc = new DirectionCheck(tu);
        e = tu.getEnemy();
    }

    public boolean PlayerCanMove(PlayerImpl p, Direction dir) {
        Coordinate playerCoordinate = new Coordinate(lab.getPlayerCoordinate(p));
        if(dir == Direction.LEFT){
            Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(),Integer.valueOf(playerCoordinate.getColumn()-1));
            if(playerCoordinate.getColumn() != 0 && 
               dc.checkLeftEntrance(playerCoordinate) && 
               dc.checkRightEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.RIGHT){
            Coordinate targetBlock = new Coordinate(playerCoordinate.getRow(),Integer.valueOf(playerCoordinate.getColumn()+1));
            if(playerCoordinate.getColumn() != mazeSize-1 && 
               dc.checkRightEntrance(playerCoordinate) && 
               dc.checkLeftEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.UP){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(playerCoordinate.getRow()-1),playerCoordinate.getColumn());
            if(playerCoordinate.getRow() != 0 && 
               dc.checkUpperEntrance(playerCoordinate) && 
               dc.checkBottomEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.DOWN){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(playerCoordinate.getRow()+1),playerCoordinate.getColumn());
            if(playerCoordinate.getRow() != mazeSize-1 && 
               dc.checkBottomEntrance(playerCoordinate) && 
               dc.checkUpperEntrance(targetBlock))
            {return true;}
        }
        return false;
    }

    public boolean BlockCanMove(Coordinate blockCoordinate) {
        BlockImpl b = lab.getGrid().GetBlock(blockCoordinate).get();
        if(b.isMovable() && (blockCoordinate.getColumn() == 0 || blockCoordinate.getColumn() == mazeSize-1
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

    public boolean CanMoveFromPosition(Coordinate coor, Direction dir) {
        if(dir == Direction.LEFT){
            Coordinate targetBlock = new Coordinate(coor.getRow(),Integer.valueOf(coor.getColumn()-1));
            if(coor.getColumn() != 0 && 
               dc.checkLeftEntrance(coor) && 
               dc.checkRightEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.RIGHT){
            Coordinate targetBlock = new Coordinate(coor.getRow(),Integer.valueOf(coor.getColumn()+1));
            if(coor.getColumn() != mazeSize-1 && 
               dc.checkRightEntrance(coor) && 
               dc.checkLeftEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.UP){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(coor.getRow()-1),coor.getColumn());
            if(coor.getRow() != 0 && 
               dc.checkUpperEntrance(coor) && 
               dc.checkBottomEntrance(targetBlock))
            {return true;}
        }else if(dir == Direction.DOWN){
            Coordinate targetBlock = new Coordinate(Integer.valueOf(coor.getRow()+1),coor.getColumn());
            if(coor.getRow() != mazeSize-1 && 
               dc.checkBottomEntrance(coor) && 
               dc.checkUpperEntrance(targetBlock))
            {return true;}
        }
        return false;
    }

    public boolean EnemyCanMove(Direction dir) {
        Coordinate enemyCoordinate = new Coordinate(lab.getEnemyCoordinate(e.get()));
        return CanMoveFromPosition(enemyCoordinate, dir);
    }
    
}
