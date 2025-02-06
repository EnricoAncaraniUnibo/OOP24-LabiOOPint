package labioopint.model.Enemy.impl;

import java.util.List;
import java.util.Random;

import labioopint.model.Core.impl.Builder;

public class RandomEnemy extends BaseEnemy{

    private final Random rand = new Random();

    public RandomEnemy(Point startPosition) {
        super(startPosition);
    }

    @Override
    public void move(Labyrinth maze, List<Player> players) {

        int direction = rand.nextInt(4);
        switch (direction) {
            case 0: moveUp(maze); break;
            case 1: moveDown(maze); break;
            case 2: moveRight(maze); break;
            case 3: moveLeft(maze); break;
        }

        

        // int dx = rand.nextInt(3) - 1; // -1, 0, 1
        // int dy = rand.nextInt(3) - 1; // -1, 0, 1

        // Point newPosition = new Point(position.getRow() + dx, position.getColumn() + dy);

        // if (maze.isValidPosition(newPosition)) {
        //     position = newPosition;
        // }
    }

    private void moveUp(Labyrinth maze){
            
    }

    private void moveDown(Labyrinth maze){
            
    }

    private void moveRight(Labyrinth maze){
            
    }

    private void moveLeft(Labyrinth maze){
            
    }

    

    


    
}
