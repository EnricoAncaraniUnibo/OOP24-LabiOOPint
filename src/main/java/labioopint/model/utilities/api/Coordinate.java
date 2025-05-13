package labioopint.model.utilities.api;

import java.io.Serializable;

public interface Coordinate extends Serializable {

    Integer getRow();
    Integer getColumn();
    boolean equals(Object obj);
}
