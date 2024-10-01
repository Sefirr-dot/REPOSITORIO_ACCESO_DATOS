package uno;

import java.util.Scanner;

public class Dos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Di el radio");
		Scanner tecladoScanner = new Scanner(System.in);
		double radio = tecladoScanner.nextDouble();
		
		System.out.println("El area es: "+(Math.PI*radio*radio));
		
		if (radio%2==0) {
			System.out.println("Es par");
	} else {
		System.out.println("No es par");
		}
	
	System.out.println("Di un numero");
	Scanner teclado1 = new Scanner(System.in);
	int numero = teclado1.nextInt();
	char numerofinal= (char) numero;
	System.out.println(numerofinal);
	
	System.out.println("Di un caracter");
	Scanner teclado2 = new Scanner(System.in);
	String numero2 = teclado1.next();
	int numerofinal2= (int) numero2.charAt(0);
	System.out.println(numerofinal2);
	
	
}
}
