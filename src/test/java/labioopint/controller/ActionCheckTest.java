package labioopint.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.api.GameController;
import labioopint.controller.impl.ActionPredicateImpl;
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
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.impl.SettingsImpl;

class ActionCheckTest {
    private static final int ENEMY_NUMBER = 1;
    private static final int PLAYER_NUMBER = 1;
    private static final int POWERUP_NUMBER = 2;
    private static final EnemyDifficulty ENEMY_DIFFICULTY = EnemyDifficulty.EASY;

    @Test
    void checkPlayer() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final ActionPredicate actionPredicate = new ActionPredicateImpl(lab);
        final Player player = lab.getPlayers().get(0);
        Block block = new BlockImpl(BlockType.CORRIDOR, 0);
        block.setRotation(Rotation.ZERO);
        lab.setBlock(block, new CoordinateImpl(0, 0));
        block = new BlockImpl(BlockType.CORRIDOR, 0);
        block.setRotation(Rotation.NINETY);
        lab.setBlock(block, new CoordinateImpl(0, 1));
        lab.playerUpdateCoordinate(player, new CoordinateImpl(0, 0));
        assertFalse(actionPredicate.playerCanMove(player, Direction.RIGHT, lab));
        lab.playerUpdateCoordinate(player, new CoordinateImpl(0, 1));
        assertFalse(actionPredicate.playerCanMove(player, Direction.LEFT, lab));
        block = new BlockImpl(BlockType.CORNER, 0);
        block.setRotation(Rotation.ZERO);
        lab.setBlock(block, new CoordinateImpl(0, 0));
        assertTrue(actionPredicate.playerCanMove(player, Direction.LEFT, lab));
    }

    @Test
    void checkEnemy() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final ActionPredicate actionPredicate = new ActionPredicateImpl(lab);
        final Enemy enemy = lab.getEnemy().getSecond();
        final List<Coordinate> ls = new LinkedList<>();
        ls.add(new CoordinateImpl(0, 0));
        Block block = new BlockImpl(BlockType.CORRIDOR, 0);
        block.setRotation(Rotation.ZERO);
        lab.setBlock(block, new CoordinateImpl(0, 0));
        block = new BlockImpl(BlockType.CORRIDOR, 0);
        block.setRotation(Rotation.NINETY);
        lab.setBlock(block, new CoordinateImpl(0, 1));
        lab.enemyUpdateCoordinate(enemy, ls);
        assertFalse(actionPredicate.enemyCanMove(Direction.RIGHT, enemy, lab));
        ls.remove(0);
        ls.add(new CoordinateImpl(0, 1));
        lab.enemyUpdateCoordinate(enemy, ls);
        assertFalse(actionPredicate.enemyCanMove(Direction.LEFT, enemy, lab));
        block = new BlockImpl(BlockType.CORNER, 0);
        block.setRotation(Rotation.ZERO);
        lab.setBlock(block, new CoordinateImpl(0, 0));
        assertTrue(actionPredicate.enemyCanMove(Direction.LEFT, enemy, lab));
    }
}
