package labioopint.view;

import labioopint.model.api.PowerUp;
import labioopint.model.player.Player;
import java.util.List;

public interface GameView {
    /**
     * Aggiorna lo stato del power-up attivato.
     * 
     * @param powerUp il power-up attivato.
     * @param player il giocatore che ha attivato il power-up.
     */
    void updatePowerUpStatus(PowerUp powerUp, Player player);

    /**
     * Aggiorna la lista dei power-up raccolti.
     * 
     * @param collectedPowerUps la lista aggiornata dei power-up raccolti.
     */
    void updateCollectedPowerUps(List<PowerUp> collectedPowerUps);

    /**
     * Mostra un messaggio di errore.
     * 
     * @param message il messaggio di errore da mostrare.
     */
    void showErrorMessage(String message);
}