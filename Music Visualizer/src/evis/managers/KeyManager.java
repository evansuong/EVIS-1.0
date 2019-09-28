package evis.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import evis.Driver;
import evis.effects.background.Blackout;

public class KeyManager implements KeyListener {
	
	// array of which effects are running
	private boolean[] effects;
	private boolean disabled = false;
	
	// checks if program is in whiteout and blackout num
	private boolean whiteout, blackout;
	
	//driver object
	private Driver driver;
	
	// holds the image number
	private int imageNum;
	
	public KeyManager (Driver driver) {
		effects = new boolean[64];
		this.driver = driver;
	}

	public boolean isWhite () {
		return disabled;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!disabled) {
			switch (e.getKeyCode()) {
		
			
			// background effects
			case KeyEvent.VK_A:
				driver.runEffect(Driver.INVERT_OFFSET, Driver.BACKGROUND_EFFECT);
				break;
			
			case KeyEvent.VK_S:
				driver.runEffect(Driver.RAINBOW_OFFSET, Driver.BACKGROUND_EFFECT);
				break;
				
			case KeyEvent.VK_D:
				driver.showColor(0);
				break;
			
			case KeyEvent.VK_F:
				driver.showColor(1);
				break;
				
			case KeyEvent.VK_G:
				driver.showColor(2);
				break;
				
			case KeyEvent.VK_H:
				driver.showColor(3);
				break;
			
			case KeyEvent.VK_J:
				driver.showColor(4);
				break;
				
			case KeyEvent.VK_K:
				driver.showColor(5);
				break;
				
				
				
			// foreground effects
				
			case KeyEvent.VK_Z:
				driver.runEffect(Driver.WHITEOUT_OFFSET, Driver.FOREGROUND_EFFECT);
				break;
				
			case KeyEvent.VK_X:
				driver.runEffect(Driver.BUBBLESCREEN_OFFSET, Driver.FOREGROUND_EFFECT);
				break;
				
			case KeyEvent.VK_C:
				driver.runEffect(Driver.RANDOMRECT_OFFSET, Driver.FOREGROUND_EFFECT);
				break;
				
			case KeyEvent.VK_V:
				driver.runEffect(Driver.DIAMOND_OFFSET, Driver.FOREGROUND_EFFECT);
				break;
				
			case KeyEvent.VK_B:
				driver.runEffect(Driver.SQUARE_OFFSET, Driver.FOREGROUND_EFFECT);
				break;
				
			
				
			// foreground effects
			case KeyEvent.VK_Q:
				driver.runEffect(Driver.EARTHQUAKE_OFFSET, Driver.MOVEMENT_EFFECT);
				break;
				
			case KeyEvent.VK_W:
				driver.runEffect(Driver.PULSE_OFFSET, Driver.MOVEMENT_EFFECT);
				break;
				
			case KeyEvent.VK_E:
				driver.runEffect(Driver.SCREENSAVER_OFFSET, Driver.MOVEMENT_EFFECT);
				break;
			
				
				
			// close effects
			case KeyEvent.VK_N:
				driver.closeAll();
				break;
		
			
		
			// fps manipulations
			case KeyEvent.VK_1:
				driver.setfps(1);
				break;
			
			case KeyEvent.VK_2:
				driver.setfps(2);
				break;
				
			case KeyEvent.VK_3:
				driver.setfps(3);
				break;
				
			case KeyEvent.VK_4:
				driver.setfps(4);
				break;
				
			case KeyEvent.VK_5:
				driver.setfps(6);
				break;
				
			case KeyEvent.VK_6:
				driver.setfps(8);
				break;
				
			case KeyEvent.VK_7:
				driver.setfps(10);
				break;
				
			case KeyEvent.VK_8:
				driver.setfps(15);
				break;
				
			case KeyEvent.VK_9:
				driver.setfps(20);
				break;
			
			case KeyEvent.VK_0:
				driver.setfps(25);
				break;
			
			case KeyEvent.VK_SHIFT:
				driver.resetfps();
				break;
			
				
			
			// image changers
			case KeyEvent.VK_UP:
				imageNum++;
				driver.changeImage(true);
				break;
			
			case KeyEvent.VK_DOWN:
				imageNum--;
				driver.changeImage(false);
				break;
		
				
			// close	
			case KeyEvent.VK_BACK_SPACE:
				driver.exit();
				break;
				
			// help screen
			case KeyEvent.VK_P:
				driver.runEffect(Driver.HELPSCREEN_OFFSET, Driver.BACKGROUND_EFFECT);
				break; 
			}
		}
	}
	
	/**
	 * returns if invert is live or not
	 * @return invert status
	 */
	public boolean getStatus () {
		return effects[KeyEvent.VK_I];
	}
	
	/**
	 * returns in earthquake is live or not
	 * @return earthquake status
	 */
	public boolean getEarthquakeStatus () {
		return effects[KeyEvent.VK_E];
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Z:
				//driver.closeEffect(Driver.MOVEMENT_EFFECT);
		}
	}

	public boolean getWhiteout () {
		return whiteout;
	}
	
	public boolean getBlackout () {
		return blackout;
	}
	
	public void setDisabled (boolean disabled) {
		this.disabled = disabled;
	}
}
