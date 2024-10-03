package datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


public class Alummno {

	private int numAlumno;
	private String nombre;
	private String localidad;
	private int numAsignaturas;
	private float notaMedia;
	private ArrayList<Notas> listaNotas;
	
	
	public Alummno(int numAlumno, String nombre, String localidad, int numAsignaturas, float notaMedia) {
		super();
		this.numAlumno = numAlumno;
		this.nombre = nombre;
		this.localidad = localidad;
		this.numAsignaturas = numAsignaturas;
		this.notaMedia = notaMedia;
	}
	
	public Alummno() {
		super();
	}

	@XmlElement(name = "Num Alumno")
	public int getNumAlumno() {
		return numAlumno;
	}
	public void setNumAlumno(int numAlumno) {
		this.numAlumno = numAlumno;
	}
	@XmlElement(name = "Nombre Alumno")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement(name = "Alumno Localidad")
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	@XmlElement(name = "Alumno Asignaturas")
	public int getNumAsignaturas() {
		return numAsignaturas;
	}
	public void setNumAsignaturas(int numAsignaturas) {
		this.numAsignaturas = numAsignaturas;
	}
	@XmlElement(name = "Alumno NotaMedia")
	public float getNotaMedia() {
		return notaMedia;
	}
	public void setNotaMedia(float notaMedia) {
		this.notaMedia = notaMedia;
	}

	@XmlElementWrapper(name = "Lista Notas")
	@XmlElement(name = "Nota")
	public ArrayList<Notas> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(ArrayList<Notas> listaNotas) {
		this.listaNotas = listaNotas;
	}
	
	
}
