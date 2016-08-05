package rome.services;

import java.util.List;

import rome.database.DBConnection;
import rome.model.base.Game;

/**
 * Created by walkercr on 8/5/16.
 */
public class GameService {

    public List<Game> getAllGames() {
        DBConnection db = new DBConnection();
        List<Game> games = db.getAllGames();
        db.close();
        return games;
    }
}
