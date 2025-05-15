package labioopint.controller.impl;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import labioopint.controller.api.GameController;
import labioopint.controller.api.LogicDrawPanel;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.api.Pair;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.utilities.impl.PairImpl;
import labioopint.model.block.api.Block;
import labioopint.model.core.impl.ImageLoader;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.api.Maze;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;

/**
 * Implementation of the class used to draw the maze, players, powerUps...
 */
public final class LogicDrawPanelImpl implements LogicDrawPanel {
    public static final long serialVersionUID = 1L;
    private final GameController gc;
    private final Integer pixelSize;
    private static final Integer CORRECT_BLOCK_DIVISION = 13;
    private static final Integer PERCENTILE_NUMERATOR = 3;
    private static final Integer PERCENTILE_DENOMINATOR = 5;
    private static final Integer REDUCTION_SCALE_NUMBER = 4;
    private static final double ROT_0 = Math.toRadians(0);
    private static final double ROT_90 = Math.toRadians(-90);
    private static final double ROT_180 = Math.toRadians(-180);
    private static final double ROT_270 = Math.toRadians(-270);

    /**
     * Construction of a {@code LogicDrawPanelImpl} with a gameController and a
     * Dimension.
     * 
     * @param gc   the game we want to print on the view.
     * @param size the size of the screen of the person who is playing the game.
     * @throws IOException 
     */
    public LogicDrawPanelImpl(final GameController gc, final Dimension size) throws IOException {
        this.gc = gc;
        pixelSize = (int) size.getWidth() / CORRECT_BLOCK_DIVISION;
        ImageLoader.load();
    }

    @Override
    public Integer getPixelSize() {
        return pixelSize;
    }

    @Override
    public Boolean canDraw() {
        return gc.getLabyrinth().getGrid() != null;
    }

    @Override
    public List<Pair<Pair<Image, Double>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> getImagesBlocks() {
        final List<Pair<Pair<Image, Double>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> ls = new ArrayList<>();
        Block b;
        boolean end = false;
        Image imageTemp = null;
        Double rotationTemp = null;
        Integer valuePrint1 = null;
        Integer valuePrint2 = null;
        Pair<Image, Double> pImageRotation;
        Pair<Integer, Integer> pPositions;
        Pair<Integer, Integer> pSize;
        Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pNumbers;
        Pair<Pair<Image, Double>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> pFinal;
        final Maze maze = gc.getLabyrinth().getGrid();
        for (int i = 0; i <= maze.getSize() && !end; i++) {
            for (int j = 0; j < maze.getSize() && !end; j++) {
                if (i == maze.getSize()) {
                    b = gc.getLabyrinth().getOutsideBlock();
                    j = maze.getSize() + 1;
                    i = 0;
                    end = true;
                } else {
                    b = maze.getBlock(new CoordinateImpl(i, j)).get();
                }
                switch (b.getType()) {
                    case CORNER:
                        imageTemp = ImageLoader.getImage("Corner").get();
                        break;
                    case CORRIDOR:
                        imageTemp = ImageLoader.getImage("Corridor").get();
                        break;
                    default:
                        imageTemp = ImageLoader.getImage("Crossing").get();
                        break;
                }
                switch (b.getRotation()) {
                    case ZERO:
                        rotationTemp = ROT_0;
                        valuePrint1 = j * pixelSize;
                        valuePrint2 = i * pixelSize;
                        break;
                    case NINETY:
                        rotationTemp = ROT_90;
                        valuePrint1 = (-i * pixelSize) - pixelSize;
                        valuePrint2 = j * pixelSize;
                        break;
                    case ONE_HUNDRED_EIGHTY:
                        rotationTemp = ROT_180;
                        valuePrint1 = (-j * pixelSize) - pixelSize;
                        valuePrint2 = (-i * pixelSize) - pixelSize;
                        break;
                    default:
                        rotationTemp = ROT_270;
                        valuePrint1 = i * pixelSize;
                        valuePrint2 = (-j * pixelSize) - pixelSize;
                        break;
                }
                pImageRotation = new PairImpl<>(imageTemp, rotationTemp);
                pPositions = new PairImpl<>(valuePrint1, valuePrint2);
                pSize = new PairImpl<>(pixelSize, pixelSize);
                pNumbers = new PairImpl<>(pPositions, pSize);
                pFinal = new PairImpl<>(pImageRotation, pNumbers);
                ls.add(pFinal);
            }
        }
        for (final Player p : gc.getLabyrinth().getPlayers()) {
            final Coordinate c = gc.getLabyrinth().getPlayerCoordinate(p);
            if (p.equals(gc.getCurrentPlayer())) {
                pImageRotation = new PairImpl<>(
                        ImageLoader.getImage(p.getID() + "Turn").get(), ROT_0);
            } else {
                pImageRotation = new PairImpl<>(
                        ImageLoader.getImage(p.getID()).get(), ROT_0);
            }
            pPositions = new PairImpl<>(
                    c.getColumn() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER,
                    c.getRow() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER);
            pSize = new PairImpl<>(pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR,
                    pixelSize * 3 / PERCENTILE_DENOMINATOR);
            pNumbers = new PairImpl<>(pPositions, pSize);
            pFinal = new PairImpl<>(
                    pImageRotation, pNumbers);
            ls.add(pFinal);
        }
        final Pair<Boolean, Enemy> e = gc.getLabyrinth().getEnemy();
        if (e.getFirst()) {
            final Coordinate c = gc.getLabyrinth().getEnemyCoordinate(e.getSecond());
            pImageRotation = new PairImpl<>(ImageLoader.getImage("Monster").get(), ROT_0);
            pPositions = new PairImpl<>(
                    c.getColumn() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER,
                    c.getRow() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER);
            pSize = new PairImpl<>(pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR,
                    pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR);
            pNumbers = new PairImpl<>(pPositions, pSize);
            pFinal = new PairImpl<>(
                    pImageRotation, pNumbers);
            ls.add(pFinal);
        }
        for (final PowerUp po : gc.getLabyrinth().getPowerUpsNotCollected()) {
            final Coordinate c = gc.getLabyrinth().getPowerUpCoordinate(po);
            pImageRotation = new PairImpl<>(
                    ImageLoader.getImage(po.getName()).get(), ROT_0);
            pPositions = new PairImpl<>(
                    c.getColumn() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER,
                    c.getRow() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER);
            pSize = new PairImpl<>(pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR,
                    pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR);
            pNumbers = new PairImpl<>(pPositions, pSize);
            pFinal = new PairImpl<>(
                    pImageRotation, pNumbers);
            ls.add(pFinal);
        }
        return ls;
    }
}
