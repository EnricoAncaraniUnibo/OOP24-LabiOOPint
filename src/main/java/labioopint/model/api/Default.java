package labioopint.model.api;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.maze.impl.Player;

public class Default {
    public List<PowerUp> getPowerUps() {
        return new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return new ArrayList<>();
    }

    public List<Enemy> getEnemy() {
        return new ArrayList<>();
    }
}
