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
        while(initialPosition == newCoordinate) {
            initialPosition = tm_easy.getLab().getEnemyCoordinate(easy);
            assertEquals(initialPosition, newCoordinate);
            tm_easy.nextAction();
            tm_easy.nextAction();
            tm_easy.nextAction();
            newCoordinate = tm_easy.getLab().getEnemyCoordinate(easy);
            tm_easy.getLab().moveBlock(new Coordinate(initialPosition.getRow()+1, 0), Direction.RIGHT);
            tm_easy.nextAction();
            tm_easy.nextAction();
            tm_easy.nextAction();
            newCoordinate = tm_easy.getLab().getEnemyCoordinate(easy);
            tm_easy.getLab().moveBlock(new Coordinate(initialPosition.getRow()-1, 0), Direction.RIGHT);
            tm_easy.nextAction();
            tm_easy.nextAction();
            tm_easy.nextAction();
            newCoordinate = tm_easy.getLab().getEnemyCoordinate(easy);
            tm_easy.getLab().moveBlock(new Coordinate(0, initialPosition.getColumn()-1), Direction.DOWN);
            tm_easy.nextAction();
            tm_easy.nextAction();
            tm_easy.nextAction();
            newCoordinate = tm_easy.getLab().getEnemyCoordinate(easy);
            tm_easy.getLab().moveBlock(new Coordinate(0, initialPosition.getColumn()+1), Direction.DOWN);
            tm_easy.nextAction();
            tm_easy.nextAction();
            tm_easy.nextAction();
            newCoordinate = tm_easy.getLab().getEnemyCoordinate(easy);
        }
        assertNotEquals(initialPosition, newCoordinate);
    }

    @Test
    void testMediumEnemyMovement() {
        TurnManager tm_medium = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.MEDIUM));
        Enemy medium = tm_medium.getEnemy().get();
        assertNotNull(medium);
        Coordinate initialPosition = tm_medium.getLab().getEnemyCoordinate(medium);
        Coordinate newCoordinate = tm_medium.getLab().getEnemyCoordinate(medium);
        while(initialPosition == newCoordinate) {
            initialPosition = tm_medium.getLab().getEnemyCoordinate(medium);
            assertEquals(initialPosition, newCoordinate);
            tm_medium.nextAction();
            tm_medium.nextAction();
            tm_medium.nextAction();
            newCoordinate = tm_medium.getLab().getEnemyCoordinate(medium);
            tm_medium.getLab().moveBlock(new Coordinate(initialPosition.getRow()+1, 0), Direction.RIGHT);
            tm_medium.nextAction();
            tm_medium.nextAction();
            tm_medium.nextAction();
            newCoordinate = tm_medium.getLab().getEnemyCoordinate(medium);
            tm_medium.getLab().moveBlock(new Coordinate(initialPosition.getRow()-1, 0), Direction.RIGHT);
            tm_medium.nextAction();
            tm_medium.nextAction();
            tm_medium.nextAction();
            newCoordinate = tm_medium.getLab().getEnemyCoordinate(medium);
            tm_medium.getLab().moveBlock(new Coordinate(0, initialPosition.getColumn()-1), Direction.DOWN);
            tm_medium.nextAction();
            tm_medium.nextAction();
            tm_medium.nextAction();
            newCoordinate = tm_medium.getLab().getEnemyCoordinate(medium);
            tm_medium.getLab().moveBlock(new Coordinate(0, initialPosition.getColumn()+1), Direction.DOWN);
            tm_medium.nextAction();
            tm_medium.nextAction();
            tm_medium.nextAction();
            newCoordinate = tm_medium.getLab().getEnemyCoordinate(medium);
        }
        assertNotEquals(initialPosition, newCoordinate);
    }

    @Test
    void testHardEnemyMovement() {
        TurnManager tm_hard = new TurnManager(new Settings(1, 2, 3, EnemyDifficulty.HARD));
        Enemy hard = tm_hard.getEnemy().get();
        Coordinate initialPosition = tm_hard.getLab().getEnemyCoordinate(hard);
        List<PlayerImpl> players = tm_hard.getPlayers();
        tm_hard.getLab().PlayerUpdateCoordinate(players.get(0), new Coordinate(initialPosition.getRow(), initialPosition.getColumn()-2));
        BlockImpl b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.NINETY);
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()-1));
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()));
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()-2));
        tm_hard.nextAction();
        tm_hard.nextAction();
        tm_hard.nextAction();
        assertEquals(tm_hard.getLab().getPlayerCoordinate(players.get(0)), tm_hard.getLab().getEnemyCoordinate(hard));

        List<Coordinate> ls = new ArrayList<>();
        ls.add(initialPosition);
        tm_hard.getLab().EnemyUpdateCoordinate(hard, ls);
        tm_hard.getLab().PlayerUpdateCoordinate(players.get(0), new Coordinate(initialPosition.getRow(), initialPosition.getColumn()+2));
        b = new BlockImpl(BlockType.CORRIDOR);
        b.setRotation(Rotation.NINETY);
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()+1));
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()));
        tm_hard.getLab().setBlock(b, new Coordinate(initialPosition.getRow(), initialPosition.getColumn()+2));
        tm_hard.nextAction();
        tm_hard.nextAction();
        tm_hard.nextAction();
        assertEquals(tm_hard.getLab().getPlayerCoordinate(players.get(0)), tm_hard.getLab().getEnemyCoordinate(hard));
    }

    @Test
    void TestPlayerHit() {

    }
}
