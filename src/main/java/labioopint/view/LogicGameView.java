package labioopint.view;

import labioopint.controller.impl.GameController;
import labioopint.controller.impl.InformationMessenger;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.ActionType;
import labioopint.model.api.Coordinate;

public class LogicGameView {

    private TurnManager turn;
    private InformationMessenger ifm;
    private GameController gc;

    public LogicGameView(TurnManager tu) {
        turn = tu;
        ifm = new InformationMessenger(turn);
        gc = new GameController(tu);
    }

    public String getTurn() {
        return ifm.getTurn();
    }

    public String getAction() {
        return ifm.getAction();
    }

    public String[] getPowerUps() {
        return ifm.getPowerUpsList();
    }

    public void activatePowerUps(String powerUp) {
        for (PowerUp pu : turn.getPowerUps()) {
            if (pu.getName().equals(powerUp)) {
                pu.activate(turn.getCurrentPlayer());
            }
        }
    }

    public void useAction(String name) {
        gc.action(name);
    }

    public void mouseAction(int x, int y, int size){
        Coordinate newCoordinate = new Coordinate((y % size < size/2) ? y/size-1 : y/size, x/size);
        gc.action(newCoordinate);
    }

    public Boolean isBlockPlacement() {
        return turn.getCurrentAction() == ActionType.BLOCK_PLACEMENT;
    }
}
