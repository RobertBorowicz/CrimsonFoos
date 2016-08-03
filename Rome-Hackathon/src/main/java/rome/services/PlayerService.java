package rome.services;

import java.util.List;

import rome.caches.PlayersCache;
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

    /** the players cache */
    private static PlayersCache cache = PlayersCache.getInstance();

    /**
     * Retrieves a list of all players.
     * @return a list of all player
     */
    public List<Player> getAllPlayers() {
        return cache.getAll();
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
