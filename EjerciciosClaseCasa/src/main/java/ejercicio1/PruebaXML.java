package ejercicio1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class PruebaXML {

	private static final String MIARCHIVO_XML = "./unlibro.xml";
	private static final ArrayList<Libros> LIBRERIAA_ARRAY_LIST = new ArrayList<Libros>();
	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		
		
		JAXBContext context = JAXBContext.newInstance(Libreria.class);
		
		Libros miLibros = new Libros("Entornos de Desarrollo","Alicia Ramos", "Garceta", "978-84-1545-297-3" );
		Libros miLibros2 = new Libros("Entornos de Juegos online","Jose Ramos", "Pepones", "118-84-1222-297-311" );
		LIBRERIAA_ARRAY_LIST.add(miLibros);
		LIBRERIAA_ARRAY_LIST.add(miLibros2);
		Libreria miLibreria = new Libreria(LIBRERIAA_ARRAY_LIST,"JUANITOS LIBRERY","Ciudad Real");
		
		
		Marshaller marshaller2 = context.createMarshaller();
		
		
		
		marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller2.marshal(miLibreria, System.out);
		marshaller2.marshal(miLibreria, new File(MIARCHIVO_XML));
		
		// Visualizamos ahora los datos del documento XML creado
		 System.out.println("------------ Leo el XML ---------");
		 //Se crea Unmarshaller en el cotexto de la clase Libreria
		 Unmarshaller unmars = context.createUnmarshaller();
		 
		 //Utilizamos el método unmarshal, para obtener datos de un Reader
		 Libreria libreria2 =(Libreria) unmars.unmarshal(new FileReader(MIARCHIVO_XML));

		 //Recuperamos los datos y visualizamos
		 System.out.println("Nombre de libreria: "+ libreria2.getNombre());
		 System.out.println("Lugar de la libreria: "+
				 libreria2.getLugar());
		 System.out.println("Libros de la librería: ");

		 ArrayList<Libros> lista = libreria2.getListaLibros();
		 for (Libros libro : lista) {
			 System.out.println("\tTítulo del libro: " 
					 + libro.getNombre() 
					 + " , autora: " + libro.getAutor());
		 }

	}

}
