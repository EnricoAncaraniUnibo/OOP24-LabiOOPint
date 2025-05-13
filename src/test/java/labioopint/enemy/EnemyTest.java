package labioopint.enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import labioopint.controller.api.GameController;
import labioopint.controller.impl.GameControllerImpl;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.api.Settings;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.impl.SettingsImpl;

class EnemyTest {

    @Test
    void testEasyEnemyMovement() {
        Settings set = new SettingsImpl(1, 2, 3, EnemyDifficulty.EASY);
        GameController gc = new GameControllerImpl(set);
        Labyrinth lab = gc.getLab();
        final Enemy e = lab.getEnemy().getSecond();
        assertNotNull(e);
        final Coordinate initialPosition = lab.getEnemyCoordinate(e);
        Coordinate newCoordinate = lab.getEnemyCoordinate(e);
        final Block b = new BlockImpl(BlockType.CORNER);
        b.setRotation(Rotation.ZERO);
        lab.setBlock(b, new CoordinateImpl(2, 3));
        lab.setBlock(b, new CoordinateImpl(2, 2));
        lab.setBlock(b, new CoordinateImpl(3, 2));
        assertEquals(initialPosition, newCoordinate);
        gc.getTurnManager().nextAction();
        gc.action("End Turn");
        newCoordinate = lab.getEnemyCoordinate(e);
        assertEquals(initialPosition, newCoordinate);
        final Block b1 = new BlockImpl(BlockType.CORRIDOR);
        b1.setRotation(Rotation.ZERO);
        lab.setBlock(b1, new CoordinateImpl(2, 2));
        lab.setBlock(b1, new CoordinateImpl(1, 2));
        gc.getTurnManager().nextAction();
        gc.action("End Turn");
        newCoordinate = lab.getEnemyCoordinate(e);
        assertNotEquals(initialPosition, newCoordinate);
    }

    @Test
    void testMediumEnemyMovement() {
        Settings set = new SettingsImpl(1, 2, 3, EnemyDifficulty.MEDIUM);
        GameController gc = new GameControllerImpl(set);
        Labyrinth lab = gc.getLab();
        final Enemy e = lab.getEnemy().getSecond();
        assertNotNull(e);
        final Coordinate initialPosition = lab.getEnemyCoordinate(e);
        Coordinate newCoordinate = lab.getEnemyCoordinate(e);
        final Block b = new BlockImpl(BlockType.CORNER);
        b.setRotation(Rotation.ZERO);
        lab.setBlock(b, new CoordinateImpl(2, 3));
        lab.setBlock(b, new CoordinateImpl(2, 2));
        lab.setBlock(b, new CoordinateImpl(3, 2));
        assertEquals(initialPosition, newCoordinate);
        gc.getTurnManager().nextAction();
        gc.action("End Turn");
        newCoordinate = lab.getEnemyCoordinate(e);
        assertEquals(initialPosition, newCoordinate);
        final Block b1 = new BlockImpl(BlockType.CORRIDOR);
        b1.setRotation(Rotation.ZERO);
        lab.setBlock(b1, new CoordinateImpl(2, 2));
        lab.setBlock(b1, new CoordinateImpl(1, 2));
        gc.getTurnManager().nextAction();
        gc.action("End Turn");
        newCoordinate = lab.getEnemyCoordinate(e);
        while (initialPosition.equals(newCoordinate)) {
            gc.getTurnManager().nextAction();
            gc.action("End Turn");
            newCoordinate = lab.getEnemyCoordinate(e);
        }
        assertNotEquals(initialPosition, newCoordinate);
    }

    @Test
    void testHardEnemyMovement() {
        Settings set = new SettingsImpl(1, 2, 3, EnemyDifficulty.HARD);
        GameController gc = new GameControllerImpl(set);
        Labyrinth lab = gc.getLab();
        final Enemy e = lab.getEnemy().getSecond();
        final Coordinate initialPosition = lab.getEnemyCoordinate(e);
        final List<Player> players = lab.getPlayers();
        lab.playerUpdateCoordinate(players.get(0),
                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() - 2));
        BlockImpl b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.NINETY);
        lab.setBlock(b, new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() - 1));
        lab.setBlock(b, new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn()));
        lab.setBlock(b, new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() - 2));
        gc.getTurnManager().nextAction();
        gc.action("End Turn");
        assertEquals(lab.getPlayerCoordinate(players.get(0)), lab.getEnemyCoordinate(e));

        final List<Coordinate> ls = new ArrayList<>();
        ls.add(initialPosition);
        lab.enemyUpdateCoordinate(e, ls);
        lab.playerUpdateCoordinate(players.get(0),
                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() + 2));
        b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.NINETY);
        lab.setBlock(b, new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() + 1));
        lab.setBlock(b, new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn()));
        lab.setBlock(b, new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() + 2));
        gc.getTurnManager().nextAction();
        gc.action("End Turn");
        assertEquals(lab.getPlayerCoordinate(players.get(0)), lab.getEnemyCoordinate(e));
    }

    @Test
    void testPlayerHit() {
        Settings set = new SettingsImpl(1, 2, 3, EnemyDifficulty.HARD);
        GameController gc = new GameControllerImpl(set);
        Labyrinth lab = gc.getLab();
        final List<Player> players = lab.getPlayers();
        final List<PowerUp> lPowerUps = lab.getPowerUpsNotCollected();
        final Block b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.ZERO);
        lab.setBlock(b, new CoordinateImpl(0, 2));
        lab.setBlock(b, new CoordinateImpl(1, 2));
        lab.setBlock(b, new CoordinateImpl(2, 2));
        lab.playerUpdateCoordinate(players.get(0), new CoordinateImpl(0, 2));
        lab.powerUpUpdateCoordinate(lPowerUps.get(0), new CoordinateImpl(1, 2));
        lab.movePlayer(players.get(0), Direction.DOWN);
        assertEquals(players.get(0).getObjetives().size(), 1);
        assertEquals(lab.getPowerUpsNotCollected().size(), lPowerUps.size() - 1);
        gc.getTurnManager().nextAction();
        gc.action("End Turn");
        assertEquals(players.get(0).getObjetives().size(), 0);
        assertEquals(lab.getPowerUpsNotCollected().size(), lPowerUps.size());
    }
}
