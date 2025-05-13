package labioopint.model.maze.api;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.api.Pair;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.Rotation;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;

public interface Labyrinth extends Serializable {

    void initialize();

    Block getOutsideBlock();

    boolean moveBlock(Coordinate c, Direction d);

    void setBlock(Block b, Coordinate coor);

    Coordinate getPlayerCoordinate(Player p);

    Coordinate getPowerUpCoordinate(PowerUp p);

    List<PowerUp> getPowerUpsNotCollected();

    Coordinate getEnemyCoordinate(Enemy e);

    void movePlayer(Player p, Direction dir);

    Maze getGrid();

    void rotateOutsideBlock(Rotation blockRotation);

    void addPowerUp(PowerUp p);

    void playerUpdateCoordinate(Player p, Coordinate coor);

    void enemyUpdateCoordinate(Enemy e, List<Coordinate> coor);

    void powerUpUpdateCoordinate(PowerUp p, Coordinate coor);

    List<Player> getPlayers();

    Pair<Boolean, Enemy> getEnemy();

    List<PowerUp> getObjectives();

    void removePlayerObject(Player p, PowerUp pou);

    Optional<Player> checkWinner();

}
