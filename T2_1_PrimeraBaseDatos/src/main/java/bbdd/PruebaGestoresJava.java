package bbdd;

import java.sql.*;
public class PruebaGestoresJava {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection connection;
		
		System.out.println("-----------SQLITE-----------");
		connection = DriverManager.getConnection("jdbc:sqlite:./BBDD/SQLITE/ejemplo.db");
		listarDepartamentos(connection);
		
		System.out.println("-----------DERBY-----------");
		connection = DriverManager.getConnection("jdbc:derby:./BBDD/derby");
		listarDepartamentos(connection);
		
		System.out.println("-----------H2-----------");
		connection = DriverManager.getConnection("jdbc:h2:./BBDD/h2ejemplo/ejemplo", "sa", "");
		listarDepartamentos(connection);
		
		System.out.println("-----------HSQLDB-----------");
		connection = DriverManager.getConnection("jdbc:hsqldb:./BBDD/hsqlejemplo/ejemplo/ejemplo");
		listarDepartamentos(connection);
		
		System.out.println("-----------ACCESS-----------");
		Connection conn = DriverManager.getConnection ("jdbc:ucanaccess://./BBDD/ACCESS/ejemplo.accdb");  
		listarDepartamentos(connection);
		
		
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
}
