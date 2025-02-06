package labioopint.model.Core.impl;

public class Builder {

    public final static int SmallLabyrint = 5;
    public final static int BigLabyrint = 5;
    public static int definitiveDimension;
    
    public static void start(TurnManager tm) {

        int numberPlayer = setting.getNumberPlayer;
        if(numberPlayer == 2) {
            definitiveDimension = SmallLabyrint;
            Labyrinth = new Labyrinth(SmallLabyrint);
        } else if(numberPlayer == 4) {
            definitiveDimension = BigLabyrint;
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
