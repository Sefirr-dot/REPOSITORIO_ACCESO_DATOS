package uno;

import java.util.Scanner;

public class Di_14 {

	public static void main(String[] args) {
		Scanner tecladoScanner = new Scanner(System.in);
		System.out.println("Variable A");
		double a = tecladoScanner.nextDouble();
		System.out.println("Variable B");
		double b = tecladoScanner.nextDouble();
		System.out.println("Variable C");
		double c = tecladoScanner.nextDouble();
		
		if((b*b)-(4*a*c)<0) {
			System.out.println("La ecuacion no es posible");
		} else {
			double raiz = Math.sqrt(b*b-4*a*c);
			double total1 = (-b + raiz)/(2*a);
			double total2 = (-b - raiz)/(2*a);
			System.out.println("Las soluciones son: "+ total1 +" y "+ total2);
		}
		

	}

}
