package labioopint.controller.impl;

import java.util.List;

import labioopint.controller.api.Controller;
import labioopint.model.Core.impl.BuilderImpl;
import labioopint.model.Core.impl.RandomTurnChooser;
import labioopint.model.Enemy.api.Enemy;

public class ControllerImpl implements Controller {

    private final BuilderImpl builder;
    private final List<Player> players;
    private final Enemy enemy;
    private final Labyrinth maze;
    private boolean endTurnPlayer;
    // mappa che associa ogni pos ad un elemento -> decidere interfaccia da esporre
    // con quali metodi
    // e quali elementi ritorna

    public ControllerImpl() {
        this.builder = new BuilderImpl();
        this.players = builder.createPlayers();
        this.enemy = builder.createEnemy();
        this.maze = builder.createMaze();
        this.endTurnPlayer = false;
    }

    @Override
    public void game() {
        RandomTurnChooser randomic = new RandomTurnChooser(players);
        List<Player> turnPlayer = randomic.randomOrder();
        int turn = 0;
        while (isGameFinished() == false) {
            Player currentPlayer = turnPlayer.get(turn);
            // mossa del player e poi si controlla se finito su tesoro o nemico

            // controllo nemici

            if (hasContact() == true) {
                currentPlayer.removeFirstPowerUp();
            } else {
                currentPlayer.skipNextTurn();
            }

            while (!endTurnPlayer) {
                Thread.sleep(2000);
                Action a = GameController.getAction();
                // stef crea la classe Action che avrà tante sottoclassi, ognuna per ogni
                // singola azione fattibile del player.
                if (a != null) {
                    if (a instanceof actionMovement) {
                        currentPlayer.getNextPosition();
                    }
                    if (a instanceof actionPowerUp) {
                        currentPlayer.usePowerUp();
                    }
                }
            }

            // metodo hasPowerUp (ritorna boolean), removefirst (ritorna void), skip
            // (ritorna void)

            // if ...
            // aggiornamento maze
            enemy.move(maze, players);

            if (hasContact() == true) {
                currentPlayer.removeFirstPowerUp();
            } else {
                currentPlayer.skipNextTurn();
            }

            turn = (turn + 1) % turnPlayer.size();
        }
    }

    private boolean isGameFinished() {
        // objects stanno nel labirinto
        if (maze.treasure().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasContact() {
        if (this.maze.getPlayerCoordinate(currentPlayer).equals(this.maze.getEnemyPosition(enemy))) {
            if (currentPlayer.hasPowerUp()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void endTurn() {
        endTurnPlayer = true;
    }
}
