package rome.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Enables fetching of a raw JSON from a URL address.
 * 
 * @author Craig Walker
 * @version 1.1
 * @since 3/12/2016
 *
 */
public class JSONService {
	
	/**
	 * size of the buffer used to read in data
	 */
	private static final int MAXSIZE = 1024;
	
	/**
	 * Fetches a raw JSON from a given URL.
	 * 
	 * @param requestUrl the URL of the request
	 * 
	 * @return a raw JSON containing the results
	 * @throws IOException
	 *     if an I/O error occurs
	 */
	public String fetch(String requestUrl) throws IOException {
		
		// connect to the provided URL
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStreamReader in = new InputStreamReader(conn.getInputStream());
		
		// read the json in as a String
		String rawJson = readJsonToString(in);
		conn.disconnect();
		
		return rawJson;
	}
	
	/**
	 * Converts a raw JSON string to a JSONObject.
	 * 
	 * @param rawJson a raw JSON string
	 * 
	 * @return the JSONObject created from the raw JSON string
	 * @throws ParseException
	 *     if a parsing error occurs in the conversion
	 */
	public JSONObject convert(String rawJson) throws ParseException {
		return (JSONObject) new JSONParser().parse(rawJson);
	}

	/**
	 * Reads in and returns a JSON string representation.
	 * 
	 * @param in an input stream reader
	 * 
	 * @return a JSON string representation
	 * @throws IOException
	 *      if an I/O error occurs
	 */
	private String readJsonToString(InputStreamReader in) throws IOException {
		StringBuilder sb = new StringBuilder();
		int bytesRead;
		char[] buf = new char[MAXSIZE];
		
		while ((bytesRead = in.read(buf)) != -1) {  // append json
			sb.append(buf, 0, bytesRead);
		}
		
		return sb.toString();                       // return as a string
	}
}
