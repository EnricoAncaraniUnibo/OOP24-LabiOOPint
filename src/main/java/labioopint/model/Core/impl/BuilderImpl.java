package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.Enemy.impl.BaseEnemy;
import labioopint.model.Enemy.impl.EnemyAI;
import labioopint.model.Enemy.impl.RandomEnemy;

public class BuilderImpl {

    public final static int SMALL_LABYRINTH = 5;
    public final static int BIG_LABYRINTH = 7;
    private int definitiveDimension; // NO USARE VAR PUBBLICHE USA METODO CON GETTER
    private int numberPlayer;

    // potrebbe avere bisogno di ricevere setting dal costruttore, se è vuoto, 
    // setting va passato in qualche modo
    public BuilderImpl() {
        numberPlayer = setting.getNumberPlayer;
    }

    public Labyrinth createMaze() {
        if (numberPlayer == 2) {
            definitiveDimension = SMALL_LABYRINTH;
            Labyrinth labyrint = new Labyrinth(SMALL_LABYRINTH);
            getDimension(definitiveDimension);
            return labyrint;
        } else if (numberPlayer == 4) {
            definitiveDimension = BIG_LABYRINTH;
            Labyrinth labyrint = new Labyrinth(BIG_LABYRINTH);
            getDimension(definitiveDimension);
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

    public int getDimension(int dim) {
        return dim;
    }

}
