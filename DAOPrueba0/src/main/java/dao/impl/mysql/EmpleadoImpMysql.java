package dao.impl.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.EmpleadoDAO;
import model.Empleado;

public class EmpleadoImpMysql implements EmpleadoDAO {
	
	static Connection connection = null;
	static String URLDB = "jdbc:mysql://localhost/ejemplo";
	static String USUARIO = "root";
	static String CLAVE = "";
	Connection conexion;
	
	public EmpleadoImpMysql() {
		conexion = crearConexion();
	}

	public static Connection crearConexion() {

		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URLDB, USUARIO, CLAVE);

			} catch (SQLException ex) {
				System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
				System.out.printf("Mensaje   : %s %n", ex.getMessage());
				System.out.printf("SQL estado: %s %n", ex.getSQLState());
				System.out.printf("Cód error : %s %n", ex.getErrorCode());
			}
		}
		return connection;

	}
	@Override
	public boolean InsertarDep(Empleado em) {
		
		return false;
	}

	@Override
	public boolean EliminarEmp(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
