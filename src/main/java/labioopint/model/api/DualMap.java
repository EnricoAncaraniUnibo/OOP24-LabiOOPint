package labioopint.model.api;

import java.util.HashMap;
import java.util.Map;

public class DualMap<X> {
    private Map<Coordinate, X> mapFromCoordinate;
    private Map<X, Coordinate> mapFromElement;

    public DualMap() {
        mapFromCoordinate = new HashMap<>();
        mapFromElement = new HashMap<>();
    }

    public void addElemWithCoordinate(final X elem, final Coordinate coor) {
        mapFromCoordinate.put(coor, elem);
        mapFromElement.put(elem, coor);
    }

    public Coordinate getCoordinateFromElement(final X elem) {
        return mapFromElement.get(elem);
    }

    public X getElemFromCoordinate(final Coordinate coor) {
        for (Coordinate obj : mapFromCoordinate.keySet()) {
            if(obj.getRow() == coor.getRow() && obj.getColumn()==coor.getColumn()) {
                return mapFromCoordinate.get(obj);
            }
        }
        return null;
    }

    public void remove(final X elem) {
        mapFromCoordinate.remove(mapFromElement.get(elem));
        mapFromElement.remove(elem);
    }

    public boolean isPresentByCoordinate(final Coordinate coor) {
        for (Coordinate checkCoordinate : mapFromCoordinate.keySet()) {
            if(coor.getRow()==checkCoordinate.getRow() && coor.getColumn()==checkCoordinate.getColumn()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPresentByObject(final X elem) {
        if(mapFromElement.containsKey(elem)) {
            return true;
        }
        return false;
    }
}
