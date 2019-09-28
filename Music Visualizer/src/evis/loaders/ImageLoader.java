 package evis.loaders;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	
	private static String[] imagePaths = {
			"/textures/1.png", 
			"/textures/2.jpg",
			"/textures/3.png",
			"/textures/bubble.png",
			"/textures/helpscreen.png" 	
	};
	
	private static BufferedImage[][] images;
	private static String [][] imageFiles;
	private static int currentIndex;
	private BufferedImage currentImage;
	private BufferedImage invertImage;
	private BufferedImage savedImage;
	private BufferedImage bubble, lightning, light, helpScreen;
	
	
	// constants
	private static final int ARRAYLENGTH = imagePaths.length - 2;
	private static final int IMAGEOFFSET = 14;
	private static final int IMAGE = 0;
	private static final int INVERTOFFSET = 1;
	private static final int PINKOFFSET = 2;
	private static final int PINKINVERT = 3;
	private static final int REDOFFSET = 4;
	private static final int REDINVERT = 5;
	private static final int YELLOWOFFSET = 6;
	private static final int YELLOWINVERT = 7;
	private static final int GREENOFFSET = 8;
	private static final int GREENINVERT = 9;
	private static final int CYANOFFSET = 10;
	private static final int CYANINVERT = 11;
	private static final int BLUEOFFSET = 12;
	private static final int BLUEINVERT = 13;
	
	private static final int MAX = 255;
	
	public ImageLoader () {
		
		// initialize new image arrays 
		images = new BufferedImage[ARRAYLENGTH][IMAGEOFFSET];
		imageFiles = new String[ARRAYLENGTH][IMAGEOFFSET];
		
		// get bubble image
		loadTextures();
		
		//set current index to 0
		currentIndex = 0;
		
		// populate the filenames
		System.out.println("populating filenames:");
		populateFilenames();
		
		// generate each type of image
		System.out.println("generating images:");
		//generateImages();
		
		// save the images to a file
		System.out.println("saving images to files:");
		loadImages();
		//saveImages();
		
		// set image to the first image
		setHelpScreen();
	}
	
	// increments the image being shown
	public void changeImage (boolean increasing) {
		if (increasing) {
			currentIndex++;
			if (currentIndex >= ARRAYLENGTH) {
				currentIndex = currentIndex - ARRAYLENGTH;
			}
		} else {
			currentIndex--;
			if (currentIndex < 0) {
				currentIndex = currentIndex + ARRAYLENGTH;
			}
		}
		ogImage();
		System.out.println(currentIndex);
		
	}
	
	
	// image getters
	public BufferedImage getCurrentImage () {
		return currentImage;
	}
	
	public BufferedImage getBubble () {
		return bubble;
	}
	
	public BufferedImage getLightning () {
		return lightning;
	}
	
	public BufferedImage getHelpScreen () {
		return helpScreen;
	}
	
	// inverts active image
	public void invertImage () {
		savedImage = currentImage;
		currentImage = invertImage;
	}
	
	// reverts active image
	public void invertBack () {
		currentImage = savedImage;
	}
	
	// turns on the help screen
	public void setHelpScreen () {
		currentImage = helpScreen;
	}
	
	
	// image changers 
	public void ogImage () {
		currentImage = images[currentIndex][IMAGE];
		invertImage = images[currentIndex][INVERTOFFSET];
		savedImage = currentImage;
	}

	public void redImage () {
		currentImage = images[currentIndex][REDOFFSET];
		invertImage = images[currentIndex][REDINVERT];
		savedImage = currentImage;
	}
	
	public void pinkImage () {
		currentImage = images[currentIndex][PINKOFFSET];
		invertImage = images[currentIndex][PINKINVERT];
		savedImage = currentImage;
	}
	
	public void yellowImage () {
		currentImage = images[currentIndex][YELLOWOFFSET];
		invertImage = images[currentIndex][YELLOWINVERT];
		savedImage = currentImage;
	}
	
	public void greenImage () {
		currentImage = images[currentIndex][GREENOFFSET];
		invertImage = images[currentIndex][GREENINVERT];
		savedImage = currentImage;
	}
	
	public void blueImage () {
		currentImage = images[currentIndex][BLUEOFFSET];
		invertImage = images[currentIndex][BLUEINVERT];
		savedImage = currentImage;
	}
	
	public void cyanImage () {
		currentImage = images[currentIndex][CYANOFFSET];
		invertImage = images[currentIndex][CYANINVERT];
		savedImage = currentImage;
	}	
	
	public BufferedImage getWhite () {
		return images[currentIndex][0];
	}
	
	public void generateImages () {
		
		// populate images array with initial raw images
		try {
			for (int i = 0; i < ARRAYLENGTH; i++) {
				for (int j = 0; j < IMAGEOFFSET; j++) {
					images[i][j] = ImageIO.read(ImageLoader.class.getResource(
							imagePaths[i]));
				}
			}
			
			// edit images array with altered versions of initial images
			for (int i = 0; i < ARRAYLENGTH; i++) {		
				
				int percent = 0;
				int iterationAmount = 100 / ARRAYLENGTH;
				
				percent = iterationAmount * i;
				System.out.println(percent + "+");
				
				System.out.println(percent);
				// inverts the pixel colors of the image passed in
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(MAX - col.getRed(),
		                                MAX - col.getGreen(),
		                                MAX - col.getBlue());
		                images[i][INVERTOFFSET].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// PINK
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(MAX,
		                                col.getGreen(),
		                                MAX);
		                images[i][PINKOFFSET].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// PINK INVERT
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(0,
		                                MAX - col.getGreen(),
		                                0);
		                images[i][PINKINVERT].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// RED
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(MAX,
		                                col.getGreen(),
		                                col.getBlue());
		                images[i][REDOFFSET].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// RED INVERT
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(0,
		                                MAX - col.getGreen(),
		                                MAX - col.getBlue());
		                images[i][REDINVERT].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// YELLOW
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(MAX,
		                                MAX,
		                                col.getBlue());
		                images[i][YELLOWOFFSET].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// YELLOW INVERT
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(0,
		                                0,
		                                MAX - col.getBlue());
		                images[i][YELLOWINVERT].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// GREEN
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(col.getRed(),
		                                MAX,
		                                col.getBlue());
		                images[i][GREENOFFSET].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// GREEN INVERT
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(MAX - col.getRed(),
		                                0,
		                                MAX - col.getBlue());
		                images[i][GREENINVERT].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// CYAN 
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(col.getRed(),
		                                MAX,
		                                MAX);
		                images[i][CYANOFFSET].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// CYAN INVERT
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(MAX - col.getRed(),
		                                0,
		                                0);
		                images[i][CYANINVERT].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// BLUE
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(col.getRed(),
		                                col.getGreen(),
		                                MAX);
		                images[i][BLUEOFFSET].setRGB(x, y, col.getRGB());
		            }
		        }
				
				// BLUE INVERT
				for (int x = 0; x < images[i][0].getWidth(); x++) {
		            for (int y = 0; y < images[i][0].getHeight(); y++) {
		                int rgba = images[i][0].getRGB(x, y);
		                Color col = new Color(rgba, true);
		                col = new Color(MAX - col.getRed(),
		                                MAX - col.getGreen(),
		                                0);
		                images[i][BLUEINVERT].setRGB(x, y, col.getRGB());
		            }
		        }
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void loadImages () {
		for (int i = 0; i < ARRAYLENGTH; i++) {
			for (int j = 0; j < IMAGEOFFSET; j++) {
				
				try {
					
					// load files 
					File inputFile = new File(imageFiles[i][j]);
					images[i][j] = ImageIO.read(inputFile);
				} catch (IOException e) {
					
					// if there are no files, generate and save them
					generateImages();
					saveImages();
				}
			}
		}
	}
	
	private void saveImages () {
		
		int percent = 0;
		int iterationAmount = 100 / ARRAYLENGTH / IMAGEOFFSET;
		
		for (int i = 0; i < ARRAYLENGTH; i++) {
			for (int j = 0; j < IMAGEOFFSET; j++) {
				
				// output percent completion
				percent = j * iterationAmount * i;
				System.out.println(percent + "%");
				
				// save files 
				File outputfile = new File(imageFiles[i][j]);
				try {
					ImageIO.write(images[i][j], "png", outputfile);
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
	}
	
	// populates filenames for ImageIO
	private void populateFilenames () {
		
		int index = 0;
		
		for (int i = 0; i < ARRAYLENGTH; i++) {
			for (int j = 0; j < IMAGEOFFSET; j++) {
				
				index = i * IMAGEOFFSET + j;
				imageFiles[i][j] = Integer.toString(index) + ".png";
			}
		}
	}
	
	private void loadTextures () {
		try {
			bubble = ImageIO.read(ImageLoader.class.getResource(
					imagePaths[3]));
			helpScreen = ImageIO.read(ImageLoader.class.getResource(
					imagePaths[4]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
