package evis.effects.foreground;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.ForegroundEffect;
import evis.effects.foreground.grid.MyGrid;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class RandomRectangle extends ForegroundEffect {
	
	// grid object
	private MyGrid myGrid;
	
	private static final int CELLCOUNT = 30;
	
	// random cell to be rendered
	private int[] cells;
	
	// random row and column
	private int randomRow, randomCol;

	public RandomRectangle(Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image, MyGrid myGrid) {
		super(driver, il, ng, image);

		cells = new int[CELLCOUNT];
		//this.image = il.getLight();
		this.myGrid = myGrid;
	}

	// renders the cell 
	@Override
	public void render(Graphics g) {
		for (int i = 0; i < CELLCOUNT; i++) {
			myGrid.render(g, cells[i], false, null);
		}
	}

	
	// updates the cell number
	@Override
	public void update() {
		
		// find a random row and column to be rendered
		for (int i = 0; i < CELLCOUNT; i++) {
			cells[i] = ng.getRandomNumber(myGrid.getCellCount());
		}
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
