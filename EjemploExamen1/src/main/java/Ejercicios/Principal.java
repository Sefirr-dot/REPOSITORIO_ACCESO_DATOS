package Ejercicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
	static Conexiones conexion = new Conexiones();
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) throws IOException{
		Connection conn = conexion.getOracle("COMPRAS", "compras");
		
			try {
				int op = 0;
				do {
					System.out.println("............................................................\n"
							+ ".  1 Mostrar totales de compras realizadas de cada producto. \n" 
							+ ".  2 Añadir columna con numero de compras realizadas por cada cliente.  \n" 							
							+ ".  3 Insertar un detalle de compra (recibe numero de compra, código de producto y unidades  \n" 
							+ ".  0 SALIR.\n"
							+ "............................................................\n");
					System.out.println("TECLEA OPERACION: ");
					op = teclado.nextInt();
					switch (op) {
					case 1: // Mostrar totales de compras realizadas de cada producto
						ejercio1(conn);						
						break;
					case 2: // Añadir columna numcompras a la tabla cliente
						ejercio2(conn);
						break;
					case 3: // Insertar un detalle de compra (recibe ncompras,  cproducto, unidades)
						ejercio3(conn, 1000, 8000, -42); // Error en todo 
						
						ejercio3(conn, 1000, 5, 42); // Error en compra 
						
						ejercio3(conn, 1, 500, 42); // Error en prod 
						
						
						ejercio3(conn, 1, 8, 42); // Grabado, la segunda vez dirá que existe

						break;
							
					} // cierra Sub menu

				} while (op != 0);

			} catch (InputMismatchException e) {
				System.out.println("ERROR. LA OPCION DEBE SER NUMERICA.\n ");
			}

	}

	private static void ejercio3(Connection conn, int ncompras, int cproducto, int unidades) {
		String comprasE = "SELECT * FROM detcompras where numcompra=?";
		String productoE = "SELECT * FROM productos where codproducto=?";
		String insertar = "insert into detcompras values(?,?,?)";

		Boolean error = false;
		String errores = "";
		if (0 >= unidades) {
			error = true;
			errores = errores + "Las unidades tiene que ser mayores que 0. \n";
		}
		try {
			PreparedStatement sentencia1 = conn.prepareStatement(comprasE);
			sentencia1.setInt(1, ncompras);
			ResultSet rs1 = sentencia1.executeQuery();
			if (!rs1.next()) {
				error = true;
				errores = errores + "El codigo de compra no existe. \n";
			}
			rs1.close();
			sentencia1.close();

			PreparedStatement sentencia2 = conn.prepareStatement(comprasE);
			sentencia2.setInt(1, cproducto);
			ResultSet rs2 = sentencia2.executeQuery();
			if (!rs2.next()) {
				error = true;
				errores = errores + "El codigo de producto no existe. \n";
			}
			rs2.close();
			sentencia2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (error) {
			System.out.println("Error:");
			System.out.println(errores);
		} else {
			try {
				PreparedStatement sentencia3 = conn.prepareStatement(insertar);
				sentencia3.setInt(1, ncompras);
				sentencia3.setInt(2, cproducto);
				sentencia3.setInt(3, unidades);
				sentencia3.executeUpdate();
				System.out.println("REGISTRO GRABADO");
				sentencia3.close();
			} catch (Exception e) {
				errores = errores + "Existe el producto para esa compra \n";
				System.out.println(errores);
			}
		}
	}

	private static void ejercio2(Connection conn) {
		String nuevacolumn = "Alter table clientes add numcompras number(4)";
		String clientes = "SELECT * FROM CLIENTES";
		String consulta0 = "SELECT CODCLIENTE, COUNT(NUMCOMPRA) FROM COMPRAS WHERE CODCLIENTE=? GROUP BY CODCLIENTE";
		String actualiza = "update clientes set numcompras = ? where CODCLIENTE= ?";
		try {
			// creamos la columna.
			PreparedStatement sentencia0 = conn.prepareStatement(nuevacolumn);
			int rs0 = sentencia0.executeUpdate();
			System.out.println("COLUMNA CREADA ");

		} catch (SQLException e) {

			System.out.println("LA COLUMNA YA EST  CREADA ");
		}
		PreparedStatement sentencia0;

		try {
			sentencia0 = conn.prepareStatement(clientes);
			ResultSet rs0 = sentencia0.executeQuery();

			while (rs0.next()) {
				int ncompras = 0;
				PreparedStatement sentencia2 = conn.prepareStatement(consulta0);
				sentencia2.setInt(1, rs0.getInt(1));
				ResultSet rs2 = sentencia2.executeQuery();
				if (rs2.next()) {
					ncompras = rs2.getInt(2);
				} else {
					ncompras = 0;
				}
				sentencia2.close();
				rs2.close();

				PreparedStatement sentActualizar = conn.prepareStatement(actualiza);
				sentActualizar.setInt(1, ncompras);
				sentActualizar.setInt(2, rs0.getInt(1));
				sentActualizar.executeUpdate();
				System.out.println("Cliente " + rs0.getString(2) + " actualizado compra: " + ncompras);
				sentActualizar.close();

			}
			rs0.close();
			sentencia0.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static void ejercio1(Connection conn) {
		String consulta0 = "select codproducto, denominacion, tipo, pvp, stock from productos  ORDER BY 1";
		String suma_unidades = "SELECT CODPRODUCTO, SUM(UNIDADES) FROM DETCOMPRAS WHERE CODPRODUCTO=? GROUP BY CODPRODUCTO";
		int iva = 0;

		PreparedStatement sentencia0;
		try {
			sentencia0 = conn.prepareStatement(consulta0);
			ResultSet rs0 = sentencia0.executeQuery();
			System.out.printf("%11s %-20s %-6s %-4s %-6s %15s %10s %5s %10s %n", "CODPRODUCTO", "DENOMINACION", "TIPO",
					"IVA", "PVP", "SUMA UNIDADES", "IMPORTE", "STOCK", "STOCK_ACTUAL");
			System.out.printf("%11s %20s %4s %4s %6s %15s %10s %5s %10s %n", "-----------", "--------------------",
					"----", "----", "------", "---------------", "----------", "-------", "------------");
			int sumaUnidades = 0;
			int sumaUtotal = 0;
			float importeTotal = 0;
			int stockTotal = 0;
			int stockAtotal = 0;
			String lista = "";
			while (rs0.next()) {

				// calcular suma de unidades
				PreparedStatement sentencia2 = conn.prepareStatement(suma_unidades);
				sentencia2.setInt(1, rs0.getInt(1));
				ResultSet rs2 = sentencia2.executeQuery();
				if (rs2.next()) {
					sumaUnidades = rs2.getInt(2);
				} else {
					sumaUnidades = 0;
				}
				sentencia2.close();
				rs2.close();
				// CALCULAR IMPORTE
				Float importe = rs0.getFloat(4) * sumaUnidades;
				// CALCULAR STOCK_ACTUAL
				int stockActual = rs0.getInt(5) - sumaUnidades;
				// CALCULAR EL IVA
				switch (rs0.getString(3)) {
				case "A":
					iva = 21;
					break;
				case "B":
					iva = 10;
					break;
				case "C":
					iva = 4;
					break;

				default:
					iva = 0;
					break;
				}

				System.out.printf("%11s %20s %4s %4s %6s %15s %10s %5s %10s %n", rs0.getInt(1), rs0.getString(2),
						rs0.getString(3), iva + "%", rs0.getFloat(4), sumaUnidades, importe, rs0.getInt(5),
						stockActual);

				if (stockActual < 10) {
					lista = lista + rs0.getString(2) + ", ";
				}
				stockAtotal = stockAtotal + stockActual;
				stockTotal = stockTotal + rs0.getInt(5);
				importeTotal = importeTotal + rs0.getFloat(4);
				sumaUtotal = sumaUtotal + sumaUnidades;
				sumaUnidades = 0;
			}

			System.out.printf("%11s %20s %4s %4s %6s %15s %10s %5s %10s %n", "-----------", "--------------------",
					"----", "----", "------", "---------------", "----------", "-----", "----------");
			System.out.printf("%-11s %20s %4s %4s %6s %15s %10s %5s %10s %n", "TOTALES", "", "", "", "", sumaUtotal,
					importeTotal, stockTotal, stockAtotal);

			System.out.println("Lista de productos a reponer: " + lista);

			sentencia0.close();
			rs0.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
