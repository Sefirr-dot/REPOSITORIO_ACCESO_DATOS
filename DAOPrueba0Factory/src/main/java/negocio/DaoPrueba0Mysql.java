package negocio;

import java.util.List;

import dao.DepartamentoDAO;
import dao.factory.DAOFactory;
import model.Departamento;

public class DaoPrueba0Mysql {
	public static void main(String[] args) {
        DAOFactory bd = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        DepartamentoDAO depDAO = bd.getDepartamentoDAO();
		// INSERTAR
		Departamento dep1 = new Departamento(17, "NÓMINAS", "SEVILLA");
		depDAO.InsertarDep(dep1);

		// CONSULTAR
		Departamento dep2 = depDAO.ConsultarDep(17);
		System.out.printf("Dep: %d, Nombre: %s, Loc: %s %n", dep2.getDeptno(), dep2.getDnombre(), dep2.getLoc());

		// MODIFICAR
		dep2.setDnombre("nuevonom");
		dep2.setLoc("nuevaloc");
		depDAO.ModificarDep(17, dep2);
		System.out.printf("Dep Modificado: %d, Nombre: %s, Loc: %s %n", dep2.getDeptno(), dep2.getDnombre(),
				dep2.getLoc());

		// LISTAR DEPARTAMENTOS

		List<Departamento> departamentos = depDAO.ListarDep(); // Llamamos al método listarDep para obtener la lista
		if (departamentos != null && !departamentos.isEmpty()) {
			for (Departamento dep : departamentos) {
				System.out.println("Dep Listado ID: " + dep.getDeptno() + ", Nombre: " + dep.getDnombre()
						+ ", Localidad: " + dep.getLoc());
			}
		} else {
			System.out.println("No existen departamentos.");
		}

		// ELIMINAR
		depDAO.EliminarDep(17);

	}
}
