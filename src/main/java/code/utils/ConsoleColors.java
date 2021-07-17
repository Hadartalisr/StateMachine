package code.utils;

import java.awt.Color;
import java.util.Random;

public final class ConsoleColors {

	private ConsoleColors() {}

	// Reset
	public static final String RESET = "\033[0m"; // Text Reset

	// Regular Colors
	public static final String[] Colors = new String[] { "\033[0;30m", // BLACK
			"\033[0;31m", // RED
//			"\033[0;32m", // GREEN
			"\033[0;33m", // YELLOW
			"\033[0;34m", // BLUE
			"\033[0;35m", // PURPLE
			"\033[0;36m", // CYAN
			"\033[0;37m" // WHITE
	};

	public static String getRandomColor() {
		int randInt = new Random().nextInt(Colors.length);
		return Colors[randInt];
	}

	public static String getRandomColor(String color) {
		String ret = color;
		while(ret.equals(color)) {
			ret = getRandomColor();
		}
		return ret;
	}
}
