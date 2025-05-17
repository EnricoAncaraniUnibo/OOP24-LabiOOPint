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

        private static final String END_TURN_STRING = "End Turn";
        private static final int TRIES = 50;

        @Test
        void testEasyEnemyMovement() {
                final Settings set = new SettingsImpl(1, 2, 3, EnemyDifficulty.EASY);
                final GameController gc = new GameControllerImpl(set);
                assertNotNull(gc.getLabyrinth().getEnemy().getSecond());
                final Coordinate initialPosition = gc.getLabyrinth()
                                .getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                Coordinate newCoordinate = gc.getLabyrinth()
                                .getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                final Block b = new BlockImpl(BlockType.CORNER, 0);
                b.setRotation(Rotation.ZERO);
                gc.getLabyrinth().setBlock(b, new CoordinateImpl(2, 3));
                gc.getLabyrinth().setBlock(b, new CoordinateImpl(2, 2));
                gc.getLabyrinth().setBlock(b, new CoordinateImpl(3, 2));
                assertEquals(initialPosition, newCoordinate);
                gc.getTurnManager().nextAction();
                gc.action(END_TURN_STRING);
                newCoordinate = gc.getLabyrinth().getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                assertEquals(initialPosition, newCoordinate);
                final Block b1 = new BlockImpl(BlockType.CORRIDOR, 0);
                b1.setRotation(Rotation.ZERO);
                gc.getLabyrinth().setBlock(b1, new CoordinateImpl(2, 2));
                gc.getLabyrinth().setBlock(b1, new CoordinateImpl(1, 2));
                gc.getTurnManager().nextAction();
                gc.action(END_TURN_STRING);
                newCoordinate = gc.getLabyrinth().getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                assertNotEquals(initialPosition, newCoordinate);
        }

        @Test
        void testMediumEnemyMovement() {
                final Settings set = new SettingsImpl(1, 2, 3, EnemyDifficulty.MEDIUM);
                final GameController gc = new GameControllerImpl(set);
                assertNotNull(gc.getLabyrinth().getEnemy().getSecond());
                final Coordinate initialPosition = gc.getLabyrinth()
                                .getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                Coordinate newCoordinate = gc.getLabyrinth()
                                .getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                final Block b = new BlockImpl(BlockType.CORNER, 0);
                b.setRotation(Rotation.ZERO);
                gc.getLabyrinth().setBlock(b, new CoordinateImpl(2, 3));
                gc.getLabyrinth().setBlock(b, new CoordinateImpl(2, 2));
                gc.getLabyrinth().setBlock(b, new CoordinateImpl(3, 2));
                assertEquals(initialPosition, newCoordinate);
                gc.getTurnManager().nextAction();
                gc.action(END_TURN_STRING);
                newCoordinate = gc.getLabyrinth().getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                assertEquals(initialPosition, newCoordinate);
                final Block b1 = new BlockImpl(BlockType.CORRIDOR, 0);
                b1.setRotation(Rotation.ZERO);
                gc.getLabyrinth().setBlock(b1, new CoordinateImpl(2, 2));
                gc.getLabyrinth().setBlock(b1, new CoordinateImpl(1, 2));
                gc.getTurnManager().nextAction();
                gc.action(END_TURN_STRING);
                newCoordinate = gc.getLabyrinth().getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                int i = 0;
                while (initialPosition.equals(newCoordinate) && i < TRIES) {
                        gc.getTurnManager().nextAction();
                        gc.action(END_TURN_STRING);
                        newCoordinate = gc.getLabyrinth().getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                        i++;
                }
                assertNotEquals(initialPosition, newCoordinate);
        }

        @Test
        void testHardEnemyMovement() {
                final Settings set = new SettingsImpl(1, 2, 3, EnemyDifficulty.HARD);
                final GameController gc = new GameControllerImpl(set);
                final Coordinate initialPosition = gc.getLabyrinth()
                                .getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond());
                gc.getLabyrinth().playerUpdateCoordinate(gc.getLabyrinth().getPlayers().get(0),
                                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() - 2));
                BlockImpl b = new BlockImpl(BlockType.CORRIDOR, 0);
                b.setRotation(Rotation.NINETY);
                gc.getLabyrinth().setBlock(b,
                                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() - 1));
                gc.getLabyrinth().setBlock(b,
                                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn()));
                gc.getLabyrinth().setBlock(b,
                                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() - 2));
                gc.getTurnManager().nextAction();
                gc.action(END_TURN_STRING);
                assertEquals(gc.getLabyrinth().getPlayerCoordinate(gc.getLabyrinth().getPlayers().get(0)),
                                gc.getLabyrinth().getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond()));

                final List<Coordinate> ls = new ArrayList<>();
                ls.add(initialPosition);
                gc.getLabyrinth().enemyUpdateCoordinate(gc.getLabyrinth().getEnemy().getSecond(), ls);
                gc.getLabyrinth().playerUpdateCoordinate(gc.getLabyrinth().getPlayers().get(0),
                                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() + 2));
                b = new BlockImpl(BlockType.CORRIDOR, 0);
                b.setRotation(Rotation.NINETY);
                gc.getLabyrinth().setBlock(b,
                                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() + 1));
                gc.getLabyrinth().setBlock(b,
                                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn()));
                gc.getLabyrinth().setBlock(b,
                                new CoordinateImpl(initialPosition.getRow(), initialPosition.getColumn() + 2));
                gc.getTurnManager().nextAction();
                gc.action(END_TURN_STRING);
                assertEquals(gc.getLabyrinth().getPlayerCoordinate(gc.getLabyrinth().getPlayers().get(0)),
                                gc.getLabyrinth().getEnemyCoordinate(gc.getLabyrinth().getEnemy().getSecond()));
        }

        @Test
        void testPlayerHit() {
                final Settings set = new SettingsImpl(1, 2, 3, EnemyDifficulty.HARD);
                final GameController gc = new GameControllerImpl(set);
                final Labyrinth lab = gc.getLabyrinth();
                final List<Player> players = lab.getPlayers();
                final List<PowerUp> lPowerUps = lab.getPowerUpsNotCollected();
                final Block b = new BlockImpl(BlockType.CORRIDOR, 0);
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
                gc.action(END_TURN_STRING);
                assertEquals(players.get(0).getObjetives().size(), 0);
                assertEquals(lab.getPowerUpsNotCollected().size(), lPowerUps.size());
        }
}
