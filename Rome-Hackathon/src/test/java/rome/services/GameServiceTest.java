package rome.services;

import java.util.List;
import org.junit.Test;
import rome.model.base.Game;

import static org.junit.Assert.*;

/**
 * Created by walkercr on 8/5/16.
 */
public class GameServiceTest {
    @Test
    public void getAllGames() throws Exception {
        GameService service = new GameService();
        List<Game> games = service.getAllGames();
        for (Game game : games) {
            System.out.println(game);
        }
    }

}