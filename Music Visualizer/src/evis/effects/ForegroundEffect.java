package evis.effects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import evis.Driver;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public abstract class ForegroundEffect extends Effect {

	public ForegroundEffect(Driver driver, ImageLoader il,
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
	}
	
	public abstract void render (Graphics g);

}
