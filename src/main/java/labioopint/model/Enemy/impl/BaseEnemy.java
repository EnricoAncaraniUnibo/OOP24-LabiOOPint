package labioopint.model.Enemy.impl;

import java.util.*;

public abstract class BaseEnemy {

    public Coordinate position;  

    public BaseEnemy(Coordinate startPosition) {
        this.position = startPosition;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public abstract void move(Labyrinth maze, List<Player> players);

    /*
     * Quando un player viene mangiato dal nemico, il player perde il primo PowerUp
     * che è in coda. Se la lista fosse vuota allora il player sta fermo un turno.
     */
    public void hit(Player player) {
        if(position == Player.position) {
            playerPowerUP.removeFirst();
        }
    }
    
}
