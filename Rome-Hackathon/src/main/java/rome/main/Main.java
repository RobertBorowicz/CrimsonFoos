package rome.main;

public class Main {
	
	/*
	 * Runs the application
	 * @param args command-line parameteres
	 */
	public static void main(String[] args) {
		System.setProperty("environment", "dev");
		Application.run(args);
	}
}