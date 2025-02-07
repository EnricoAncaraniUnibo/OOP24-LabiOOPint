package labioopint.model.Enemy.impl;

import java.util.List;
import java.util.Random;

import labioopint.model.Core.impl.Builder;

public class RandomEnemy extends BaseEnemy{

    private final Random rand = new Random();

    public RandomEnemy(Coordinate startPosition) {
        super(startPosition);
    }

    @Override
    public void move(Labyrinth maze, List<Player> players) {

        int direction = rand.nextInt(4);
        // il numero di step va nella classe turn, mentre la direzione in cui muoversi è di questo metodo qui
        // turno dice che il nemico si muove (enemy.move) -> io gli restituisco dove si deve muovere, le nuove coordinate
        // turn passa da action predicate (se posso farlo) e continuo a farlo per n step (ogni volta che si incontra un muro,
        // si cambia direzione e si riprova).
        switch (direction) {
            case 0: return moveUp(maze); break;
            case 1: return moveDown(maze); break;
            case 2: return moveRight(maze); break;
            case 3: return moveLeft(maze); break;
        }

        

        // int dx = rand.nextInt(3) - 1; // -1, 0, 1
        // int dy = rand.nextInt(3) - 1; // -1, 0, 1

        // Point newPosition = new Point(position.getRow() + dx, position.getColumn() + dy);

        // if (maze.isValidPosition(newPosition)) {
        //     position = newPosition;
        // }
    }

    protected Coordinate moveUp(Labyrinth maze){
        Coordinate c = maze.getCoordinateEnemy;
        return new Coordinate(c.getRow() - 1, c.getColumn());
    }

    protected Coordinate moveDown(Labyrinth maze){
        Coordinate c = maze.getCoordinateEnemy;
        return new Coordinate(c.getRow() + 1, c.getColumn());
    }

    protected Coordinate moveRight(Labyrinth maze){
        Coordinate c = maze.getCoordinateEnemy;
        return new Coordinate(c.getRow(), c.getColumn() + 1);  
    }

    protected Coordinate moveLeft(Labyrinth maze){
        Coordinate c = maze.getCoordinateEnemy;
        return new Coordinate(c.getRow(), c.getColumn() - 1);
    }

    

    


    
}
