package ec.ups.edu.sistemafinancieroLocal.utils;

import java.util.Random;

public class RandomUtil {
	
	private static String[] alfabetoStrings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            								   "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            								   "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            								   "u", "v", "w", "x", "y", "z", "%", "#", "@", "^"};
	
	public static String generarPassword() {
		return generarTextoRandomico(alfabetoStrings, 5);
	}
	
	public static String generarNumeroCuenta() {
		return generarTextoRandomico(alfabetoStrings, 15);
	}
	
	private static String generarTextoRandomico(String[] symbols, int dimension) {
		Random random = new Random();
		
		StringBuilder stringBuilder = new StringBuilder(dimension);
		
		for (int i = 0; i < dimension; i++) {
			int indexRandom = random.nextInt(symbols.length);
			stringBuilder.append(symbols[indexRandom]);
		}
		
		return stringBuilder.toString();
	}

}
