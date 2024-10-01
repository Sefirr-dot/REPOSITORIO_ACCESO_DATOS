package ejercicio1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder= {"nombre", "editorial", "autor", "isbn"})
@XmlRootElement(name = "MiLibriko")
		public class Libros {private String nombre;
		private String autor;
		private String editorial;
		private String isbn;

		public Libros(String nombre, String autor, String editorial,String isbn) {
			super();
			this.nombre = nombre;
			this.autor = autor;
			this.editorial = editorial;
			this.isbn = isbn;
} 
			public Libros() {}
			@XmlElement(name="Nombreciko")
			
			public String getNombre() { return nombre; }
			@XmlElement(name="Autorciko")
			public String getAutor() { return autor; }
			@XmlElement(name="Editorialcika")
			public String getEditorial() {return editorial; }
			@XmlElement(name="Isbnciko")
			public String getIsbn() { return isbn;}
			
			
			public void setNombre(String nombre) 
			{ this.nombre = nombre; }
			
			public void setAutor(String autor) 
			{ this.autor = autor; }
			
			public void setEditorial(String editorial) 
			{ this.editorial = editorial; }
			
			public void setIsbn(String isbn) 
			{ this.isbn = isbn; }
			}
			
