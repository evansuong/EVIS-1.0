package evis;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import evis.effects.BackgroundEffect;
import evis.effects.Effect;
import evis.effects.ForegroundEffect;
import evis.effects.MovementEffect;
import evis.effects.background.Blackout;
import evis.effects.background.HelpScreen;
import evis.effects.background.Invert;
import evis.effects.background.None;
import evis.effects.background.Rainbow;
import evis.effects.foreground.BubbleScreen;
import evis.effects.foreground.DiamondScramble;
import evis.effects.foreground.RandomRectangle;
import evis.effects.foreground.SquareScramble;
import evis.effects.foreground.Whiteout;
import evis.effects.foreground.grid.MyGrid;
import evis.effects.movement.Earthquake;
import evis.effects.movement.Pulse;
import evis.effects.movement.Screensaver;
import evis.generators.NumberGenerator;
import evis.loaders.ImageLoader;
import evis.managers.KeyManager;

public class Driver implements Runnable {
	
	// Window variables
	private int width, height;
	private String title;
	
	// graphics objects
	private BufferStrategy bs;
	private Graphics g;
	
	// indicates if the program loop should be running
	private boolean running;
	
	// game run
	private Thread thread;
	private Window window;
	
	// input manager
	private KeyManager km;
	
	// number generator
	NumberGenerator ng;
	
	// Images
	private BufferedImage image;
	private ImageLoader il;
		
	// amount of times looped before update and render
	private static final int BASEFPS = 20;
	private int fps = BASEFPS;
	
	// my grid
	private MyGrid myGrid;
	
	// Effects
	private Effect effect;	// the effect object itself
	private BackgroundEffect invert, none, 
				   rainbow, blackout, helpScreen;
	private ForegroundEffect bubbleScreen, randomRect,
							diamondScramble, squareScramble,
							whiteout;
	private MovementEffect earthquake, screenSaver, pulse;
	
	// effects array and index offsets
	private BackgroundEffect backgroundEffects[];
	private ForegroundEffect foregroundEffects[];
	private MovementEffect movementEffects[];
	private Effect effects[];
	
	// array indexes
	// effects 
	public static final int EFFECTS_NUM = 3;
	public static final int BACKGROUND_EFFECT = 0;
	public static final int FOREGROUND_EFFECT = 1;
	public static final int MOVEMENT_EFFECT = 2;
	
	
	// background effects
	public static final int BACKGROUND_EFFECTS_NUM = 3;
	public static final int RAINBOW_OFFSET = 0;
	public static final int INVERT_OFFSET = 1; 
	public static final int HELPSCREEN_OFFSET = 2;
	
	// foreground effects
	public static final int FOREGROUND_EFFECTS_NUM = 5;
	public static final int WHITEOUT_OFFSET = 0;
	public static final int BUBBLESCREEN_OFFSET = 1;
	public static final int RANDOMRECT_OFFSET = 2;
	public static final int DIAMOND_OFFSET = 3;
	public static final int SQUARE_OFFSET = 4;
	
	// movement effects
	public static final int MOVEMENT_EFFECTS_NUM = 3;
	public static final int EARTHQUAKE_OFFSET = 0;
	public static final int PULSE_OFFSET = 1;
	public static final int SCREENSAVER_OFFSET = 2;
	
	// image coordinates
	private int x, y = 0;
	private int savedx, savedy = 0;
	
/* methods ------------------------------------------------*/
	
	/**
	 * ctor for Driver initializes elements 
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
	public Driver (String title, int width, int height) {
		
		// build a new window
		this.width = width;
		this.height = height;
		this.title = title;
		window = new Window(title, width, height);
		
		km = new KeyManager(this);		
		
	}
	
	/**
	 * initialize threads and add key manager to the window
	 */
	private void init () {
		
		window.getFrame().addKeyListener(km);
		
		// build new ImageLoader
		il = new ImageLoader();
		ng = new NumberGenerator();
				
		// set the image
		image = il.getCurrentImage();
		
		// initialize grid objects
		myGrid = new MyGrid(this, ng, il);
		
		// initialize background effects
		none = new None(this, il, ng, image);
		invert = new Invert(this, il, ng, image);	
		rainbow = new Rainbow(this, il, ng, image);
		blackout = new Blackout(this, il, ng, image);
		helpScreen = new HelpScreen(this, il, ng, image);
		
		// initialize foreground effects
		bubbleScreen = new BubbleScreen(this, il, ng, image);
		randomRect = new RandomRectangle(this, il, ng, image, myGrid);
		diamondScramble = new DiamondScramble(this, il, ng, image, myGrid);
		squareScramble = new SquareScramble(this, il, ng, image, myGrid);
		whiteout = new Whiteout(this, il, ng, image);
		
		// initialize movement effects
		earthquake = new Earthquake(this, il, ng, image);
		pulse = new Pulse(this, il, ng, image); 
		screenSaver = new Screensaver(this, il, ng, image);

		
		
		// populate effects array
		backgroundEffects = new BackgroundEffect[BACKGROUND_EFFECTS_NUM];
		foregroundEffects = new ForegroundEffect[FOREGROUND_EFFECTS_NUM];
		movementEffects = new MovementEffect[MOVEMENT_EFFECTS_NUM];
		effects = new Effect[EFFECTS_NUM];
		
		populateArrays();
		bubbleScreen.init();
		
	}
	
	/**
	 * the main program loop
	 */
	public void run() {
		init();
		
		// timer variables
		double updateTick = 0;
		double renderTick = 0;
		double updateTimeChange = 0;
		double renderTimeChange = 0;
		long currentTime;
		long lastTime = System.nanoTime();
		long timer = 0;

		while (running) {
			
			// timer algorithm
			updateTick = 1_000_000_000 / fps;
			renderTick = 1_000_000_000 / 20;
			currentTime = System.nanoTime();
			updateTimeChange += currentTime - lastTime;
			renderTimeChange += currentTime - lastTime;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			
			if(updateTimeChange >= updateTick) {
				update();
				updateTimeChange = 0;
			}
			
			if(renderTimeChange >= renderTick) {
				
				// bubblescreen is not affected by change in fps
				bubbleScreen.update();
				
				render();
				renderTimeChange = 0;
			}
			if(timer >= 1_000_000_000) {
				//System.out.println(ticks);
				timer = 0;
			}
		}
		stop();
	}
	
	
	
	/**
	 * updates the rest of the program 
	 */
	private void update () {
		
		// update all running effect
		for (int i = 0; i < EFFECTS_NUM; i++) {
			if (effects[i].running()) {
				effects[i].update();
			}
		}
	}
	
	

	/**
	 * renders each component of the program
	 */
	private void render () {
		
		// check if the canvas has a buffer strategy
		bs = window.getCanvas().getBufferStrategy();
		if (bs == null) {
			
			// create a buffer strategy if there isn't one
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		
		// initialize the graphics object
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		// render the background image
		g.drawImage(il.getCurrentImage(), x, y, width, height, null);
		
		// render the overlaying image
		renderForeground(g);
		
		// bring the new buffer to the front
		bs.show();
		g.dispose(); 
	}
	
/* helper methods ----------------------------------------*/
	
	// renders the foreground
	private void renderForeground (Graphics g) {
		for (int i = 0; i < FOREGROUND_EFFECTS_NUM; i++) {
			if (foregroundEffects[i].running()) {
				foregroundEffects[i].render(g);
			}
		}
	}
	
	// populates the effects arrays
	private void populateArrays () {
		
		// background effects
		backgroundEffects[RAINBOW_OFFSET] = rainbow;
		backgroundEffects[INVERT_OFFSET] = invert;
		backgroundEffects[HELPSCREEN_OFFSET] = helpScreen;
		
		// foreground effects
		foregroundEffects[WHITEOUT_OFFSET] = whiteout;
		foregroundEffects[BUBBLESCREEN_OFFSET] = bubbleScreen;
		foregroundEffects[RANDOMRECT_OFFSET] = randomRect;
		foregroundEffects[DIAMOND_OFFSET] = diamondScramble;
		foregroundEffects[SQUARE_OFFSET] = squareScramble;
		
		// movement effects
		movementEffects[EARTHQUAKE_OFFSET] = earthquake;
		movementEffects[PULSE_OFFSET] = pulse;
		movementEffects[SCREENSAVER_OFFSET] = screenSaver;
		
		// effects
		effects[BACKGROUND_EFFECT] = none;
		effects[MOVEMENT_EFFECT] = none;
		effects[FOREGROUND_EFFECT] = none;
		
	}
	
	// closes all running effects
	public boolean closeAll () {
		
		// close all running effects
		System.out.println("closing all");
		
		// goes through all background effects to see if running
		for (int i = 0; i < BACKGROUND_EFFECTS_NUM; i++) {
			if (backgroundEffects[i].getRunning()) {
				backgroundEffects[i].close();
			}
		}
		
		// goes through all foreground effects to see if running
		for (int i = 0; i < FOREGROUND_EFFECTS_NUM; i++) {
			if (foregroundEffects[i].getRunning()) {
				foregroundEffects[i].close();
			}
		}
		
		// goes through all movement effects to see if running
		for (int i = 0; i < MOVEMENT_EFFECTS_NUM; i++) {
			if (movementEffects[i].getRunning()) {
				movementEffects[i].close();
			}
		}
		
		
		none.update();
		return true;
	}
	
	// changes the image 
	public void changeImage(boolean increasing) {
		
		// wait until image has closed to change image
		while (!closeAll()) {
			System.out.println("closing image...");
		}
		
		effect = none;
		resetfps();
	
		il.changeImage(increasing);
		effect.init();
	}
	
	// toggle effects running 
	public void runEffect (int i, int effect) {
		
		// toggle background effect
		if (effect == BACKGROUND_EFFECT) {
			
			if (effects[BACKGROUND_EFFECT].getRunning() && 
					effects[BACKGROUND_EFFECT] == backgroundEffects[i]) {
				effects[BACKGROUND_EFFECT].close();
			} else {
				effects[BACKGROUND_EFFECT].close();

				effects[BACKGROUND_EFFECT] = backgroundEffects[i];
				effects[BACKGROUND_EFFECT].run();
			}
			
		// toggle foreground effect
		} else if (effect == FOREGROUND_EFFECT) {
	
			if (effects[FOREGROUND_EFFECT].getRunning() && 
					effects[FOREGROUND_EFFECT] == foregroundEffects[i]) {
				effects[FOREGROUND_EFFECT].close();
			} else {
				effects[FOREGROUND_EFFECT].close();

				effects[FOREGROUND_EFFECT] = foregroundEffects[i];
				effects[FOREGROUND_EFFECT].run();
			}
			
		// toggle movement effect
		} else {
			
			if (effects[MOVEMENT_EFFECT].getRunning() && 
					effects[MOVEMENT_EFFECT] == movementEffects[i]) {
				effects[MOVEMENT_EFFECT].close();
			} else {
				effects[BACKGROUND_EFFECT].close();

				effects[MOVEMENT_EFFECT] = movementEffects[i];
				effects[MOVEMENT_EFFECT].run();
			}
		}
	}
	
	// closes an effect
	public void closeEffect (int effect) {
		
		effects[effect].close();
		
	}
	
	
	// sets the current image to be the color
	public void showColor (int color) {
		switch (color) {
	
			case 0:
				il.pinkImage();
				break;
			
			case 1:
				il.redImage();
				break;
				
			case 2:
				il.yellowImage();
				break;
				
			case 3:
				il.greenImage();
				break;
				
			case 4:
				il.cyanImage();
				break;
				
			case 5:
				il.blueImage();
				break;
		}	
	}
		
	
	
	
	
/* thread methods ----------------------------------------*/
	/**
	 * starts the visualizations
	 */
	public synchronized void start () {
		if (running)
			return;
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	
	/**
	 * stops the visualizations
	 */
	public synchronized void stop () {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void exit () {
		System.exit(1);
	}
	
	

	
	
	
	
	/* --------- GETTERS AND SETTERS --------- */

	/* GET CLASSES */
	public ImageLoader getIl () {
		return il;
	}
	
	/* SET/GET COORDINATES */
	public void setx (int x) {
		this.x = x;
	}
	
	public void sety (int y) {
		this.y = y;
	}
	
	public int getx () {
		return x;
	}
	
	public int gety () {
		return y;
	}
	
	public int getSavedX () {
		return savedx;
	}
	
	public int getSavedY () {
		return savedy;
	}
	
	// increment x either positive or negative
	public void addx (int amt, boolean positive) {
		if (positive) {
			x = x + amt;
		} else {
			x = x - amt;
		}
		savedx = x;
	}
	
	// increment y either positive or negative
	public void addy (int amt, boolean positive) {
		if (positive) {
			y = y + amt;
		} else {
			y = y - amt;
		}
		savedy = y;
	}
	
	
	
	/* GET/SET DIMS */
	public int getWindowHeight () {
		return window.getHeight();
	}
	
	public int getWindowWidth () {
		return window.getWidth();
	}
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}
	
	public void setWidth (int width) {
		this.width = width;
	}
	
	public void setHeight (int height) {
		this.height = height;
	}
	
	public void incWidth (int amt, boolean increasing) {
		if (increasing) {
			width = width + amt;
		} else {
			width = width - amt;
		}
	}
	
	public void incHeight (int amt, boolean increasing) {
		if (increasing) {
			height = height + amt;
		} else {
			height = height - amt;
		}
	}
	
	
	/* GET EFFECTS */
	public Effect getEffect() {
		return effect;
	}
	
	public void setEffect(Effect effect) {
		this.effect = effect;
		this.effect.init();
	}
	
	
	/* GET EFFECTS */
	public Effect getInvert() {
		return invert;
	}
	
	public Effect getNone () {
		return none;
	}

	public Effect getEarthquake() {
		return earthquake;
	}
	
	public Effect getRainbow() {
		return rainbow;
	}
	
	
	/* GET FPS */
	public int getfps() {
		return fps;
	}
	
	public void setfps(int fps) {
		this.fps = fps;
	}
	
	// resets fps to default amt
	public void resetfps() {
		fps = BASEFPS;
	}
}
