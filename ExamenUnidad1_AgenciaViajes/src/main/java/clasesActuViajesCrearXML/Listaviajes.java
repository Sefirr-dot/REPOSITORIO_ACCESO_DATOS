package clasesActuViajesCrearXML;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "ListaViajes")
public class Listaviajes {
	   private ArrayList<Viajes> lista = new ArrayList<Viajes>();

    public Listaviajes() {}
	public Listaviajes(ArrayList<Viajes> lista) {
		super();
		this.lista = lista;
	}

	@XmlElement(name = "viaje")
	public ArrayList<Viajes> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Viajes> lista) {
		this.lista = lista;
	}

}