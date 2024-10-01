package uno;

import java.util.Scanner;

public class Di_20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner tecladoScanner = new Scanner(System.in);
		int numero = tecladoScanner.nextInt();
		boolean salir = false;
		int contador = 2;
		if(numero <=1) {
			System.out.println("No es primo");
		} else {
			while(!salir) {
				contador++;
				if(numero%(contador-1)==0) {
					salir =true;
					System.out.println("No es primo");
				} else if(contador==numero) {
					salir=true;
					System.out.println("Es primo");
				}
				
			}
		}
		
		
	}

}
