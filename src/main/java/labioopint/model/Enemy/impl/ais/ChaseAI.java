package labioopint.model.Enemy.impl.ais;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import labioopint.commons.Coordinate;
import labioopint.model.Enemy.api.EnemyAI;
import labioopint.model.Labyrinth.api.Labyrinth;
import labioopint.model.Player.api.Player;

/**
 * EnemyAI represents an enemy with artificial intelligence that can move
 * towards players in the labyrinth.
 */
public class ChaseAI implements EnemyAI {

    private static final int STEPS = 5;

    /**
     * Moves the enemy in the labyrinth towards the players.
     * 
     * @param maze    the labyrinth in which the enemy moves.
     * @param players the list of players in the game.
     */
    @Override
    public Coordinate getNextPosition(Labyrinth maze, List<Player> players, Coordinate current) {

        List<Coordinate> walkableCells = maze.getLabyrinth()
                .entrySet()
                .stream()
                .filter(x -> x.getValue() == true)
                .map(x -> x.getKey())
                .toList();

        var path = getPath(walkableCells, players, current);
        if (path.isPresent()) {
            return path.get().getLast(); // ritorna l'ultima coordinata del percorso
        } else {
            return current;
        }
    }

    // Optional perché potrebbe non esistere un percorso di coordinate, altrimenti
    // definisce un percorso dal nemico al player.
    /**
     * Finds a path from the enemy to one of the players.
     * 
     * @param walkableCells the list of walkable cells in the labyrinth.
     * @param players       the list of players in the game.
     * @return an optional list of coordinates representing the path to a player.
     */
    private Optional<List<Coordinate>> getPath(List<Coordinate> walkableCells, List<Player> players, Coordinate start) {
        List<Coordinate> playerPositions = players.stream()
                .map(p -> p.getCoordinate())
                .toList();
        List<Coordinate> visited = new ArrayList<>();
        // chiavi -> nodo corrente, valore -> nodo precedente (null per il primo nodo)
        Map<Coordinate, Coordinate> predecessors = new HashMap<>();
        Queue<Coordinate> queue = new ArrayDeque<>();
        queue.add(start);
        Coordinate previous = null;
        Optional<Coordinate> playerFound = Optional.empty();
        // primo elem coda è il prox nodo da visitare
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            predecessors.put(current, previous);
            visited.add(current);
            // controllo se siamo arrivati
            if (playerPositions.contains(current)) {
                playerFound = Optional.of(current);
                break;
            }

            List<Coordinate> neighbours = neighbours(current, walkableCells, visited);
            for (Coordinate elem : neighbours) {
                queue.add(elem);
            }
            previous = current;
        }

        if (playerFound.isPresent()) {
            List<Coordinate> path = new ArrayList<>();
            Coordinate current = playerFound.get();
            while (current != null) {
                path.add(current);
                current = predecessors.get(current);
            }
            // percorso dal nemico al player
            Collections.reverse(path);
            List<Coordinate> shortPath = path.stream()
                    .limit(STEPS)
                    .collect(Collectors.toList());
            return Optional.of(shortPath);
        } else {
            // TODO cosa fare se non si può raggiungere nessun player
            return Optional.empty();
        }

    }

    /**
     * Retrieves the neighboring cells of the current node that are walkable and not
     * visited.
     * 
     * @param currentNode the current node.
     * @param maze        the list of walkable cells in the labyrinth.
     * @param visited     the list of visited cells.
     * @return a list of neighboring coordinates.
     */
    private List<Coordinate> neighbours(Coordinate currentNode, List<Coordinate> maze, List<Coordinate> visited) {
        List<Coordinate> neighbours = new ArrayList<>();
        for (int i = currentNode.getRow() - 1; i <= currentNode.getRow() + 1; i++) {
            for (int j = currentNode.getColumn() - 1; j <= currentNode.getColumn() + 1; j++) {
                Coordinate partial = new Coordinate(i, j);
                if (!partial.equals(currentNode) && maze.contains(partial) && !visited.contains(partial)) {
                    neighbours.add(partial);
                }
            }
        }
        return neighbours;
    }

}
