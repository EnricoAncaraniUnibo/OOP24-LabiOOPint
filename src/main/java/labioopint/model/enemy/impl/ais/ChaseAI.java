package labioopint.model.enemy.impl.ais;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import labioopint.model.utilities.api.Coordinate;
import labioopint.model.utilities.impl.CoordinateImpl;
import labioopint.model.enemy.api.EnemyAI;
import labioopint.model.enemy.impl.MovementUtilities;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.api.Labyrinth;
import labioopint.model.player.api.Player;
import labioopint.controller.api.ActionPredicate;

/**
 * The enemyAi that if can reach the player follow him.
 */
public final class ChaseAI implements EnemyAI {

    public static final long serialVersionUID = 1L;

    @Override
    public List<Coordinate> getNextPosition(final List<Player> players, final Coordinate current,
            final ActionPredicate actionPredicate,
            final Labyrinth labyrinth) {
        final List<Coordinate> walkableCells = getWalkableCells(current, actionPredicate);

        final var path = getPath(walkableCells, players, current, actionPredicate, labyrinth);
        if (path.isPresent()) {
            return path.get();
        } else {
            final List<Coordinate> ls = new ArrayList<>();
            ls.add(current);
            return ls;
        }
    }

    private List<Coordinate> getWalkableCells(final Coordinate enemyCoordinate, final ActionPredicate actionPredicate) {
        final List<Coordinate> output = new ArrayList<>();
        final Queue<Coordinate> queue = new ArrayDeque<>();
        queue.add(enemyCoordinate);
        while (!queue.isEmpty()) {
            final Coordinate current = queue.poll();
            output.add(current);
            for (final Direction dir : List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)) {
                final Coordinate next = MovementUtilities.getNextCoordinate(current, dir);
                if (actionPredicate.enemyCanMoveFromPosition(current, dir) && !output.contains(next)) {
                    queue.add(next);
                }
            }

        }
        return output;
    }

    private Optional<List<Coordinate>> getPath(final List<Coordinate> walkableCells, final List<Player> players,
            final Coordinate start, final ActionPredicate actionPredicate, final Labyrinth labyrinth) {
        final List<Coordinate> playerPositions = players.stream()
                .map(labyrinth::getPlayerCoordinate)
                .toList();

        if (playerPositions.isEmpty() || walkableCells.isEmpty()) {
            return Optional.empty();
        }
        final Coordinate predecessor = new CoordinateImpl(-1, -1);
        final Set<Coordinate> visited = new HashSet<>();
        final Map<Coordinate, Coordinate> predecessors = new HashMap<>();
        final Queue<Coordinate> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        predecessors.put(start, predecessor);
        Optional<Coordinate> targetPlayer = Optional.empty();
        while (!queue.isEmpty() && targetPlayer.isEmpty()) {
            final Coordinate current = queue.poll();
            if (playerPositions.contains(current)) {
                targetPlayer = Optional.of(current);
                break;
            }
            final List<Coordinate> neighbors = getNeighbors(current, walkableCells, visited);
            for (final Coordinate neighbor : neighbors) {
                queue.add(neighbor);
                visited.add(neighbor);
                predecessors.put(neighbor, current);
            }
        }
        if (targetPlayer.isPresent()) {
            final List<Coordinate> path = new ArrayList<>();
            Coordinate current = targetPlayer.get();
            while (!current.equals(predecessor)) {
                path.add(current);
                current = predecessors.get(current);
            }
            Collections.reverse(path);

            return Optional.of(path);
        } else {
            final SingleStepRandomAI randomAI = new SingleStepRandomAI();
            return Optional.of(randomAI.getNextPosition(players, start, actionPredicate, labyrinth));
        }
    }

    private List<Coordinate> getNeighbors(final Coordinate current, final List<Coordinate> walkableCells,
            final Set<Coordinate> visited) {
        final List<Coordinate> neighbors = new ArrayList<>();
        final int[][] directions = {
                { -1, 0 },
                { 0, 1 },
                { 1, 0 },
                { 0, -1 }
        };

        for (final int[] dir : directions) {
            final int newRow = current.getRow() + dir[0];
            final int newCol = current.getColumn() + dir[1];

            final Coordinate neighbor = new CoordinateImpl(newRow, newCol);
            if (walkableCells.contains(neighbor) && !visited.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }
}
