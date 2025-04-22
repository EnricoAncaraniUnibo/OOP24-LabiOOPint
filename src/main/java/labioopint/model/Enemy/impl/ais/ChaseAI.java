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

import labioopint.model.api.Coordinate;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.EnemyAI;
import labioopint.model.enemy.impl.MovementUtilities;
import labioopint.model.maze.api.Direction;
import labioopint.model.maze.impl.LabyrinthImpl;
import labioopint.model.player.impl.PlayerImpl;
import labioopint.controller.impl.ActionPredicate;

/**
 * Implementation of the {@link EnemyAI} interface that allows an enemy to chase
 * players in the labyrinth using pathfinding.
 */
public class ChaseAI implements EnemyAI {

    private ActionPredicate ap;
    private TurnManager turn;

    /**
     * Constructs a ChaseAI with the given TurnManager.
     *
     * @param tu the TurnManager used to manage game state and validate moves
     */
    public ChaseAI(final TurnManager tu) {
        turn = tu;
    }

    /**
     * Determines the next positions for the enemy by finding the shortest path
     * to the nearest player or falling back to a random move if no path exists.
     *
     * @param players the list of players in the game
     * @param current the current position of the enemy
     * @return a list of coordinates representing the enemy's path
     */
    @Override
    public List<Coordinate> getNextPosition(final List<PlayerImpl> players, final Coordinate current) {
        ap = new ActionPredicate(turn);
        List<Coordinate> walkableCells = getWalkableCells(current);

        var path = getPath(walkableCells, players, current);
        if (path.isPresent()) {
            return path.get();
        } else {
            List<Coordinate> ls = new ArrayList<>();
            ls.add(current);
            return ls;
        }
    }

    /**
     * Retrieves all walkable cells starting from the enemy's current position.
     *
     * @param enemyCoordinate the current position of the enemy
     * @return a list of walkable coordinates
     */
    private List<Coordinate> getWalkableCells(final Coordinate enemyCoordinate) {
        List<Coordinate> output = new ArrayList<>();
        Queue<Coordinate> queue = new ArrayDeque<>();
        queue.add(enemyCoordinate);
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            output.add(current);
            for (Direction dir : List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)) {
                Coordinate next = MovementUtilities.getNextCoordinate(current, dir);
                if (ap.CanMoveFromPosition(current, dir) && !output.contains(next)) {
                    queue.add(next);
                }
            }

        }
        return output;
    }

    // Optional perché potrebbe non esistere un percorso di coordinate, altrimenti
    // definisce un percorso dal nemico al player.
    /**
     * Finds the shortest path from the enemy's position to the nearest player.
     * If no path exists, falls back to a random move.
     *
     * @param walkableCells the list of walkable cells
     * @param players       the list of players in the game
     * @param start         the starting position of the enemy
     * @return an {@link Optional} containing the path as a list of coordinates,
     *         or empty if no path exists
     */
    private Optional<List<Coordinate>> getPath(final List<Coordinate> walkableCells, final List<PlayerImpl> players,
            final Coordinate start) {
        LabyrinthImpl lab = turn.getLab();
        List<Coordinate> playerPositions = players.stream()
                .map(p -> lab.getPlayerCoordinate(p))
                .toList();

        if (playerPositions.isEmpty() || walkableCells.isEmpty()) {
            return Optional.empty();
        }
        Set<Coordinate> visited = new HashSet<>();
        Map<Coordinate, Coordinate> predecessors = new HashMap<>();
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        predecessors.put(start, null);
        Coordinate targetPlayer = null;
        while (!queue.isEmpty() && targetPlayer == null) {
            Coordinate current = queue.poll();
            if (playerPositions.contains(current)) {
                targetPlayer = current;
                break;
            }
            List<Coordinate> neighbors = getNeighbors(current, walkableCells, visited);
            for (Coordinate neighbor : neighbors) {
                queue.add(neighbor);
                visited.add(neighbor);
                predecessors.put(neighbor, current);
            }
        }
        if (targetPlayer != null) {
            List<Coordinate> path = new ArrayList<>();
            Coordinate current = targetPlayer;
            while (current != null) {
                path.add(current);
                current = predecessors.get(current);
            }
            Collections.reverse(path);

            return Optional.of(path);
        } else {
            SingleStepRandomAI randomAI = new SingleStepRandomAI(turn);
            return Optional.of(randomAI.getNextPosition(players, start));
        }
    }

    /**
     * Retrieves the valid neighboring cells for a given coordinate.
     *
     * @param current       the current coordinate
     * @param walkableCells the list of walkable cells
     * @param visited       the set of already visited cells
     * @return a list of valid neighboring coordinates
     */
    private List<Coordinate> getNeighbors(final Coordinate current, final List<Coordinate> walkableCells,
            final Set<Coordinate> visited) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[][] directions = {
                { -1, 0 },
                { 0, 1 },
                { 1, 0 },
                { 0, -1 }
        };

        for (int[] dir : directions) {
            int newRow = current.getRow() + dir[0];
            int newCol = current.getColumn() + dir[1];

            Coordinate neighbor = new Coordinate(newRow, newCol);
            if (walkableCells.contains(neighbor) && !visited.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

}
