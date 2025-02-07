package labioopint.model.Core.impl;

import java.util.*;

public class RandomTurnChooser {

    private List<Player> players = new ArrayList<>();
    private Random random = new Random();

    public RandomTurnChooser(List<Player> players) {
        this.players = players;
    }

    public List<Player> randomOrder() {
        List<Integer> idies = new ArrayList<>();
        List<Integer> idiesRandomic = new ArrayList<>();
        List<Player> playerRandomic = new ArrayList<>();
        for (Player player : players) {
            idies.add(player.getId);
        }

        while (idies.size() != 0) {
            int index = random.nextInt(0, idies.size());
            idiesRandomic.add(idies.get(index));
            idies.remove(index);
        }

        for (Integer integer : idiesRandomic) {
            for (Player player : players) {
                if (player.getId == integer) {
                    playerRandomic.add(player);
                }
            }
        }

        return playerRandomic;
    }

}
