package labioopint.model.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import labioopint.model.player.impl.PlayerImpl;

/**
 * RandomTurnChooser is responsible for randomizing the order of players' turns.
 */
public class RandomTurnChooser {

    private final List<PlayerImpl> players;
    private final Random random = new Random();

    /**
     * Constructs a RandomTurnChooser instance with the specified list of players.
     * 
     * @param players the list of players to be randomized.
     */
    public RandomTurnChooser(final List<PlayerImpl> players) {
        this.players = players;
    }

    /**
     * Randomizes the order of players' turns.
     * 
     * @return a list of players in random order.
     */
    public List<PlayerImpl> randomOrder() {
        final List<String> idies = new ArrayList<>();
        final List<String> idiesRandomic = new ArrayList<>();
        final List<PlayerImpl> playerRandomic = new ArrayList<>();
        for (final PlayerImpl player : players) {
            idies.add(player.getID());
        }

        while (!idies.isEmpty()) {
            final int index = random.nextInt(0, idies.size());
            idiesRandomic.add(idies.get(index));
            idies.remove(index);
        }

        for (final String s : idiesRandomic) {
            for (final PlayerImpl player : players) {
                if (player.getID().equals(s)) {
                    playerRandomic.add(player);
                }
            }
        }

        return playerRandomic;
    }

}
