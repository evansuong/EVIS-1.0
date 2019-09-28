package evis.effects.foreground;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.ForegroundEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class Whiteout extends ForegroundEffect{
	
	private Color white;
	private int alpha = 255;
	private int speed = 6;
	
	public Whiteout (Driver driver, ImageLoader il,
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
		name = "Whiteout";
	}
	
	public void init () {
		alpha = 255;
	}
	
	public void update () {
				
		alpha = alpha - speed;		
		
		if (alpha <= 0) {
			alpha = 0;
			close();
		}
		
		white = new Color(255, 255, 255, alpha);
		
		printName();
	}
	
	public void render (Graphics g) {
		g.setColor(white);
	    g.fillRect(0, 0, driver.getWidth(), driver.getHeight()); 
	}
}
