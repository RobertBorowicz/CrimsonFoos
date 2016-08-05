package rome.services;

import java.util.List;
import rome.database.DBConnection;
import rome.model.base.Player;

/**
 * Created by walkercr on 8/5/16.
 */
public class PlayerService {

    public List<Player> getAllPlayers() {
        DBConnection db = new DBConnection();
        List<Player> players = db.getPlayersAsList();
        db.close();
        return players;
    }

    public int createPlayer(Player p) {
        DBConnection db = new DBConnection();
        int id = db.addNewPlayer(p);
        db.close();
        return id;
    }

    public boolean updatePlayer(int id, Player p) {
        DBConnection db = new DBConnection();
        boolean result = db.updatePlayer(id, p.getFirstName(), p.getLastName(), p.getNickname());
        db.close();
        return result;
    }

    public boolean deletePlayerById(int id) {
        DBConnection db = new DBConnection();
        boolean result = db.deletePlayer(id);
        db.close();
        return result;
    }
}
