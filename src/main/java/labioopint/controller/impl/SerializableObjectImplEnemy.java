package labioopint.controller.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import labioopint.controller.api.SerializableObject;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.Enemy;
import labioopint.model.player.impl.PlayerImpl;

public class SerializableObjectImplEnemy implements Serializable,SerializableObject{
    private List<PlayerImpl> playersList;
    private Enemy enemy;
    private List<BlockImpl> labyrinthList;

    public SerializableObjectImplEnemy(TurnManager turnManager){
        this.playersList = turnManager.getPlayers();
        this.enemy = turnManager.getEnemy().getSecond();
        this.labyrinthList = turnManager.getLab().getGrid().getListofBlocks();
    }
}
