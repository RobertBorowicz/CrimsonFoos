package rome.caches;

import java.util.ArrayList;
import java.util.List;
import org.ehcache.Cache;

/**
 * Abstract cache superclass.
 * 
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 *
 * @param <K> key type
 * @param <V> value type
 */
public abstract class ProjectCache<K,V> {
	
	/**
	 * 1 day time-to-live
	 */
	protected static final long TTL_1DAY = 86400L;  
	/**
	 * 2 hour time-to-live
	 */
	protected static final long TTL_2HRS = 7200L;      
	/**
	 * the cache manager
	 */
	private static final ProjectCacheManager MANAGER = ProjectCacheManager.getInstance();
	/**
	 * the cache
	 */
	protected Cache<K,V> cache;
	
	/**
	 * Creates a new ProjectCache with the given input
	 * @param alias cache alias
	 * @param keyType key type
	 * @param valueType value type
	 * @param ttlSeconds time-to-live in seconds
	 */
	protected ProjectCache(String alias, Class<K> keyType,
                           Class<V> valueType, long ttlSeconds) {
		cache = MANAGER.createCache(alias, keyType, valueType, ttlSeconds);
	}
	
	/**
	 * Checks whether a mapping for the given key is present, without retrieving
	 * the associated value.
	 * @param key the key, may not be null
	 * @return true if a mapping is present, false otherwise
	 */
	public synchronized boolean contains(K key) {
		return (key == null) ? false : cache.containsKey(key);
	}
	
	/**
	 * Retrieves the value currently mapped to the provided key.
	 * @param key the key, may not be null
	 * @return the value mapped to the key, null if none
	 */
	public synchronized V get(K key) {
		return (key == null) ? null : cache.get(key);
	}

	/**
	 * Retrieves all the values in the cache.
	 * @return a list of all values in the cache
     */
	public synchronized List<V> getAll() {
		return new ArrayList<V>();
	}

	/**
	 * Updates the value of the element with the given id if it exists
     */
	public synchronized void update(K key, V value) {
		if (cache.containsKey(key)) {
			cache.put(key, value);
		}
	}
	
	/**
	 * Associates the given value to the given key in this cache. No 
	 * association is made if key or value is null.
	 * @param key the key, may not be null
	 * @param value the value, may not be null
	 */
	public synchronized void create(K key, V value) {
		if ((key != null) && (value != null)) {
			cache.putIfAbsent(key, value);
		}
	}

	/**
	 * Deletes the element with the given key if it exists.
	 * @param key the key, may not be null
	 */
	public synchronized void delete(K key) {
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
	}

	/**
	 * Deletes all items from the cache.
     */
	public synchronized void deleteAll() {
		cache.clear();
	}
}
