package labioopint.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import labioopint.model.Block.api.BlockType;
import labioopint.model.Block.api.Rotation;
import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyDifficulty;
import labioopint.model.Maze.api.Direction;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.Settings;

public class EnemyTest {

    @Test
    void testEasyEnemyMovement() {
        TurnManager tm_easy = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.EASY));
        Enemy easy = tm_easy.getEnemy().get();
        assertNotNull(easy);
        Coordinate initialPosition = tm_easy.getLab().getEnemyCoordinate(easy);
        Coordinate newCoordinate = tm_easy.getLab().getEnemyCoordinate(easy);
        BlockImpl b = new BlockImpl(BlockType.CORNER);
        b.setRotation(Rotation.ZERO);
        tm_easy.getLab().setBlock(b, new Coordinate(2, 3));
        tm_easy.getLab().setBlock(b, new Coordinate(2, 2));
        tm_easy.getLab().setBlock(b, new Coordinate(3, 2));
        assertEquals(initialPosition, newCoordinate);
        tm_easy.nextAction();
        tm_easy.nextAction();
        tm_easy.nextAction();
        newCoordinate = tm_easy.getLab().getEnemyCoordinate(easy);
        assertEquals(initialPosition, newCoordinate);
        BlockImpl b1 = new BlockImpl(BlockType.CORRIDOR);
        b1.setRotation(Rotation.ZERO);
        tm_easy.getLab().setBlock(b1, new Coordinate(2, 2));
        tm_easy.getLab().setBlock(b1, new Coordinate(1, 2));
        tm_easy.nextAction();
        newCoordinate = tm_easy.getLab().getEnemyCoordinate(easy);
        assertNotEquals(initialPosition, newCoordinate);
    }

    @Test
    void testMediumEnemyMovement() {
        TurnManager tm_medium = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.MEDIUM));
        Enemy medium = tm_medium.getEnemy().get();
        assertNotNull(medium);
        Coordinate initialPosition = tm_medium.getLab().getEnemyCoordinate(medium);
        Coordinate newCoordinate = tm_medium.getLab().getEnemyCoordinate(medium);
        BlockImpl b = new BlockImpl(BlockType.CORNER);
        b.setRotation(Rotation.ZERO);
        tm_medium.getLab().setBlock(b, new Coordinate(2, 3));
        tm_medium.getLab().setBlock(b, new Coordinate(2, 2));
        tm_medium.getLab().setBlock(b, new Coordinate(3, 2));
        assertEquals(initialPosition, newCoordinate);
        tm_medium.nextAction();
        tm_medium.nextAction();
        tm_medium.nextAction();
        newCoordinate = tm_medium.getLab().getEnemyCoordinate(medium);
        assertEquals(initialPosition, newCoordinate);
        BlockImpl b1 = new BlockImpl(BlockType.CORRIDOR);
        b1.setRotation(Rotation.ZERO);
        tm_medium.getLab().setBlock(b1, new Coordinate(2, 2));
        tm_medium.getLab().setBlock(b1, new Coordinate(1, 2));
        tm_medium.nextAction();
        tm_medium.nextAction();
        tm_medium.nextAction();
        newCoordinate = tm_medium.getLab().getEnemyCoordinate(medium);
        assertNotEquals(initialPosition, newCoordinate);
    }

    @Test
    void testHardEnemyMovement() {
        TurnManager tm_hard = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.HARD));
        Enemy hard = tm_hard.getEnemy().get();
        Coordinate initialPosition = tm_hard.getLab().getEnemyCoordinate(hard);
        List<PlayerImpl> players = tm_hard.getPlayers();
        tm_hard.getLab().PlayerUpdateCoordinate(players.get(0),
                new Coordinate(initialPosition.getRow(), initialPosition.getColumn() - 2));
        BlockImpl b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.NINETY);
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn() - 1));
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()));
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn() - 2));
        tm_hard.nextAction();
        tm_hard.nextAction();
        tm_hard.nextAction();
        assertEquals(tm_hard.getLab().getPlayerCoordinate(players.get(0)), tm_hard.getLab().getEnemyCoordinate(hard));

        List<Coordinate> ls = new ArrayList<>();
        ls.add(initialPosition);
        tm_hard.getLab().EnemyUpdateCoordinate(hard, ls);
        tm_hard.getLab().PlayerUpdateCoordinate(players.get(0),
                new Coordinate(initialPosition.getRow(), initialPosition.getColumn() + 2));
        b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.NINETY);
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn() + 1));
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()));
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn() + 2));
        tm_hard.nextAction();
        tm_hard.nextAction();
        tm_hard.nextAction();
        assertEquals(tm_hard.getLab().getPlayerCoordinate(players.get(0)), tm_hard.getLab().getEnemyCoordinate(hard));
    }

    @Test
    void TestPlayerHit() {
        TurnManager tm = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.HARD));
        List<PlayerImpl> players = tm.getPlayers();
        List<PowerUp> lPowerUps = tm.getPowerUps();
        BlockImpl b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.ZERO);
        tm.getLab().setBlock(b, new Coordinate(0, 2));
        tm.getLab().setBlock(b, new Coordinate(1, 2));
        tm.getLab().setBlock(b, new Coordinate(2, 2));
        tm.getLab().PlayerUpdateCoordinate(players.get(0), new Coordinate(0, 2));
        tm.getLab().PowerUpUpdateCoordinate(lPowerUps.get(0), new Coordinate(1, 2));
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
