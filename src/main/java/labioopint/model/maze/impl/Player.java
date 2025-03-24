package labioopint.model.maze.impl;

import labioopint.model.api.Movable;

public class Player extends Movable{
    private final Integer id;

    public Player(final Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }
}
