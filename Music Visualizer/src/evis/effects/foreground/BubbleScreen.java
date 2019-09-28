package evis.effects.foreground;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.ForegroundEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class BubbleScreen extends ForegroundEffect {
	
	private static final int BUBBLECOUNT = 30;
	private Bubble[] bubbles;
	
	
	public BubbleScreen(Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
	}

	private void populateBubbles () {
		for (int i = 0; i < BUBBLECOUNT; i++) {
			bubbles[i] = new Bubble(driver, il, ng);
		}
	}
	
	public void render (Graphics g) {
		for (int i = 0; i < BUBBLECOUNT; i++) {
			bubbles[i].render(g);
		}
	}

	@Override
	public void init() {
		bubbles = new Bubble[BUBBLECOUNT];
		populateBubbles();
	} 

	@Override
	public void update() {
		for (int i = 0; i < BUBBLECOUNT; i++) {
			bubbles[i].update();
		}
	}
}
