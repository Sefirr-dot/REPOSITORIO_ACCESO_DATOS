package ejercicio;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
	//alumnos.dat 4 int
	//40 string nombre
	//20 string curso 
	//4 float
	//asignaturas.dat 4 int
	//30 string nombre
	//4 int num alumnos
	//4 suma notas float
	//notas.dat 4 int cod alumno
	//4 int cod Asignatura
	// 4 float Nota
	public final static long POSCIONALUMNOS = 68;
	public final static long POSICIONASIGNATURAS = 42;
	public final static long POSICIONNOTAS = 12;
	public static void main(String[] args) throws IOException {

		File zfich = new File(".//alumnos.dat");

		RandomAccessFile ficheroAlumnos = new RandomAccessFile(zfich, "rw");

		File zfich1 = new File(".//asignaturas.dat");

		RandomAccessFile ficheroAsignaturas = new RandomAccessFile(zfich1, "rw");

		File zfich2 = new File(".//notas.dat");

		RandomAccessFile ficheroNotas = new RandomAccessFile(zfich2, "rw");

		long posicion1 = 0;


		while(ficheroAlumnos.getFilePointer()<ficheroAlumnos.length()) {
			ficheroAlumnos.seek(posicion1);
	
			int codAlumnoALUMNOS = ficheroAlumnos.readInt();
			char nombreAlumnoALUMNOS[] = new char[20], auxnombreAlumnoALUMNOS;
			char cursoAlumnoALUMNOS[] = new char[20], auxcursoAlumnoALUMNOS;

			for (int i = 0; i < nombreAlumnoALUMNOS.length; i++) {
				auxnombreAlumnoALUMNOS=ficheroAlumnos.readChar();
				nombreAlumnoALUMNOS[i] = auxnombreAlumnoALUMNOS;

			}
			for (int i = 0; i < cursoAlumnoALUMNOS.length; i++) {
				auxcursoAlumnoALUMNOS=ficheroAlumnos.readChar();
				cursoAlumnoALUMNOS[i] = auxcursoAlumnoALUMNOS;			
			}

			String nombreAlumnosALUMNOString = new String(nombreAlumnoALUMNOS);
			String cursoAlumnosALUMNOString =new String (cursoAlumnoALUMNOS);
			int posicion2 = 0;
			
			
			
			
			while(ficheroNotas.getFilePointer()<ficheroNotas.length()) {
				ficheroNotas.seek(posicion2);
				
				
				int codAlumnoNOTAS = ficheroNotas.readInt();
				int codAsignaturaNOTAS = ficheroNotas.readInt();
				float notaNOTAS = ficheroNotas.readFloat();
				int posicion3 = 0;
				
				
				
				if(codAlumnoALUMNOS==codAlumnoNOTAS) {
					
					while(ficheroAsignaturas.getFilePointer()<ficheroAsignaturas.length()) {
						
						
						ficheroAsignaturas.seek(posicion3);
						int codigoAsignaturaASIGNATURAS= ficheroAsignaturas.readInt();
						char nombreAsignaturaASIGNATURAS[] = new char[15], auxnombreAsignaturaASIGNATURAS;
						
						
						for (int i = 0; i < nombreAsignaturaASIGNATURAS.length; i++) {
							auxnombreAsignaturaASIGNATURAS=ficheroAlumnos.readChar();
							nombreAsignaturaASIGNATURAS[i] = auxnombreAsignaturaASIGNATURAS;
						}
						String nombreAsignaturaASIGNATURAString ="";	
						
						int numAlumnosASIGNATURAS = ficheroAsignaturas.readInt();
						float sumaNotasASIGNATURAS = ficheroAsignaturas.readFloat();
						
						
						posicion3+=POSICIONASIGNATURAS;
						System.out.println("Hola");
						if(codigoAsignaturaASIGNATURAS==codAsignaturaNOTAS) {
							nombreAsignaturaASIGNATURAString = new String(nombreAsignaturaASIGNATURAS);
						} else {
							nombreAsignaturaASIGNATURAString = "SIN ASIGNATURA";
						}
					}
				}


				posicion2+=POSICIONNOTAS;
			}




			posicion1 += POSCIONALUMNOS;
		}






	}

}
