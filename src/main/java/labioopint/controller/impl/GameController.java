package labioopint.controller.impl;

import labioopint.model.api.ActionType;
import labioopint.model.api.Coordinate;
import labioopint.model.block.api.Rotation;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.LabyrinthImpl;


public class GameController {
    //ActionType.MOVE_BLOCK;
    private LabyrinthImpl lab;
    private TurnManager turn;
    private ActionPredicate ap;

    public GameController(TurnManager tu) {
        turn = tu;
    }

    public void action(Object action){
        ap = new ActionPredicate(turn);
        lab = turn.getLab();
        ActionType current_action = turn.getCurrentAction();
        switch (current_action) {
            case ActionType.BLOCK_PLACEMENT:
                if(action instanceof String){
                    Direction dir = (action.equals("←")) ? Direction.LEFT :
                                    Direction.RIGHT;
                    RotateBlock(dir);
                } else if(action instanceof Coordinate){
                    Coordinate blockCoordinate = (Coordinate)action;
                    if(ap.BlockCanMove(blockCoordinate)){
                        if(blockCoordinate.getColumn() == 0){
                            turn.nextAction();
                            lab.moveBlock(blockCoordinate, Direction.RIGHT);
                        }else if(blockCoordinate.getColumn() == lab.getGrid().getSize()-1){
                            turn.nextAction();
                            lab.moveBlock(blockCoordinate, Direction.LEFT);
                        }else if(blockCoordinate.getRow() == 0){
                            turn.nextAction();
                            lab.moveBlock(blockCoordinate, Direction.DOWN);
                        }else if(blockCoordinate.getRow() == lab.getGrid().getSize()-1){
                            turn.nextAction();
                            lab.moveBlock(blockCoordinate, Direction.UP);
                        }else{
                            //TurnManager.invalidBlockPosition();
                        }
                    } 
                }
                break;
        
            case ActionType.PLAYER_MOVEMENT:
                if(action instanceof String){
                    if(action.equals("←") || action.equals("→") || action.equals("↑") || action.equals("↓")){
                        Direction dir = (action.equals("←")) ? Direction.LEFT :
                                        (action.equals("→")) ? Direction.RIGHT :
                                        (action.equals("↑")) ? Direction.UP :
                                        Direction.DOWN;
                        if(ap.PlayerCanMove(turn.getCurrentPlayer(), dir)){
                            lab.movePlayer(turn.getCurrentPlayer(), dir);
                        }else{
                            //TurnManager.invalidMovement();
                        }
                    }else if(action.equals("End Turn")){
                        turn.nextAction();
                    }
                }
                break;
            case ActionType.ENEMY_MOVEMENT:
                if(action instanceof Direction){
                    if(turn.getEnemy().isPresent()){
                        turn.getEnemy().get().move(turn.getPlayers());
                        turn.nextAction();
                    }else{
                        //TurnManager.invalidMovement();
                    }
                }
            default:
                break;
        }
    }

    private void RotateBlock(Direction dir){
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
            default:
                break;
        }

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
