package labioopint.controller.api;

import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.maze.impl.PowerUp;
import labioopint.model.player.impl.Player;
import labioopint.model.api.ActionType;


public class GameController {
    private static ActionType current_action = ActionType.MOVE_BLOCK;
    /* BLOCK MOVEMENT 
     * Return true if the block can move
     * Return false if the block can't move
     */
    public static void MoveBlock(Direction dir,Block block,Labyrinth lab){
        switch (dir) {
            case Direction.LEFT:
                if(ActionPredicate.BlockCanMove(block, dir, lab)){
                    current_action = ActionType.MOVE_PLAYER;
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
    public boolean MovePlayer(Direction dir, Player p){
        return false;
    }

    /* ENEMY MOVEMENT
     * Return true if the enemy can move in a specific direction
     * Otherwise it returns false
     */
    public boolean MoveEnemy(Direction dir){
        return false;
    }  

    /*
     * USE POWER UP
     */
    public boolean UsePowerUp(PowerUp pUP){
        return false;
    }

    public ActionType GetCurrentAction(){
        return current_action;
    }
}
