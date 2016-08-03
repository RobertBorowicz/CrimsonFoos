package rome.caches;

import static org.ehcache.config.builders.CacheConfigurationBuilder
                                         .newCacheConfigurationBuilder;

import java.util.concurrent.TimeUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

/**
 * Provides a singleton cache manager.
 * 
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 *
 */
public class ProjectCacheManager {

	/**
	 * singleton instance
	 */
	private static final ProjectCacheManager INSTANCE = new ProjectCacheManager();
	/**
	 * internal cache manager
	 */
	private CacheManager cacheManager;       
	
	/**
	 * Creates a new ProjectCacheManager.
	 */
	private ProjectCacheManager() {
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
	}
	
	/**
	 * Returns the singleton instance of ProjectCacheManager.
	 * 
	 * @return the singleton instance
	 */
	public static ProjectCacheManager getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Creates a new Cache with the given input.
	 * 
	 * @param alias the name of the cache
	 * @param keyType the key type
	 * @param valueType the value type
	 * @param ttlSeconds time to live in seconds
	 * 
	 * @return the created cache
	 */
	public <K, V> Cache<K, V> createCache(String alias, Class<K> keyType, 
	                                      Class<V> valueType, long ttlSeconds) {
		
		Cache<K, V> cache = cacheManager.getCache(alias, keyType, valueType);
		
		if (cache == null) {
			CacheConfiguration<K, V> cacheConfig = 
					getCacheConfig(keyType, valueType, ttlSeconds);
			cache = cacheManager.createCache(alias, cacheConfig);
		}
		
		return cache;
	}
	
	/**
	 * Creates a new CacheConfiguration with the given input values.
	 * 
	 * @param keyType the key type
	 * @param valueType the value type
	 * @param ttlSeconds time to live in seconds
	 * 
	 * @return the cache configuration built
	 */
	private <K, V> CacheConfiguration<K, V> getCacheConfig(Class<K> keyType,
			Class<V> valueType, long ttlSeconds) {
		
		Duration ttl = new Duration(ttlSeconds, TimeUnit.SECONDS);
		return newCacheConfigurationBuilder(keyType, valueType)
				.withExpiry(Expirations.timeToLiveExpiration(ttl))
				.withSizeOfMaxObjectGraph(1500L)
				.build();
	}
}
