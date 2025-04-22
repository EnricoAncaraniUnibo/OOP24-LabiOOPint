package labioopint.controller.impl;

import java.util.Optional;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.api.DirectionCheck;
import labioopint.model.api.Coordinate;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.impl.PlayerImpl;

public class ActionPredicateImpl implements ActionPredicate{
    private LabyrinthImpl lab;
    private Integer mazeSize;
    private DirectionCheck dc;
    private Optional<Enemy> e;

    public ActionPredicateImpl(TurnManager tu) {
        lab = tu.getLab();
        mazeSize = lab.getGrid().getSize();
        dc = new DirectionCheck(tu);
        e = tu.getEnemy();
    }

    @Override
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

    @Override
    public boolean BlockCanMove(Coordinate blockCoordinate) {
        BlockImpl b = lab.getGrid().getBlock(blockCoordinate).get();
        if(b.isMove() && (blockCoordinate.getColumn() == 0 || blockCoordinate.getColumn() == mazeSize-1
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

    @Override
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

    @Override
    public boolean EnemyCanMove(Direction dir) {
        Coordinate enemyCoordinate = new Coordinate(lab.getEnemyCoordinate(e.get()));
        return CanMoveFromPosition(enemyCoordinate, dir);
    }
    
}
