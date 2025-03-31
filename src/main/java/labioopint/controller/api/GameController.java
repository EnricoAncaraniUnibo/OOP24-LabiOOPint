package labioopint.controller.api;

import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Rotation;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Labyrinth;
//import labioopint.model.maze.impl.PowerUp;
import labioopint.model.player.impl.Player;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.api.ActionType;
import labioopint.model.api.Coordinate;


public class GameController {
    //ActionType.MOVE_BLOCK;
    private static Labyrinth lab = TurnManager.GetLab();

    public static void action(Object action){
        ActionType current_action = TurnManager.GetCurrentAction();
        switch (current_action) {
            case ActionType.BLOCK_PLACEMENT:
                if(action instanceof String){
                    Direction dir = (action.equals("←")) ? Direction.LEFT :
                                    Direction.RIGHT;
                    RotateBlock(dir);
                }
                if(action instanceof Coordinate){
                    Coordinate blockCoordinate = (Coordinate)action;
                    if(blockCoordinate.getColumn() == 0){
                        lab.moveBlock(blockCoordinate, Direction.RIGHT);
                    }else if(blockCoordinate.getColumn() == lab.getGrid().getSize()-1){
                        lab.moveBlock(blockCoordinate, Direction.LEFT);
                    }else if(blockCoordinate.getRow() == 0){
                        lab.moveBlock(blockCoordinate, Direction.DOWN);
                    }else if(blockCoordinate.getRow() == lab.getGrid().getSize()-1){
                        lab.moveBlock(blockCoordinate, Direction.UP);
                    }else{
                        //TurnManager.invalidBlockPosition();
                    }
                    TurnManager.nextAction();
                }
                break;
        
            case ActionType.PLAYER_MOVEMENT:
                if(action instanceof String){
                    if(action.equals("←") || action.equals("→") || action.equals("↑") || action.equals("↓")){
                        Direction dir = (action.equals("←")) ? Direction.LEFT :
                                        (action.equals("→")) ? Direction.RIGHT :
                                        (action.equals("↑")) ? Direction.UP :
                                        Direction.DOWN;
                        if(ActionPredicate.PlayerCanMove(TurnManager.GetCurrentPlayer(), dir)){
                            lab.updateCoordinateByCoordinate(TurnManager.GetCurrentPlayer(), dir);
                        }else{
                            //TurnManager.invalidMovement();
                        }
                    }else if(action.equals("End Turn")){
                        TurnManager.nextAction();
                    }
                }
                break;
            case ActionType.ENEMY_MOVEMENT:
                if(action instanceof Direction){
                    Direction enemyDirection = (Direction)action;
                    if(TurnManager.GetEnemy().isPresent()){
                        TurnManager.GetEnemy().get().move(TurnManager.GetPlayers());
                        TurnManager.nextAction();
                    }else{
                        //TurnManager.invalidMovement();
                    }
                }
            default:
                break;
        }
    }

    private static void RotateBlock(Direction dir){
        Rotation blockRotation = lab.getOutsideBlock().getRotation();
        switch (dir) {
            case Direction.RIGHT:
                blockRotation = (blockRotation == Rotation.ZERO) ? Rotation.NINETY : 
                                (blockRotation == Rotation.NINETY) ? Rotation.ONE_HUNDRED_EIGHTY :
                                (blockRotation == Rotation.ONE_HUNDRED_EIGHTY) ? Rotation.TWO_HUNDRED_SEVENTY :
                                Rotation.ZERO;
                lab.RotateOutsideBlock(blockRotation);       
                break;
        
            case Direction.LEFT:
                blockRotation = (blockRotation == Rotation.ZERO) ? Rotation.TWO_HUNDRED_SEVENTY : 
                                (blockRotation == Rotation.TWO_HUNDRED_SEVENTY) ? Rotation.ONE_HUNDRED_EIGHTY :
                                (blockRotation == Rotation.ONE_HUNDRED_EIGHTY) ? Rotation.NINETY :
                                Rotation.ZERO;
                lab.RotateOutsideBlock(blockRotation);
                break;
        }
    }

    /* PLAYER MOVEMENT
     * Return true if the player can move in a specific direction
     * Otherwise it returns false
     */
    private static boolean MovePlayer(Direction dir, Player p){
        switch (dir) {
            case Direction.LEFT:
                if(ActionPredicate.PlayerCanMove(p, dir)){
                    lab.updateCoordinateByCoordinate(p,dir);
                    return true;
                }
                break;
            case Direction.RIGHT:
                if(ActionPredicate.PlayerCanMove(p, dir)){
                    lab.updateCoordinateByCoordinate(p,dir);
                    return true;
                }
                break;
            case Direction.UP:
                if(ActionPredicate.PlayerCanMove(p, dir)){
                    lab.updateCoordinateByCoordinate(p,dir);
                    return true;
                }
                break;
            case Direction.DOWN:
                if(ActionPredicate.PlayerCanMove(p, dir)){
                    lab.updateCoordinateByCoordinate(p,dir);
                    return true;
                }
                break;
        }
        return false;
    }

    /* ENEMY MOVEMENT
     * Return true if the enemy can move in a specific direction
     * Otherwise it returns false
     */
    /*private static boolean MoveEnemy(Direction dir){
        Enemy e = TurnManager.GetEnemy().get();
        switch (dir) {
            case Direction.LEFT:
                if(ActionPredicate.EnemyCanMove(dir)){
                    lab.updateCoordinate(e,dir);
                    return true;
                }
                break;
            case Direction.RIGHT:
                if(ActionPredicate.EnemyCanMove(dir)){
                    lab.updateCoordinate(e,dir);
                    return true;
                }
                break;
            case Direction.UP:
                if(ActionPredicate.EnemyCanMove(dir)){
                    lab.updateCoordinate(e,dir);
                    return true;
                }
                break;
            case Direction.DOWN:
                if(ActionPredicate.EnemyCanMove(dir)){
                    lab.updateCoordinate(e,dir);
                    return true;
                }
                break;
        }
        return false;
    }  

    /*
     * USE POWER UP
     *
    private static boolean UsePowerUp(PowerUp pUP){
        return false;
    }*/
}
