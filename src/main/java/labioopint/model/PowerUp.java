package labioopint.model;

import java.util.List;

public interface PowerUp {
    /**
     * Attiva il power-up.
     */
    void activate();

    /**
     * Verifica se il power-up è stato raccolto.
     * 
     * @return true se il power-up è stato raccolto, false altrimenti.
     */
    boolean isCollected();

    /**
     * Restituisce la lista dei power-up collezionati.
     * 
     * @return la lista dei power-up collezionati.
     */
    List<PowerUp> getCollectedPowerUps();
}
