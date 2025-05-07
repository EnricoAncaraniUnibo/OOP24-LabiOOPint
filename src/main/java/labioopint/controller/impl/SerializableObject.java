package labioopint.controller.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.player.impl.PlayerImpl;

public class SerializableObject implements Serializable{
    private TurnManager turnManager;
    private List<PlayerImpl> playersList;
    private Optional<Enemy> enemy;
    private List<BlockImpl> labyrinthList;

    public SerializableObject(){
        this.playersList = turnManager.getPlayers();
        this.enemy = turnManager.getEnemy();
        this.labyrinthList = turnManager.getLab().getGrid().getListofBlocks();
    }
}
