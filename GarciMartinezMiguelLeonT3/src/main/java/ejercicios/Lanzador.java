package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import clases.Camisetas;
import clases.Ciclistas;
import clases.Equipos;
import clases.ResumenCamisetas;
import clases.ResumenCamisetasId;

public class Lanzador {

	private static SessionFactory sesion;

	public static void main(String[] args) {
		int opcion;
		Logger logger = Logger.getLogger("org.hibernate.engine.jdbc.spi.SqlExceptionHelper");
		logger.setLevel(Level.OFF);

		try {
			sesion = Conexion.getSession(); 
			Scanner sc = new Scanner(System.in);

			do {
				menu();
				System.out.println("Selecciona una opción: ");
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					resumenCamisetas();
					break;
				case 2:
					resumenCiclistasEquipo();
					break;
				case 3:
					int opcionSubmenu;
					do {
						subMenu();
						System.out.println("Selecciona una opción: ");
						opcionSubmenu = sc.nextInt();

						switch (opcionSubmenu) {
						case 1: 
							consulta1();
							break;
						case 2:
							consulta2();
							break;
						case 3:
							consulta3();
							break;
						case 4: 
							consulta4();
							break;
						case 0:
							System.out.println("-- Saliendo al menú principal --");
							break;
						default:
							System.out.println("-- Opción NO válida --");
						}

					} while (opcionSubmenu != 0);

					break;
				case 0:
					System.out.println("-- Saliendo de la aplicación --");
					break;
				default:
					System.out.println("Opción NO válida");
				}
			} while (opcion != 0);


		} catch (Exception e) {
			System.err.println("Error general en la aplicación: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (sesion != null) {
				sesion.close(); 
			}
		}
	}


	private static void resumenCamisetas() {
		Transaction tx = null;
		Session session = null;

		try {

			session = sesion.openSession();
			tx = session.beginTransaction();


			String hql = """
					SELECT e.codigoequipo, c.codigociclista, l.id.codigocamiseta, 
					COUNT(l.id.codigocamiseta) AS numveces,
					COUNT(l.id.codigocamiseta) * cm.importepremio AS importepremio
					FROM Lleva l
					JOIN l.ciclistas c
					JOIN c.equipos e
					JOIN l.camisetas cm
					GROUP BY e.codigoequipo, c.codigociclista, l.id.codigocamiseta, cm.importepremio
					ORDER BY e.codigoequipo, c.codigociclista
					"""; 

			Query<Object[]> query = session.createQuery(hql, Object[].class);
			List<Object[]> results = query.getResultList();

			System.out.println("Llenar Tabla RESUMEN-CAMISETAS");
			System.out.println("Equipo :   CAMISETA NºVECES  IMPORTE PREMIO");
			System.out.println("------------------------------------------------------------");


			int lastCodigoequipo = -1;
			String equipoNombre = "";


			for (Object[] row : results) {
				int codigoequipo = (int) row[0];
				int codigociclista = (int) row[1];
				int codigocamiseta = (int) row[2];
				int numveces = ((Long) row[3]).intValue(); 
				float importepremio = (float) row[4];

				Equipos equipos = new Equipos();
				equipos.setCodigoequipo(codigoequipo);

				Ciclistas ciclistas = new Ciclistas();
				ciclistas.setCodigociclista(codigociclista);

				Camisetas camisetas = new Camisetas();
				camisetas.setCodigocamiseta(codigocamiseta);

				ResumenCamisetasId resumenCamisetasId = new ResumenCamisetasId(codigoequipo, codigociclista, codigocamiseta);

				ResumenCamisetas resumen = new ResumenCamisetas();
				resumen.setId(resumenCamisetasId);
				resumen.setEquipos(equipos);
				resumen.setCiclistas(ciclistas);
				resumen.setCamisetas(camisetas);
				resumen.setNumveces(numveces);
				resumen.setImportepremio(importepremio);

				try {
					session.persist(resumen); 
					session.flush(); 

					if (lastCodigoequipo != codigoequipo) {
						if (lastCodigoequipo != -1) {  
							System.out.println(); 
						}
						lastCodigoequipo = codigoequipo;
						equipoNombre = getNombreEquipo(session, codigoequipo); 
						System.out.printf("Equipo : %2d, %-40s CAMISETA NºVECES  IMPORTE PREMIO\n", codigoequipo, equipoNombre);
						System.out.println("------------------------------------------------------------------------------------------------");
					}

					System.out.printf(" Insertado : %3d %-40s %3d   %5d %10.2f\n", 
							codigociclista, getNombreCiclista(session, codigociclista), codigocamiseta, numveces, importepremio);

				} catch (Exception e) {

					System.err.println("Error al intentar insertar el registro, registro duplicado.");

				}
			}


			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.getStatus().canRollback()) {
				tx.rollback();
			}
			//e.printStackTrace();
			System.out.println("Error: Ha ocrrido un error");
		} finally {
			if (session != null) {
				session.close(); 
			}
		}
	}

	private static String getNombreCiclista(Session session, int codigociclista) {

		return session.createQuery("SELECT nombreciclista FROM Ciclistas WHERE codigociclista = :id", String.class)
				.setParameter("id", codigociclista)
				.uniqueResult();
	}

	private static String getNombreEquipo(Session session, int codigoequipo) {

		return session.createQuery("SELECT nombreequipo FROM Equipos WHERE codigoequipo = :id", String.class)
				.setParameter("id", codigoequipo)
				.uniqueResult();
	}

	private static void menu() {

		System.out.println("-------------------------");
		System.out.println("------- CICLISTAS -------");
		System.out.println("-------------------------");
		System.out.println("1. Resumen Camisetas");
		System.out.println("2. Resumen Ciclistas de un equipo");
		System.out.println("3. Consultas Ciclistas");
		System.out.println("0. Salir");
		System.out.println("-------------------------");

	}

	private static void subMenu() {

		System.out.println("-------------------------");
		System.out.println("------- CONSULTAS -------");
		System.out.println("-------------------------");
		System.out.println("1. Consulta 1");
		System.out.println("2. Consulta 2");
		System.out.println("3. Consulta 3");
		System.out.println("4. Consulta 4");
		System.out.println("0. Volver al menú principal");
		System.out.println("-------------------------");

	}

	private static void consulta1() {
		Session session = sesion.openSession();
		Transaction tx = null;

		try {

			System.out.println("#########################################################################################################");

			System.out.println("Datos de las etapas que pasan por algún tramo de montaña y que tienen salida y llegada en la misma\n"
					+ "población. (codigoetapa, km, pobsalida , pobllegada, nombreciclista)");

			System.out.println("#########################################################################################################");
			System.out.println();

			System.out.println();

			System.out.printf("%-6s %-6s %-20s %-20s %-30s%n", 
					"CODIGO", "KM", "POB SALIDA", "POB LLEGADA", "NOMBRE GANADOR ETAPA");
			System.out.printf("%-6s %-6s %-20s %-20s %-30s%n", 
					"======", "======", "===================", "===================", "==============================");

			String hql = "SELECT e.codigoetapa, e.km, e.pobsalida, e.pobllegada, t.ciclistas.nombreciclista " +
					"FROM Etapas e " +
					"INNER JOIN Tramospuertos t ON t.etapas.codigoetapa = e.codigoetapa " +
					"WHERE e.pobsalida = e.pobllegada";

			Query<Object[]> query = session.createQuery(hql, Object[].class);
			List<Object[]> results = query.getResultList();


			for (Object[] row : results) {
				System.out.printf("%-6d %-6.2f %-20s %-20s %-30s%n", 
						row[0], row[1], row[2], row[3], row[4]);
			}


			System.out.printf("%-6s %-6s %-20s %-20s %-30s%n", 
					"======", "======", "===================", "===================", "==============================");

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void consulta2() {

		Session session = sesion.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction(); 
			String hql = "SELECT c.codigociclista, c.nombreciclista, e.codigoetapa, e.tipoetapa, " +
					"t.codigotramo, t.nombretramo, t.categoria " +
					"FROM Ciclistas c " +
					"INNER JOIN Tramospuertos t ON t.ciclistas.codigociclista = c.codigociclista " +
					"INNER JOIN Etapas e ON e.codigoetapa = t.etapas.codigoetapa " +
					"WHERE SUBSTRING(t.pendiente, 1, LENGTH(t.pendiente) - 1) > '5.5' " + 
					"ORDER BY c.codigociclista";

			Query<Object[]> query = session.createQuery(hql, Object[].class);
			List<Object[]> results = query.getResultList();

			System.out.println("#########################################################################################################");

			System.out.println("Obtener el código de ciclista, nombre, código de etapa, tipo, código de tramo, nombre y categoría, de\n"
					+ "aquellos ciclistas que han ganado los puertos que tienen una pendiente del 5,5%, ordenado por código de\n"
					+ "ciclista.");

			System.out.println("#########################################################################################################");
			System.out.println();


			System.out.printf("%-13s %-30s %-10s %-20s %-15s %-40s %-10s%n", 
					"Cod Ciclista", "Nombre Ciclista", "Cod Etapa", "Tipo Etapa", 
					"Cod Tramo", "Nombre Tramo", "Categoría");


			System.out.printf("%-13s %-30s %-10s %-20s %-15s %-40s %-10s%n",
					"=============", "==============================", "==========", "====================",
					"===============", "========================================", "==========");


			for (Object[] row : results) {
				System.out.printf("%-13s %-30s %-10s %-20s %-15s %-40s %-10s%n", 
						row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
			}

			System.out.printf("%-13s %-30s %-10s %-20s %-15s %-40s %-10s%n",
					"=============", "==============================", "==========", "====================",
					"===============", "========================================", "==========");

			tx.commit();  
		} catch (Exception e) {
			if (tx != null) tx.rollback();  
			e.printStackTrace();
		} finally {
			session.close();  
		}


	}

	private static void consulta3() {

		Session session = sesion.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction(); 
			String hql = "SELECT "
					+ "    e.codigoequipo, "
					+ "    e.nombreequipo, "
					+ "    c.nombreciclista, "
					+ "    SUM(r.numveces) "
					+ "FROM "
					+ "    ResumenCamisetas r "
					+ "INNER JOIN "
					+ "    r.equipos e "
					+ "INNER JOIN "
					+ "    r.ciclistas c "
					+ "INNER JOIN "
					+ "    r.camisetas cami "
					+ "WHERE "
					+ "    cami.color = 'Lunares' "
					+ "GROUP BY "
					+ "    e.codigoequipo, e.nombreequipo, c.nombreciclista "
					+ "ORDER BY "
					+ "    e.codigoequipo, c.nombreciclista";

			Query<Object[]> query = session.createQuery(hql, Object[].class);
			List<Object[]> results = query.getResultList();

			System.out.println("#########################################################################################################");

			System.out.println("Consulta que muestre los ciclistas que han llevado alguna vez camiseta de color Lunares, indicando durante\n"
					+ "cuántas etapas lo han llevado. Mostrar el código de equipo, nombre, nombre de ciclista y el número de\n"
					+ "etapas en las que ha llevado la camiseta de Lunares ordenado por código de equipo y nombre de ciclista.");

			System.out.println("#########################################################################################################");
			System.out.println();


			System.out.printf("%-20s %-30s %-30s %-30s%n", 
					"Cod Equipo", "Nombre Equipo", "Nombre Ciclista", "NUM VECES LUNARES");


			System.out.printf("%-20s %-30s %-30s %-30s%n", 
					"====================", "==============================", "==============================", 
					"====================");


			for (Object[] row : results) {
				System.out.printf("%-20s %-30s %-30s %-30s%n", 
						row[0], row[1], row[2], row[3]);
			}

			System.out.printf("%-20s %-30s %-30s %-30s%n", 
					"====================", "==============================", "==============================", 
					"====================");

			tx.commit();  
		} catch (Exception e) {
			if (tx != null) tx.rollback();  
			e.printStackTrace();
		} finally {
			session.close();  
		}

	}
	
	private static void consulta4() {

		Session session = sesion.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction(); 
			String hql = "SELECT "
			           + "    e.nombreequipo, "
			           + "    cami.codigocamiseta, "
			           + "    cami.color, "
			           + "    SUM(r.numveces) "
			           + "FROM "
			           + "    ResumenCamisetas r "
			           + "JOIN "
			           + "    r.equipos e "
			           + "JOIN "
			           + "    r.camisetas cami "
			           + "GROUP BY "
			           + "    e.nombreequipo, cami.codigocamiseta, cami.color "
			           + "ORDER BY "
			           + "    e.codigoequipo, cami.codigocamiseta";


			Query<Object[]> query = session.createQuery(hql, Object[].class);
			List<Object[]> results = query.getResultList();

			System.out.println("#########################################################################################################");

			System.out.println("Por cada equipo, el número de veces que sus ciclistas han llevado camisetas mostrando el codigoequipo,\n"
					+ "nombreequipo, codigocamiseta, color y número de veces que han llevado la camiseta.");

			System.out.println("#########################################################################################################");
			System.out.println();


			System.out.printf("%-30s %-30s %-30s %-30s%n", 
					"Nombre Equipo", "Codigo Camiseta", "Color", "NUM VECES");


			System.out.printf("%-30s %-30s %-30s %-30s%n", 
					"==============================", "==============================", "==============================", 
					"====================");


			for (Object[] row : results) {
				System.out.printf("%-30s %-30s %-30s %-30s%n", 
						row[0], row[1], row[2], row[3]);
			}

			System.out.printf("%-30s %-30s %-30s %-30s%n", 
					"==============================", "==============================", "==============================", 
					"====================");

			tx.commit();  
		} catch (Exception e) {
			if (tx != null) tx.rollback();  
			e.printStackTrace();
		} finally {
			session.close();  
		}

	}

	private static void resumenCiclistasEquipo() {
		Session session = sesion.openSession();
		Scanner scanner = new Scanner(System.in);

		System.out.print("Introduce el código del equipo: ");
		int codigoEquipo = scanner.nextInt();

		Transaction tx = null;

		try {
			tx = session.beginTransaction();


			Equipos equipo = session.get(Equipos.class, codigoEquipo);
			if (equipo == null) {
				System.out.println("El equipo con código " + codigoEquipo + " no existe.");
				return;
			}


			System.out.println("COD-EQUIPO: " + codigoEquipo + "   NOMBRE: " + equipo.getNombreequipo());
			System.out.println("PAIS: " + equipo.getPais() + ", Jefe de Equipo: " + equipo.getDirector());
			System.out.println("-".repeat(100));
			System.out.println("CODIGO  NOMBRE                                       Etap Ganadas  Tramos Ganados  Nº VecesCamiseta");
			System.out.println("======  ============================================ ============  ==============  ================");


			List<Ciclistas> ciclistas = session.createQuery(
					"FROM Ciclistas WHERE equipos.codigoequipo = :codigoequipo ORDER BY codigociclista", Ciclistas.class)
					.setParameter("codigoequipo", codigoEquipo)
					.getResultList();

			if (ciclistas.isEmpty()) {
				System.out.println("El equipo no tiene ciclistas.");
				return;
			}


			int maxEtapas = 0;
			int maxTramos = 0;
			List<String> mejoresEtapas = new ArrayList<>();
			List<String> mejoresTramos = new ArrayList<>();


			for (Ciclistas ciclista : ciclistas) {
				int etapasGanadas = session.createQuery(
						"SELECT COUNT(e) FROM Etapas e WHERE e.ciclistas.codigociclista = :codigociclista", Long.class)
						.setParameter("codigociclista", ciclista.getCodigociclista())
						.uniqueResult()
						.intValue();

				int tramosGanados = session.createQuery(
						"SELECT COUNT(t) FROM Tramospuertos t WHERE t.ciclistas.codigociclista = :codigociclista", Long.class)
						.setParameter("codigociclista", ciclista.getCodigociclista())
						.uniqueResult()
						.intValue();


				int vecesCamiseta = session.createQuery(
						"SELECT COUNT(l) FROM Lleva l WHERE l.ciclistas.codigociclista = :codigociclista", Long.class)
						.setParameter("codigociclista", ciclista.getCodigociclista())
						.uniqueResult()
						.intValue();


				if (etapasGanadas > maxEtapas) {
					maxEtapas = etapasGanadas;
					mejoresEtapas.clear();
					mejoresEtapas.add(ciclista.getNombreciclista());
				} else if (etapasGanadas == maxEtapas) {
					mejoresEtapas.add(ciclista.getNombreciclista());
				}

				if (tramosGanados > maxTramos) {
					maxTramos = tramosGanados;
					mejoresTramos.clear();
					mejoresTramos.add(ciclista.getNombreciclista());
				} else if (tramosGanados == maxTramos) {
					// mejoresTramos.add(ciclista.getNombreciclista());
				}


				System.out.printf("%-6d  %-50s %-12d %-14d %-18d%n",
						ciclista.getCodigociclista(),
						ciclista.getNombreciclista(),
						etapasGanadas,
						tramosGanados,
						vecesCamiseta);
			}


			System.out.println("======  ============================================ ============  ==============  ================");


			if (mejoresEtapas.isEmpty()) {
				System.out.println("Nombres de corredor/es con más etapas ganadas: No Hay");
			} else {
				String nombresEtapas = String.join(", ", mejoresEtapas);
				System.out.println("Nombres de corredor/es con más etapas ganadas: " + nombresEtapas);
			}


			if (mejoresTramos.isEmpty()) {
				System.out.println("Nombres de corredor/es con más tramos de montaña ganados: No Hay");
			} else {
				String nombresTramos = String.join(", ", mejoresTramos);
				System.out.println("Nombres de corredor/es con más tramos de montaña ganados: " + nombresTramos);
			}


			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

	}



}
