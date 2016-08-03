package rome.services;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rome.caches.PlayersCache;
import rome.database.PlayersDAO;
import rome.database.PlayersDAOImpl;
import rome.model.base.Player;
import rome.database.DBConnection;

/**
 * The Player controller.
 *
 * @author Rome
 * @version 1.0
 * @since 7/27/2016
 *
 */
public class PlayerService {

    /** the players cache */
    private static PlayersCache cache = PlayersCache.getInstance();

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
     * Retrieves the player with the given id.
     * @param id id of the player
     * @return the player with the given id
     */
    public Player getPlayerById(long id) {
        return cache.get(id);
    }

    /**
     * Checks for the existence of the given player.
     * @param player the player to search for
     * @return true if the player exists, false otherwise
     */
    public boolean exists(Player player) {
        return cache.contains(player.getId()); // || database contains id
    }

    /**
     * Creates a new player.
     * @param player the player to create
     */
    public void createPlayer(Player player) {
        cache.create(player.getId(), player);
        // other logic to occur for database insert
    }

    /**
     * Updates the given player.
     * @param player the player to update
     */
    public void updatePlayer(Player player) {
        cache.update(player.getId(), player);
        // other logic would occur for database update
    }

    /**
     * Deletes the player with the given id.
     * @param id the id of the player to delete
     */
    public void deletePlayerById(long id) {
        cache.delete(id);
        // other logic to delete from database
    }

    /**
     * Deletes all players.
     */
    public void deleteAllPlayers() {
        cache.deleteAll();
        // other logic to delete from database
    }
}
