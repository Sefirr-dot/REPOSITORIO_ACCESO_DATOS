package datos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Notas {


	private String asignatura;
	private float notaAsignatura;
	public Notas( String asignatura, float nota) {
		super();

		this.asignatura = asignatura;
		this.notaAsignatura = nota;
	}
	public Notas() {
		super();
	}

	@XmlElement(name = "Alumno Asigna")
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	@XmlElement(name = "Alumno Nota")
	public float getNota() {
		return notaAsignatura;
	}
	public void setNota(float nota) {
		this.notaAsignatura = nota;
	}

	
}
