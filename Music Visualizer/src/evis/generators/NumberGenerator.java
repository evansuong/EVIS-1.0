package evis.generators;

import java.util.Random;

public class NumberGenerator {
	
	Random random = new Random ();

	public int getRandomNumber (int bounds) {
		return random.nextInt(bounds);
	}
	
	public double getRandomDouble () {
		return random.nextDouble();
	}
	
}
