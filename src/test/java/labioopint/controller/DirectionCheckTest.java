package labioopint.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import labioopint.controller.api.DirectionCheck;
import labioopint.controller.api.GameController;
import labioopint.controller.impl.DirectionCheckImpl;
import labioopint.controller.impl.GameControllerImpl;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.impl.SettingsImpl;

class DirectionCheckTest {
    private static final int ENEMY_NUMBER = 0;
    private static final int PLAYER_NUMBER = 1;
    private static final int POWERUP_NUMBER = 2;
    private static final EnemyDifficulty ENEMY_DIFFICULTY = EnemyDifficulty.EASY;

    @Test
    void checkRight() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final DirectionCheck directionCheck = new DirectionCheckImpl();
        Block b = new BlockImpl(BlockType.CORRIDOR, 0);
        b.setRotation(Rotation.ZERO);
        final Coordinate blockCoordinate = new CoordinateImpl(0, 0);
        lab.setBlock(b, blockCoordinate);
        assertFalse(directionCheck.checkRightEntrance(blockCoordinate, lab));
        b.setRotation(Rotation.NINETY);
        lab.setBlock(b, blockCoordinate);
        assertTrue(directionCheck.checkRightEntrance(blockCoordinate, lab));
        b = new BlockImpl(BlockType.CORNER, 0);
        b.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
        lab.setBlock(b, blockCoordinate);
        assertFalse(directionCheck.checkRightEntrance(blockCoordinate, lab));
        b = new BlockImpl(BlockType.CROSSING, 0);
        b.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
        lab.setBlock(b, blockCoordinate);
        assertFalse(directionCheck.checkRightEntrance(blockCoordinate, lab));
    }

    @Test
    void checkLeft() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final DirectionCheck directionCheck = new DirectionCheckImpl();
        Block b = new BlockImpl(BlockType.CORRIDOR, 0);
        b.setRotation(Rotation.ZERO);
        final Coordinate blockCoordinate = new CoordinateImpl(0, 0);
        lab.setBlock(b, blockCoordinate);
        assertFalse(directionCheck.checkLeftEntrance(blockCoordinate, lab));
        b.setRotation(Rotation.NINETY);
        lab.setBlock(b, blockCoordinate);
        assertTrue(directionCheck.checkLeftEntrance(blockCoordinate, lab));
        b = new BlockImpl(BlockType.CORNER, 0);
        b.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
        lab.setBlock(b, blockCoordinate);
        assertTrue(directionCheck.checkLeftEntrance(blockCoordinate, lab));
        b = new BlockImpl(BlockType.CROSSING, 0);
        b.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
        lab.setBlock(b, blockCoordinate);
        assertTrue(directionCheck.checkLeftEntrance(blockCoordinate, lab));
    }

    @Test
    void checkUp() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final DirectionCheck directionCheck = new DirectionCheckImpl();
        Block b = new BlockImpl(BlockType.CORRIDOR, 0);
        b.setRotation(Rotation.ZERO);
        final Coordinate blockCoordinate = new CoordinateImpl(0, 0);
        lab.setBlock(b, blockCoordinate);
        assertTrue(directionCheck.checkUpperEntrance(blockCoordinate, lab));
        b.setRotation(Rotation.NINETY);
        lab.setBlock(b, blockCoordinate);
        assertFalse(directionCheck.checkUpperEntrance(blockCoordinate, lab));
        b = new BlockImpl(BlockType.CORNER, 0);
        b.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
        lab.setBlock(b, blockCoordinate);
        assertTrue(directionCheck.checkUpperEntrance(blockCoordinate, lab));
        b = new BlockImpl(BlockType.CROSSING, 0);
        b.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
        lab.setBlock(b, blockCoordinate);
        assertTrue(directionCheck.checkUpperEntrance(blockCoordinate, lab));
    }

    @Test
    void checkBottom() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final DirectionCheck directionCheck = new DirectionCheckImpl();
        Block block = new BlockImpl(BlockType.CORRIDOR, 0);
        block.setRotation(Rotation.ZERO);
        final Coordinate blockCoordinate = new CoordinateImpl(0, 0);
        lab.setBlock(block, blockCoordinate);
        assertTrue(directionCheck.checkBottomEntrance(blockCoordinate, lab));
        block.setRotation(Rotation.NINETY);
        lab.setBlock(block, blockCoordinate);
        assertFalse(directionCheck.checkBottomEntrance(blockCoordinate, lab));
        block = new BlockImpl(BlockType.CORNER, 0);
        block.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
        lab.setBlock(block, blockCoordinate);
        assertFalse(directionCheck.checkBottomEntrance(blockCoordinate, lab));
        block = new BlockImpl(BlockType.CROSSING, 0);
        block.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
        lab.setBlock(block, blockCoordinate);
        assertTrue(directionCheck.checkBottomEntrance(blockCoordinate, lab));
    }
}
