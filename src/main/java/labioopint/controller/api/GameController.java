package labioopint.controller.api;

import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Rotation;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.maze.impl.PowerUp;
import labioopint.model.player.impl.Player;
import labioopint.model.Core.impl.TurnMenager;
import labioopint.model.api.ActionType;
import labioopint.model.api.Coordinate;


public class GameController {
    //ActionType.MOVE_BLOCK;
    private static Labyrinth lab = TurnMenager.GetLab();

    public static void action(Object action,Object subject){
        ActionType current_action = TurnMenager.GetCurrentAction();
        switch (current_action) {
            case ActionType.MOVE_BLOCK:
                if(action instanceof String){
                    Direction dir = (action.equals("←")) ? Direction.LEFT :
                                    Direction.RIGHT;
                    RotateBlock(dir);
                }
                if(action instanceof Coordinate){
                    MoveBlock(null, null);
                    //TurnMenager.nextAction();
                }
                break;
        
            case ActionType.MOVE_PLAYER:
                if(action instanceof String){
                    Direction dir = (action.equals("←")) ? Direction.LEFT :
                                    (action.equals("→")) ? Direction.RIGHT :
                                    (action.equals("↑")) ? Direction.UP :
                                    Direction.DOWN;
                    if(MovePlayer(dir, (Player)subject)){
                        //TurnMenager.nextAction();
                    }
                }
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
    /* BLOCK MOVEMENT 
     * Return true if the block can move
     * Return false if the block can't move
     */
    private static void MoveBlock(Direction dir,Block block){
        switch (dir) {
            case Direction.LEFT:
                if(ActionPredicate.BlockCanMove(block, dir)){
                    lab.moveBlock(lab.getGrid().getCoordinate(block), dir);
                }
                break;
            case Direction.RIGHT:

                break;
            case Direction.UP:

                break;
            case Direction.DOWN:

                break;
            default:
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
                    lab.updateCoordinate(p,dir);
                    return true;
                }
                break;
            case Direction.RIGHT:
                if(ActionPredicate.PlayerCanMove(p, dir)){
                    lab.updateCoordinate(p,dir);
                    return true;
                }
                break;
            case Direction.UP:
                if(ActionPredicate.PlayerCanMove(p, dir)){
                    lab.updateCoordinate(p,dir);
                    return true;
                }
                break;
            case Direction.DOWN:
                if(ActionPredicate.PlayerCanMove(p, dir)){
                    lab.updateCoordinate(p,dir);
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
    private static boolean MoveEnemy(Direction dir){
        return false;
    }  

    /*
     * USE POWER UP
     */
    private static boolean UsePowerUp(PowerUp pUP){
        return false;
    }
}
