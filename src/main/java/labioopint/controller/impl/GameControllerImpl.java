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

public class GameControllerImpl implements GameController,Serializable {
    private LabyrinthImpl lab;
    private final TurnManager turn;
    private SaveControllerImpl saveController;

    public GameControllerImpl(final TurnManager tu) {
        saveController = new SaveControllerImpl(tu);
        turn = tu;
    }

    @Override
    public void action(final Object action) {
        final ActionPredicate ap = new ActionPredicateImpl(turn);
        lab = turn.getLab();
        final ActionType currentAction = turn.getCurrentAction();
        switch (currentAction) {
            case ActionType.BLOCK_PLACEMENT:
                if (action instanceof String) {
                    final Direction dir = "←".equals(action) ? Direction.LEFT : Direction.RIGHT;
                    rotateBlock(dir);
                } else if (action instanceof Coordinate) {
                    final Coordinate blockCoordinate = (Coordinate) action;
                    if (ap.blockCanMove(blockCoordinate)) {
                        if (blockCoordinate.getColumn() == 0) {
                            turn.nextAction();
                            lab.moveBlock(blockCoordinate, Direction.RIGHT);
                        } else if (blockCoordinate.getColumn() == lab.getGrid().getSize() - 1) {
                            turn.nextAction();
                            lab.moveBlock(blockCoordinate, Direction.LEFT);
                        } else if (blockCoordinate.getRow() == 0) {
                            turn.nextAction();
                            lab.moveBlock(blockCoordinate, Direction.DOWN);
                        } else if (blockCoordinate.getRow() == lab.getGrid().getSize() - 1) {
                            turn.nextAction();
                            lab.moveBlock(blockCoordinate, Direction.UP);
                        }
                        saveController.save(turn);
                    }
                }
                break;

            case ActionType.PLAYER_MOVEMENT:
                if (action instanceof String) {
                    if ("←".equals(action) || "→".equals(action) || "↑".equals(action) || "↓".equals(action)) {
                        final Direction dir = "←".equals(action) ? Direction.LEFT
                                : "→".equals(action) ? Direction.RIGHT
                                        : "↑".equals(action) ? Direction.UP : Direction.DOWN;
                        if (ap.playerCanMove(turn.getCurrentPlayer(), dir)) {
                            lab.movePlayer(turn.getCurrentPlayer(), dir);
                        }
                    } else if ("End Turn".equals(action)) {
                        turn.nextAction();
                        saveController.save(turn);
                    }
                }
                break;
            case ActionType.ENEMY_MOVEMENT:
                if (action instanceof Direction && turn.getEnemy().getFirst() == Boolean.TRUE) {
                    turn.getEnemy().getSecond().move(turn.getPlayers());
                    turn.nextAction();
                    saveController.save(turn);
                }
            default:
                break;
        }
        lab.updateView();
    }

    private void rotateBlock(final Direction dir) {
        Rotation blockRotation = lab.getOutsideBlock().getRotation();
        switch (dir) {
            case Direction.RIGHT:
                blockRotation = (blockRotation == Rotation.ZERO) ? Rotation.NINETY
                        : (blockRotation == Rotation.NINETY) ? Rotation.ONE_HUNDRED_EIGHTY
                                : (blockRotation == Rotation.ONE_HUNDRED_EIGHTY) ? Rotation.TWO_HUNDRED_SEVENTY
                                        : Rotation.ZERO;
                lab.rotateOutsideBlock(blockRotation);
                break;

            case Direction.LEFT:
                blockRotation = (blockRotation == Rotation.ZERO) ? Rotation.TWO_HUNDRED_SEVENTY
                        : (blockRotation == Rotation.TWO_HUNDRED_SEVENTY) ? Rotation.ONE_HUNDRED_EIGHTY
                                : (blockRotation == Rotation.ONE_HUNDRED_EIGHTY) ? Rotation.NINETY : Rotation.ZERO;
                lab.rotateOutsideBlock(blockRotation);
                break;
            default:
                break;
        }

    }
}
