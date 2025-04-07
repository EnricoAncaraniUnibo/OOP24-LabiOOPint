package labioopint.controller.impl;

import java.util.ArrayList;
import java.util.List;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.ActionType;

public class InformationMessenger {
    public static String getTurn() {
        return "Player: "+TurnManager.GetCurrentPlayer().getID();
    }

    public static String getAction() {
        if(TurnManager.GetCurrentAction()==ActionType.BLOCK_PLACEMENT) {
            return "Devi posizionare il blocco";
        }
        if(TurnManager.GetCurrentAction()==ActionType.PLAYER_MOVEMENT) {
            return "Puoi muovere il personaggio";
        }
        return "";
    }

    public static String[] getPowerUpsList(){
        List<PowerUp> lpu = new ArrayList<>();
        lpu.addAll(TurnManager.GetCurrentPlayer().getUsablePowerUps());
        String[] names = new String[lpu.size()];
        int i=0;
        for (PowerUp pu : lpu) {
            names[i] = (pu.getName());
            i++;
        }
        return names;
    }

    public static List<String> getObjectivesInGame() {
        List<PowerUp> lpu = new ArrayList<>();
        lpu.addAll(TurnManager.GetLab().getListOfPowerUps());
        List<String> names=new ArrayList<>(); 
        for (PowerUp pu : lpu) {
            names.add(pu.getName());
        }
        return names;
    }
}
