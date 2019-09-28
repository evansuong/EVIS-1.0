package evis.effects.background;

import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.BackgroundEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class Invert extends BackgroundEffect {
	
	// check if image is inverted
	private boolean inverted = false;

	/**
	 * ctor
	 * @param driver gives access to private data fields
	 * @param image 
	 */
	public Invert(Driver driver, ImageLoader il,
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
		name = "invert";
	}

	@Override
	public void update() {
		
		if (inverted) {
			il.invertBack();
			inverted = false;
		} else {
			il.invertImage();
			inverted = true;
		}
	
		printName();
	}
	
	/**
	 * waits until image is done inverting before closing image
	 */
	@Override
	public boolean close () {
		il.ogImage();
		image = il.getCurrentImage();
		running = false;
		return true;
	}

	@Override
	public void init() {}
}
