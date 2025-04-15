package labioopint.model.Enemy.impl;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Enemy.api.EnemyFactory;
import labioopint.model.Enemy.impl.ais.ChaseAI;
import labioopint.model.Enemy.impl.ais.RandomAI;
import labioopint.model.Enemy.impl.ais.SingleStepRandomAI;

public class EnemyFactoryImpl implements EnemyFactory {

    @Override
    public Enemy createRandomEnemy(TurnManager tu) {
        return new EnemyImpl(new RandomAI(tu),tu);
    }

    @Override
    public Enemy createChaseEnemy(TurnManager tu) {
        return new EnemyImpl(new ChaseAI(tu),tu);
    }

    @Override
    public Enemy createSingleStepEnemy(TurnManager tu) {
        return new EnemyImpl(new SingleStepRandomAI(tu),tu);
    }

}
