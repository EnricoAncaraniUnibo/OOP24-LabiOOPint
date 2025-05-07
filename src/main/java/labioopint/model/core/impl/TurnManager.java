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
    private final LabyrinthImpl maze;
    private List<PlayerImpl> players;
    //private final Optional<Enemy> enemy;
    private Pair<Boolean,Enemy> enemy;
    private final List<PowerUp> powerUps;
    private ActionType currentAction;
    private int index;
    //private Optional<Integer> indexNext;
    private final SaveController saveController;

    /**
     * Constructs a TurnManager with the given settings.
     *
     * @param st the game settings used to initialize the game components
     */
    public TurnManager(final Settings st) {
        saveController = new SaveControllerImpl(this);
        currentAction = ActionType.BLOCK_PLACEMENT;
        index = 0;
        final BuilderImpl bi = new BuilderImpl(st, this);
        players = bi.createPlayers();
        players = new RandomTurnChooser(players).randomOrder();
        enemy = new Pair<Boolean,Enemy>(true, null);
        if(enemy.getFirst() == true){
            enemy = new Pair<>(true, bi.createEnemy());
        }
        powerUps = bi.createPowerUps();
        maze = bi.createMaze();
        //indexNext = Optional.empty();
    }

    /**
     * Sets the next player to take their turn.
     */
    public void setNextTurnPlayer() {
        //indexNext = Optional.of(index);
    }

    /**
     * Gets the labyrinth of the game.
     *
     * @return the labyrinth
     */
    public LabyrinthImpl getLab() {
        return maze;
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
    public Pair<Boolean,Enemy> getEnemy() {
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
        return players.get(index);
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
            index = (index + 1) % players.size();
            /*if (indexNext.isPresent()) {
                index = indexNext.get();
                indexNext = Optional.empty();
            }*/
            if (enemy.getFirst() == Boolean.TRUE) {
                if (enemy.getSecond().getEnemyAI() instanceof SingleStepRandomAI) {
                    maze.enemyUpdateCoordinate(enemy.getSecond(), enemy.getSecond().move(players));
                    enemy.getSecond().playerHit(players);
                }
                if (enemy.getSecond().getEnemyAI() instanceof RandomAI) {
                    maze.enemyUpdateCoordinate(enemy.getSecond(), enemy.getSecond().move(players));
                    enemy.getSecond().playerHit(players);
                }
                if (enemy.getSecond().getEnemyAI() instanceof ChaseAI) {
                    maze.enemyUpdateCoordinate(enemy.getSecond(), enemy.getSecond().move(players));
                    enemy.getSecond().playerHit(players);
                }
                currentAction = ActionType.BLOCK_PLACEMENT;
                saveController.save();
            } else {
                currentAction = ActionType.BLOCK_PLACEMENT;
                saveController.save();
            }
        } else if (currentAction == ActionType.ENEMY_MOVEMENT) {
            currentAction = ActionType.BLOCK_PLACEMENT;
        }
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
