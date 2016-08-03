package rome.loggers;

import java.util.logging.Logger;

/**
 * Concrete error logger
 * 
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 */
public class ErrorLogger extends ProjectLogger {
	
	/**
	 * singleton instance that holds the error logger
	 */
	private static ErrorLogger instance = new ErrorLogger();

	/**
	 * Creates a new ErrorLogger
	 */
	private ErrorLogger() {
		super("ErrorLogger", "error.log");
	}
	
	/**
	 * Returns the internal error logger to enable error tracing to specific
	 * methods.
	 * 
	 * @return the internal error logger
	 */
	public static Logger getLogger() {
	    return instance.logger;
	}
}
