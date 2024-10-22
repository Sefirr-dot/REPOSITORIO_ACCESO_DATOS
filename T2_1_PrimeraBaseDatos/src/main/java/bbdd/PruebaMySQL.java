package bbdd;

import java.sql.*;


public class PruebaMySQL {

	public static void main(String[] args) {
		
		try {

			 //Establecemos la conexion con la BD
			 Connection conexion = DriverManager.getConnection
			 ("jdbc:mysql://localhost:3306/ejemplo", "root", "");
			 // Preparamos la consulta
			 Statement sentencia = conexion.createStatement();
			 String sql = "SELECT * FROM departamentos";
			 ResultSet resul = sentencia.executeQuery(sql);
			 //Recorremos el resultado para visualizar cada fila
			 //Se hace un bucle mientras haya registros y se van mostrando
			  
			 resul.last(); //Nos situamos en el último registro
			 System.out.println ("NÚMERO DE FILAS: " + resul.getRow());
			 resul.beforeFirst(); //Nos situamos antes del primer registro 
			  
			 //Recorremos el resultado para visualizar cada fila
			 while (resul.next()) 
			  System.out.printf("Fila %d: %d, %s, %s %n", 
			 resul.getRow(),
			 resul.getInt(1), 
			  resul.getString(2), 
			  resul.getString(3) );
			 
			 while (resul.next()) {
			 System.out.printf("%d, %s, %s %n", 
			 resul.getInt(1), 
			 resul.getString(2), 
			 resul.getString(3));

			 }
			 
			 
			 
			 resul.close(); // Cerrar ResultSet
			 sentencia.close(); // Cerrar Statement
			 conexion.close(); // Cerrar conexión
			 } catch (SQLException e) {
			 e.printStackTrace();
			 }
			 

	}

}
