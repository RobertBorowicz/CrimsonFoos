package rome.services;

import java.util.List;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rome.database.PlayersDAO;
import rome.model.base.Player;

/**
 * The Player controller.
 *
 * @author Rome
 * @version 1.0
 * @since 7/27/2016
 *
 */
public class PlayerService {

    /**
     * Retrieves a list of all players.
     * @return a list of all player
     */
    public List<Player> getAllPlayers() {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        List<Player> players;

        PlayersDAO conn = (PlayersDAO) context.getBean("playerDAO");

        players = conn.getPlayers();

        for (Player p : players) {
            System.out.println(p.getNickname());
        }

        return players;
    }

    /**
     * Creates a new player.
     * @param player the player to create
     */
    public Player createPlayer(Player player) {
        // TODO: Create player in the database
        return null;
    }

    /**
     * Updates the given player.
     * @param player the player to update
     */
    public boolean updatePlayer(long id, Player player) {
        // TODO: Update player in the database
        return false;
    }

    /**
     * Deletes the player with the given id.
     * @param id the id of the player to delete
     */
    public boolean deletePlayerById(long id) {
        // TODO: Delete player in the database
        return false;
    }
}
