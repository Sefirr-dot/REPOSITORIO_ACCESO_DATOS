package dao;

import java.util.List;

import model.Empleado;
public interface EmpleadoDAO {
	public boolean InsertarEmp(Empleado emp);
	public boolean EliminarEmp(int empno);
	public boolean ModificarEmp(int empno, Empleado emp);
	public Empleado ConsultarEmp(int empno);
	public List<Empleado> ListarEmp();
}