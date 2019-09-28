package evis.effects.foreground;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.ForegroundEffect;
import evis.effects.foreground.grid.Cell;
import evis.effects.foreground.grid.MyGrid;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class DiamondScramble extends ForegroundEffect {

	// grid
	private Cell[] cells;
	private int centerCell;
	
	// cells
	private int upCell, downCell, rightCell, leftCell;
	private int cellCount;
	
	// myGrid
	private MyGrid myGrid;
	private ImageLoader il;
	
	public DiamondScramble (Driver driver, ImageLoader il, NumberGenerator ng,
			 BufferedImage image, MyGrid myGrid) {
		
		super(driver, il, ng, image);
		
		this.myGrid = myGrid;
		this.il = il;
		
		cellCount = myGrid.getCellCount();
		centerCell = (cellCount - 1) / 2;
		
		cells = myGrid.getGrid();
		name = "diamondScramble";
	}

	

	@Override
	public void init() {
		 cells[centerCell].turnOn();
		 cells[centerCell].toRender();
		 
		 image = il.getLightning();
	}

	@Override
	public void update() {
		for (int i = 0; i < cellCount; i++) {
			if (cells[i].isOn()) {
				cells[cells[i].getUpCell()].toRender();
				cells[cells[i].getDownCell()].toRender();
				cells[cells[i].getLeftCell()].toRender();
				cells[cells[i].getRightCell()].toRender();
				cells[i].turnOff();
			}
		}
		printName();
	}
	
	@Override
	public void render(Graphics g) {
		for (int i = 0; i < cellCount; i++) {
			if (cells[i].isToRender()) {
				cells[i].render(g, false, null);
			}
		}
	}
	
	@Override
	public boolean close () {
		for (int i = 0; i < cellCount; i++) {
			cells[i].turnOff();
			cells[i].turnOffRender();
		}
		System.out.println("here");
		running = false;
		return true;
	}
}
