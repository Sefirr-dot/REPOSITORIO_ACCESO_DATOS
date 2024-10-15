package clasesActuViajesCrearXML;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

public class Viajes {
	
	private int codviaje;
	private String nombre;
	private int pvp;
	private int numplazas;
	private int numreservas;
	private int totalimporte;
	private ArrayList<Reservas> lista;
	
	public Viajes(){}
	public Viajes(int codviaje, String nombre, int pvp, int numplazas, int numreservas, int totalimporte,
			ArrayList<Reservas> lista) {
		super();
		this.codviaje = codviaje;
		this.nombre = nombre;
		this.pvp = pvp;
		this.numplazas = numplazas;
		this.numreservas = numreservas;
		this.totalimporte = totalimporte;
		this.lista = lista;
	}
	@XmlElement
	public int getCodviaje() {
		return codviaje;
	}
	public void setCodviaje(int codviaje) {
		this.codviaje = codviaje;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public int getPvp() {
		return pvp;
	}
	public void setPvp(int pvp) {
		this.pvp = pvp;
	}
	@XmlElement
	public int getNumplazas() {
		return numplazas;
	}
	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
	}
	@XmlElement
	public int getNumreservas() {
		return numreservas;
	}
	public void setNumreservas(int numreservas) {
		this.numreservas = numreservas;
	}
	@XmlElement
	public int getTotalimporte() {
		return totalimporte;
	}
	public void setTotalimporte(int totalimporte) {
		this.totalimporte = totalimporte;
	}
	@XmlElement
	public ArrayList<Reservas> getLista() {
		return lista;
	}
	public void setLista(ArrayList<Reservas> lista) {
		this.lista = lista;
	}
	
}
