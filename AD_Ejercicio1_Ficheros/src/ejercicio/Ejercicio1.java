package ejercicio;

import java.io.IOException;

import java.util.Scanner;
import ejercicio.MetodosEjerciico1;
public class Ejercicio1 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		
		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch(opcion) {
			case 1:
				MetodosEjerciico1.crearfichero();
				break;
			case 2:
				//int codeDepartamento, String nombre, String localidad,int NumEmpleado, float mediaSalario
				System.out.println("Dime codigo Departamento");
				int codeDep = sc.nextInt();
				System.out.println("Dime Nombre Departamento");
				String nombreDep = sc.next();
				System.out.println("Dime localidad Departamento");
				String localidadDep = sc.next();
				System.out.println("Dime Numero de empleados");
				int numEmpleados = sc.nextInt();
				System.out.println("Dime media del salario");
				Double mediaSalario = sc.nextDouble();
				MetodosEjerciico1.insertarRegistro(codeDep, nombreDep, localidadDep, numEmpleados, mediaSalario);
				break;
			case 3:
				System.out.println("Di el numero de el departamento");
				int numEmpleados1 = sc.nextInt();
				if(MetodosEjerciico1.consultarRegistro(numEmpleados1)) {
					System.out.println("Existe");
				} else {
					System.out.println("No existe");
				}
				break;
			case 4:
				System.out.println("Di el numero de el departamento");
				int codeDep1 = sc.nextInt();
				MetodosEjerciico1.visualizarRegistro(codeDep1);
				break;
			case 5:
				System.out.println("Di el numero de el departamento");
				int codeDep2 = sc.nextInt();
				if(MetodosEjerciico1.consultarRegistro(codeDep2)) {
					System.out.println(" Teclea la localidad: ");
					String locDep = sc.next();
					System.out.println(" Teclea la media de salario del departamento: ");
					Double medSal = sc.nextDouble();
					
					MetodosEjerciico1.modificarRegistro(codeDep2, locDep, medSal);
				} else {
					System.out.println("El registro no existe, prueba otra vez con otro codigo");
				}
				break;
			case 6:
				System.out.println("Di el numero de el departamento");
				int codeDep3 = sc.nextInt();;
				if (MetodosEjerciico1.consultarRegistro(codeDep3)) {
					MetodosEjerciico1.borrarRegistro(codeDep3);
				} else {
					System.out.println("El registro no existe");
				}
				break;
			case 7:
				System.out.println("Adios!");
				break;
			default:
				System.out.println("Seleccione una opción válida!");
				break;
			}
		}while(opcion!=7);
		
		sc.close();
		
		
	}
	public static void mostrarMenu() {
		System.out.println("------------------------------------------------------");
		System.out.println("OPERACIONES CON Ficheros");
		System.out.println("  1. Crear fichero");
		System.out.println("  2. Crear registro");
		System.out.println("  3. Consultar si un registro existe");
		System.out.println("  4. Visualizar registro");
		System.out.println("  5. Modificar registro");
		System.out.println("  6. Borrar Registro");
		System.out.println("  7. Salir");
		System.out.println("------------------------------------------------------");
	}

}
