package labioopint.model.Core.impl;

public class Builder {
    
    
    public static void start(TurnManager tm) {
        int numberPlayer = setting.getNumberPlayer;
        private final static int SmallLabyrint = 5;
        private final static int BigLabyrint = 5;
        if(numberPlayer == 2) {
            Labyrinth = new Labyrinth(SmallLabyrint);
        } else if(numberPlayer == 4) {
            Labyrinth = new Labyrinth(BigLabyrint);
        } else {
            throw new IllegalArgumentException();
        }

        for (int i = 1; i <= numberPlayer; i++) {
            Player a = new Player(i);
            tm.addPlayer(a);
        }


        
    }
    
}
