package labioopint.model.Enemy.impl;

import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyFactory;
import labioopint.model.Enemy.impl.ais.ChaseAI;
import labioopint.model.Enemy.impl.ais.RandomAI;
import labioopint.model.Enemy.impl.ais.SingleStepRandomAI;

public class EnemyFactoryImpl implements EnemyFactory {

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
