package dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.DepartamentoDAO;
import dao.impl.mysql.DepartamentoImplMysql;

public class DAOFactoryMysql extends DAOFactory {
	static Connection con = null;
	static String URLDB = "";
	static String USUARIO = "root";
	static String CLAVE = "";

	public DAOFactoryMysql() {
		URLDB = "jdbc:mysql://localhost/unidad6";
	}

	// crear la conexión si no está creada
	public static Connection crearConexion() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(URLDB, USUARIO, CLAVE);

			} catch (SQLException ex) {
				System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
				System.out.printf("Mensaje   : %s %n", ex.getMessage());
				System.out.printf("SQL estado: %s %n", ex.getSQLState());
				System.out.printf("Cód error : %s %n", ex.getErrorCode());
			}
		}
		return con;
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		return new DepartamentoImplMysql();
	}
}