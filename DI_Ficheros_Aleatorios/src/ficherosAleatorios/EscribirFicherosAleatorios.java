package ficherosAleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscribirFicherosAleatorios {

	public static void main(String[] args) throws IOException {

		File fichero = new File(".\\AleatorioEmple.dat");

		//declara el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		//arrays con los datos
		String apellido[] = {"FERNANDEZ","GIL","LOPEZ","RAMOS","SEVILLA","CASILLA", "REY"}; //apellidos
		int dep[] = {10, 20, 10, 10, 30, 30, 20}; //departamentos
		Double salario[]={1000.45, 2400.60, 3000.0, 1500.56,2200.0, 1435.87, 2000.0};//salarios

		StringBuffer buffer = null;//buffer para almacenar apellido

		int n=apellido.length;//numero de elementos del array

		for (int i=0;i<n; i++){ //recorro los arrays
			file.writeInt(i+1); //uso i+1 para identificar empleado

			buffer = new StringBuffer( apellido[i] );
			buffer.setLength(10); //10 caracteres para el apellido
			file.writeChars(buffer.toString());//insertar apellido
			file.writeInt(dep[i]); //insertar departamento
			file.writeDouble(salario[i]);//insertar salario
		}
		file.close(); //cerrar fichero
		crearUTF();
		leerUTF();
	}
	public static void crearUTF() throws IOException {
		File fichero = new File(".\\AleatorioEmpleUTF.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");
		String apellido[] = { "FERNANDEZ","GIL","LOPEZ","RAMOS","SEVILLA","CASILLA","REY" };
		int dep[] = { 10, 20, 10, 10, 30, 30, 20 };
		Double salario[] = { 1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87, 2000.0 };
		int n = apellido.length;
		int posicion = 0;
		for (int i = 0; i < n; i++) {
			file.seek(posicion);
			file.writeInt(i + 1);
			file.writeUTF(apellido[i]);
			file.writeInt(dep[i]);
			file.writeDouble(salario[i]);
			posicion = posicion + 36;
		}
		file.close();
	}
	public static void leerUTF() throws IOException {
		File fichero = new File(".\\AleatorioEmpleUTF.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		int id, dep, posicion = 0;
		Double salario;
		for (;;) {
			file.seek(posicion);
			id = file.readInt();
			String apellidoS = file.readUTF();
			dep = file.readInt();
			salario = file.readDouble();
			System.out.println( "ID: " + id + ", Apellido: " + apellidoS
					+ ", Departamento: " + dep + ", Salario: " + salario);
			System.out.println("Posicion=" + posicion + ", filepointer= "
					+ file.getFilePointer() + ", file leng: "+ file.length());
			posicion = posicion + 36;
			if (posicion >= file.length())
				break;
		}
		file.close();
	}

}




