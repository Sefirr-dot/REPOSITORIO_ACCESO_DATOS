package ejercicios;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class Conexion {
	private static final SessionFactory sessionFactory;
	static {
		try {
			
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			
			System.err.println("INICIO DE SessionFactory fallado.." + ex);
			throw new ExceptionInInitializerError(ex);
			
		}
	}
	public static SessionFactory getSession() {
		return sessionFactory;
	}
}
