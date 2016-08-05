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

    public Player createPlayer(Player p) {
        DBConnection db = new DBConnection();
        db.addNewPlayer(p);
        db.close();
        return null;
    }

    public boolean updatePlayer(int id, Player p) {
        DBConnection db = new DBConnection();
        db.updatePlayer(id, p.getFirstName(), p.getLastName(), p.getNickname());
        db.close();
        return true;
    }

    public boolean deletePlayerById(int id) {
        DBConnection db = new DBConnection();
        db.deletePlayer(id);
        db.close();
        return true;
    }
}
