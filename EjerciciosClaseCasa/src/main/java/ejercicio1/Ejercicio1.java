package ejercicio1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Ejercicio1 {

		private static final String MIARCHIVO_XML = "./Libreria.xml";
		private static final ArrayList<Libros> LIBRERIAA_ARRAY_LIST = new ArrayList<Libros>();
		private static final ArrayList<Libros> LIBRERIAA_ARRAY_LIST2 = new ArrayList<Libros>();
		private static final ArrayList<Libreria> LIBRERIAS = new ArrayList<Libreria>();
		public static void main(String[] args) throws JAXBException, FileNotFoundException {
			
			
			JAXBContext context = JAXBContext.newInstance(MisLibrerias.class);
			
			Libros miLibros = new Libros("Entornos de Desarrollo","Alicia Ramos", "Garceta", "978-84-1545-297-3" );
			Libros miLibros2 = new Libros("Entornos de Juegos online","Jose Ramos", "Pepones", "118-84-1222-297-311" );
			Libros miLibros3 = new Libros("Juegos en el parke del Desarrollo","Alexiss Jose Maria manuel del otro Ramos", "Jepetoss", "978-84-1545-297-3" );
			Libros miLibros4 = new Libros("Entornos de Juegos multiplataforma NAdroid","Juanjo rios", "Pelepess", "118-84-0-297-311" );
			Libros miLibros5 = new Libros("Entornos de Historias Andergraund","Jose felix Ramos", "Colals", "008-84-1545-297-3" );
			
			LIBRERIAA_ARRAY_LIST.add(miLibros);
			LIBRERIAA_ARRAY_LIST.add(miLibros2);
			LIBRERIAA_ARRAY_LIST2.add(miLibros3);
			LIBRERIAA_ARRAY_LIST2.add(miLibros4);
			LIBRERIAA_ARRAY_LIST2.add(miLibros5);
			Libreria miLibreria = new Libreria(LIBRERIAA_ARRAY_LIST,"JUANITOS LIBRERY","Ciudad Real");
			Libreria miLibreria2 = new Libreria(LIBRERIAA_ARRAY_LIST2,"Pankos LIBRERY","Madrid");
			LIBRERIAS.add(miLibreria);
			LIBRERIAS.add(miLibreria2);
			MisLibrerias librefinalDelPoderLibrerias = new MisLibrerias(LIBRERIAS, "Librerias Martinez", "Manzanares");
			
			Marshaller marshaller2 = context.createMarshaller();
			
			
			
			marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller2.marshal(librefinalDelPoderLibrerias, System.out);
			marshaller2.marshal(librefinalDelPoderLibrerias, new File(MIARCHIVO_XML));
			
			// Visualizamos ahora los datos del documento XML creado
			 System.out.println("------------ Leo el XML ---------");
			 //Se crea Unmarshaller en el cotexto de la clase Libreria
			 Unmarshaller unmars = context.createUnmarshaller();
			 
			 //Utilizamos el método unmarshal, para obtener datos de un Reader
			 MisLibrerias libreria2 = (MisLibrerias) unmars.unmarshal(new FileReader(MIARCHIVO_XML));

			 //Recuperamos los datos y visualizamos
			 System.out.println("Nombre de libreria: "+ libreria2.getNombre());
			 System.out.println("Lugar de la libreria: "+
					 libreria2.getLugar());
			 System.out.println("Libros de la librería: ");

			 ArrayList<Libreria> lista = librefinalDelPoderLibrerias.getListaLibrerias();
			 
			 for (Libreria librerira1 : lista) {
				 ArrayList<Libros> librosDeCadaLibreria = librerira1.getListaLibros();
				 
				 for (Libros libreria : librosDeCadaLibreria ){
					 System.out.println("La Libreria: "+librerira1.getNombre()+" tiene el libro: "+ libreria.getNombre()+" del autor: "+ libreria.getAutor());
					
				}
			 }

		}}



	


