package ejercicio1;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "LibreriaMaxima")
public class MisLibrerias {
	private ArrayList<Libreria> listaLibrerias;
	private String nombre;
	private String lugar;
	public MisLibrerias(ArrayList<Libreria> listaLibrerias, String nombre, String lugar) {
		super();
		this.listaLibrerias = listaLibrerias;
		this.nombre = nombre;
		this.lugar = lugar;
	}
	public MisLibrerias(){
		super();
	}
	public ArrayList<Libreria> getListaLibrerias() {
		return listaLibrerias;
	}
	public void setListaLibrerias(ArrayList<Libreria> listaLibrerias) {
		this.listaLibrerias = listaLibrerias;
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
