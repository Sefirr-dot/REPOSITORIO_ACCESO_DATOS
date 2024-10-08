package datos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;



public class Main {

	static final int POSICIONALUMNOS = 92;
	static final int POSICIONNOTAS = 48;
	public static void main(String[] args) throws IOException, JAXBException {

		Scanner sc = new Scanner(System.in);

		int opcion;
		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch(opcion) {
			case 1:
				listarAlumnos();
				break;
			case 2:
				listarNotas();
				break;
			case 3:
				actualizarFicheroAlumnos();
				listarAlumnos();
				verNotas();
				break;
			case 4:
				generarAlumnoXML();
				break;
			case 5:
				System.out.println("Adios!!!");
				break;
			default:
				System.out.println("Elige una opcion valida");
			}
		} while(opcion !=5);
		sc.close();
	}
	private static void generarAlumnoXML() throws IOException, JAXBException {
		
		File archivo1 = new File(".\\Alumnos.dat");
	    RandomAccessFile archAccessFile1 = new RandomAccessFile(archivo1, "rw");
	    
	    File archivo2 = new File(".\\Notas.dat");
	    
	    
	    RandomAccessFile archAccessFile2 = new RandomAccessFile(archivo2, "r"); // Solo lectura para notas
	    
	    ArrayList<Alummno> listaAlumnos = new ArrayList<Alummno>();
	    
	    
	    // Variables de ayuda
	    long posicion1 = 0;  // Posición dentro del archivo Alumnos
	    
	   
	    // Procesar cada alumno en el archivo de alumnos
	    while (posicion1 < archAccessFile1.length()) {
	    	Alummno alumnAlummno = new Alummno();
	    	Notas notas1 = new Notas();
	        long posicion2 = 0; // Reiniciar la posición para el archivo de notas
	        int contador = 0;
	        float notaMediaFinal = 0;
	        
	        
	        
	        archAccessFile1.seek(posicion1); // Mover a la posición actual del archivo de alumnos
	        int numAlumno = archAccessFile1.readInt(); // Leer número de alumno
	        
	        
	        alumnAlummno.setNumAlumno(numAlumno);
	        char nombre[] = new char[20],auxnombre;
	        
	        
	        for (int i = 0; i < nombre.length; i++) {
				auxnombre = archAccessFile1.readChar();
				nombre[i] = auxnombre;
			}
	        
	        
	        char localidad[] = new char[20], auxLocalidad;
	        String nombreString = new String(nombre);
	        alumnAlummno.setNombre(nombreString);
	        
	        
			for (int i = 0; i < localidad.length; i++) {
				auxLocalidad = archAccessFile1.readChar();
				localidad[i] = auxLocalidad;
			}
			
			
			String localidadString = new String(localidad);
			int numAsignaturas = archAccessFile1.readInt();
			alumnAlummno.setLocalidad(localidadString);
			alumnAlummno.setNumAsignaturas(numAsignaturas);
			
	        // Leer el registro del alumno
			ArrayList<Notas> listaNotas = new ArrayList<Notas>();
	        // Leer las notas de ese alumno en el archivo de notas
	        while (posicion2 < archAccessFile2.length()) {
	        	
	            archAccessFile2.seek(posicion2); // Mover a la posición actual del archivo de notas
	            
	            int numAlumno2 = archAccessFile2.readInt(); // Leer número de alumno en archivo de notas

	            if (numAlumno == numAlumno2 && numAlumno!=0) {
	            	char asignatura[] = new char[20], auxAsignatura;
	            	archAccessFile2.seek(posicion2+4);
	            	for(int i =0;i<asignatura.length;i++) {
	            		auxAsignatura = archAccessFile2.readChar();
	            		asignatura[i] = auxAsignatura;
	            	}
	            	String asignaturaString = new String(asignatura);
	            	notas1.setAsignatura(asignaturaString.trim());
	            	archAccessFile2.seek(posicion2+44);
	                float notaMedia = archAccessFile2.readFloat();
	                notas1.setNota(notaMedia);
	                listaNotas.add(notas1);
	            }
	            
	            // Avanzar a la siguiente posición en el archivo de notas
	            posicion2 += POSICIONNOTAS; // 48 bytes por cada registro de notas
	            
	        }
	        alumnAlummno.setListaNotas(listaNotas);
	        listaAlumnos.add(alumnAlummno);
	        // Avanzar al siguiente alumno
	        posicion1 += POSICIONALUMNOS; // 92 bytes por cada registro de alumno
	        
	        // Reiniciar el puntero del archivo de notas para procesar el próximo alumno
	        archAccessFile2.seek(0); 
	    }
		ListaAlumnos listaFinAlumnos = new ListaAlumnos(listaAlumnos);
		JAXBContext context = JAXBContext.newInstance(ListaAlumnos.class);
		Marshaller marshaller2 = context.createMarshaller();
		
		
		
		marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller2.marshal(listaFinAlumnos, System.out);
		marshaller2.marshal(listaFinAlumnos, new File(".\\Alumnos.xml"));
		
	}
	private static void verNotas() throws IOException {
		File archivo = new File(".\\Alumnos.dat");
		RandomAccessFile archAccessFile = new RandomAccessFile(archivo, "r");
		float salario =0;
		char nombre[] = new char[20],auxnombre;
		
		char localidad[] = new char[20], auxLocalidad;
		long posicion = 0;
		int contador = 0;
		double notaTodos=0;
		float notaMediaFinal = 0;
		String alumnoIlustreString="";
		while(archAccessFile.getFilePointer() != archAccessFile.length()) {
			archAccessFile.seek(posicion);
			int numAlumno = archAccessFile.readInt();
			if(numAlumno!=0) {
				for (int i = 0; i < nombre.length; i++) {
					auxnombre = archAccessFile.readChar();
					nombre[i] = auxnombre;
				}
				
				String nombreString = new String(nombre);
				for (int i = 0; i < localidad.length; i++) {
					auxLocalidad = archAccessFile.readChar();
					localidad[i] = auxLocalidad;
				}
				String localidadString = new String(localidad);
				int numAsignaturas = archAccessFile.readInt();
				float notaMedia = archAccessFile.readFloat();
				notaTodos+=notaMedia;
				if(notaMedia>notaMediaFinal) {
					notaMediaFinal=notaMedia;
					alumnoIlustreString = nombreString;
				}
				contador++;
			}
			
			posicion+=POSICIONALUMNOS;
		}		
		System.out.println("El alumno con la nota media mas alta es: "+alumnoIlustreString);
		System.out.println("La nota media de la clase es: "+notaTodos/contador);
	}
	private static void actualizarFicheroAlumnos() throws IOException {
	    // Archivos
	    File archivo1 = new File(".\\Alumnos.dat");
	    RandomAccessFile archAccessFile1 = new RandomAccessFile(archivo1, "rw");
	    
	    File archivo2 = new File(".\\Notas.dat");
	    RandomAccessFile archAccessFile2 = new RandomAccessFile(archivo2, "r"); // Solo lectura para notas
	    
	    // Variables de ayuda
	    long posicion1 = 0;  // Posición dentro del archivo Alumnos
	    
	    // Procesar cada alumno en el archivo de alumnos
	    while (posicion1 < archAccessFile1.length()) {
	        long posicion2 = 0; // Reiniciar la posición para el archivo de notas
	        int contador = 0;
	        float notaMediaFinal = 0;

	        // Leer el registro del alumno
	        archAccessFile1.seek(posicion1); // Mover a la posición actual del archivo de alumnos
	        int numAlumno = archAccessFile1.readInt(); // Leer número de alumno

	        // Leer las notas de ese alumno en el archivo de notas
	        while (posicion2 < archAccessFile2.length()) {
	            archAccessFile2.seek(posicion2); // Mover a la posición actual del archivo de notas

	            int numAlumno2 = archAccessFile2.readInt(); // Leer número de alumno en archivo de notas

	            if (numAlumno == numAlumno2) {
	            	archAccessFile2.seek(posicion2+44);
	                float notaMedia = archAccessFile2.readFloat();
	                notaMediaFinal += notaMedia; // Acumular la nota
	                contador++; // Contar cuántas notas tiene el alumno
	            }

	            // Avanzar a la siguiente posición en el archivo de notas
	            posicion2 += POSICIONNOTAS; // 48 bytes por cada registro de notas
	        }

	        // Si encontramos alguna nota, calculamos la media
	        if (contador > 0) {
	            notaMediaFinal = notaMediaFinal / contador;

	            // Actualizar la nota media en el archivo de alumnos
	            long posicionAsignaturas = posicion1 +84;
	            archAccessFile1.seek(posicionAsignaturas);
	            archAccessFile1.writeInt(contador);
	            long posicionNotaMedia = posicion1 + 88; // La posición de la nota media en el registro de alumno
	            archAccessFile1.seek(posicionNotaMedia); // Moverse a la posición de la nota media
	            archAccessFile1.writeFloat(notaMediaFinal); // Sobreescribir la nota media
	        }

	        // Avanzar al siguiente alumno
	        posicion1 += POSICIONALUMNOS; // 92 bytes por cada registro de alumno
	        
	        // Reiniciar el puntero del archivo de notas para procesar el próximo alumno
	         
	    }

	    // Cerrar archivos
	    archAccessFile1.close();
	    archAccessFile2.close();
	}




		

	private static void listarNotas() throws IOException {
		File archivo = new File(".\\Notas.dat");
		RandomAccessFile archAccessFile = new RandomAccessFile(archivo, "r");
		int posicion = 0;
		int registro=1;
		char nombreAsignatura[]= new char[20], auxNombreasignatura;
		System.out.println("REGIS     NUMALUM   ASIGNATURA           NOTAMEDIA");
		System.out.println("--------- --------- -------------------- ---------");
		while(archAccessFile.getFilePointer() != archAccessFile.length()) {
			archAccessFile.seek(posicion);
			int numAlumno = archAccessFile.readInt();
			if(numAlumno!=0) {
				
				for (int i = 0; i < nombreAsignatura.length; i++) {
					auxNombreasignatura = archAccessFile.readChar();
					nombreAsignatura[i] = auxNombreasignatura;
				}
				
				String nombreString = new String(nombreAsignatura);

				float notaAsignatura = archAccessFile.readFloat();
				
				System.out.printf("%-9d %-9s %-20s %-10.2f%n",registro ,numAlumno, nombreString.trim(), notaAsignatura);
				
			}
			registro++;
			
			posicion+=POSICIONNOTAS;
		
	}
		System.out.println("--------------------------------------------------");
}

	private static void listarAlumnos() throws IOException {
		File archivo = new File(".\\Alumnos.dat");
		RandomAccessFile archAccessFile = new RandomAccessFile(archivo, "r");
		float salario =0;
		char nombre[] = new char[20],auxnombre;
		
		char localidad[] = new char[20], auxLocalidad;
		long posicion = 0;
		System.out.println("NUMALUMNO NOMBRE               LOCALIDAD            NUMASIG NOTAMEDIA");
		System.out.println("--------- -------------------- -------------------- ------- ---------");
		while(archAccessFile.getFilePointer() != archAccessFile.length()) {
			archAccessFile.seek(posicion);
			int numAlumno = archAccessFile.readInt();
			if(numAlumno!=0) {
				for (int i = 0; i < nombre.length; i++) {
					auxnombre = archAccessFile.readChar();
					nombre[i] = auxnombre;
				}
				
				String nombreString = new String(nombre);
				for (int i = 0; i < localidad.length; i++) {
					auxLocalidad = archAccessFile.readChar();
					localidad[i] = auxLocalidad;
				}
				String localidadString = new String(localidad);
				int numAsignaturas = archAccessFile.readInt();
				float notaMedia = archAccessFile.readFloat();
				
				System.out.printf("%-9d %-20s %-20s %-7d %-10.2f%n", numAlumno, nombreString.trim(), localidadString.trim(), numAsignaturas, notaMedia);
				
			}
			
			posicion+=POSICIONALUMNOS;
		}		
		System.out.println("---------------------------------------------------------------------");
	}

	private static void mostrarMenu() {
		System.out.println("--------------------------------------");
		System.out.println("---------1.LISTAR ALUMNOS-------------");
		System.out.println("---------2.LISTAR NOTAS---------------");
		System.out.println("---------3.ACTUALIZAR FICHERO ALUMNOS-");
		System.out.println("---------4.GENERAR FICHERO alumnos.XML");
		System.out.println("---------5.SALIR----------------------");
	}
	
	

}
