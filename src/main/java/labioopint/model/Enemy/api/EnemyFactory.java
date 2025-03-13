package labioopint.model.Enemy.api;

public interface EnemyFactory {

    // un nemico che si muove in modo randomico con degli step
    Enemy createRandomEnemy();

    // un nemico che insegue il giocatore o sta fermo se non esiste un percorso
    // verso un giocatore
    Enemy createChaseEnemy();

    // un nemico che si muove in modo randomico di un solo step
    Enemy createSingleStepEnemy();
}
