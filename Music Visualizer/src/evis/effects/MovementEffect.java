package evis.effects;

import java.awt.image.BufferedImage;

import evis.Driver;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public abstract class MovementEffect extends Effect {

	public MovementEffect(Driver driver, ImageLoader il, NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
	}

}
