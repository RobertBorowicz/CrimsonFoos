package rome.model.test_data;

import rome.model.base.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by walkercr on 7/27/16.
 */
public class TestPlayers {
    private static List<Player> players = Arrays.asList(
            new Player(1, "Craig", "Walker", "cwalk"),
            new Player(2, "April", "Walker", "awalk")
    );

    public static List<Player> getAllPlayers() {
        return players;
    }

    public static Player getPlayerById(long id) {
        for (Player p : players) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public static boolean isPlayerExist(Player player) {
        for (Player p : players) {
            if (player.equals(p)) {
                return true;
            }
        }
        return false;
    }
}
