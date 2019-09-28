package evis.effects;

import java.awt.image.BufferedImage;

import evis.Driver;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public abstract class Effect {
	
	protected Driver driver;
	protected int x, y = 0;
	protected int width, height;
	protected int arrayIndex;
	protected boolean running = false;
	protected String name;
	protected NumberGenerator ng;
	protected ImageLoader il;
	protected BufferedImage image;
	
	public Effect (Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image) {
		this.width = driver.getWindowWidth();
		this.height = driver.getWindowHeight();
		this.driver = driver;
		this.image = image;
		this.il = il;
		this.ng = ng;
	}
	
	public abstract void init();
	public abstract void update();	
	
	// prints name of effect for debugging
	public void printName () {
		System.out.println(name);
	}
	
	// turns the effect on
	public void run () {
		init();
		running = true;
	}
	
	// checks if effect is running
	public boolean running () {
		return running;
	}
	
	// closes the effect and returns true when done
	public boolean close () {
		System.out.println("closing");
		running = false;
		return true;
	}
	
	public boolean getRunning () {
		return running;
	}
}
