package labioopint.model.Enemy.api;

import labioopint.model.Core.impl.TurnManager;

public interface EnemyFactory {

    // un nemico che si muove in modo randomico con degli step
    Enemy createRandomEnemy(TurnManager tu);

    // un nemico che insegue il giocatore o sta fermo se non esiste un percorso
    // verso un giocatore
    Enemy createChaseEnemy(TurnManager tu);

    // un nemico che si muove in modo randomico di un solo step
    Enemy createSingleStepEnemy(TurnManager tu);
}
