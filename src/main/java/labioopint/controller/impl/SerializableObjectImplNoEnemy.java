package labioopint.controller.impl;

import java.io.Serializable;
import java.util.List;

import labioopint.controller.api.SerializableObject;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.player.impl.PlayerImpl;

public class SerializableObjectImplNoEnemy implements SerializableObject,Serializable {
    private List<PlayerImpl> playersList;
    private List<BlockImpl> labyrinthList;
    

    public SerializableObjectImplNoEnemy(TurnManager turnManager){
        this.playersList = turnManager.getPlayers();
        this.labyrinthList = turnManager.getLab().getGrid().getListofBlocks();
    }
}
