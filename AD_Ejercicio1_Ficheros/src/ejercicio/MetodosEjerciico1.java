package ejercicio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StreamCorruptedException;
import java.nio.Buffer;
import java.util.Scanner;

public class MetodosEjerciico1 {


	
	public static void crearfichero() {
		File archivoFile = new File(".\\empleados.dat");
		try {
			if(archivoFile.createNewFile()) {
				
				System.out.println("Archivo creado "+archivoFile.getName());
			} else {
				System.out.println("El archivo ya existe");
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}
	}
	
	//posicion 72
	
	public static void insertarRegistro(int codeDepartamento, String nombre, String localidad,int NumEmpleado, double mediaSalario) throws IOException {
		
		if(codeDepartamento>0 && codeDepartamento<101) {
			try {
				
				RandomAccessFile fileRandomAccessFile = new RandomAccessFile(".\\empleados.dat", "rw");
				Long posicionLong = (long) ((codeDepartamento-1)*76);
				fileRandomAccessFile.seek(posicionLong);
				fileRandomAccessFile.writeInt(codeDepartamento);
				StringBuffer buffer = new StringBuffer(nombre);
				
				
				buffer.setLength(15);
				
				fileRandomAccessFile.writeChars(buffer.toString());
				
				buffer = new StringBuffer(localidad);
				buffer.setLength(15);
				
				fileRandomAccessFile.writeChars(buffer.toString());
				
				fileRandomAccessFile.writeInt(NumEmpleado);
				
				fileRandomAccessFile.writeDouble(mediaSalario);	
				
				
				
				System.out.println(" REGISTRO INSERTADO ");
			} catch (FileNotFoundException e) {
				System.out.println(" ERROR REGISTRO NO INSERTADO ");
				e.printStackTrace();
			}
			
		} else {
			System.out.println(" ERROR EL DEPARTAMENTO DEBE ESTAR ENTRE 1 Y 100,");
			
		}
				
		
	}
	
	public static boolean consultarRegistro(int codeDepartamento) {
		boolean existe = false;
		
		
		try {
			RandomAccessFile file = new RandomAccessFile(".\\empleados.dat", "r");
			Long posicionLong = (long) ((codeDepartamento-1)*76);
			file.seek(posicionLong);
			
				int deptCodeInFile = file.readInt();
				if(codeDepartamento == deptCodeInFile) {
					existe = true;
				}
		
		} catch (Exception e) {
			System.out.println("Error");
		}
		return existe;
	}
	public static void visualizarRegistro(int depCode) throws IOException {
		File fichero = new File(".\\empleados.dat");
		//declara el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		//
		int numEmpleadoso;
		
		double salario;
		char nombre[] = new char[15];
		
		char localidad[] = new char[15];
		int posicion=(depCode-1)*76; //para situarnos al principio
		
		if(consultarRegistro(depCode)) {
			
				file.seek(posicion); //nos posicionamos en posicion
				int codigoDepartamento = file.readInt();
				// Leer el nombre
		        for (int i = 0; i < nombre.length; i++) {
		            nombre[i] = file.readChar();
		        }

		        // Leer la localidad
		        for (int i = 0; i < localidad.length; i++) {
		            localidad[i] = file.readChar();
		        }

		        // Convertir el array de caracteres en strings, y eliminar los espacios en blanco o caracteres nulos al final
		        String nombreFinal = new String(nombre).trim();
		        String localidadFinal = new String(localidad).trim();

		        // Leer el número de empleados y el salario
		        numEmpleadoso = file.readInt();
		        salario = file.readDouble();
				
				
				

				System.out.println("ID: " + depCode + ", Nombre: "+ nombreFinal + 
						", Localidad: "+localidadFinal + ", NumEmpleados: " + numEmpleadoso+", Salario: "+salario); 
				 
				
		 
		}else {
			System.out.println("El departamento no existe");
		

		}//fin bucle for
		file.close(); //cerrar fichero 
		
	}
	public static void modificarRegistro(int numD, String locD, Double medS) throws IOException {      
		File fichero = new File(".\\empleados.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");
		StringBuffer buffer = null;
		int pos;	
		pos=(numD-1)*76+34;   
		file.seek(pos);  
		buffer = new StringBuffer(locD);
		buffer.setLength(15);
		file.writeChars(buffer.toString());
		pos=pos+34;
		file.seek(pos);  
		file.writeDouble(medS);
		System.out.println("---------------------------------------------------------");
		System.out.println("------------ DEPARTAMENTO MODIFICADO  " + numD + " -----------------");
	    file.close();		
	    try {
			System.out.println( "Pulsa una tecla para volver. ");
			int c = System.in.read();
		} catch (IOException e) {System.out.println (e);}		 
	}
	public static void borrarRegistro(int numD) {
		File fichero = new File(".\\empleados.dat");
		RandomAccessFile file;
		try {
			file = new RandomAccessFile(fichero, "rw");
			int pos;
			// borro el registro, pongo a 0 los campos numericos y relleno con espacios en
			// las cadenas
			pos = (numD - 1) * 76;
			file.seek(pos);
			file.writeInt(0);
			String nombreD = "               ";
			StringBuffer buffer = new StringBuffer(nombreD);
			buffer.setLength(15);
			file.writeChars(buffer.toString());
			String localidadD = "               ";
			buffer = new StringBuffer(localidadD);
			buffer.setLength(15);
			file.writeChars(buffer.toString());
			file.writeInt(0);
			file.writeDouble(0);
			System.out.println("---------------------------------------------------------");
			System.out.println("------------ DEPARTAMENTO BORRADO  " + numD + " -----------------");
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println(" ERROR NO ENCONTRADO EL FICHERO. ");
		} catch (IOException e) {
			System.out.println("ERROR DE E/S ");
		}
	}
}
