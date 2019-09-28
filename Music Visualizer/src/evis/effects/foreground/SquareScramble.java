package evis.effects.foreground;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import evis.Driver;
import evis.effects.ForegroundEffect;
import evis.effects.foreground.grid.Cell;
import evis.effects.foreground.grid.MyGrid;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;

public class SquareScramble extends ForegroundEffect {

	// grid
	private Cell[] cells;
	private int centerCell;
	
	// 
	private int counter;
	
	// cells
	private int upCell, downCell, rightCell, leftCell;
	private int cellCount;
	
	// myGrid
	private MyGrid myGrid;
	
	public SquareScramble(Driver driver, ImageLoader il, 
			NumberGenerator ng, BufferedImage image, MyGrid myGrid) {
		
		super(driver, il, ng, image);
		
		this.myGrid = myGrid;
		
		cellCount = myGrid.getCellCount();
		centerCell = (cellCount - 1) / 2;
		
		cells = myGrid.getGrid();
		name = "Square Scramble";
	}

	

	@Override
	public void init() {
		 cells[centerCell].turnOn();
		 cells[centerCell].toRender();
		 counter = 0;
	}

	@Override
	public void update() {
		
		if (counter % 2 == 1) {
			for (int i = 0; i < cellCount; i++) {
				if (cells[i].isOn()) {		
					cells[cells[i].getUpCell()].toRender();
					cells[cells[i].getDownCell()].toRender();
					cells[i].turnOff();
				}
			}
		} else {
			for (int i = 0; i < cellCount; i++) {
				if (cells[i].isOn()) {
					cells[cells[i].getLeftCell()].toRender();
					cells[cells[i].getRightCell()].toRender();
					cells[i].turnOff();
				}
			}
		}
		printName();
		counter++;
		
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
		running = false;
		return true;
	}
}
