package ficherosAleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BorrarFicheroAleatorio {

	public static void main(String[] args) throws IOException {
		File ficheroFile = new File(".\\AleatorioEmple.dat");
		RandomAccessFile fileRandmAccessFile = new RandomAccessFile(ficheroFile, "rw");
		
		int registro = 4;
		long posicion = (registro-1) *36;
		
		
	    fileRandmAccessFile.seek(posicion);
	    fileRandmAccessFile.writeInt(0);
			
			
		
		fileRandmAccessFile.close();
		
	}

}
