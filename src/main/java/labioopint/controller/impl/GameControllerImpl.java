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

    private final Labyrinth lab;
    private final TurnManager turn;
    private final ActionController ac;
    private final SaveController save;

    /**
     * Constructs an {@code GameControllerImpl} with the settings.
     * 
     * @param set the settings used to create the game.
     */
    public GameControllerImpl(final Settings set) {
        save = new SaveControllerImpl();
        Builder builder = new BuilderImpl(set);
        lab = builder.createMaze();
        turn = builder.createTurnManager();
        ac = builder.createActionController();
    }

    @Override
    public Player getCurrentPlayer() {
        return lab.getPlayers().get(turn.getCurrentPlayer());
    }

    @Override
    public TurnManager getTurnManager() {
        return turn;
    }

    @Override
    public Labyrinth getLab() {
        return lab;
    }

    @Override
    public void action(final Object s) {
        ac.action(s, lab, turn);
        save.save(this);
    }
}
