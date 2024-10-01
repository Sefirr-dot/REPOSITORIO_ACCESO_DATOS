package uno;

import java.util.Scanner;

public class Nueve {
	public final static double iva = 1.21;
	public static void main(String[] args) {
		Scanner tecladoScanner = new Scanner(System.in);
		
		System.out.println("Cual es el precio?");
		double precio = tecladoScanner.nextDouble();
		
		System.out.println("El precio final es: "+precio*iva);
		
	}

}
