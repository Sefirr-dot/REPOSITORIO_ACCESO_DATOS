package uno;

import java.util.Scanner;

public class Di_16 {

	public static void main(String[] args) {
		String passwordString = "HolaMeGustaDAM2";
		Scanner tecladoScanner = new Scanner(System.in);
		boolean salir = false;
		int intentos = 0;
		
		while(!salir && intentos<3) {
			System.out.println("Escribe la contrasenia: ");
			String contraseniaString = tecladoScanner.next();
			if(contraseniaString.equals(passwordString)) {
				System.out.println("Enhorabuena");
				salir = true;
			} else {
				intentos++;
				System.out.println("Contraseña incorrecta te quedan "+(3-intentos)+" intentos");
			}
		}
		if(intentos==3) {
			System.out.println("Mamaste");
		}

	}

}
