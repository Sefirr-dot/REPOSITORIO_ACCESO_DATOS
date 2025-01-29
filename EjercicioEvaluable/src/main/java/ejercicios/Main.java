package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import modelos.Estudiante;
import modelos.Participa;
import modelos.Proyecto;

import java.util.Scanner;

import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("OPERACIONES PROYECTOS");
            System.out.println("1. Crear BD");
            System.out.println("2. Listar un proyecto");
            System.out.println("3. Insertar participación");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearBD();
                    break;
                case 2:
                	System.out.println("Di el codigo de proyecto que quieres listar: ");
                	int num = scanner.nextInt();
                    listarProyecto(num);
                    break;
                case 3:
                	
                	insertarParticipacion(1, 1, "Asesor", 5);

                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void crearBD() {
    	List<Proyecto> proyectos = leerProyectosDesdeMySQL();
        List<Estudiante> estudiantes = leerEstudiantesDesdeMySQL();
        List<Participa> participaciones = leerParticipacionesDesdeMySQL(estudiantes, proyectos);

        // Crear la base de datos Neodatis
        crearBaseDatosNeodatis(proyectos, estudiantes, participaciones);
    }

    private static List<Proyecto> leerProyectosDesdeMySQL() {
        List<Proyecto> proyectos = new ArrayList<>();
        String query = "SELECT * FROM proyectos";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectos", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int codigoproyecto = rs.getInt("codigoproyecto");
                String nombre = rs.getString("nombre");
                Date fechainicio = rs.getDate("fechainicio");
                Date fechafin = rs.getDate("fechafin");
                float presupuesto = rs.getFloat("presupuesto");
                float extraaportacion = rs.getFloat("extraaportacion");

                Proyecto proyecto = new Proyecto(codigoproyecto, nombre, fechainicio, fechafin, presupuesto, extraaportacion);
                proyectos.add(proyecto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyectos;
    }

    private static List<Estudiante> leerEstudiantesDesdeMySQL() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String query = "SELECT * FROM estudiantes";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectos", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int codestudiante = rs.getInt("codestudiante");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String tlf = rs.getString("tlf");
                Date fechaalta = rs.getDate("fechaalta");

                Estudiante estudiante = new Estudiante(codestudiante, nombre, direccion, tlf, fechaalta);
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudiantes;
    }

    private static List<Participa> leerParticipacionesDesdeMySQL(List<Estudiante> estudiantes, List<Proyecto> proyectos) {
        List<Participa> participaciones = new ArrayList<>();
        String query = "SELECT * FROM participa";

        // Mapa para buscar estudiantes y proyectos por su código
        Map<Integer, Estudiante> mapaEstudiantes = new HashMap<>();
        for (Estudiante estudiante : estudiantes) {
            mapaEstudiantes.put(estudiante.getCodestudiante(), estudiante);
        }

        Map<Integer, Proyecto> mapaProyectos = new HashMap<>();
        for (Proyecto proyecto : proyectos) {
            mapaProyectos.put(proyecto.getCodigoproyecto(), proyecto);
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectos", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int codparticipacion = rs.getInt("codparticipacion");
                int codestudiante = rs.getInt("codestudiante");
                int codigoproyecto = rs.getInt("codigoproyecto");
                String tipoparticipacion = rs.getString("tipoparticipacion");
                int numaportaciones = rs.getInt("numaportaciones");

                Estudiante estudiante = mapaEstudiantes.get(codestudiante);
                Proyecto proyecto = mapaProyectos.get(codigoproyecto);

                if (estudiante != null && proyecto != null) {
                    Participa participa = new Participa(codparticipacion, estudiante, proyecto, tipoparticipacion, numaportaciones);
                    participaciones.add(participa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return participaciones;
    }

    private static void crearBaseDatosNeodatis(List<Proyecto> proyectos, List<Estudiante> estudiantes, List<Participa> participaciones) {
    	ODB odb = ODBFactory.open("proyectos.db");

    	// Almacenar proyectos y estudiantes primero
    	for (Proyecto proyecto : proyectos) {
    	    odb.store(proyecto);
    	}
    	for (Estudiante estudiante : estudiantes) {
    	    odb.store(estudiante);
    	}

    	// Luego almacenar las participaciones y actualizar listas
    	for (Participa participa : participaciones) {
    	    participa.getEstudiante().getParticipaen().add(participa);
    	    participa.getProyecto().getParticipantes().add(participa);
    	    odb.store(participa);
    	}

    	// Finalmente, volver a almacenar estudiantes y proyectos con las listas actualizadas
    	for (Proyecto proyecto : proyectos) {
    	    odb.store(proyecto);
    	}
    	for (Estudiante estudiante : estudiantes) {
    	    odb.store(estudiante);
    	}

    	odb.close();

        System.out.println("Base de datos Neodatis creada correctamente.");
    }


    private static void listarProyecto(int codigoProyecto) {
        // Abrir la base de datos
        ODB odb = ODBFactory.open("proyectos.db");

        // Buscar un proyecto por su código
        IQuery query = new CriteriaQuery(Proyecto.class, Where.equal("codigoproyecto", codigoProyecto));
        Objects<Proyecto> proyectos = odb.getObjects(query);

        if (proyectos.hasNext()) {
            Proyecto proyecto = proyectos.next();

            // Encabezado del proyecto
            System.out.printf("Código proyecto: %d           Nombre: %s\n", proyecto.getCodigoproyecto(), proyecto.getNombre());
            System.out.printf("Fecha inicio: %s     Fecha fin: %s\n", proyecto.getFechainicio(), proyecto.getFechafin());
            System.out.printf("Presupuesto: %.2f       Extraaportación: %.2f\n", proyecto.getPresupuesto(), proyecto.getExtraaportacion());
            System.out.println("--------------------- --------------------- -----------------------");

            List<Participa> participantes = proyecto.getParticipantes();

            if (participantes.isEmpty()) {
                System.out.println("No hay participantes en este proyecto.");
                return;
            }

            System.out.println("Participantes en el proyecto:");
            System.out.println("----------------------------");
            System.out.printf("%-18s %-18s %-24s %-18s %-18s %-10s\n",
                    "CODPARTICIPACION", "CODESTUDIANTE", "NOMBREESTUDIANTE", "TIPAPORTACION", "NUMAPORTACIONES", "IMPORTE");
            System.out.println("----------------  ------------------- ------------------------ ------------------ ------------------ ----------");

            int totalAportaciones = 0;
            float totalImporte = 0.0f;

            for (Participa participa : participantes) {
                Estudiante estudiante = participa.getEstudiante();
                float importe = participa.getNumaportaciones() * proyecto.getExtraaportacion();
                System.out.printf("%-18d %-18d %-24s %-18s %-18d %-10.2f\n",
                        participa.getCodparticipacion(),
                        estudiante.getCodestudiante(),
                        estudiante.getNombre(),
                        participa.getTipoparticipacion(),
                        participa.getNumaportaciones(),
                        importe);
                totalAportaciones += participa.getNumaportaciones();
                totalImporte += importe;
            }

            System.out.println("----------------  ------------------- ------------------------ ------------------ ------------------ ----------");
            System.out.printf("TOTALES:                                                                          %-18d %-10.2f\n",
                    totalAportaciones, totalImporte);

        } else {
            System.out.println("El proyecto no existe en la BD.");
        }

        // Cerrar la base de datos
        odb.close();
    }



    private static void insertarParticipacion(int codEstudiante, int codProyecto, String tipoAportacion, int numAportaciones) {
        ODB odb = ODBFactory.open("proyectos.db");

        // Buscar el proyecto y el estudiante
        IQuery queryProyecto = new CriteriaQuery(Proyecto.class, Where.equal("codigoproyecto", codProyecto));
        Objects<Proyecto> proyectos = odb.getObjects(queryProyecto);

        IQuery queryEstudiante = new CriteriaQuery(Estudiante.class, Where.equal("codestudiante", codEstudiante));
        Objects<Estudiante> estudiantes = odb.getObjects(queryEstudiante);

        if (proyectos.hasNext() && estudiantes.hasNext()) {
            Proyecto proyecto = proyectos.next();
            Estudiante estudiante = estudiantes.next();

            // Crear una nueva participación
            Participa participa = new Participa(getNextCodParticipacion(odb), estudiante, proyecto, tipoAportacion, numAportaciones);
            odb.store(participa);

            // Actualizar las listas de participaciones en el proyecto y el estudiante
            proyecto.getParticipantes().add(participa);
            estudiante.getParticipaen().add(participa);

            odb.store(proyecto);
            odb.store(estudiante);

            System.out.println("Participación insertada correctamente.");
        } else {
            System.out.println("El proyecto o el estudiante no existen en la BD.");
        }

        odb.close();
    }

    
    private static int getNextCodParticipacion(ODB odb) {
        Objects<Participa> participaciones = odb.getObjects(Participa.class);
        int maxCod = 0;
        while (participaciones.hasNext()) {
            Participa participa = participaciones.next();
            if (participa.getCodparticipacion() > maxCod) {
                maxCod = participa.getCodparticipacion();
            }
        }
        return maxCod + 1;
    }
}