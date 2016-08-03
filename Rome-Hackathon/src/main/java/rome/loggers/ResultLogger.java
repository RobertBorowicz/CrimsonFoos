package rome.loggers;

/**
 * Concrete result logger
 * 
 * @author Craig Walker
 * @version 1.0
 * @since 7/26/2016
 */
public class ResultLogger extends ProjectLogger {
	
	/**
	 * singleton instance 
	 */
	private static ResultLogger instance;

	/**
	 * Creates a new ResultLogger
	 */
	private ResultLogger() {
		super("ResultLogger", "result.log");
	}
	
	/**
	 * Returns the singleton instance
	 * 
	 * @return the singleton instance
	 */
	public static ResultLogger getInstance() {
		if (instance == null) {
			instance = new ResultLogger();
		}
		return instance;
	}
    
	/**
	 * Logs the result
	 * 
	 * @param result a result
	 */
    public void log(String result) {
    	logger.info(result);
    }
}
