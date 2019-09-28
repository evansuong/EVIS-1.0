package evis;

import javax.swing.JFrame;

public class Launcher {
	
	public static final String TITLE = "EVIS 1.0";
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;	
	public static void main (String[] args) {
		Driver driver = new Driver(TITLE, WIDTH, HEIGHT);
		driver.start();
	}
	
}

