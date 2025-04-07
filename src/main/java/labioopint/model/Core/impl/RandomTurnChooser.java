package labioopint.model.Core.impl;

import java.util.*;

import labioopint.model.player.impl.Player;

/**
 * RandomTurnChooser is responsible for randomizing the order of players' turns.
 */
public class RandomTurnChooser {

    private List<Player> players = new ArrayList<>();
    private Random random = new Random();

    /**
     * Constructs a RandomTurnChooser instance with the specified list of players.
     * 
     * @param players the list of players to be randomized.
     */
    public RandomTurnChooser(List<Player> players) {
        this.players = players;
    }

    /**
     * Randomizes the order of players' turns.
     * 
     * @return a list of players in random order.
     */
    public List<Player> randomOrder() {
        List<String> idies = new ArrayList<>();
        List<String> idiesRandomic = new ArrayList<>();
        List<Player> playerRandomic = new ArrayList<>();
        for (Player player : players) {
            idies.add(player.getID());
        }

        while (idies.size() != 0) {
            int index = random.nextInt(0, idies.size());
            idiesRandomic.add(idies.get(index));
            idies.remove(index);
        }

        for (String s : idiesRandomic) {
            for (Player player : players) {
                if (player.getID().equals(s)) {
                    playerRandomic.add(player);
                }
            }
        }

        return playerRandomic;
    }

}
