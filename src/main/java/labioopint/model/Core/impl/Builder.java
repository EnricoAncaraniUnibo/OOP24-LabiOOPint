package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.Enemy.impl.BaseEnemy;
import labioopint.model.Enemy.impl.EnemyAI;
import labioopint.model.Enemy.impl.RandomEnemy;

public class Builder {

    public final static int SmallLabyrint = 5;
    public final static int BigLabyrint = 7;
    public static int definitiveDimension;
    private int numberPlayer;

    public Builder() {
        numberPlayer = setting.getNumberPlayer;
    }

    public Labyrint createMaze() {
        if (numberPlayer == 2) {
            definitiveDimension = SmallLabyrint;
            Labyrinth labyrint = new Labyrinth(SmallLabyrint);
            return labyrint;
        } else if (numberPlayer == 4) {
            definitiveDimension = BigLabyrint;
            Labyrinth labyrint = new Labyrinth(BigLabyrint);
            return labyrint;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public List<Player> createPlayers() {
        List<Player> tm = new ArrayList<>();
        for (int i = 1; i <= numberPlayer; i++) {
            Player a = new Player(i);
            tm.addPlayer(a);
        }
        return tm;
    }

    public BaseEnemy createEnemy() {
        int type = setting.getTypeEnemy;
        if (type == 0) {
            BaseEnemy enemy = new RandomEnemy();
            return enemy;
        } else {
            BaseEnemy enemy = new EnemyAI();
            return enemy;
        }

    }

}
