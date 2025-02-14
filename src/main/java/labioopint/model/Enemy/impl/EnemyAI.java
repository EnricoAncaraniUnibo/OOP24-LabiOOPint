package labioopint.model.Enemy.impl;

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
import labioopint.model.Labyrinth.api.Labyrinth;
import labioopint.model.Player.api.Player;

public class EnemyAI extends BaseEnemy {

    private static final int STEPS = 5;

    @Override
    public void move(Labyrinth maze, List<Player> players) {
        List<Coordinate> walkableCells = maze.getLabyrinth()
                .entrySet()
                .stream()
                .filter(x -> x.getValue() == true)
                .map(x -> x.getKey())
                .toList();

        getPath(walkableCells, players);

    }

    // Optional perché potrebbe non esistere un percorso di coordinate, altrimenti
    // definisce un percorso dal nemico al player.
    private Optional<List<Coordinate>> getPath(List<Coordinate> walkableCells, List<Player> players) {
        List<Coordinate> playerPositions = players.stream()
                .map(p -> p.getCoordinate())
                .toList();
        Coordinate start = this.getPosition();
        List<Coordinate> visited = new ArrayList<>();
        // chiavi -> nodo corrente, valore -> nodo precedente (null per il primo nodo)
        Map<Coordinate, Coordinate> predecessors = new HashMap<>();
        Queue<Coordinate> queue = new ArrayDeque<>(); // TODO trovare struttura dati migliore
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

    @Override
    public Optional<Player> playerHit(List<Player> players) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playerHit'");
    }

}

// import java.util.*;

// class Labyrinth {
// static class Position {
// int x, y, steps;

// Position(int x, int y, int steps) {
// this.x = x;
// this.y = y;
// this.steps = steps;
// }
// }

// public static Position moveEnemy(int startX, int startY, int targetX, int
// targetY, char[][] grid) {
// int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Su, Giù,
// Sinistra, Destra
// Queue<Position> queue = new LinkedList<>();
// Set<String> visited = new HashSet<>();
// queue.add(new Position(startX, startY, 0));
// visited.add(startX + "," + startY);

// List<Position> possibleMoves = new ArrayList<>();

// while (!queue.isEmpty()) {
// Position current = queue.poll();

// if (current.steps == 3) {
// possibleMoves.add(current); // Raggiunto il limite di 3 passi, aggiungo alla
// lista delle possibili mosse
// continue;
// }

// for (int[] dir : directions) {
// int newX = current.x + dir[0];
// int newY = current.y + dir[1];

// if (isValidMove(newX, newY, grid, visited)) {
// queue.add(new Position(newX, newY, current.steps + 1));
// visited.add(newX + "," + newY);
// }
// }
// }

// // Se ci sono più posizioni valide, scegli la più vicina al giocatore
// return chooseBestMove(possibleMoves, targetX, targetY, startX, startY);
// }

// private static boolean isValidMove(int x, int y, char[][] grid, Set<String>
// visited) {
// return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length &&
// grid[x][y] != '#' && !visited.contains(x + "," + y);
// }

// private static Position chooseBestMove(List<Position> possibleMoves, int
// targetX, int targetY, int startX, int startY) {
// if (possibleMoves.isEmpty()) {
// return new Position(startX, startY, 0); // Se nessuna mossa è valida, resta
// fermo
// }

// // Trova la posizione più vicina al target
// return possibleMoves.stream()
// .min(Comparator.comparingInt(pos -> Math.abs(pos.x - targetX) +
// Math.abs(pos.y - targetY)))
// .orElse(possibleMoves.get(0));
// }
