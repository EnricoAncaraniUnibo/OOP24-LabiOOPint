package labioopint;

import labioopint.model.Core.impl.TurnMenager;
import labioopint.model.api.Settings;

public class Prova {
    public static void main(String[] args) {
        Settings test = new Settings();
        try {
            TurnMenager turn = new TurnMenager(test);
        } catch (Exception e) {
           
        }
        
    }
}
