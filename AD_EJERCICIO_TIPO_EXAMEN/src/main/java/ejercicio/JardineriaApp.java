package ejercicio;

import java.sql.*;
import java.util.Scanner;

public class JardineriaApp {

    // Clase para manejar la conexión a la base de datos
    static class DatabaseConnection {
        private static final String URL = "jdbc:oracle:thin:@localhost:1521:free";
        private static final String USER = "system";
        private static final String PASSWORD = "Ora1234";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    // Clase para los métodos de cada ejercicio
    static class JardineriaOperations {

        // Ejercicio 1: Insertar empleado
        public static void insertarEmpleado(String nombre, String apellido1, String apellido2, String extension,
                                            String email, String codigoOficina, int codigoJefe, String puesto) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                // Comprobar si existen el código de oficina y el código del jefe
                String checkQuery = "SELECT COUNT(*) FROM OFICINAS WHERE CODIGOOFICINA = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                checkStmt.setString(1, codigoOficina);
                ResultSet rs = checkStmt.executeQuery();
                if (!rs.next() || rs.getInt(1) == 0) {
                    System.out.println("Error: La oficina no existe.");
                    return;
                }

                // Obtener el máximo código de empleado
                String maxCodeQuery = "SELECT MAX(CODIGOEMPLEADO) FROM EMPLEADOS";
                Statement maxStmt = conn.createStatement();
                rs = maxStmt.executeQuery(maxCodeQuery);
                int nuevoCodigo = 1;
                if (rs.next()) {
                    nuevoCodigo = rs.getInt(1) + 1;
                }

                // Insertar el nuevo empleado
                String insertQuery = "INSERT INTO EMPLEADOS (CODIGOEMPLEADO, NOMBRE, APELLIDO1, APELLIDO2, EXTENSION, EMAIL, " +
                        "CODIGOOFICINA, CODIGOJEFE, PUESTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setInt(1, nuevoCodigo);
                insertStmt.setString(2, nombre);
                insertStmt.setString(3, apellido1);
                insertStmt.setString(4, apellido2);
                insertStmt.setString(5, extension);
                insertStmt.setString(6, email);
                insertStmt.setString(7, codigoOficina);
                insertStmt.setInt(8, codigoJefe);
                insertStmt.setString(9, puesto);

                int rows = insertStmt.executeUpdate();
                System.out.println("Empleado insertado correctamente. Filas afectadas: " + rows);

            } catch (SQLException e) {
                System.err.println("Error al insertar empleado: " + e.getMessage());
            }
        }

        // Esqueleto de los demás ejercicios
        public static void visualizarPedidosCliente(int codigoCliente) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                // Verificar si el cliente existe
                String clienteQuery = "SELECT NOMBRECLIENTE, LINEADIRECCION1 FROM CLIENTES WHERE CODIGOCLIENTE = ?";
                PreparedStatement clienteStmt = conn.prepareStatement(clienteQuery);
                clienteStmt.setInt(1, codigoCliente);
                ResultSet clienteRs = clienteStmt.executeQuery();

                if (!clienteRs.next()) {
                    System.out.println("Error: El cliente no existe.");
                    return;
                }

                String nombreCliente = clienteRs.getString("NOMBRECLIENTE");
                String direccionCliente = clienteRs.getString("LINEADIRECCION1");
                System.out.printf("COD-CLIENTE: %d    NOMBRE: %s\nDIRECCIÓN1: %s\n", 
                        codigoCliente, nombreCliente, direccionCliente);

                // Recuperar pedidos del cliente
                String pedidosQuery = "SELECT P.CODIGOPEDIDO, P.FECHAPEDIDO, P.ESTADO, DP.NUMEROLINEA, "
                		+ " DP.CODIGOPRODUCTO, PR.NOMBRE AS NOMBREPRODUCTO, DP.CANTIDAD, DP.PRECIOUNIDAD, (DP.CANTIDAD * DP.PRECIOUNIDAD) AS IMPORTE "
                		+ "FROM PEDIDOS P "
                		+ "JOIN DETALLEPEDIDOS DP ON P.CODIGOPEDIDO = DP.CODIGOPEDIDO "
                		+ "JOIN PRODUCTOS PR ON DP.CODIGOPRODUCTO = PR.CODIGOPRODUCTO "
                		+ "WHERE P.CODIGOCLIENTE = ? "
                		+ "ORDER BY P.CODIGOPEDIDO, DP.NUMEROLINEA";
                PreparedStatement pedidosStmt = conn.prepareStatement(pedidosQuery);
                pedidosStmt.setInt(1, codigoCliente);
                ResultSet pedidosRs = pedidosStmt.executeQuery();

                if (!pedidosRs.next()) {
                    System.out.println("El cliente no tiene pedidos.");
                    return;
                }

                int currentPedido = -1;
                double totalPedido = 0;
                do {
                    int codigoPedido = pedidosRs.getInt("CODIGOPEDIDO");
                    if (codigoPedido != currentPedido) {
                        if (currentPedido != -1) {
                            System.out.printf("TOTALES POR PEDIDO: %.2f\n\n", totalPedido);
                        }
                        currentPedido = codigoPedido;
                        totalPedido = 0;
                        System.out.printf("COD-PEDIDO: %d    FECHA PEDIDO: %s    ESTADO: %s\n", 
                                codigoPedido, pedidosRs.getDate("FECHAPEDIDO"), pedidosRs.getString("ESTADO"));
                    }

                    int cantidad = pedidosRs.getInt("CANTIDAD");
                    double precioUnidad = pedidosRs.getDouble("PRECIOUNIDAD");
                    double importe = pedidosRs.getDouble("IMPORTE");
                    totalPedido += importe;

                    System.out.printf("LINEA: %d    PROD: %s    NOMBRE: %s    CANT: %d    PRECIO: %.2f    IMPORTE: %.2f\n",
                            pedidosRs.getInt("NUMEROLINEA"), pedidosRs.getString("CODIGOPRODUCTO"),
                            pedidosRs.getString("NOMBREPRODUCTO"), cantidad, precioUnidad, importe);
                } while (pedidosRs.next());

                System.out.printf("TOTALES POR PEDIDO: %.2f\n\n", totalPedido);
            } catch (SQLException e) {
                System.err.println("Error al consultar pedidos: " + e.getMessage());
            }
        }


        public static void crearClientesSinPedido() {
            try (Connection conn = DatabaseConnection.getConnection()) {
                // Crear la tabla CLIENTESSINPEDIDO si no existe
                String createTableQuery = "CREATE TABLE CLIENTESSINPEDIDO AS "
                		+ "SELECT * FROM CLIENTES WHERE 1=0";
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableQuery);
                    System.out.println("Tabla CLIENTESSINPEDIDO creada.");
                } catch (SQLException e) {
                    System.out.println("La tabla CLIENTESSINPEDIDO ya existe.");
                }

                // Mover clientes sin pedidos a CLIENTESSINPEDIDO
                String insertQuery = "INSERT INTO CLIENTESSINPEDIDO "
                		+ " SELECT * FROM CLIENTES WHERE CODIGOCLIENTE NOT IN "
                		+ "(SELECT DISTINCT CODIGOCLIENTE FROM PEDIDOS)";
                String deleteQuery = "DELETE FROM CLIENTES WHERE CODIGOCLIENTE NOT IN "
                		+ "(SELECT DISTINCT CODIGOCLIENTE FROM PEDIDOS)";
                conn.setAutoCommit(false);
                try (Statement stmt = conn.createStatement()) {
                    int inserted = stmt.executeUpdate(insertQuery);
                    int deleted = stmt.executeUpdate(deleteQuery);
                    conn.commit();
                    System.out.printf("Clientes movidos: %d. Clientes eliminados: %d.\n", inserted, deleted);
                } catch (SQLException e) {
                    conn.rollback();
                    System.err.println("Error al mover clientes: " + e.getMessage());
                }
            } catch (SQLException e) {
                System.err.println("Error al procesar clientes sin pedido: " + e.getMessage());
            }
        }


        public static void actualizarClientesPorEmpleado() {
            // Implementación del ejercicio 4
        }

        public static void crearStockActualizado() {
            // Implementación del ejercicio 5
        }

        public static void oficinasConFuncionAlmacenada(String codigoOficina) {
            // Implementación del ejercicio 6
        }

        public static void verPedidosDeTodosLosClientes() {
            // Implementación del ejercicio 7
        }

        public static void tratarNuevosEmpleados() {
            // Implementación del ejercicio 8
        }
    }

    // Clase principal con el menú
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Insertar Empleado");
            System.out.println("2. Visualizar pedidos de un cliente");
            System.out.println("3. Crear clientes sin pedido");
            System.out.println("4. Actualizar Clientes por empleado");
            System.out.println("5. Crear STOCKACTUALIZADO");
            System.out.println("6. Oficinas con función almacenada");
            System.out.println("7. Ver los pedidos de todos los clientes");
            System.out.println("8. Tratar nuevos empleados");
            System.out.println("0. Salir");
            System.out.println("-------------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	System.out.println("Di el nombre");
                	String nombreString = scanner.next();
                	System.out.println("Di el primer apellido");
                	String apellido1String = scanner.next();
                	System.out.println("Di el segundo apellido");
                	String apellido2String = scanner.next();
                	System.out.println("Di la extension");
                	String extensionString = scanner.next();
                	System.out.println("Di el email");
                	String emailString = scanner.next();
                	System.out.println("Di el codigo oficina");
                	String codigoOficinaString = scanner.next();
                	System.out.println("Di el codigo del jefe");
                	int codigoJefe = scanner.nextInt();
                	System.out.println("Di el puesto del empleado");
                	String puestoEmpleado = scanner.next();
                    JardineriaOperations.insertarEmpleado(nombreString, apellido1String, apellido2String, extensionString, emailString, codigoOficinaString, codigoJefe, puestoEmpleado);
                    break;
                case 2:
                	System.out.println("Di el codigo del cliente que quieres visualizar");
                	int id = scanner.nextInt();
                    JardineriaOperations.visualizarPedidosCliente(id);
                    break;
                case 3:
                    JardineriaOperations.crearClientesSinPedido();
                    break;
                case 4:
                    JardineriaOperations.actualizarClientesPorEmpleado();
                    break;
                case 5:
                    JardineriaOperations.crearStockActualizado();
                    break;
                case 6:
                    JardineriaOperations.oficinasConFuncionAlmacenada("OF1");
                    break;
                case 7:
                    JardineriaOperations.verPedidosDeTodosLosClientes();
                    break;
                case 8:
                    JardineriaOperations.tratarNuevosEmpleados();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
