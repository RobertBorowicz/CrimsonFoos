package rome.loggers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Abstract custom logger
 * 
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 */
public abstract class ProjectLogger {
	
	/**
	 * local directory for logs
	 */
	private static final String DIRECTORY = "logs/";
	/**
	 * its logger
	 */
	protected final Logger logger;
	
	/**
     * Creates a new ProjectLogger with the given input
     * 
     * @param loggerName the name of the logger
     * @param fileName the name of the file to write to
     * 
     * @return a configured Logger
     */
    protected ProjectLogger(String loggerName, String fileName) {
    	
        // get a Logger with the given logger name
    	Logger logger = null;
        try {
            logger = Logger.getLogger(loggerName);
        	
            // create a FileHandler with the given file name
            FileHandler fh = new FileHandler(DIRECTORY + fileName); 
            
            fh.setFormatter(new SimpleFormatter()); // use a simple formatter
            logger.addHandler(fh);                  // add the FileHandler
            logger.setUseParentHandlers(false);     // disable logging to stderr
            logger.setLevel(Level.ALL);             // log all levels
            
        } catch (NullPointerException ex) {             // print error
            System.err.println("Logger error: Name is null");
            
        } catch (SecurityException | IOException ex) {  // print error
            System.err.println("Logger error: " + ex.getMessage());
        }
        
        this.logger = logger;
    }
}
