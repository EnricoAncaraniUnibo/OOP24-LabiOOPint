package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.api.ActionType;
import labioopint.model.api.Settings;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.maze.impl.PowerUp;
import labioopint.model.player.impl.Player;

public class TurnMenager {
    private static Labyrinth maze;
    private static List<Player> players;
    private static Optional<Enemy> enemy;
    private static List<PowerUp> powerUps;
    private static ActionType currentAction = ActionType.MOVE_BLOCK;

    public TurnMenager(Settings st) throws Exception{
        BuilderImpl bi = new BuilderImpl(st);
        players = bi.createPlayers();
        enemy = bi.createEnemy();
        maze = bi.createMaze(players, enemy, powerUps);
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
        Player p = players.get(0);
        return p;
    }

    public static ActionType GetCurrentAction() {
        return currentAction;
    }
}
