package labioopint.controller.api;

import labioopint.model.api.Coordinate;

public interface DirectionCheck {

    /**
     * It check if the block have an entrance on the right side
     * 
     * @param coord             the coordinate of the target block 
     * @return true if there is the entrance, false otherwise
     */
    boolean checkRightEntrance(Coordinate coord);

    /**
     * It check if the block have an entrance on the left side
     * 
     * @param coord             the coordinate of the target block 
     * @return true if there is the entrance, false otherwise
     */
    boolean checkLeftEntrance(Coordinate coord);

    /**
     * It check if the block have an entrance on the bottom side
     * 
     * @param coord             the coordinate of the target block 
     * @return true if there is the entrance, false otherwise
     */
    boolean checkBottomEntrance(Coordinate coord);

    /**
     * It check if the block have an entrance on the upper side
     * 
     * @param coord             the coordinate of the target block 
     * @return true if there is the entrance, false otherwise
     */
    boolean checkUpperEntrance(Coordinate coord);

}
