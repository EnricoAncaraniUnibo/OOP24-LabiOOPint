package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.impl.ais.ChaseAI;
import labioopint.model.Enemy.impl.ais.RandomAI;
import labioopint.model.Enemy.impl.ais.SingleStepRandomAI;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.ActionType;
import labioopint.model.api.Settings;

public class TurnManager {
    private static LabyrinthImpl maze;
    private static List<PlayerImpl> players;
    private static Optional<Enemy> enemy;
    private static List<PowerUp> powerUps;
    private static ActionType currentAction;
    private static int index;

    public TurnManager(Settings st) throws Exception{
        currentAction = ActionType.BLOCK_PLACEMENT;
        index = 0;
        BuilderImpl bi = new BuilderImpl(st);
        players = bi.createPlayers();
        enemy = bi.createEnemy();
        powerUps = bi.createPowerUps();
        maze = bi.createMaze();
        players = new RandomTurnChooser(players).randomOrder();
    }

    public static LabyrinthImpl GetLab(){
        return maze;
    }

    public static List<PlayerImpl> GetPlayers(){
        return players;
    }

    public static Optional<Enemy> GetEnemy(){
        return enemy;
    }

    public static List<PowerUp> GetPowerUps(){
        return powerUps;
    }

    public static PlayerImpl GetCurrentPlayer() {
        PlayerImpl p = players.get(index);
        return p;
    }

    public static ActionType GetCurrentAction() {
        return currentAction;
    }

    public static void nextAction(){
        if(currentAction == ActionType.BLOCK_PLACEMENT){
            currentAction = ActionType.PLAYER_MOVEMENT;
        } else if(currentAction == ActionType.PLAYER_MOVEMENT){
            index = (index + 1) % players.size();
            if(enemy.isPresent()){
                if(enemy.get().getEnemyAI() instanceof SingleStepRandomAI) {
                    maze.absoluteUpdateCoordinate(enemy.get(), enemy.get().move(players));         
                }
                if(enemy.get().getEnemyAI() instanceof RandomAI) {
                    maze.absoluteUpdateCoordinate(enemy.get(), enemy.get().move(players));      
                }
                if(enemy.get().getEnemyAI() instanceof ChaseAI) {
                    maze.absoluteUpdateCoordinate(enemy.get(), enemy.get().move(players));      
                }
                currentAction = ActionType.BLOCK_PLACEMENT;
            } else {
                currentAction = ActionType.BLOCK_PLACEMENT;
            }
        } else if(currentAction == ActionType.ENEMY_MOVEMENT){
            currentAction = ActionType.BLOCK_PLACEMENT;
        }
    }
}
