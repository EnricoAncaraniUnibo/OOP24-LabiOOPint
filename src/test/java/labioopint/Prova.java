package labioopint;

import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.EnemyDifficulty;
import labioopint.model.api.Settings;

public class Prova {
    public static void main(String[] args) {
        Settings test = new Settings(1,4,2,EnemyDifficulty.HARD);
        try {
            TurnManager tu = new TurnManager(test);
        } catch (Exception e) {
           
        }
    }
}
