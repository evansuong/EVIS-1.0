package evis.effects.foreground.grid;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.ForegroundEffect;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class MyGrid {
	
	private static final int ROWS = 65;
	private static final int COLS = 65;
	private int cellCount = ROWS * COLS;
	private int cellWidth, cellHeight;
	
	private Cell[] grid;
	private Driver driver;
	private ImageLoader il;
	private BufferedImage light;
	
	public MyGrid(Driver driver, NumberGenerator ng, ImageLoader il) {
		
		cellWidth = driver.getWidth() / COLS;
		cellHeight = driver.getHeight() / (ROWS - 2);
		
		grid = new Cell[cellCount];
		
		this.il = il;
		this.driver = driver;
		
		buildGrid();
	}
	
	private void buildGrid () {
		for (int i = 0; i < cellCount; i++) {
			grid[i] = new Cell(cellWidth, cellHeight, ROWS, COLS, i, il);
		}
	}
	
	public void render (Graphics g, int cellNum, boolean renderImage, BufferedImage image) {
		grid[cellNum].render(g, renderImage, image);
	}
	
	public int getCellCount () {
		return cellCount;
	}
	
	public Cell[] getGrid () {
		return grid;
	}
	
	public int getRowCount () {
		return ROWS;
	}
	
	public int getColCount () {
		return COLS;
	}
}
