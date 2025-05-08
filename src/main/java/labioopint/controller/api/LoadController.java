package labioopint.controller.api;

import labioopint.model.core.impl.TurnManager;

public interface LoadController {
    
    public void loadLastGame();

    public boolean isLoaded();

    public TurnManager getTurnManager();
}
