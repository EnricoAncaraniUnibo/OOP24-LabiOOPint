package labioopint.model.powerup.impl;

import java.util.List;
import java.util.Random;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;

/**
 * Power-up that, when activated, randomly selects an uncollected power-up in
 * the maze
 * and creates a direct path from the current player to that power-up.
 */
public final class CorridorToPowerUpPowerUp extends PowerUpImpl {
    public static final long serialVersionUID = 1L;
    private static final Random RANDOM = new Random();

    /**
     * Constructs a new CorridorToPowerUpPowerUp with the specified id.
     *
     * @param id the identifier of the power-up
     */
    public CorridorToPowerUpPowerUp(final int id) {
        super(id);
        super.setName("Corridor To PowerUp");
    }

    @Override
    public void activate(final Labyrinth lab, final TurnManager turn) {
        final Player p = lab.getPlayers().get(turn.getCurrentPlayer());
        if (p.getUsablePowerUps().contains(this)) {
            if (isCollected()) {
                final List<PowerUp> notCollected = lab.getPowerUpsNotCollected();
                if (!notCollected.isEmpty()) {
                    final PowerUp target = notCollected.get(RANDOM.nextInt(notCollected.size()));
                    final Coordinate playerCoord = lab.getPlayerCoordinate(p);
                    final Coordinate targetCoord = lab.getPowerUpCoordinate(target);

                    int row = playerCoord.getRow();
                    int col = playerCoord.getColumn();

                    if (row == targetCoord.getRow()) {
                        lab.setBlock(
                                new BlockImpl(BlockType.CORRIDOR,
                                        lab.getGrid().getBlock(new CoordinateImpl(row, col)).get().getID()),
                                new CoordinateImpl(row, col));
                    } else {
                        final BlockImpl block = new BlockImpl(BlockType.CORRIDOR,
                                lab.getGrid().getBlock(new CoordinateImpl(row, col)).get().getID());
                        block.setRotation(Rotation.NINETY);
                        lab.setBlock(block, new CoordinateImpl(row, col));
                    }

                    final BlockImpl block = new BlockImpl(BlockType.CORRIDOR,
                            lab.getGrid().getBlock(new CoordinateImpl(row, col)).get().getID());
                    block.setRotation(Rotation.NINETY);
                    lab.setBlock(block, new CoordinateImpl(row, col));
                    while (col != targetCoord.getColumn()) {
                        if (col > targetCoord.getColumn()) {
                            col--;
                        } else {
                            col++;
                        }
                        final BlockImpl corridor = new BlockImpl(BlockType.CORRIDOR,
                                lab.getGrid().getBlock(new CoordinateImpl(row, col)).get().getID());
                        corridor.setRotation(Rotation.NINETY);
                        lab.setBlock(corridor, new CoordinateImpl(row, col));
                    }
                    if (targetCoord.getRow() != row) {
                        BlockImpl corner = new BlockImpl(BlockType.CORNER,
                                lab.getGrid().getBlock(new CoordinateImpl(row, col)).get().getID());
                        if (targetCoord.getRow() < playerCoord.getRow()
                                && targetCoord.getColumn() > playerCoord.getColumn()) {
                            corner.setRotation(Rotation.ONE_HUNDRED_EIGHTY);
                        } else if (targetCoord.getRow() < playerCoord.getRow()
                                && targetCoord.getColumn() < playerCoord.getColumn()) {
                            corner.setRotation(Rotation.NINETY);
                        } else if (targetCoord.getRow() > playerCoord.getRow()
                                && targetCoord.getColumn() > playerCoord.getColumn()) {
                            corner.setRotation(Rotation.TWO_HUNDRED_SEVENTY);
                        } else if (targetCoord.getColumn().equals(playerCoord.getColumn())) {
                            corner = new BlockImpl(BlockType.CORRIDOR, corner.getID());
                        }
                        lab.setBlock(corner, new CoordinateImpl(row, col));
                    }
                    while (row != targetCoord.getRow()) {
                        if (row > targetCoord.getRow()) {
                            row--;
                        } else {
                            row++;
                        }
                        lab.setBlock(
                                new BlockImpl(BlockType.CORRIDOR,
                                        lab.getGrid().getBlock(new CoordinateImpl(row, col)).get().getID()),
                                new CoordinateImpl(row, col));
                    }
                }
            }
            p.removePowerUp(this);
        }
    }
}
