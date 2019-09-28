package evis.effects.movement;

import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.BackgroundEffect;
import evis.effects.MovementEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class Earthquake extends MovementEffect {
	
	// offsets
	private int xoffset, yoffset;
	
	// max distance the image can be offset
	private static final int ZOOM = 80;
	private static final int DEFAULTOFFSET = ZOOM / 4;
	private static final int SHIFT = ZOOM / 2;
	private static final int BOUNDARY = DEFAULTOFFSET / 2;
	
	/**
	 * constructor
	 * 
	 * @param driver gives access to private data
	 * @param il gives access to image
	 * @param numGen produces the x and y offset values
	 */
	public Earthquake(Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
		name = "earthquake";	
	}
	
	@Override
	public void run () {
		setZoom(false);
		setCoordinates(false);
		running = true;
	}
	
	@Override
	public void init () {}

	@Override
	public void update() {
		
		// have the movement take place here
		xoffset = ng.getRandomNumber(DEFAULTOFFSET);
		yoffset = ng.getRandomNumber(DEFAULTOFFSET);
		
		// checks to see if image is move in + or - directions
		if (xoffset > BOUNDARY) {
			xoffset = -xoffset;
		} else if (yoffset > BOUNDARY) {
			yoffset = -yoffset;
		}
		
		// sets new coordinates for the image
		setCoordinates(false);
	}
	
	//ALLOW EARTHQUAKE AND SCREENSAVER TO RUN TOGETHER
	 
	/**
	 * resets the x and y offsets to 0 before changing the image
	 */
	public boolean close () {
		System.out.println("closing image...");
		setZoom(true);
		setCoordinates(true);
		running = false;
		return true;
	}
	
	private void setCoordinates (boolean closing) {
		if (closing) {
			driver.setx(0);
			driver.sety(0);
		} else {
			driver.setx(xoffset - SHIFT);
			driver.sety(yoffset - SHIFT);
		}
	}
	
	public void setZoom (boolean closing) {
		
		if(closing) {
			driver.setWidth(driver.getWindowWidth());
			driver.setHeight(driver.getWindowHeight());
		} else {
			driver.setWidth(driver.getWindowWidth() + ZOOM);
			driver.setHeight(driver.getWindowHeight() + ZOOM);
		}
	}
}
