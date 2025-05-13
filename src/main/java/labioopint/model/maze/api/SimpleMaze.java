package labioopint.model.maze.api;

import java.io.Serializable;

import labioopint.model.block.api.Block;

public interface SimpleMaze extends Serializable{

    Block generate();

}