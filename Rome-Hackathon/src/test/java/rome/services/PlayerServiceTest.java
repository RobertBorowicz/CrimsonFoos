package rome.services;

import org.junit.Test;
import rome.model.base.Player;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by borowicr on 8/3/16.
 */
public class PlayerServiceTest {
    @Test
    public void getAllPlayers() throws Exception {
        PlayerService ps = new PlayerService();
        List<Player> players = ps.getAllPlayers();
        for (Player p : players) {
            System.out.println(p.getNickname());
        }
    }

}