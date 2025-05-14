package labioopint.labyrinth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import labioopint.controller.api.GameController;
import labioopint.controller.impl.GameControllerImpl;
import labioopint.model.block.api.Block;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.impl.SettingsImpl;

class ShiftsTest {

    private static final int ENEMY_NUMBER = 1;
    private static final int PLAYER_NUMBER = 2;
    private static final int POWERUP_NUMBER = 5;
    private static final EnemyDifficulty ENEMY_DIFFICULTY = EnemyDifficulty.EASY;

    @Test
    void testShiftRowRight() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final Block initialOutsideBlock = lab.getOutsideBlock();
        final List<Block> ls = new ArrayList<>();
        for (int i = 0; i < lab.getGrid().getSize(); i++) {
            ls.add(lab.getGrid().getBlock(new CoordinateImpl(2, i)).get());
        }
        lab.moveBlock(new CoordinateImpl(2, 0), Direction.RIGHT);
        assertEquals(initialOutsideBlock, lab.getGrid().getBlock(new CoordinateImpl(2, 0)).get());
        for (int i = 0; i < lab.getGrid().getSize() - 1; i++) {
            assertEquals(ls.get(i), lab.getGrid().getBlock(new CoordinateImpl(2, i + 1)).get());
        }
        assertEquals(ls.get(ls.size() - 1), lab.getOutsideBlock());
    }

    @Test
    void testShiftRowLeft() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final Block initialOutsideBlock = lab.getOutsideBlock();
        final List<Block> ls = new ArrayList<>();
        for (int i = lab.getGrid().getSize() - 1; i >= 0; i--) {
            ls.add(lab.getGrid().getBlock(new CoordinateImpl(2, i)).get());
        }
        lab.moveBlock(new CoordinateImpl(2, lab.getGrid().getSize() - 1), Direction.LEFT);
        assertEquals(initialOutsideBlock,
                lab.getGrid().getBlock(new CoordinateImpl(2, lab.getGrid().getSize() - 1)).get());
        int index = 0;
        for (int i = lab.getGrid().getSize() - 1; i > 0; i--) {
            assertEquals(ls.get(index), lab.getGrid().getBlock(new CoordinateImpl(2, i - 1)).get());
            index++;
        }
        assertEquals(ls.get(ls.size() - 1), lab.getOutsideBlock());
    }

    @Test
    void testShiftColumnDown() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final Block initialOutsideBlock = lab.getOutsideBlock();
        final List<Block> ls = new ArrayList<>();
        for (int i = 0; i < lab.getGrid().getSize(); i++) {
            ls.add(lab.getGrid().getBlock(new CoordinateImpl(i, 2)).get());
        }
        lab.moveBlock(new CoordinateImpl(0, 2), Direction.DOWN);
        assertEquals(initialOutsideBlock, lab.getGrid().getBlock(new CoordinateImpl(0, 2)).get());
        for (int i = 0; i < lab.getGrid().getSize() - 1; i++) {
            assertEquals(ls.get(i), lab.getGrid().getBlock(new CoordinateImpl(i + 1, 2)).get());
        }
        assertEquals(ls.get(ls.size() - 1), lab.getOutsideBlock());
    }

    @Test
    void testShiftColumnUp() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final Block initialOutsideBlock = lab.getOutsideBlock();
        final List<Block> ls = new ArrayList<>();
        for (int i = lab.getGrid().getSize() - 1; i >= 0; i--) {
            ls.add(lab.getGrid().getBlock(new CoordinateImpl(i, 2)).get());
        }
        lab.moveBlock(new CoordinateImpl(lab.getGrid().getSize() - 1, 2), Direction.UP);
        assertEquals(initialOutsideBlock,
                lab.getGrid().getBlock(new CoordinateImpl(lab.getGrid().getSize() - 1, 2)).get());
        int index = 0;
        for (int i = lab.getGrid().getSize() - 1; i > 0; i--) {
            assertEquals(ls.get(index), lab.getGrid().getBlock(new CoordinateImpl(i - 1, 2)).get());
            index++;
        }
        assertEquals(ls.get(ls.size() - 1), lab.getOutsideBlock());
    }
}
