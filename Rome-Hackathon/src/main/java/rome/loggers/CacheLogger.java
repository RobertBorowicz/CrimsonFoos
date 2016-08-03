package rome.loggers;

/**
 * Concrete cache logger
 * 
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 */
public class CacheLogger extends ProjectLogger {
	
	/**
	 * singleton instance 
	 */
	private static CacheLogger instance;

	/**
	 * Creates a new CacheLogger
	 */
	private CacheLogger() {
		super("CacheLogger", "cache.log");
	}
	
	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static CacheLogger getInstance() {
		if (instance == null) {                
			instance = new CacheLogger();
		}
		return instance;
	}
    
	/**
	 * Logs the given input.
	 * 
	 * @param action the action
	 * @param cacheAlias cache alias
	 * @param key the key
	 * @param value the value
	 */
	public void log(Action action, String cacheAlias, String key, Object value) {
		logger.info(format(action, cacheAlias, key, value));
	}
	
	/**
	 * Formats the given input.
	 * 
	 * @param action the action 
	 * @param cacheAlias cache alias
	 * @param key the key
	 * @param value the value
	 * 
	 * @return the formatted input
	 */
	private static String format(Action action, String cacheAlias, String key, 
	                             Object value) {
		return new StringBuilder(action.description).append(cacheAlias)
	    		.append("\nkey: ").append(key)
	    		.append("\nvalue: ").append(value)
	    		.append("\n").toString();
	}
	
	/**
	 * Represents the log-worthy action performed on the cache.
	 * 
	 * @author Craig Walker
	 * @version 1.0
	 * @since 4/18/2016
	 */
	public enum Action {
		/**
		 * cache insertion action
		 */
		INSERTION("Inserted into "),         
		/**
		 * cache retrieval action
		 */
		RETRIEVAL("Retrieved from ");       
		
		/**
		 * action description
		 */
		private String description;         
		
		/**
		 * Creates a new Action with the given description
		 * 
		 * @param description action description
		 */
		private Action(String description) {
			this.description = description;
		}
	}
}
