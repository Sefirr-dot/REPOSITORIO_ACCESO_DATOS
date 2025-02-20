package dao.impl.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DepartamentoDAO;
import model.Departamento;

public class DepartamentoImplMysql implements DepartamentoDAO {
	static Connection con = null;
	static String URLDB = "jdbc:mysql://localhost/unidad6";
	static String USUARIO = "root";
	static String CLAVE = "";
	Connection conexion;

	public DepartamentoImplMysql() {
		conexion = crearConexion();
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
	public boolean InsertarDep(Departamento dep) {
		boolean valor = false;
		String sql = "INSERT INTO departamentos VALUES(?, ?, ?)";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, dep.getDeptno());
			sentencia.setString(2, dep.getDnombre());
			sentencia.setString(3, dep.getLoc());
			int filas = sentencia.executeUpdate();
			if (filas > 0) {
				valor = true;
				System.out.printf("Departamento %d insertado%n%n", dep.getDeptno());
			}
			sentencia.close();

		} catch (SQLException e) {
			MensajeExcepcion(e);
		}

		return valor;
	}

	@Override
	public boolean EliminarDep(int deptno) {
		boolean valor = false;
		String sql = "DELETE FROM departamentos WHERE dept_no = ? ";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, deptno);
			int filas = sentencia.executeUpdate();
			if (filas > 0) {
				valor = true;
				System.out.printf("Departamento %d eliminado%n%n", deptno);
			}
			sentencia.close();
		} catch (SQLException e) {
			MensajeExcepcion(e);
		}

		return valor;
	}

	@Override
	public boolean ModificarDep(int num, Departamento dep) {
		boolean valor = false;
		String sql = "UPDATE departamentos SET dnombre= ?, loc = ? WHERE dept_no = ? ";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(3, num);
			sentencia.setString(1, dep.getDnombre());
			sentencia.setString(2, dep.getLoc());
			int filas = sentencia.executeUpdate();
			if (filas > 0) {
				valor = true;
				System.out.printf("Departamento %d modificado%n%n", num);
			}
			sentencia.close();
		} catch (SQLException e) {
			MensajeExcepcion(e);
		}

		return valor;
	}

	@Override
	public Departamento ConsultarDep(int deptno) {
		String sql = "SELECT dept_no, dnombre, loc FROM departamentos WHERE dept_no = ?";
		PreparedStatement sentencia;
		Departamento dep = new Departamento();
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, deptno);
			ResultSet rs = sentencia.executeQuery();
			if (rs.next()) {
				dep.setDeptno(rs.getInt("dept_no"));
				dep.setDnombre(rs.getString("dnombre"));
				dep.setLoc(rs.getString("loc"));
			} else
				System.out.printf("Departamento: %d No existe%n", deptno);

			rs.close();// liberar recursos
			sentencia.close();

		} catch (SQLException e) {
			MensajeExcepcion(e);
		}
		return dep;
	}

	@Override
	public List<Departamento> ListarDep() {

		return null;
	}

	private void MensajeExcepcion(SQLException e) {
		System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
		System.out.printf("Mensaje   : %s %n", e.getMessage());
		System.out.printf("SQL estado: %s %n", e.getSQLState());
		System.out.printf("Cód error : %s %n", e.getErrorCode());
	}
}
