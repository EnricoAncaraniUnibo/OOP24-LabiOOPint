package labioopint.controller.impl;

import labioopint.controller.api.ActionController;
import labioopint.controller.api.GameController;
import labioopint.controller.api.SaveController;
import labioopint.model.core.api.Builder;
import labioopint.model.core.api.TurnManager;
import labioopint.model.core.impl.BuilderImpl;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.utilities.api.Settings;

/**
 * Implementation of the class used to manage the entire game.
 */
public final class GameControllerImpl implements GameController {

    private final Labyrinth labyrinth;
    private final TurnManager turnManager;
    private final ActionController actionController;
    private final SaveController saveController;
    public static final long serialVersionUID = 1L;

    /**
     * Constructs an {@code GameControllerImpl} with the settings.
     * 
     * @param settings the settings used to create the game.
     */
    public GameControllerImpl(final Settings settings) {
        saveController = new SaveControllerImpl();
        final Builder builder = new BuilderImpl(settings);
        labyrinth = builder.createMaze();
        turnManager = builder.createTurnManager();
        actionController = builder.createActionController();
    }

    @Override
    public Player getCurrentPlayer() {
        return labyrinth.getPlayers().get(turnManager.getCurrentPlayer());
    }

    @Override
    public TurnManager getTurnManager() {
        return turnManager;
    }

    @Override
    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    @Override
    public void action(final Object action) {
        actionController.action(action, labyrinth, turnManager);
        saveController.save(this);
    }
}
