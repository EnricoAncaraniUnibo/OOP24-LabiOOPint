package labioopint.controller.api;

import labioopint.model.api.Coordinate;

public interface DirectionCheck {

    /*
     * Every functions check if there are an entrance in the specified direction
     */
    boolean checkRightEntrance(Coordinate coord);

    boolean checkLeftEntrance(Coordinate coord);

    boolean checkBottomEntrance(Coordinate coord);

    boolean checkUpperEntrance(Coordinate coord);

}