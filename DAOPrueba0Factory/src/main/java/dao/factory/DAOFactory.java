package dao.factory;

import dao.factory.DAOFactoryNeodatis;
import dao.factory.DAOFactoryMysql;
import dao.DepartamentoDAO;

public abstract class DAOFactory {
	// Bases de datos soportadas
	public static final int MYSQL = 1;
	public static final int NEODATIS = 2;

	public abstract DepartamentoDAO getDepartamentoDAO();

	public static DAOFactory getDAOFactory(int bd) {
		switch (bd) {
		case MYSQL:
			return new DAOFactoryMysql();
		case NEODATIS:
			return new DAOFactoryNeodatis();
		default:
			return null;
		}
	}
}