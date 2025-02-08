package labioopint.controller.impl;

import java.util.List;

import labioopint.controller.api.Controller;
import labioopint.model.Core.impl.BuilderImpl;
import labioopint.model.Core.impl.RandomTurnChooser;
import labioopint.model.Enemy.api.Enemy;

public class ControllerImpl implements Controller{
    
    private final BuilderImpl builder;
    private final List<Player> players;
    private final Enemy enemy;
    private final Labyrinth maze;
    //mappa che associa ogni pos ad un elemento -> decidere interfaccia da esporre con quali metodi 
    // e quali elementi ritorna

    public ControllerImpl() {
        this.builder = new BuilderImpl();
        this.players = builder.createPlayers();
        this.enemy = builder.createEnemy();
        this.maze = builder.createMaze();
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
            if(currentPlayer.getPosition().equals(enemy.getPosition())) {
                if(currentPlayer.hasPowerUp()) {
                    currentPlayer.removeFirstPowerUp();
                } else {
                    currentPlayer.skipNextTurn();
                }
            }

            //metodo hasPoerrUp (ritorna boolean), removefirst (ritorna void), skip (ritorna void)


            // if ...
            // aggiornamento maze
            enemy.move(maze, players);
            turn = (turn + 1) % turnPlayer.size();
        }
    }
        
    private boolean isGameFinished() {
        // objects stanno nel labirinto
        if(maze.treasure().size() == 0) {
            return true;
        } else {
            return false;
        }
    } 

}
