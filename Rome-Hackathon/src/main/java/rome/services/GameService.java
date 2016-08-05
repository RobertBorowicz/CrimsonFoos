package rome.services;

import java.util.List;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rome.database.GamesDAO;
import rome.model.base.Game;

/**
 * The GameScore service.
 *
 * @author Rome
 * @version 1.0
 * @since 8/3/2016
 *
 */
public class GameService {

    /**
     * Retrieves a list of all teams.
     * @return a list of all teams
     */
    public List<Game> getAllGames() {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        List<Game> games;

        GamesDAO conn = (GamesDAO) context.getBean("gamesDAO");

        games = conn.getGames();

        return games;
    }
}
