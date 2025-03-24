package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.api.Settings;
import labioopint.model.maze.impl.Labyrinth;
import labioopint.model.maze.impl.PowerUp;
import labioopint.model.player.impl.Player;

public class TurnMenager {
    private Labyrinth maze;
    private List<Player> players;
    private Optional<Enemy> enemy;
    private List<PowerUp> powerUps;

    public TurnMenager(Settings st) throws Exception{
        BuilderImpl bi = new BuilderImpl(st);
        players = bi.createPlayers();
        enemy = bi.createEnemy();
        maze = bi.createMaze(players, enemy, powerUps);
    }




}
