package clases;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Cargar la configuración de Hibernate (hibernate.cfg.xml)
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    // Método para obtener la SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Método para oftener una nueva sesión
    public static Session getSession() {
        return getSessionFactory().openSession();
    }

    // Método para cerrar la SessionFactory
    public static void shutdown() {
        getSessionFactory().close();
    }
}
