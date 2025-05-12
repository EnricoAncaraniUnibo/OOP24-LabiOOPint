package labioopint.controller.impl;

import java.io.Serializable;

import labioopint.controller.api.ActionPredicate;
import labioopint.controller.api.GameController;
import labioopint.model.api.ActionType;
import labioopint.model.api.Coordinate;
import labioopint.model.block.api.Rotation;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.LabyrinthImpl;

public final class GameControllerImpl implements GameController, Serializable {
    private LabyrinthImpl labyrinth;
    private final TurnManager turnManager;
    private SaveControllerImpl saveController;

    public GameControllerImpl(final TurnManager tu) {
        saveController = new SaveControllerImpl(tu);
        turnManager = tu;
    }

    @Override
    public void action(final Object action) {
        final ActionPredicate actionPredicate = new ActionPredicateImpl(turnManager);
        labyrinth = turnManager.getLab();
        final ActionType currentAction = turnManager.getCurrentAction();
        switch (currentAction) {
            case ActionType.BLOCK_PLACEMENT:
                if (action instanceof String) {
                    final Direction dir = "←".equals(action) ? Direction.LEFT : Direction.RIGHT;
                    rotateBlock(dir);
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
                        saveController.save(turnManager);
                    }
                }
                break;

            case ActionType.PLAYER_MOVEMENT:
                if (action instanceof String) {
                    if ("←".equals(action) || "→".equals(action) || "↑".equals(action) || "↓".equals(action)) {
                        final Direction dir = "←".equals(action) ? Direction.LEFT
                                : "→".equals(action) ? Direction.RIGHT
                                        : "↑".equals(action) ? Direction.UP : Direction.DOWN;
                        if (actionPredicate.playerCanMove(turnManager.getCurrentPlayer(), dir)) {
                            labyrinth.movePlayer(turnManager.getCurrentPlayer(), dir);
                        }
                    } else if ("End Turn".equals(action)) {
                        turnManager.nextAction();
                        saveController.save(turnManager);
                    }
                }
                break;
            case ActionType.ENEMY_MOVEMENT:
                if (action instanceof Direction && turnManager.getEnemy().getFirst() == Boolean.TRUE) {
                    turnManager.getEnemy().getSecond().move(turnManager.getPlayers());
                    turnManager.nextAction();
                    saveController.save(turnManager);
                }
            default:
                break;
        }
        labyrinth.updateView();
    }

    private void rotateBlock(final Direction dir) {
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
}
