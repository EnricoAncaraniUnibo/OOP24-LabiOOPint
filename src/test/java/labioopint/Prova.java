package labioopint;

import labioopint.model.api.Settings;
import labioopint.model.core.impl.TurnManager;
import labioopint.model.enemy.api.EnemyDifficulty;

public class Prova {
    public static void main(String[] args) {
        Settings test = new Settings(1,4,2,EnemyDifficulty.HARD);
        try {
            TurnManager tu = new TurnManager(test);
        } catch (Exception e) {
           
        }
    }
}
