package labioopint.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import labioopint.model.api.Coordinate;
import labioopint.model.api.Settings;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Direction;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;

class EnemyTest {

    @Test
    void testEasyEnemyMovement() {
        final TurnManager tmEasy = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.EASY));
        final Enemy easy = tmEasy.getEnemy().get();
        assertNotNull(easy);
        final Coordinate initialPosition = tmEasy.getLab().getEnemyCoordinate(easy);
        Coordinate newCoordinate = tmEasy.getLab().getEnemyCoordinate(easy);
        final BlockImpl b = new BlockImpl(BlockType.CORNER);
        b.setRotation(Rotation.ZERO);
        tmEasy.getLab().setBlock(b, new Coordinate(2, 3));
        tmEasy.getLab().setBlock(b, new Coordinate(2, 2));
        tmEasy.getLab().setBlock(b, new Coordinate(3, 2));
        assertEquals(initialPosition, newCoordinate);
        tmEasy.nextAction();
        tmEasy.nextAction();
        tmEasy.nextAction();
        newCoordinate = tmEasy.getLab().getEnemyCoordinate(easy);
        assertEquals(initialPosition, newCoordinate);
        final BlockImpl b1 = new BlockImpl(BlockType.CORRIDOR);
        b1.setRotation(Rotation.ZERO);
        tmEasy.getLab().setBlock(b1, new Coordinate(2, 2));
        tmEasy.getLab().setBlock(b1, new Coordinate(1, 2));
        tmEasy.nextAction();
        newCoordinate = tmEasy.getLab().getEnemyCoordinate(easy);
        assertNotEquals(initialPosition, newCoordinate);
    }

    @Test
    void testMediumEnemyMovement() {
        final TurnManager tmMedium = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.MEDIUM));
        final Enemy medium = tmMedium.getEnemy().get();
        assertNotNull(medium);
        final Coordinate initialPosition = tmMedium.getLab().getEnemyCoordinate(medium);
        Coordinate newCoordinate = tmMedium.getLab().getEnemyCoordinate(medium);
        final BlockImpl b = new BlockImpl(BlockType.CORNER);
        b.setRotation(Rotation.ZERO);
        tmMedium.getLab().setBlock(b, new Coordinate(2, 3));
        tmMedium.getLab().setBlock(b, new Coordinate(2, 2));
        tmMedium.getLab().setBlock(b, new Coordinate(3, 2));
        assertEquals(initialPosition, newCoordinate);
        tmMedium.nextAction();
        tmMedium.nextAction();
        tmMedium.nextAction();
        newCoordinate = tmMedium.getLab().getEnemyCoordinate(medium);
        assertEquals(initialPosition, newCoordinate);
        final BlockImpl b1 = new BlockImpl(BlockType.CORRIDOR);
        b1.setRotation(Rotation.ZERO);
        tmMedium.getLab().setBlock(b1, new Coordinate(2, 2));
        tmMedium.getLab().setBlock(b1, new Coordinate(1, 2));
        tmMedium.nextAction();
        tmMedium.nextAction();
        tmMedium.nextAction();
        newCoordinate = tmMedium.getLab().getEnemyCoordinate(medium);
        assertNotEquals(initialPosition, newCoordinate);
    }

    @Test
    void testHardEnemyMovement() {
        final TurnManager tmHard = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.HARD));
        final Enemy hard = tmHard.getEnemy().get();
        final Coordinate initialPosition = tmHard.getLab().getEnemyCoordinate(hard);
        final List<PlayerImpl> players = tmHard.getPlayers();
        tmHard.getLab().playerUpdateCoordinate(players.get(0),
                new Coordinate(initialPosition.getRow(), initialPosition.getColumn() - 2));
        BlockImpl b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.NINETY);
        tmHard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn() - 1));
        tmHard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()));
        tmHard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn() - 2));
        tmHard.nextAction();
        tmHard.nextAction();
        tmHard.nextAction();
        assertEquals(tmHard.getLab().getPlayerCoordinate(players.get(0)), tmHard.getLab().getEnemyCoordinate(hard));

        final List<Coordinate> ls = new ArrayList<>();
        ls.add(initialPosition);
        tmHard.getLab().enemyUpdateCoordinate(hard, ls);
        tmHard.getLab().playerUpdateCoordinate(players.get(0),
                new Coordinate(initialPosition.getRow(), initialPosition.getColumn() + 2));
        b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.NINETY);
        tmHard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn() + 1));
        tmHard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()));
        tmHard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn() + 2));
        tmHard.nextAction();
        tmHard.nextAction();
        tmHard.nextAction();
        assertEquals(tmHard.getLab().getPlayerCoordinate(players.get(0)), tmHard.getLab().getEnemyCoordinate(hard));
    }

    @Test
    void testPlayerHit() {
        final TurnManager tm = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.HARD));
        final List<PlayerImpl> players = tm.getPlayers();
        final List<PowerUp> lPowerUps = tm.getPowerUps();
        final BlockImpl b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.ZERO);
        tm.getLab().setBlock(b, new Coordinate(0, 2));
        tm.getLab().setBlock(b, new Coordinate(1, 2));
        tm.getLab().setBlock(b, new Coordinate(2, 2));
        tm.getLab().playerUpdateCoordinate(players.get(0), new Coordinate(0, 2));
        tm.getLab().powerUpUpdateCoordinate(lPowerUps.get(0), new Coordinate(1, 2));
        tm.getLab().movePlayer(players.get(0), Direction.DOWN);
        assertEquals(players.get(0).getObjetives().size(), 1);
        assertEquals(tm.getLab().getListOfPowerUps().size(), lPowerUps.size() - 1);
        tm.nextAction();
        tm.nextAction();
        tm.nextAction();
        assertEquals(players.get(0).getObjetives().size(), 0);
        assertEquals(tm.getLab().getListOfPowerUps().size(), lPowerUps.size());
    }
}
