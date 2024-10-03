package datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Alumnillos")
public class ListaAlumnos {

	
	private ArrayList<Alummno> listaAlumnoss;

	public ListaAlumnos(ArrayList<Alummno> listaAlumnoss) {
		super();
		this.listaAlumnoss = listaAlumnoss;
	}

	public ListaAlumnos() {
		super();
	}

	@XmlElement(name = "Alumno")
	public ArrayList<Alummno> getListaAlumnoss() {
		return listaAlumnoss;
	}

	public void setListaAlumnoss(ArrayList<Alummno> listaAlumnoss) {
		this.listaAlumnoss = listaAlumnoss;
	}
	
	
}
