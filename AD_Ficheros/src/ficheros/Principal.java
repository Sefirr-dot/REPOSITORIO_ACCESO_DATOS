package ficheros;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		
		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch(opcion) {
			case 1:
				CrearArchivo();
				break;
			case 2:
				CrearArchivo2();
				break;
			case 3:
				CrearArchivo3();
				break;
			case 4:
				VerDir();
			case 5:
				CrearArchivoAleatorio();
				break;
			case 6:
				
			default:
				System.out.println("Seleccione una opción válida!");
				break;
			}
		}while(opcion!=6);
		
		sc.close();
	}
		
	private static void mostrarMenu() {
		System.out.println("------------------------------------------------------");
		System.out.println("OPERACIONES CON ALUMNOS");
		System.out.println("  1. Crear archivo metodo 1");
		System.out.println("  2. Crear archivo metodo 2");
		System.out.println("  3. Crear archivo metodo 3");
		System.out.println("  4. Ver Directorio");
		System.out.println("  5. Crear archivo Aleatorio");
		System.out.println("  6. Salir");
		System.out.println("------------------------------------------------------");
	}
	private static void CrearArchivo() {
		File fichero1 = new File("C:\\Users\\Alumno\\Desktop\\ACCESO DATOS\\ejemplo1.txt\\");
		try {
			if(fichero1.createNewFile()) {
				System.out.println("Archivo creado "+fichero1.getName());
			} else {
				System.out.println("El archivo ya existe");
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}
	}
	private static void CrearArchivo2() {
		File fichero2 = new File("C:\\Users\\Alumno\\Desktop\\ACCESO DATOS\\","ejemplo2.txt");
		try {
			if(fichero2.createNewFile()) {
				System.out.println("Archivo creado "+fichero2.getName());
			} else {
				System.out.println("El archivo ya existe");
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}
	}
	private static void CrearArchivo3() {
		File direc = new File("C:\\Users\\Alumno\\Desktop\\ACCESO DATOS\\");
		if(direc.mkdirs()) {
			System.out.println("Directorio creado: "+direc.getPath());
		} else {
			System.out.println("El directorio ya existe");
		} 
		File fichero3 = new File(direc,"ejmplo3.txt");
		try {
			if(fichero3.createNewFile()) {
				System.out.println("Archivo creado: "+fichero3.getName());
			} else {
				System.out.println("El archivo ya existe");
			}
			
		} catch (Exception e) {
			System.out.println("Ocurrio un error.");
			e.printStackTrace();			
		}
	}
	
	
	public static void VerDir() {
		String dir = "C:\\Users\\Alumno\\Desktop\\ACCESO DATOS\\"; //directorio actual
		File f = new File(dir);
		String[] archivos = f.list();
		System.out.printf("Ficheros en el directorio actual: %d %n",
				archivos.length);
		for (int i = 0; i < archivos.length; i++) {
			File f2 = new File(f, archivos[i]);
			if(f2.isFile()) {
				System.out.printf("Nombre: %s, es fichero%n", archivos[i]);
			} else {
				if(f2.isDirectory()) {
					System.out.printf("Nombre: %s, es directorio %n", archivos[i]);
				}
			}
		}
	}
	
	public static void VerDirConArray() {
		
	}
	private static void CrearArchivoAleatorio() {
		File fichero1 = new File("C:\\Users\\Alumno\\Desktop\\ACCESO DATOS\\ejemploArchivoAleatorio11.txt\\");
		
		try {
			if(fichero1.createNewFile()) {
				RandomAccessFile file = new RandomAccessFile(fichero1, "rw");
				System.out.println("Archivo creado "+fichero1.getName());
			} else {
				System.out.println("El archivo ya existe");
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}
	}
}
