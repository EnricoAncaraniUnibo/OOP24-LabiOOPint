package labioopint.model.enemy.api;

public interface EnemyFactory {

    Enemy createRandomEnemy();

    Enemy createChaseEnemy();

    Enemy createSingleStepEnemy();

}