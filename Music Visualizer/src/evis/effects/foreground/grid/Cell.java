package evis.effects.foreground.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import evis.loaders.ImageLoader;

public class Cell {
	
	private int width, height;
	private int cellRow, cellCol;
	private int cellNum;
	private int x, y;
	private int upCell, downCell, rightCell, leftCell;
	private boolean on = false;
	private boolean toRender = false;
	private ImageLoader il;
	
	
	public Cell(int width, int height, int rows, int cols, int cellNum, ImageLoader il) {
		
		this.width = width;
		this.height = height;
		
		this.cellRow = cellNum / rows;
		this.cellCol = cellNum % cols;
		
		// find the surrounding cells
		if (cellNum % cols > 0) {
			leftCell = cellNum - 1; 
		}
		if (cellNum % cols < cols - 1) {
			rightCell = cellNum + 1;
		}
		if (cellNum >= rows) {
			upCell = cellNum - cols;
		}
		if (cellNum <= rows * (cols - 1) - 1) {
			downCell = cellNum + cols;
		}
		
		// get coordinates
		x = cellCol * width;
		y = cellRow * height;
		
		this.il = il;
	}
	
	public void render (Graphics g, boolean renderImage, BufferedImage image) {
		toRender = false;
		on = true;
		
		if (renderImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height);
		}
	}

	public int getCellRow() {
		return cellRow;
	}

	public int getCellCol() {
		return cellCol;
	}

	public int getCellNum() {
		return cellNum;
	}

	public int getUpCell() {
		return upCell;
	}

	public int getDownCell() {
		return downCell;
	}

	public int getRightCell() {
		return rightCell;
	}

	public int getLeftCell() {
		return leftCell;
	}

	public boolean isOn() {
		return on;
	}
	
	public void turnOn() {
		this.on = true;
	}
	
	public void turnOff() {
		this.on = false;
	}
	
	public void toRender() {
		toRender = !toRender;
	}
	
	public boolean isToRender() {
		return toRender;
	}
	
	public void turnOffRender () {
		toRender = false;
	}
}