package labioopint.model.Enemy.impl.ais;

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


import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Enemy.impl.MovementUtilities;
import labioopint.model.Maze.api.Direction;
import labioopint.model.Maze.impl.LabyrinthImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.api.Coordinate;
import labioopint.controller.impl.ActionPredicate;
import labioopint.model.Core.impl.TurnManager;

/**
 * EnemyAI represents an enemy with artificial intelligence that can move
 * towards players in the labyrinth.
 */
public class ChaseAI implements EnemyAI {

    private ActionPredicate ap;
    private TurnManager turn;

    public ChaseAI(TurnManager tu) {
        turn = tu;
    }

    @Override
    public List<Coordinate> getNextPosition(final List<PlayerImpl> players, final Coordinate current) {
        ap = new ActionPredicate(turn);
        List<Coordinate> walkableCells = getWalkableCells(current);

        var path = getPath(walkableCells, players, current);
        if (path.isPresent()) {
            return path.get(); // ritorna l'ultima coordinata del percorso
        } else {
            List<Coordinate> ls = new ArrayList<>();
            ls.add(current);
            return ls;
        }
    }

    private List<Coordinate> getWalkableCells(final Coordinate enemyCoordinate) {
        List<Coordinate> output = new ArrayList<>();
        Queue<Coordinate> queue = new ArrayDeque<>();
        queue.add(enemyCoordinate);
        while(!queue.isEmpty()) {
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

    // // Optional perché potrebbe non esistere un percorso di coordinate, altrimenti
    // // definisce un percorso dal nemico al player.
    // /**
    //  * Finds a path from the enemy to one of the players.
    //  * 
    //  * @param walkableCells the list of walkable cells in the labyrinth.
    //  * @param players       the list of players in the game.
    //  * @return an optional list of coordinates representing the path to a player.
    //  */
    // private Optional<List<Coordinate>> getPath(final List<Coordinate> walkableCells, final List<PlayerImpl> players, final Coordinate start) {
        
    //     Coordinate startPos = new Coordinate(start.getRow(), start.getColumn());
    //     LabyrinthImpl lab = TurnManager.GetLab();
    //     List<Coordinate> playerPositions = players.stream()
    //         .map(p -> lab.getPlayerCoordinate(p))
    //         .toList();
    //     List<Coordinate> visited = new ArrayList<>();

    //     //chiavi -> nodo corrente, valore -> nodo precedente (null per il primo nodo)
    //     Map<Coordinate, Coordinate> predecessors = new HashMap<>();
    //     Queue<Coordinate> queue = new ArrayDeque<>();
    //     queue.add(start);
    //     Coordinate previous = null;
    //     Optional<Coordinate> playerFound = Optional.empty();
    //     // primo elem coda è il prox nodo da visitare
    //     while (!queue.isEmpty()) {
    //         Coordinate current = queue.poll();
    //         predecessors.put(current, previous);
    //         visited.add(current);
    //         //controllo se siamo arrivati
    //         if (playerPositions.contains(current)) {
    //             playerFound = Optional.of(current);
    //             break;
    //         }

    //         List<Coordinate> neighbours = neighbours(current, walkableCells, visited);
    //         for (Coordinate elem : neighbours) {
    //             queue.add(elem);
    //         }
    //         previous = current;
    //     }

    //     if (playerFound.isPresent()) {
    //         List<Coordinate> path = new ArrayList<>();
    //         Coordinate current = playerFound.get();
    //         while (current != null) {
    //             path.add(current);
    //             current = predecessors.get(current);
    //         }
    //         // percorso dal nemico al player
    //         Collections.reverse(path);
    //         List<Coordinate> shortPath = path.stream()
    //                 .limit(STEPS)
    //                 .collect(Collectors.toList());
    //         return Optional.of(shortPath);
    //     } else {
    //         SingleStepRandomAI randomAI = new SingleStepRandomAI();
    //         return Optional.of(List.of(randomAI.getNextPosition(players, startPos)));
    //     }

    // }

    // /**
    //  * Retrieves the neighboring cells of the current node that are walkable and not
    //  * visited.
    //  * 
    //  * @param currentNode the current node.
    //  * @param maze        the list of walkable cells in the labyrinth.
    //  * @param visited     the list of visited cells.
    //  * @return a list of neighboring coordinates.
    //  */
    // private List<Coordinate> neighbours(final Coordinate currentNode, final List<Coordinate> maze, final List<Coordinate> visited) {
    //     List<Coordinate> neighbours = new ArrayList<>();
    //     for (int i = currentNode.getRow() - 1; i <= currentNode.getRow() + 1; i++) {
    //         for (int j = currentNode.getColumn() - 1; j <= currentNode.getColumn() + 1; j++) {
    //             Coordinate partial = new Coordinate(i, j);
    //             if (!partial.equals(currentNode) && maze.contains(partial) && !visited.contains(partial)) {
    //                 neighbours.add(partial);
    //             }
    //         }
    //     }
    //     return neighbours;
    // }

    /**
     * Finds the shortest path from the start position to any player position.
     * 
     * @param walkableCells List of coordinates that can be traversed
     * @param players List of players in the game
     * @param start The starting coordinate for pathfinding
     * @return Optional containing a list of coordinates representing the path, or empty if no path exists
     */
    private Optional<List<Coordinate>> getPath(final List<Coordinate> walkableCells, final List<PlayerImpl> players, final Coordinate start) {
        LabyrinthImpl lab = turn.GetLab();
        
        // Get all player positions
        List<Coordinate> playerPositions = players.stream()
            .map(p -> lab.getPlayerCoordinate(p))
            .toList();
        
        // If no players or walkable cells, return empty
        if (playerPositions.isEmpty() || walkableCells.isEmpty()) {
            return Optional.empty();
        }
        
        // Track visited nodes to avoid cycles
        Set<Coordinate> visited = new HashSet<>();
        
        // Track predecessors to reconstruct the path
        Map<Coordinate, Coordinate> predecessors = new HashMap<>();
        
        // BFS queue
        Queue<Coordinate> queue = new LinkedList<>();
        
        // Start the search
        queue.add(start);
        visited.add(start);
        predecessors.put(start, null); // Start has no predecessor
        
        Coordinate targetPlayer = null;
        
        // BFS algorithm
        while (!queue.isEmpty() && targetPlayer == null) {
            Coordinate current = queue.poll();
            
            // Check if we've found a player
            if (playerPositions.contains(current)) {
                targetPlayer = current;
                break;
            }
            
            // Get all valid neighbors (4-way movement: up, down, left, right)
            List<Coordinate> neighbors = getNeighbors(current, walkableCells, visited);
            
            // Add valid neighbors to the queue
            for (Coordinate neighbor : neighbors) {
                queue.add(neighbor);
                visited.add(neighbor);
                predecessors.put(neighbor, current);
            }
        }
        
        // If a player was found, reconstruct the path
        if (targetPlayer != null) {
            List<Coordinate> path = new ArrayList<>();
            Coordinate current = targetPlayer;
            
            // Reconstruct path from target to start
            while (current != null) {
                path.add(current);
                current = predecessors.get(current);
            }
            
            // Reverse to get path from start to target
            Collections.reverse(path);
            
            return Optional.of(path);
        } else {
            // No path found, use random movement as fallback
            SingleStepRandomAI randomAI = new SingleStepRandomAI(turn);
            return Optional.of(randomAI.getNextPosition(players, start));
        }
    }

    /**
     * Gets the valid neighboring cells for a coordinate (4-way movement).
     * 
     * @param current The current coordinate
     * @param walkableCells List of walkable cells
     * @param visited Set of already visited cells
     * @return List of valid neighboring coordinates
     */
    private List<Coordinate> getNeighbors(final Coordinate current, final List<Coordinate> walkableCells, final Set<Coordinate> visited) {
        List<Coordinate> neighbors = new ArrayList<>();
        
        // The four cardinal directions (up, right, down, left)
        int[][] directions = {
            {-1, 0}, // Up
            {0, 1},  // Right
            {1, 0},  // Down
            {0, -1}  // Left
        };
        
        for (int[] dir : directions) {
            int newRow = current.getRow() + dir[0];
            int newCol = current.getColumn() + dir[1];
            
            Coordinate neighbor = new Coordinate(newRow, newCol);
            
            // Check if the neighbor is walkable and not visited
            if (walkableCells.contains(neighbor) && !visited.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        
        return neighbors;
    }
}