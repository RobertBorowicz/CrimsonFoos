package rome.caches;

import rome.model.base.Player;

/**
 * Concrete business cache
 * 
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 */
//must suppress to use List with ehcache
@SuppressWarnings("rawtypes")
public class PlayersCache extends ProjectCache<Long, Player> {
	
	/**
	 * singleton instance
	 */
	private static final PlayersCache INSTANCE = new PlayersCache();
	
	/**
	 * Creates a new PlayersCache.
	 */
	private PlayersCache() {
		super("players", Long.class, Player.class, TTL_1DAY);
	}
	
	/**
	 * Returns the singleton instance of PlayersCache.
	 * 
	 * @return singleton instance
	 */
	public static PlayersCache getInstance() {
		return INSTANCE;
	}
}
