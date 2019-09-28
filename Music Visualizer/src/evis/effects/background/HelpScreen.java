package evis.effects.background;

import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.BackgroundEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class HelpScreen extends BackgroundEffect {

	public HelpScreen(Driver driver, ImageLoader il, NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		il.setHelpScreen();
	}
	
	public boolean close () {
		il.ogImage();
		return true;
	}

}
