package labioopint.model.Core.impl;

import java.util.*;

public class TurnManager {

    private List<Player> players;
    private Enemy enemy;
    private Builder build;
    private TurnEnemy te;
    private Labyrinth maze;
    private TurnPlayer tp;

    public TurnManager() {
        this.players = build.createPlayers();
        this.maze = build.createMaze();
        this.enemy = build.createEnemy();
    }

    private void game() {
        RandomTurnChooser randomic = new RandomTurnChooser(players);
        List<Player> turnPlayer = randomic.randomOrder();
        while (maze.isGameFinished == false) {

        }
    }

    public Labyrinth getMaze() {
        return maze;
    }

}
