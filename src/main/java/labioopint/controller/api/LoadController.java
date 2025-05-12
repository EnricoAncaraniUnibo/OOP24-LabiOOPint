package labioopint.controller.api;

import labioopint.model.core.impl.TurnManager;

public interface LoadController {
    void loadLastGame();

    boolean isLoaded();

    TurnManager getTurnManager();
}
