package dao.impl.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.EmpleadoDAO;
import model.Empleado;

public class EmpleadoImplMysql implements EmpleadoDAO{
	static Connection con = null;
	static String URLDB = "jdbc:mysql://localhost/prueba";
	static String USUARIO = "root";
	static String CLAVE = "";
	Connection conexion;
	
	public EmpleadoImplMysql() {
		conexion=crearConexion();
	}
	
	public static Connection crearConexion() {
		if(con==null) {
			try {
				con=DriverManager.getConnection(URLDB,USUARIO,CLAVE);
			} catch(SQLException ex) {
				System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
				System.out.printf("Mensaje   : %s %n", ex.getMessage());
				System.out.printf("SQL estado: %s %n", ex.getSQLState());
				System.out.printf("Cód error : %s %n", ex.getErrorCode());
			}
		}
		return con;
	}
	
	@Override
	public boolean InsertarEmp(Empleado emp) {
		boolean valor = false;
		String sql = "INSERT INTO empleados VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, emp.getEmp_no());
			sentencia.setString(2, emp.getApellido());
			sentencia.setString(3, emp.getOficio());
			sentencia.setInt(4, emp.getDir());
			sentencia.setDate(5, emp.getFecha_alt());
			sentencia.setFloat(6, emp.getSalario());
			sentencia.setFloat(7, emp.getComision());
			sentencia.setInt(8, emp.getDep());
			int filas = sentencia.executeUpdate();
			if (filas > 0) {
				valor = true;
				System.out.printf("Empleado %d insertado%n%n", emp.getEmp_no());
			}
			sentencia.close();

		} catch (SQLException e) {
			MensajeExcepcion(e);
		}

		return valor;
	}
	
	@Override
	public boolean EliminarEmp(int empno) {
		boolean valor = false;
		String sql = "DELETE FROM empleados WHERE emp_no = ? ";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, empno);
			int filas = sentencia.executeUpdate();
			if (filas > 0) {
				valor = true;
				System.out.printf("Empleado %d eliminado%n%n", empno);
			}
			sentencia.close();
		} catch (SQLException e) {
			MensajeExcepcion(e);
		}

		return valor;
	}
	
	@Override
	public boolean ModificarEmp(int num, Empleado emp) {
		boolean valor = false;
		String sql = "UPDATE empleados SET apellido= ?, oficio = ?, dir = ?, fecha_alt = ?, salario = ?, comision = ?, dept_no = ? WHERE emp_no = ? ";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(8, num);
			sentencia.setString(1, emp.getApellido());
			sentencia.setString(2, emp.getOficio());
			sentencia.setInt(3, emp.getDir());
			sentencia.setDate(4, emp.getFecha_alt());
			sentencia.setFloat(5, emp.getSalario());
			sentencia.setFloat(6, emp.getComision());
			sentencia.setInt(7, emp.getDep());
			int filas = sentencia.executeUpdate();
			if (filas > 0) {
				valor = true;
				System.out.printf("Empleado %d modificado%n%n", num);
			}
			sentencia.close();
		} catch (SQLException e) {
			MensajeExcepcion(e);
		}

		return valor;
	}
	
	@Override
	public Empleado ConsultarEmp(int empno) {
		String sql = "SELECT emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no FROM empleados WHERE emp_no = ?";
		PreparedStatement sentencia;
		Empleado emp = new Empleado();
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, empno);
			ResultSet rs = sentencia.executeQuery();
			if (rs.next()) {
				emp.setEmp_no(rs.getInt("emp_no"));
				emp.setApellido(rs.getString("apellido"));
				emp.setOficio(rs.getString("oficio"));
				emp.setDir(rs.getInt("dir"));
				emp.setFecha_alt(rs.getDate("fecha_alt"));
				emp.setSalario(rs.getFloat("salario"));
				emp.setComision(rs.getFloat("comision"));
				emp.setDep(rs.getInt("dept_no"));
			} else
				System.out.printf("Empleado: %d No existe%n", empno);

			rs.close();// liberar recursos
			sentencia.close();

		} catch (SQLException e) {
			MensajeExcepcion(e);
		}
		return emp;
	}
	
	@Override
	public List<Empleado> ListarEmp() {
		List<Empleado> lista = new ArrayList<>();
		String sql = "SELECT emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no FROM empleados";
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				Empleado emp = new Empleado(
					rs.getInt("emp_no"),
					rs.getString("apellido"),
					rs.getString("oficio"),
					rs.getInt("dir"),
					rs.getDate("fecha_alt"),
					rs.getFloat("salario"),
					rs.getFloat("comision"),
					rs.getInt("dept_no")
				);
				lista.add(emp);
			}
			rs.close();
			sentencia.close();
		} catch (SQLException e) {
			MensajeExcepcion(e);
		}
		return lista;
	}	
	
	private void MensajeExcepcion(SQLException e) {
		System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
		System.out.printf("Mensaje   : %s %n", e.getMessage());
		System.out.printf("SQL estado: %s %n", e.getSQLState());
		System.out.printf("Cód error : %s %n", e.getErrorCode());
	}
}