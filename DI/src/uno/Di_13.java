package uno;

import java.util.Scanner;

public class Di_13 {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce el numero de ventas deseado");
		int numVentas = teclado.nextInt();
		int sumaVentas=0;
		for (int i = 0; i < numVentas; i++) {
			System.out.println("Di el precio de la venta "+(i+1));
			double precio = teclado.nextDouble();
			sumaVentas+=precio;
		}
		System.out.println("El numero de ventas total es: "+sumaVentas);
	}

}
