package bbdd;

import java.sql.*;
public class PruebaGestoresJava {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection connection;
		ResultSet resul;
		ResultSet columnas;
		
		System.out.println("-----------SQLITE-----------");
		connection = DriverManager.getConnection("jdbc:sqlite:./BBDD/SQLITE/ejemplo.db");
		DatabaseMetaData dbmd = connection.getMetaData();
		resul = dbmd.getTables(null, null, null, null); // No se necesita ningún valor
		listarDepartamentos(connection);
		informacionTablas(resul);
		columnas = dbmd.getColumns(null, "ejemplo", "departamentos", null);
		mostrarColumnas(columnas);
		
		System.out.println("-----------DERBY-----------");
		connection = DriverManager.getConnection("jdbc:derby:./BBDD/derby");
		dbmd = connection.getMetaData();
		resul = dbmd.getTables(null, "APP", null, null );  // APP es el usuario administrador por defecto de esta BD.
		listarDepartamentos(connection);
		informacionTablas(resul);
		columnas = dbmd.getColumns(null, "ejemplo", "DEPARTAMENTOS", null);
		mostrarColumnas(columnas);
		
		System.out.println("-----------H2-----------");
		connection = DriverManager.getConnection("jdbc:h2:./BBDD/h2ejemplo/ejemplo", "sa", "");
		dbmd = connection.getMetaData();
		listarDepartamentos(connection);
		
		System.out.println("-----------HSQLDB-----------");
		connection = DriverManager.getConnection("jdbc:hsqldb:./BBDD/hsqlejemplo/ejemplo/ejemplo");
		dbmd = connection.getMetaData();
		resul = dbmd.getTables("PUBLIC", "PUBLIC", null, null );  //PUBLIC es el catálogo y esquema por defecto de esta BD. 
		listarDepartamentos(connection);
		informacionTablas(resul);
		columnas = dbmd.getColumns(null, "ejemplo", "DEPARTAMENTOS", null);
		mostrarColumnas(columnas);
		
		System.out.println("-----------ACCESS-----------");
		Connection conn = DriverManager.getConnection ("jdbc:ucanaccess://./BBDD/ACCESS/ejemplo.accdb");  
		dbmd = conn.getMetaData();
		listarDepartamentosAcces(conn);
		informacionTablas(resul);
		columnas = dbmd.getColumns(null, "ejemplo", "departamentos", null);
		mostrarColumnas(columnas);

		
//		System.out.println("-----------ORACLE-----------");
//		connection  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:free", "C##miguel","Ora1234");
//		dbmd = connection.getMetaData();
//		resul = dbmd.getTables(null, "C##MIGUEL", null, null ); // EJEMPLO es el usuario 
//		listarDepartamentos(connection);
//		informacionTablas(resul);
//		columnas = dbmd.getColumns(null, "C##MIGUEL", "DEPARTAMENTOS", null);
//		mostrarColumnas(columnas);
		
		System.out.println("-----------MYSQL-----------");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","root", "");
		dbmd = connection.getMetaData();
		String[] tipoStrings = {"TABLE","SYNONYM"};
		resul = dbmd.getTables("ejemplo", "root", null, null ); // ejemplo es la BD
		listarDepartamentos(connection);
	    informacionTablas(resul);
	    columnas = dbmd.getColumns(null, "ejemplo", "departamentos", null);
		mostrarColumnas(columnas);
		
		ResultSet proc = dbmd.getProcedures(null, "ejemplo", null); 
		while (proc.next()) { 
		   String proc_name = proc.getString("PROCEDURE_NAME"); 
		   String proc_type = proc.getString("PROCEDURE_TYPE"); 
		   System.out.printf("Nombre Procedimiento: %s - Tipo: %s %n", 
		                      proc_name, proc_type); 
		} 
		
	}
	
	public static void mostrarColumnas(ResultSet columnas) throws SQLException {
		while (columnas.next()) {       
			  String nombCol = columnas.getString("COLUMN_NAME"); //getString(4) 
			  String tipoCol = columnas.getString("TYPE_NAME");   //getString(6) 
			  String tamCol = columnas.getString("COLUMN_SIZE");  //getString(7) 
			  String nula  = columnas.getString("IS_NULLABLE");   //getString(18) 
			  System.out.printf("Columna: %s, Tipo: %s, Tamaño: %s, ¿Puede ser Nula:? %s %n", nombCol, tipoCol, tamCol, nula); 
			} 
	}
	
	public static void informacionTablas(ResultSet resul) throws SQLException {
		while (resul.next()) {       
		      String catalogo = resul.getString(1);//columna 1  
		      String esquema = resul.getString(2); //columna 2 
		      String tabla = resul.getString(3);   //columna 3 
		      String tipo = resul.getString(4); //columna 4 
		        System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre: %s %n", tipo, catalogo, esquema, tabla); 
		      }        
		      
	}

	private static void listarDepartamentos(Connection connection) throws SQLException {
		
		String sqlString = "SELECT * FROM departamentos";
		
		Statement sentenciaStatement = connection.createStatement();
		
		ResultSet result = sentenciaStatement.executeQuery(sqlString);
		
		while (result.next()) 
			  System.out.printf("%d, %s, %s %n", 

			 result.getInt(1), 
			  result.getString(2), 
			  result.getString(3) );
	}
	private static void listarDepartamentosAcces(Connection connection) throws SQLException {
		
		String sqlString = "SELECT * FROM departamentos";
		
		Statement sentenciaStatement = connection.createStatement();
		
		ResultSet result = sentenciaStatement.executeQuery(sqlString);
		
		while (result.next()) 
			  System.out.printf("%d, %s, %s %n", 

			 result.getInt(3), 
			  result.getString(1), 
			  result.getString(2) );
	}
}
