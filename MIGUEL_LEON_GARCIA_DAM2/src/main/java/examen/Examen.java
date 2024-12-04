package examen;

import java.sql.*;

public class Examen {

    static Connection connection;

    public static void main(String[] args) throws SQLException {
        // Conexión a la base de datos Oracle
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:free", "system", "Ora1234");
        System.out.println("Examen del Miguel-León García Martínez DAM 2 Tardes");

        // Llamada a los ejercicios 
        ejercicio1();
        
        //Codigo IES Planeta Tierra
        ejercicio2(1022);
        
        //Con esos datos añadira la asignatura al profesor ya que no la tiene
        ejercicio3(2000, "IF0002");
    }

    public static void ejercicio1() {
        try (Statement statement1 = connection.createStatement()) {
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("                               EJERCICIO 1");
            System.out.println("-----------------------------------------------------------------------------");

            // Intentar añadir la columna si no existe
            try {
                statement1.executeUpdate("ALTER TABLE C1_PROFESORES ADD NUM_ASIG NUMBER");
                System.out.println("Columna NUM_ASIG añadida.");
            } catch (SQLException e) {
                if (e.getErrorCode() == 1430) { // Error específico si ya existe
                    System.out.println("La columna NUM_ASIG ya existe.");
                } else {
                    throw e;
                }
            }

            // Actualizar la columna NUM_ASIG con la cantidad de asignaturas
            statement1.executeUpdate(
                "UPDATE C1_PROFESORES P SET NUM_ASIG = (SELECT COUNT(*) FROM C1_ASIGPROF A WHERE A.C1_PROFESORES_COD_PROF = P.COD_PROF)"
            );
            System.out.println("Columna NUM_ASIG actualizada.");

            // Mostrar el resultado
            ResultSet rs = statement1.executeQuery(
                "SELECT A.COD_ASIG, A.NOMBRE_ASI, P.COD_PROF, P.NOMBRE_APE FROM C1_ASIGPROF AP " +
                "JOIN C1_ASIGNATURAS A ON AP.C1_ASIGNATURAS_COD_ASIG = A.COD_ASIG " +
                "JOIN C1_PROFESORES P ON AP.C1_PROFESORES_COD_PROF = P.COD_PROF"
            );

            System.out.printf("%-8s %-20s %-8s %-30s%n", "COD ASIG", "NOMBRE ASIG", "COD PROF", "NOMBRE PROF");
            System.out.println("-------- -------------------- -------- ------------------------------");
            while (rs.next()) {
                System.out.printf("%-8s %-20s %-8s %-30s%n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            System.out.println("-----------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Error en ejercicio1: " + e.getMessage());
        }
    }

    public static void ejercicio2(int codCentro) {
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(
                "SELECT C.COD_CENTRO, C.NOM_CENTRO, P.COD_PROF, P.NOMBRE_APE, P.ESPECIALIDAD, " +
                "NVL(P.NUM_ASIG, 0) AS NUM_ASIG, COALESCE(J.NOMBRE_APE, 'No tiene') AS NOMBRE_JEFE " +
                "FROM C1_CENTROS C LEFT JOIN C1_PROFESORES P ON C.COD_CENTRO = P.COD_CENTRO " +
                "LEFT JOIN C1_PROFESORES J ON P.COD_PROF1 = J.COD_PROF WHERE C.COD_CENTRO = ?")) {
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("                               EJERCICIO 2");
            System.out.println("-----------------------------------------------------------------------------");
            preparedStatement1.setInt(1, codCentro);
            ResultSet rs = preparedStatement1.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("El centro no existe.");
                System.out.println("-----------------------------------------------------------------------------");
                return;
            }

            rs.next();
            System.out.printf("COD-CENTRO: %d  NOMBRE CENTRO: %s%n", rs.getInt("COD_CENTRO"), rs.getString("NOM_CENTRO"));
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("PROFESORES DEL CENTRO");
            System.out.printf("%-8s %-25s %-12s %-8s %-25s%n", "COD-PROF", "NOMBRE", "ESPECIALIDAD", "NUM-ASIG", "NOMBRE JEFE");
            System.out.println("-------- ------------------------- ------------ -------- -------------------------");
            do {
                System.out.printf("%-8d %-25s %-12s %-8d %-25s%n",
                        rs.getInt("COD_PROF"), rs.getString("NOMBRE_APE"), rs.getString("ESPECIALIDAD"),
                        rs.getInt("NUM_ASIG"), rs.getString("NOMBRE_JEFE"));
            } while (rs.next());

            // Profesor con más asignaturas
            String maxQuery = "SELECT P.NOMBRE_APE FROM C1_PROFESORES P WHERE P.COD_CENTRO = ? AND P.NUM_ASIG = (SELECT MAX(NUM_ASIG) FROM C1_PROFESORES WHERE COD_CENTRO = ?)";
            try (PreparedStatement pstmtMax = connection.prepareStatement(maxQuery)) {
                pstmtMax.setInt(1, codCentro);
                pstmtMax.setInt(2, codCentro);
                ResultSet rsMax = pstmtMax.executeQuery();

                if (rsMax.isBeforeFirst()) {
                	System.out.println("------------------------------------------------------------------------------");
                    System.out.print("Nombre de profesor que imparte más asignaturas: ");
                    while (rsMax.next()) {
                        System.out.print(rsMax.getString(1) + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("No hay profesores con asignaturas en este centro.");
                }
            }
            System.out.println("-----------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Error en ejercicio2: " + e.getMessage());
        }
    }

    public static void ejercicio3(int codProf, String codAsig) {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("                               EJERCICIO 3");
        System.out.println("-----------------------------------------------------------------------------");
        try {
            // Comprobar si el profesor existe
            try (PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT COUNT(*) FROM C1_PROFESORES WHERE COD_PROF = ?")) {
                preparedStatement1.setInt(1, codProf);
                ResultSet rs = preparedStatement1.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    System.out.println("El profesor no existe.");
                    System.out.println("-----------------------------------------------------------------------------");
                    return;
                }
            }

            // Comprobar si la asignatura existe
            try (PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) FROM C1_ASIGNATURAS WHERE COD_ASIG = ?")) {
                pstmt.setString(1, codAsig);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    System.out.println("La asignatura no existe.");
                    System.out.println("-----------------------------------------------------------------------------");
                    return;
                }
            }

            // Verificar si ya imparte la asignatura
            try (PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) FROM C1_ASIGPROF WHERE C1_PROFESORES_COD_PROF = ? AND C1_ASIGNATURAS_COD_ASIG = ?")) {
                pstmt.setInt(1, codProf);
                pstmt.setString(2, codAsig);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("El profesor ya imparte esta asignatura.");
                    System.out.println("-----------------------------------------------------------------------------");
                    return;
                }
            }

            // Insertar la nueva relación
            try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO C1_ASIGPROF VALUES (?, ?)")) {
                pstmt.setString(1, codAsig);
                pstmt.setInt(2, codProf);
                pstmt.executeUpdate();
                System.out.println("Registro añadido a C1_ASIGPROF.");
            }

            // Actualizar el número de asignaturas del profesor
            try (PreparedStatement pstmt = connection.prepareStatement("UPDATE C1_PROFESORES SET NUM_ASIG = NUM_ASIG + 1 WHERE COD_PROF = ?")) {
                pstmt.setInt(1, codProf);
                pstmt.executeUpdate();
                System.out.println("Columna NUM_ASIG actualizada.");
            }
            System.out.println("-----------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Error en ejercicio3: " + e.getMessage());
            System.out.println("-----------------------------------------------------------------------------");
            //Espero que este todo bien :D
        }
    }
}
