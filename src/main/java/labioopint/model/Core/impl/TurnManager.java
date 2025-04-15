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
    private LabyrinthImpl maze;
    private List<PlayerImpl> players;
    private Optional<Enemy> enemy;
    private List<PowerUp> powerUps;
    private ActionType currentAction;
    private int index;

    public TurnManager(Settings st){
        currentAction = ActionType.BLOCK_PLACEMENT;
        index = 0;
        BuilderImpl bi = new BuilderImpl(st,this);
        players = bi.createPlayers();
        players = new RandomTurnChooser(players).randomOrder();
        enemy = bi.createEnemy();
        powerUps = bi.createPowerUps();
        maze = bi.createMaze();
    }

    public LabyrinthImpl GetLab(){
        return maze;
    }

    public List<PlayerImpl> GetPlayers(){
        return players;
    }

    public Optional<Enemy> GetEnemy(){
        return enemy;
    }

    public List<PowerUp> GetPowerUps(){
        return powerUps;
    }

    public PlayerImpl GetCurrentPlayer() {
        PlayerImpl p = players.get(index);
        return p;
    }

    public ActionType GetCurrentAction() {
        return currentAction;
    }

    public void nextAction(){
        if(currentAction == ActionType.BLOCK_PLACEMENT){
            currentAction = ActionType.PLAYER_MOVEMENT;
        } else if(currentAction == ActionType.PLAYER_MOVEMENT){
            index = (index + 1) % players.size();
            if(enemy.isPresent()){
                if(enemy.get().getEnemyAI() instanceof SingleStepRandomAI) {
                    maze.EnemyUpdateCoordinate(enemy.get(), enemy.get().move(players));   
                    enemy.get().playerHit(players);      
                }
                if(enemy.get().getEnemyAI() instanceof RandomAI) {
                    maze.EnemyUpdateCoordinate(enemy.get(), enemy.get().move(players));      
                    enemy.get().playerHit(players);  
                }
                if(enemy.get().getEnemyAI() instanceof ChaseAI) {
                    maze.EnemyUpdateCoordinate(enemy.get(), enemy.get().move(players));     
                    enemy.get().playerHit(players);   
                }
                currentAction = ActionType.BLOCK_PLACEMENT;
            } else {
                currentAction = ActionType.BLOCK_PLACEMENT;
            }
        } else if(currentAction == ActionType.ENEMY_MOVEMENT){
            currentAction = ActionType.BLOCK_PLACEMENT;
        }
    }

    public void addAddictionalPowerUp(PowerUp p) {
        powerUps.add(p);
    }
}
