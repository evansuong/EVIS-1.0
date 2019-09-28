package evis.effects.foreground;

import java.awt.Graphics;

import evis.Driver;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class Bubble {
	
	private static final int MAXSPEED = 4;
	private int height, width;
	
	private int x, y;
	private double xspeed = 0;
	private double yspeed = 0;
	private double xacc = 0;
	private double yacc = 0;	
	
	
	private NumberGenerator ng;
	private Driver driver;
	private ImageLoader il;

	public Bubble(Driver driver, ImageLoader il, 
					NumberGenerator ng) {
		this.ng = ng;
		this.driver = driver;
		this.il = il;
		
		height = driver.getWindowHeight() / 20;
		width = height;
		init();
	}

	public void init() {
		 x = ng.getRandomNumber(driver.getWindowWidth());
		 y = ng.getRandomNumber(driver.getWindowHeight());
	}

	public void update() {
	
		// get random acceleration of bubble
		xacc = xacc + ng.getRandomDouble() - .5;
		yacc = yacc + ng.getRandomDouble() - .5;
					
		// alter speed based on acceleration
		xspeed = xspeed + xacc;
		yspeed = yspeed + yacc;
		
		// ensure xspeed doesn't go too high
		if (xspeed > MAXSPEED) {
			xspeed = MAXSPEED;
		} else if (xspeed < -MAXSPEED) {
			xspeed = -MAXSPEED;
		}
		
		// ensure yspeed doesn't go too high
		if (yspeed > MAXSPEED) {
			yspeed = MAXSPEED;
		} else if (yspeed < -MAXSPEED) {
			yspeed = -MAXSPEED;
		}
		
		// ensure bubble stays within bounds	
		if (x > driver.getWindowWidth() || x < 0) {
			xspeed = -xspeed;
			xacc = 0;
		}	
		if (y > driver.getWindowHeight() || y < 0) {
			yspeed = -yspeed;
			yacc = 0;
		}
		
		// change x and y positions
		x = x + (int) xspeed;
		y = y + (int) yspeed;
	}
	
	public void render (Graphics g) {
		g.drawImage(il.getBubble(), x, y, height, width, null);
	}
}
