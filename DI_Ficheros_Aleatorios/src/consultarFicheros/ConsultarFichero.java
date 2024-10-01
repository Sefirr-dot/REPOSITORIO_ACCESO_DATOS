package consultarFicheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ConsultarFichero {

	public static void main(String[] args) throws IOException {
		
		RandomAccessFile file = new RandomAccessFile("AleatorioEmpleUTF.dat", "rw");;
		int identificador = 5;
		long posicion;
		posicion = (identificador-1) * 36;
		if (posicion >= file.length()) {
			System.out.println("ID"+ identificador+", no existe EMPLEADO");
		} else {
			file.seek(posicion);
			identificador=file.readInt();
			System.out.println(identificador);
		}

	}

}
