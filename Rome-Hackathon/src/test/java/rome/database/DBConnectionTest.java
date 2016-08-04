package rome.database;

import org.junit.Test;
import rome.model.base.Game;
import rome.model.base.Player;
import rome.model.base.Stats;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by borowicr on 8/4/16.
 */
public class DBConnectionTest {
    @Test
    public void close() throws Exception {
        DBConnection db = new DBConnection();
        List<Player> players = db.getPlayersAsList();
        for (Player p : players) {
            System.out.println(String.format("ID: %1$d, First: %2$s, Last: %3$s, Nickname: %4$s:", p.getId(), p.getFirstName(), p.getLastName(), p.getNickname()));
        }
        db.close();
    }

    @Test
    public void createTeam() throws Exception {
        DBConnection db = new DBConnection();
        db.recordGame(300, "blue", 300, 10, 310, 9);
    }

}