package claseJAXB;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Libreria")
public class Libreria {

	private ArrayList<Libros> listaLibros;
	private String nombre;
	private String lugar;
	public Libreria(ArrayList<Libros> listaLibros, String nombre, String lugar) {
		super();
		this.listaLibros = listaLibros;
		this.nombre = nombre;
		this.lugar = lugar;
	}
	public Libreria() {
		super();
	}
	
	@XmlElementWrapper(name="ListaLibro")
	@XmlElement(name="Libro")
	public ArrayList<Libros> getListaLibros() {
		return listaLibros;
	}
	public void setListaLibros(ArrayList<Libros> listaLibros) {
		this.listaLibros = listaLibros;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	
}
