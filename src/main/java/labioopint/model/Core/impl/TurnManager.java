package labioopint.model.Core.impl;

import java.util.*;

public class TurnManager {

    List<Player> players = new ArrayList<>();

    public TurnManager() {
        Builder.start();
    }

    public void createEnemy() {

    }

    public void addPlayer(Player p) {
        players.add(p);
    }
    
}
