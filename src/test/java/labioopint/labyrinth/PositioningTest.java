package labioopint.labyrinth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import labioopint.controller.api.GameController;
import labioopint.controller.impl.GameControllerImpl;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyDifficulty;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.impl.SettingsImpl;

class PositioningTest {
    private static final Integer SIZE = 5;
    private static final int ENEMY_NUMBER = 1;
    private static final int PLAYER_NUMBER = 2;
    private static final int POWERUP_NUMBER = 5;
    private static final EnemyDifficulty ENEMY_DIFFICULTY = EnemyDifficulty.EASY;

    @Test
    void checkPositionForAll() {
        boolean passed = true;
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        for (final Player p : lab.getPlayers()) {
            final Coordinate c = lab.getPlayerCoordinate(p);
            if (Objects.isNull(c)) {
                passed = false;
            }
        }
        assertTrue(passed);
        passed = true;
        Coordinate c = lab.getEnemyCoordinate(lab.getEnemy().getSecond());
        if (Objects.isNull(c)) {
            passed = false;
        }
        assertTrue(passed);
        passed = true;
        for (final PowerUp p : lab.getPowerUpsNotCollected()) {
            c = lab.getPowerUpCoordinate(p);
            if (Objects.isNull(c)) {
                passed = false;
            }
        }
        assertTrue(passed);
    }

    @Test
    void startingPlayerPositions() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        boolean passed = true;
        for (final Player p : lab.getPlayers()) {
            final Coordinate c = lab.getPlayerCoordinate(p);
            if (!(c.getRow() == 0 && c.getColumn() == 0) && !(c.getRow() == 0 && c.getColumn() == SIZE - 1)
                    && !(c.getRow() == SIZE - 1 && c.getColumn() == SIZE - 1)
                    && !(c.getRow() == SIZE - 1 && c.getColumn() == 0)) {
                passed = false;
            }
        }
        assertTrue(passed);
    }

    @Test
    void changePosition() {
        final GameController gc = new GameControllerImpl(
                new SettingsImpl(ENEMY_NUMBER, PLAYER_NUMBER, POWERUP_NUMBER, ENEMY_DIFFICULTY));
        final Labyrinth lab = gc.getLabyrinth();
        final List<Player> ls = lab.getPlayers();
        final Enemy e = lab.getEnemy().getSecond();

        Coordinate old = lab.getPlayerCoordinate(ls.get(0));
        lab.playerUpdateCoordinate(ls.get(0), new CoordinateImpl(3, 3));
        boolean oldRemoved = true;
        if (lab.getPlayerCoordinate(ls.get(0)).equals(old)) {
            oldRemoved = false;
        }
        assertTrue(oldRemoved);

        boolean correctChanged = true;
        if (lab.getPlayerCoordinate(ls.get(0)).getRow() != 3 || lab.getPlayerCoordinate(ls.get(0)).getColumn() != 3) {
            correctChanged = false;
        }
        assertTrue(correctChanged);

        old = lab.getEnemyCoordinate(e);
        final List<Coordinate> lCoor = new ArrayList<>();
        lCoor.add(new CoordinateImpl(Math.abs(old.getRow() - 1), Math.abs(old.getColumn() - 1)));
        lab.enemyUpdateCoordinate(e, lCoor);
        oldRemoved = true;
        if (lab.getEnemyCoordinate(e).equals(old)) {
            oldRemoved = false;
        }
        assertTrue(oldRemoved);

        correctChanged = true;
        if (lab.getEnemyCoordinate(e).getRow() != Math.abs(old.getRow() - 1)
                || lab.getEnemyCoordinate(e).getColumn() != Math.abs(old.getColumn() - 1)) {
            correctChanged = false;
        }
        assertTrue(correctChanged);
    }

}
