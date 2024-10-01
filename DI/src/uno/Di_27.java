package uno;

import java.util.Scanner;

public class Di_27 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner tecladoScanner = new Scanner(System.in);
		
		int suma = 0;
		int numero = 1;
		while (numero!=-1) {
			System.out.println("Escribe el numero que quieras, -1 para salir");
			numero = tecladoScanner.nextInt();
			if(numero!=-1) {
				suma++;
			} 
		}
		System.out.println("La suma total es: " +suma);
	}

}
