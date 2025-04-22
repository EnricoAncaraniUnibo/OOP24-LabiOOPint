package labioopint.model.powerup.api;

import java.util.List;

import labioopint.model.player.impl.PlayerImpl;

public interface PowerUp {
    /**
     * Attiva il power-up.
     */
    void activate(PlayerImpl currentPlayer);

    /**
     * Verifica se il power-up è stato raccolto.
     * 
     * @return true se il power-up è stato raccolto, false altrimenti.
     */
    boolean isCollected();

    /**
     * Raccoglie il power-up.
     */
    void collect();

    /**
     * Restituisce la lista dei power-up collezionati.
     * 
     * @return la lista dei power-up collezionati.
     */
    List<PowerUp> getCollectedPowerUps();

    String getName();
}