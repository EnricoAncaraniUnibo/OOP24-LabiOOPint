package labioopint.controller.impl;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.api.ActionController;
import labioopint.model.utilities.impl.ActionType;
import labioopint.model.utilities.api.Coordinate;
import labioopint.model.block.api.Rotation;
import labioopint.model.core.api.TurnManager;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.model.powerup.api.PowerUp;

/**
 * This class have to translate the action from the game view
 * into commands.
 */
public final class ActionControllerImpl implements ActionController {
    public static final long serialVersionUID = 1L;

    @Override
    public void action(final Object action, final Labyrinth labyrinth, final TurnManager turnManager) {
        final ActionPredicate actionPredicate = new ActionPredicateImpl(labyrinth);
        final ActionType currentAction = turnManager.getCurrentAction();
        switch (currentAction) {
            case ActionType.BLOCK_PLACEMENT:
                if (action instanceof String) {
                    if ("←".equals(action) || "→".equals(action)) {
                        final Direction dir = "←".equals(action) ? Direction.LEFT : Direction.RIGHT;
                        rotateBlock(dir, labyrinth);
                    } else {
                        checkPowerUp((String) action, labyrinth,
                                labyrinth.getPlayers().get(turnManager.getCurrentPlayer()), turnManager);
                    }
                } else if (action instanceof Coordinate) {
                    final Coordinate blockCoordinate = (Coordinate) action;
                    if (actionPredicate.blockCanMove(blockCoordinate)) {
                        if (blockCoordinate.getColumn() == 0) {
                            turnManager.nextAction();
                            labyrinth.moveBlock(blockCoordinate, Direction.RIGHT);
                        } else if (blockCoordinate.getColumn() == labyrinth.getGrid().getSize() - 1) {
                            turnManager.nextAction();
                            labyrinth.moveBlock(blockCoordinate, Direction.LEFT);
                        } else if (blockCoordinate.getRow() == 0) {
                            turnManager.nextAction();
                            labyrinth.moveBlock(blockCoordinate, Direction.DOWN);
                        } else if (blockCoordinate.getRow() == labyrinth.getGrid().getSize() - 1) {
                            turnManager.nextAction();
                            labyrinth.moveBlock(blockCoordinate, Direction.UP);
                        }
                    }
                }
                break;

            default:
                final Player currentPlayer = labyrinth.getPlayers().get(turnManager.getCurrentPlayer());
                if (action instanceof String) {
                    if ("←".equals(action) || "→".equals(action) || "↑".equals(action) || "↓".equals(action)) {
                        final Direction dir = "←".equals(action) ? Direction.LEFT
                                : "→".equals(action) ? Direction.RIGHT
                                        : "↑".equals(action) ? Direction.UP : Direction.DOWN;
                        if (actionPredicate.playerCanMove(currentPlayer, dir)) {
                            labyrinth.movePlayer(currentPlayer, dir);
                        }
                    } else if ("End Turn".equals(action)) {
                        final boolean enemyStop = turnManager.isDoubleTurn();
                        turnManager.nextAction();
                        if (labyrinth.getEnemy().getFirst() && !enemyStop) {
                            labyrinth.enemyUpdateCoordinate(labyrinth.getEnemy().getSecond(), labyrinth.getEnemy()
                                    .getSecond().move(labyrinth.getPlayers(), actionPredicate, labyrinth));
                            if (labyrinth.getEnemy().getSecond().isPresentLastHit()
                                    && labyrinth.getEnemy().getSecond().getLastHit()
                                            .equals(labyrinth.getPlayers().get(turnManager.getCurrentPlayer()))) {
                                labyrinth.getEnemy().getSecond().clearLastHit();
                            }
                        }
                    } else {
                        checkPowerUp((String) action, labyrinth, currentPlayer, turnManager);
                    }
                }
                break;
        }
    }

    private void rotateBlock(final Direction dir, final Labyrinth labyrinth) {
        Rotation blockRotation = labyrinth.getOutsideBlock().getRotation();
        switch (dir) {
            case Direction.RIGHT:
                blockRotation = (blockRotation == Rotation.ZERO) ? Rotation.NINETY
                        : (blockRotation == Rotation.NINETY) ? Rotation.ONE_HUNDRED_EIGHTY
                                : (blockRotation == Rotation.ONE_HUNDRED_EIGHTY) ? Rotation.TWO_HUNDRED_SEVENTY
                                        : Rotation.ZERO;
                labyrinth.rotateOutsideBlock(blockRotation);
                break;

            case Direction.LEFT:
                blockRotation = (blockRotation == Rotation.ZERO) ? Rotation.TWO_HUNDRED_SEVENTY
                        : (blockRotation == Rotation.TWO_HUNDRED_SEVENTY) ? Rotation.ONE_HUNDRED_EIGHTY
                                : (blockRotation == Rotation.ONE_HUNDRED_EIGHTY) ? Rotation.NINETY : Rotation.ZERO;
                labyrinth.rotateOutsideBlock(blockRotation);
                break;
            default:
                break;
        }
    }

    private void checkPowerUp(final String namePowerUp, final Labyrinth labyrinth, final Player currentPlayer,
            final TurnManager turnManager) {
        for (final PowerUp powerUp : labyrinth.getObjectives()) {
            if (powerUp.getName().equals(namePowerUp) && currentPlayer.getUsablePowerUps().contains(powerUp)) {
                powerUp.activate(labyrinth, turnManager);
                break;
            }
        }
    }
}
