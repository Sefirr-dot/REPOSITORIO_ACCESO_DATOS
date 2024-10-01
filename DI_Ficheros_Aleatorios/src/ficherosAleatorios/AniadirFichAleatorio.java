package ficherosAleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AniadirFichAleatorio {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File ficheroFile = new File(".\\AleatorioEmple.dat");
		RandomAccessFile fileRandmAccessFile = new RandomAccessFile(ficheroFile, "rw");
		
		StringBuffer buffer = null;
		String apellido = "Gonzalez";
		Double salarioDouble = 1230.87;
		int id = 20;
		int dep = 10;
		
		long posicion = (id-1) * 36;
		
		fileRandmAccessFile.seek(posicion);
		fileRandmAccessFile.writeInt(id);
		buffer = new StringBuffer(apellido);
		buffer.setLength(10);
		fileRandmAccessFile.writeChars(buffer.toString());
		fileRandmAccessFile.writeInt(dep);
		fileRandmAccessFile.writeDouble(salarioDouble);
		
		
		fileRandmAccessFile.close();
		

	}

}
