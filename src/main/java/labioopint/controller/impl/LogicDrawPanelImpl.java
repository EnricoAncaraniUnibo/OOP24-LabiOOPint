package labioopint.controller.impl;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import labioopint.controller.api.LogicDrawPanel;
import labioopint.model.api.Coordinate;
import labioopint.model.api.DualMap;
import labioopint.model.api.Pair;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.ImageLoader;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.maze.impl.MazeImpl;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.model.powerup.api.PowerUp;
/**
 * The LogicDrawPanel class is responsible for managing the logic and data
 * required to draw the game panel, including blocks, players, enemies, and power-ups.
 */
public class LogicDrawPanelImpl implements LogicDrawPanel {

    private final TurnManager turn;
    private final Integer pixelSize;
    private MazeImpl maze;
    private DualMap<PlayerImpl> coorPlayers;
    private DualMap<Enemy> coorEnemies;
    private DualMap<PowerUp> coorPowerUps;
    private BlockImpl outsideBlock;
    private static final Integer CORRECT_BLOCK_DIVISION = 13;
    private static final double NINETY_ROTATION = -90;
    private static final double ZERO_ROTATION = 0;
    private static final double ONE_HUNDRED_EIGHTY_ROTATION = -180;
    private static final double TWO_HUNDRED_SEVENTY = -270;
    private static final Integer PERCENTILE_NUMERATOR = 3;
    private static final Integer PERCENTILE_DENOMINATOR = 5;
    private static final Integer REDUCTION_SCALE_NUMBER = 4;

     /**
     * Constructs a LogicDrawPanel with the specified TurnManager and panel size.
     *
     * @param tu   the TurnManager instance to manage turns
     * @param size the Dimension of the panel
     */
    public LogicDrawPanelImpl(final TurnManager tu, final Dimension size) {
        turn = tu;
        pixelSize = (int) size.getWidth() / CORRECT_BLOCK_DIVISION;
        ImageLoader.load();
    }
    /**
     * Gets the size of a block in the panel.
     *
     * @return the value as an Integer
     */
    @Override
    public Integer getPixelSize() {
        return pixelSize;
    }
    /**
     * Updates the data required for drawing the panel, including the maze, players,
     * enemy, powerups, and the outside block.
     *
     * @param m           the MazeImpl instance representing the maze
     * @param mapPlayers  the DualMap containing player coordinates
     * @param mapEnemies  the DualMap containing enemy coordinates
     * @param mapPowerUps the DualMap containing power-up coordinates
     * @param outside     the BlockImpl representing the outside block
     */
    @Override
    public void updateData(final MazeImpl m, final DualMap<PlayerImpl> mapPlayers,
            final DualMap<Enemy> mapEnemies, final DualMap<PowerUp> mapPowerUps,
            final BlockImpl outside) {
        this.maze = m;
        this.coorPlayers = mapPlayers;
        this.coorEnemies = mapEnemies;
        this.coorPowerUps = mapPowerUps;
        this.outsideBlock = outside;
    }

    /**
     * Checks if the panel is ready to be drawn.
     *
     * @return true if the maze is not null, false otherwise
     */
    @Override
    public Boolean canDraw() {
        return maze != null;
    }

    /**
     * Retrieves a list of images and their associated properties (rotation, position, size)
     * for all blocks, players, enemies, and power-ups to be drawn on the panel.
     *
     * @return a List of Pairs containing image, rotation, position, and size information
     */
    @Override
    public List<Pair<Pair<Image, Double>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> getImagesBlocks() {
        final List<Pair<Pair<Image, Double>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> ls = new ArrayList<>();
        BlockImpl b;
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
        for (int i = 0; i <= maze.getSize() && !end; i++) {
            for (int j = 0; j < maze.getSize() && !end; j++) {
                if (i == maze.getSize()) {
                        b = outsideBlock;
                        j = maze.getSize() + 1;
                        i = 0;
                        end = true;
                } else {
                        b = maze.getBlock(new Coordinate(i, j)).get();
                }
                switch (b.getType()) {
                        case CORNER:
                                imageTemp = ImageLoader.getImage("Corner").get();
                                break;
                        case CORRIDOR:
                                imageTemp = ImageLoader.getImage("Corridor").get();
                                break;
                        case CROSSING:
                                imageTemp = ImageLoader.getImage("Crossing").get();
                                break;
                }
                switch (b.getRotation()) {
                    case ZERO:
                            rotationTemp = Math.toRadians(ZERO_ROTATION);
                            valuePrint1 = j * pixelSize;
                            valuePrint2 = i * pixelSize;
                            break;
                    case NINETY:
                            rotationTemp = Math.toRadians(NINETY_ROTATION);
                            valuePrint1 = (-i * pixelSize) - pixelSize;
                            valuePrint2 = j * pixelSize;
                            break;
                    case ONE_HUNDRED_EIGHTY:
                            rotationTemp = Math.toRadians(ONE_HUNDRED_EIGHTY_ROTATION);
                            valuePrint1 = (-j * pixelSize) - pixelSize;
                            valuePrint2 = (-i * pixelSize) - pixelSize;
                            break;
                    case TWO_HUNDRED_SEVENTY:
                            rotationTemp = Math.toRadians(TWO_HUNDRED_SEVENTY);
                            valuePrint1 = i * pixelSize;
                            valuePrint2 = (-j * pixelSize) - pixelSize;
                            break;
                }
                pImageRotation = new Pair<>(imageTemp, rotationTemp);
                pPositions = new Pair<>(valuePrint1, valuePrint2);
                pSize = new Pair<>(pixelSize, pixelSize);
                pNumbers = new Pair<>(pPositions, pSize);
                pFinal = new Pair<>(pImageRotation, pNumbers);
                ls.add(pFinal);
            }
        }
        for (final PlayerImpl p : coorPlayers.getElemets()) {
            final Coordinate c = coorPlayers.getCoordinateFromElement(p);
            if (p.equals(turn.getCurrentPlayer())) {
                pImageRotation = new Pair<>(
                    ImageLoader.getImage(p.getID() + "Turn").get(), Math.toRadians(ZERO_ROTATION));
            } else {
                pImageRotation = new Pair<>(
                    ImageLoader.getImage(p.getID()).get(), Math.toRadians(ZERO_ROTATION));
            }
            pPositions = new Pair<>(
                c.getColumn() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER, 
                c.getRow() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER);
            pSize = new Pair<>(pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR, pixelSize * 3/5);
            pNumbers = new Pair<>(pPositions, pSize);
            pFinal = new Pair<>(
                pImageRotation, pNumbers);
            ls.add(pFinal);
        }
        for (final Enemy e : coorEnemies.getElemets()) {
            final Coordinate c = coorEnemies.getCoordinateFromElement(e);
            pImageRotation = new Pair<>(ImageLoader.getImage("Monster").get(), Math.toRadians(ZERO_ROTATION));
            pPositions = new Pair<>(
                c.getColumn() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER, 
                c.getRow() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER);
            pSize = new Pair<>(pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR, 
            pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR);
            pNumbers = new Pair<>(pPositions, pSize);
            pFinal = new Pair<>(
                pImageRotation, pNumbers);
            ls.add(pFinal);
        }
        for (final PowerUp po : coorPowerUps.getElemets()) {
            final Coordinate c = coorPowerUps.getCoordinateFromElement(po);
            pImageRotation = new Pair<>(
                ImageLoader.getImage(po.getName()).get(), Math.toRadians(ZERO_ROTATION));
            pPositions = new Pair<>(
                c.getColumn() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER, 
                c.getRow() * pixelSize + pixelSize / REDUCTION_SCALE_NUMBER);
            pSize = new Pair<>(pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR, 
            pixelSize * PERCENTILE_NUMERATOR / PERCENTILE_DENOMINATOR);
            pNumbers = new Pair<>(pPositions, pSize);
            pFinal = new Pair<>(
                pImageRotation, pNumbers);
            ls.add(pFinal);
        }
        return ls;
    }
}
