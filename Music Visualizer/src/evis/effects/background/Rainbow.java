package evis.effects.background;

import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.BackgroundEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class Rainbow extends BackgroundEffect {
	
	// holds color indexes
	private int colorIndex;
	private int lastNum;

	public Rainbow(Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
		name = "rainbow";
	}

	@Override
	public void update() {

		// generate a random color index
		colorIndex = ng.getRandomNumber(6);
		
		// check if color is the same as last color
		if (colorIndex == lastNum) {
			colorIndex++;
		}
		
		// update last index
		lastNum = colorIndex;
		
		driver.showColor(colorIndex);
		image = il.getCurrentImage();
		//System.out.println(colorIndex);
		//printName();
	}

	public boolean close () {
		
		// set image back to original
		il.ogImage();
		
		running = false;
		return true;
	}

	@Override
	public void init() {}
}
