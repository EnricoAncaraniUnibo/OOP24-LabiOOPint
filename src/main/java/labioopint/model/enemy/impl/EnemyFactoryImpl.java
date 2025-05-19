package labioopint.model.enemy.impl;

import labioopint.model.enemy.api.Enemy;
import labioopint.model.enemy.api.EnemyFactory;
import labioopint.model.enemy.impl.ais.ChaseAI;
import labioopint.model.enemy.impl.ais.RandomAI;
import labioopint.model.enemy.impl.ais.SingleStepRandomAI;

/**
 * The class used to create an Enemy with a predefined intelligence.
 */
public final class EnemyFactoryImpl implements EnemyFactory {

    @Override
    public Enemy createRandomEnemy() {
        return new EnemyImpl(new RandomAI());
    }

    @Override
    public Enemy createChaseEnemy() {
        return new EnemyImpl(new ChaseAI());
    }

    @Override
    public Enemy createSingleStepEnemy() {
        return new EnemyImpl(new SingleStepRandomAI());
    }

}
