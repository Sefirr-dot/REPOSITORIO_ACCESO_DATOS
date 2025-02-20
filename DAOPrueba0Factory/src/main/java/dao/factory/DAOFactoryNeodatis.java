package dao.factory;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import dao.DepartamentoDAO;
import dao.impl.neodatis.DepartamentoImplNeodatis;

public class DAOFactoryNeodatis extends DAOFactory {
	static ODB odb = null;

	public DAOFactoryNeodatis() {
	}

	public static ODB crearConexion() {
		if (odb == null) {
			odb = ODBFactory.open("Departamento.BD");
		}
		return odb;
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		return new DepartamentoImplNeodatis();
	}

}
