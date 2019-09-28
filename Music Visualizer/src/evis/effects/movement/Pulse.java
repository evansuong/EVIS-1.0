package evis.effects.movement;

import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.BackgroundEffect;
import evis.effects.MovementEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class Pulse extends MovementEffect {
	
	private boolean increasing;
	
	private int maxCount = 3;
	private int count; 
	private int widthInc, heightInc;
	private int widthShift, heightShift;
	
	private boolean changing;

	public Pulse(Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
		// set the amount to change dims
		widthInc = driver.getWidth() / 4;
		heightInc = driver.getHeight() / 4;
		
		// set the shift with each zoom
		widthShift = widthInc / 2;
		heightShift = heightInc / 2;
	}

	@Override
	public void init() {
		increasing = true;
		
		driver.incWidth(widthInc, true);
		driver.incHeight(heightInc, true);
		
		driver.addx(widthShift, false);
		driver.addy(heightShift, false);
		count = 0;
	}

	@Override
	public void update() {
		
		width = driver.getWidth();
		height = driver.getHeight();
		
		System.out.println(width);
		
		// check whether or not we increase or decrease
		if (count >= maxCount) {
			if (!increasing) {
				changing = false;
			}
			increasing = !increasing;
			count = 0;
		} else {
			count++;
		}
		
		// alter the size
		driver.incWidth(widthInc, increasing);
		driver.incHeight(heightInc, increasing);
		
		// alter the location
		driver.addx(widthShift, !increasing);
		driver.addy(heightShift, !increasing);
		
	}
	
	@Override
	public boolean close () {
		
		System.out.println(count);
		
		driver.setx(0);
		driver.sety(0);
		
		driver.setWidth(driver.getWindowWidth());
		driver.setHeight(driver.getWindowHeight());
		
		
		running = false;
		return true;
	}
}
