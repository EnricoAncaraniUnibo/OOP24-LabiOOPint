package labioopint.model.core.impl;

import java.io.Serializable;
import java.util.List;

import labioopint.controller.api.SaveController;
import labioopint.controller.impl.SaveControllerImpl;
import labioopint.model.api.ActionType;
import labioopint.model.api.Pair;
import labioopint.model.api.Settings;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.impl.ais.ChaseAI;
import labioopint.model.enemy.impl.ais.RandomAI;
import labioopint.model.enemy.impl.ais.SingleStepRandomAI;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;

/**
 * Manages the turns and actions in the game, including players, enemies, and
 * power-ups.
 */
public class TurnManager implements Serializable {
    private final LabyrinthImpl labyrinth;
    private List<PlayerImpl> players;
    private Pair<Boolean, Enemy> enemy;
    private final List<PowerUp> powerUps;
    private ActionType currentAction;
    private int currentPlayer;
    private final SaveController saveController;
    private boolean enemyMove; // If true the enemy have the permission to move

    /**
     * Constructs a TurnManager with the given settings.
     *
     * @param st the game settings used to initialize the game components
     */
    public TurnManager(final Settings st) {
        enemyMove = true;
        saveController = new SaveControllerImpl(this);
        currentAction = ActionType.BLOCK_PLACEMENT;
        currentPlayer = 0;
        final BuilderImpl bi = new BuilderImpl(st, this);
        players = bi.createPlayers();
        players = new RandomTurnChooser(players).randomOrder();
        if (st.getEnemy() == 1) {
            enemy = new Pair<>(true, bi.createEnemy());
        } else {
            enemy = new Pair<>(false, null);
        }
        powerUps = bi.createPowerUps();
        labyrinth = bi.createMaze();
    }

    /*
     * Constucts a Turn Manager with a loaded game
     */
    public TurnManager(final TurnManager loadedTurnManager) {
        enemyMove = true;
        currentAction = loadedTurnManager.getCurrentAction();
        players = loadedTurnManager.getPlayers();
        enemy = loadedTurnManager.getEnemy();
        powerUps = loadedTurnManager.getPowerUps();
        labyrinth = loadedTurnManager.getLab();
        int i = 0;
        for (PlayerImpl playerImpl : players) {
            if (playerImpl == loadedTurnManager.getCurrentPlayer()) {
                currentPlayer = i;
            }
            i++;
        }
        saveController = new SaveControllerImpl(this);
        labyrinth.startView();
    }

    /**
     * Sets the next player to take their turn.
     */
    public void repeatPlayerTurn() {
        currentPlayer--;
        if (currentPlayer < 0) {
            currentPlayer = players.size() - 1;
        }
        enemyMove = false;
        nextAction();
        enemyMove = true;
    }

    /**
     * Gets the labyrinth of the game.
     *
     * @return the labyrinth
     */
    public LabyrinthImpl getLab() {
        return labyrinth;
    }

    /**
     * Gets the list of players in the game.
     *
     * @return the list of players
     */
    public List<PlayerImpl> getPlayers() {
        return players;
    }

    /**
     * Gets the enemy in the game, if present.
     *
     * @return an Optional containing the enemy, or empty if no enemy exists
     */
    public Pair<Boolean, Enemy> getEnemy() {
        return enemy;
    }

    /**
     * Gets the list of power-ups in the game.
     *
     * @return the list of power-ups
     */
    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    /**
     * Gets the current player whose turn it is.
     *
     * @return the current player
     */
    public PlayerImpl getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Gets the current action type in the game.
     *
     * @return the current action type
     */
    public ActionType getCurrentAction() {
        return currentAction;
    }

    /**
     * Advances the game to the next action or turn. This method handles the
     * transition between different phases of the game, such as block placement,
     * player movement, and enemy movement.
     */
    public void nextAction() {
        if (currentAction == ActionType.BLOCK_PLACEMENT) {
            currentAction = ActionType.PLAYER_MOVEMENT;
        } else if (currentAction == ActionType.PLAYER_MOVEMENT) {
            currentPlayer = (currentPlayer + 1) % players.size();
            if (enemy.getFirst() == Boolean.TRUE && enemyMove == true) {
                if (enemy.getSecond().getEnemyAI() instanceof SingleStepRandomAI) {
                    labyrinth.enemyUpdateCoordinate(enemy.getSecond(), enemy.getSecond().move(players));
                    enemy.getSecond().playerHit(players);
                }
                if (enemy.getSecond().getEnemyAI() instanceof RandomAI) {
                    labyrinth.enemyUpdateCoordinate(enemy.getSecond(), enemy.getSecond().move(players));
                    enemy.getSecond().playerHit(players);
                }
                if (enemy.getSecond().getEnemyAI() instanceof ChaseAI) {
                    labyrinth.enemyUpdateCoordinate(enemy.getSecond(), enemy.getSecond().move(players));
                    enemy.getSecond().playerHit(players);
                }
                if (enemy.getSecond().isPresentLastHit()) {
                    if (enemy.getSecond().getLastHit().equals(this.getCurrentPlayer())) {
                        enemy.getSecond().clearLastHit();
                    }
                }
                currentAction = ActionType.BLOCK_PLACEMENT;
                saveController.save(this);
            } else {
                currentAction = ActionType.BLOCK_PLACEMENT;
                saveController.save(this);
            }
        }
        this.getLab().updateView();
    }

    /**
     * Adds an additional power-up to the game.
     *
     * @param p the power-up to add
     */
    public void addAddictionalPowerUp(final PowerUp p) {
        powerUps.add(p);
    }
}
