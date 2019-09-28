package evis.effects.background;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.BackgroundEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class Blackout extends BackgroundEffect {
	
	private Driver driver;
	private Color black;
	private int alpha = 255;
	private boolean lighten;
	
	public Blackout (Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image) {
		super(driver, il, ng, image);
		this.driver = driver;
		name = "blackout";
	}
	
	public void init () {
		lighten = true;
	}
	
	public void update () {
		if (lighten) {
			alpha = alpha - 85;
			if (alpha <= -85) {
				lighten = false;
			}
		} else {
			if (alpha < 185) {
				alpha = alpha + 85;
			} else {
				alpha = 255;
			}
		}
		if (alpha > 255) {
			black = new Color(0, 0, 0, 255);
		} else if (alpha <= 0) {
			black = new Color(0, 0, 0, 0);
		} else {
			black = new Color(0, 0, 0, alpha);
		}
		
		//printName();
	}
	
	public void render (Graphics g) {		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(black);
	    g2d.drawRect(0, 0, driver.getWindowWidth(), driver.getWindowHeight());
	    g2d.fillRect(0, 0, driver.getWindowWidth(), driver.getWindowHeight());
	}
	
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
}
