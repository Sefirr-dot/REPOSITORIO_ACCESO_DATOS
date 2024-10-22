package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Empleadoos {

	public static void main(String[] args) {

		try {

			 //Establecemos la conexion con la BD
			 Connection conexion = DriverManager.getConnection
			 ("jdbc:mysql://localhost:3306/ejemplo", "root", "");
			 // Preparamos la consulta
			 Statement sentencia = conexion.createStatement();
			 String sql = "SELECT apellido, oficio, salario FROM empleados WHERE dept_no=10";
			 ResultSet resul = sentencia.executeQuery(sql);
			 //Recorremos el resultado para visualizar cada fila
			 //Se hace un bucle mientras haya registros y se van mostrando
			  
			 resul.last(); //Nos situamos en el último registro
			 System.out.println ("NÚMERO DE FILAS: " + resul.getRow());
			 resul.beforeFirst(); //Nos situamos antes del primer registro 
			  
			 //Recorremos el resultado para visualizar cada fila
			 while (resul.next()) 
			  System.out.printf("Fila %d: %s, %s, %.2f %n", 
			 resul.getRow(), 
			  resul.getString(1), 
			  resul.getString(2),
			  resul.getFloat(3));
			 
			 
			 
			 resul.close(); // Cerrar ResultSet
			 sentencia.close(); // Cerrar Statement
			 conexion.close(); // Cerrar conexión
			 } catch (SQLException e) {
			 e.printStackTrace();
			 }
	}

}
