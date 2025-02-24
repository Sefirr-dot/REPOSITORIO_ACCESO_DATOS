package negocio;

import java.sql.Date;
import java.util.List;

import dao.DepartamentoDAO;
import dao.EmpleadoDAO;
//import dao.impl.neodatis.DepartamentoImplNeodatis;
import dao.impl.mysql.DepartamentoImplMysql;
import dao.impl.mysql.EmpleadoImplMysql;
import model.Departamento;
import model.Empleado;

public class DaoPrueba0Mysql {
	public static void main(String[] args) {
		//DepartamentoDAO depDAO = new DepartamentoImplNeodatis();
		EmpleadoDAO empDAO = new EmpleadoImplMysql();
		// INSERTAR
		Empleado emp1 = new Empleado(8000, "GARCIA", "EMPLEADP", 6777,  Date.valueOf("1999-09-09"), 766, 77, 10);
		empDAO.InsertarEmp(emp1);

		// CONSULTAR
		Empleado emp2 = empDAO.ConsultarEmp(8000);
		System.out.printf("Dep: %d, Apellido: %s, Comision: %s %n", emp2.getDep(), emp2.getApellido(), emp2.getComision());

		// MODIFICAR
		emp2.setApellido("nuevonom");
		emp2.setComision(23);
		empDAO.ModificarEmp(8000, emp2);
		System.out.printf("Emp Modificado: %d, Apellido: %s, Comision: %s %n", emp2.getEmp_no(), emp2.getApellido(),
				emp2.getComision());

		// LISTAR DEPARTAMENTOS

		List<Empleado> empleados = empDAO.ListarEmp(); // Llamamos al método listarDep para obtener la lista
		if (empleados != null && !empleados.isEmpty()) {
			for (Empleado dep : empleados) {
				System.out.println("Emp Listado ID: " + dep.getEmp_no() + ", Apellido: " + dep.getApellido()
						+ ", Comision: " + dep.getComision());
			}
		} else {
			System.out.println("No existen departamentos.");
		}

		// ELIMINAR
		empDAO.EliminarEmp(8000);

	}
}
