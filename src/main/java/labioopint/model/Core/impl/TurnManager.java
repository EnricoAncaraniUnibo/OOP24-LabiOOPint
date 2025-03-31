package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.impl.EnemyImpl;
import labioopint.model.Enemy.impl.ais.ChaseAI;
import labioopint.model.Enemy.impl.ais.RandomAI;
import labioopint.model.Enemy.impl.ais.SingleStepRandomAI;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.ActionType;
import labioopint.model.api.Settings;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.player.impl.Player;

public class TurnManager {
    private static Labyrinth maze;
    private static List<Player> players;
    private static Optional<Enemy> enemy;
    private static List<PowerUp> powerUps;
    private static ActionType currentAction;
    private static int index;

    public TurnManager(Settings st) throws Exception{
        BuilderImpl bi = new BuilderImpl(st);
        players = bi.createPlayers();
        enemy = bi.createEnemy();
        powerUps = bi.createPowerUps();
        maze = bi.createMaze();
        currentAction = ActionType.BLOCK_PLACEMENT;
        players = new RandomTurnChooser(players).randomOrder();
        index = 0;
    }

    public static Labyrinth GetLab(){
        return maze;
    }

    public static List<Player> GetPlayers(){
        return players;
    }

    public static Optional<Enemy> GetEnemy(){
        return enemy;
    }

    public static List<PowerUp> GetPowerUps(){
        return powerUps;
    }

    public static Player GetCurrentPlayer() {
        Player p = players.get(index);
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
            currentAction = ActionType.ENEMY_MOVEMENT;
            if(enemy.isPresent()){
                if(enemy.get().getEnemyAI() instanceof SingleStepRandomAI) {
                    enemy.get().move(players);
                }
                if(enemy.get().getEnemyAI() instanceof RandomAI) {
                    enemy.get().move(players);
                }
                if(enemy.get().getEnemyAI() instanceof ChaseAI) {
                    enemy.get().move(players);
                }
            } else {
                TurnManager.nextAction();
            }
        } else if(currentAction == ActionType.ENEMY_MOVEMENT){
            currentAction = ActionType.BLOCK_PLACEMENT;
        }
    }
}
