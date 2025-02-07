package labioopint.model.Core.impl;

import java.util.*;

public class TurnManager {

    private List<Player> players;
    private Enemy enemy;
    private Builder build;
    private TurnEnemy te;
    private static Labyrinth maze;
    private TurnPlayer tp;

    // o il labirinto è nel turnManager (è la soluzione più logica) 
    // però bisogna trovare il modo in cui la classe turnPlayer e turnEnemy possano accedere al Labyrinth
    // altrimenti possiamo mettere labyrinth dentro una classe turn (soluzione meno logica ma più facile ad implementare)

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
