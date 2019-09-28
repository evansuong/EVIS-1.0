package evis.effects.movement;

import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.BackgroundEffect;
import evis.effects.MovementEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class Screensaver extends MovementEffect {
	
	private int xshift, yshift;
	private int xcount, ycount;
	private int xzoom, yzoom;
	private int xmove, ymove;
	private int xoffset, yoffset;
	
	private boolean incrementX = false;
	private boolean incrementY = false;

	public Screensaver(Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
		
		// zoom amount
		xzoom = driver.getWindowWidth() / 6;
		yzoom = driver.getWindowHeight() / 6;
		
		// amount of updates before redirect
		xcount = driver.getWindowWidth() / 30;
		ycount = driver.getWindowHeight() / 30;
		
		// shift to keep image centered post zoom
		xshift = xzoom / 2;
		yshift = yzoom / 2;
		
		// amount image will move with each update
		xmove = 2;
		ymove = 2;
		
		xoffset = 0;
		yoffset = 0;
	}

	@Override
	public void init() {}
	
	@Override
	public void run () {
		setZoom(false);
		setCoordinates(false);
		running = true;
	}
	
	/**
	 * resets the x and y offsets to 0 before changing the image
	 */
	public boolean close () {
		System.out.println("closing image...");
		
		xoffset = 0;
		yoffset = 0;
		
		setZoom(true);
		setCoordinates(true);
		running = false;
		return true;
	}
  
	@Override
	public void update() {
		
		x = driver.getx();
		y = driver.gety();

		// ensure x stays within the boundary
		if (xoffset >= xcount) {
			incrementX = !incrementX;
			xoffset = -xcount;
		} else {
			xoffset++;
		}
		
		// ensure y stays within the boundary
		if (yoffset >= ycount) {
			incrementY = !incrementY; 
			yoffset = -ycount;
		} else {
			yoffset++;
		}
		
		System.out.println(xoffset);
		
		// set the new coordinates
		driver.addx(xmove, incrementX);
		driver.addy(ymove, incrementY);
	}
	
	private void setCoordinates (boolean closing) {
		if (closing) {
			driver.setx(0);
			driver.sety(0);
		} else {
			driver.addx(xshift, false);
			driver.addy(yshift, false);
		}
	}
	
	public void setZoom (boolean closing) {
		
		if(closing) {
			driver.incWidth(xzoom, false);
			driver.incHeight(yzoom, false);
			driver.setx(0);
			driver.sety(0);
		} else {
			driver.incWidth(xzoom, true);
			driver.incHeight(yzoom, true);
		}
	}
}
