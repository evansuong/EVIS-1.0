package evis.effects.background;

import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.BackgroundEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class None extends BackgroundEffect{
	
	private boolean running = false;

	public None(Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
		name = "none";
	}
	
	@Override
	public void init () {
		image = il.getCurrentImage();
	}

	@Override
	public void update() {
		printName();
		il.ogImage();
		image = il.getCurrentImage();
	}
}
