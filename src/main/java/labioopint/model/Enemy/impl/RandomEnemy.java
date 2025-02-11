package labioopint.model.Enemy.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import labioopint.commons.Coordinate;
import labioopint.model.Core.impl.BuilderImpl;

public class RandomEnemy extends BaseEnemy {

    private final Random rand = new Random();

    @Override
    public void move(Labyrinth maze, List<Player> players) {

        int direction = rand.nextInt(4);
        int steps = rand.nextInt(4) + 2;    // da 2 a 5
        Coordinate current = maze.getCoordinateEnemy();

        for (int i = 0; i < steps; i++) {
            Coordinate next = getNextCoordinate(current, direction);
            if (maze.isValidPosition(next)) {
                current = next;
            } else {
                direction = rand.nextInt(4);
            }
        }
        maze.setCoordinateEnemy(current);
    }

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

    @Override
    public Optional<Player> playerHit(List<Player> players) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playerHit'");
    }
}

   

    // int dx = rand.nextInt(3) - 1; // -1, 0, 1
    // int dy = rand.nextInt(3) - 1; // -1, 0, 1

    // Point newPosition = new Point(position.getRow() + dx, position.getColumn() +
    // dy);

    // if (maze.isValidPosition(newPosition)) {
    // position = newPosition;
    // }
// il numero di step va nella classe turn, mentre la direzione in cui muoversi è
// di questo metodo qui
// turno dice che il nemico si muove (enemy.move) -> io gli restituisco dove si
// deve muovere, le nuove coordinate
// turn passa da action predicate (se posso farlo) e continuo a farlo per n step
// (ogni volta che si incontra un muro,
// si cambia direzione e si riprova).
