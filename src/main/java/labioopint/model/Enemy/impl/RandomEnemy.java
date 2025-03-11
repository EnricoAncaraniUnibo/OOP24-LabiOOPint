package labioopint.model.Enemy.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import labioopint.commons.Coordinate;
import labioopint.model.Core.impl.BuilderImpl;
import labioopint.model.Labyrinth.api.Labyrinth;
import labioopint.model.Player.api.Player;

/**
 * RandomEnemy represents an enemy that moves randomly within the labyrinth.
 */
public class RandomEnemy extends BaseEnemy {

    private final Random rand = new Random();

    /**
     * Moves the enemy in the labyrinth in a random direction.
     * 
     * @param maze    the labyrinth in which the enemy moves.
     * @param players the list of players in the game.
     */
    @Override
    public void move(Labyrinth maze, List<Player> players) {

        int direction = rand.nextInt(4);
        int steps = rand.nextInt(4) + 2; // da 2 a 5
        Coordinate current = maze.getCoordinateEnemy();

        for (int i = 0; i < steps; i++) {
            Boolean success = false;
            while (!success) {
                Coordinate next = getNextCoordinate(current, direction);
                if (ActionPredicate.isValidPosition(current, next)) {
                    current = next;
                    success = true;
                } else {
                    direction = rand.nextInt(4);
                }
            }
        }
        maze.setCoordinateEnemy(current);
    }

    /**
     * Determines the next coordinate based on the current position and direction.
     * 
     * @param c         the current coordinate.
     * @param direction the direction to move (0: up, 1: down, 2: right, 3: left).
     * @return the next coordinate after moving in the specified direction.
     */
    private Coordinate getNextCoordinate(Coordinate c, int direction) {
        switch (direction) {
            case 0:
                return new Coordinate(c.getRow() - 1, c.getColumn());
            case 1:
                return new Coordinate(c.getRow() + 1, c.getColumn());
            case 2:
                return new Coordinate(c.getRow(), c.getColumn() + 1);
            case 3:
                return new Coordinate(c.getRow(), c.getColumn() - 1);
            default:
                return c;
        }
    }

    /**
     * Determines if a player has been hit by the enemy.
     * 
     * @param players the list of players in the game.
     * @return an optional player that has been hit.
     */
    @Override
    public Optional<Player> playerHit(List<Player> players) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playerHit'");
    }
}

// il numero di step va nella classe turn, mentre la direzione in cui muoversi è
// di questo metodo qui
// turno dice che il nemico si muove (enemy.move) -> io gli restituisco dove si
// deve muovere, le nuove coordinate
// turn passa da action predicate (se posso farlo) e continuo a farlo per n step
// (ogni volta che si incontra un muro, si cambia direzione e si riprova).
