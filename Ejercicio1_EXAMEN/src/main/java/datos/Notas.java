package datos;

public class Notas {

	private int registro;
	private int numAlumno;
	private String asignatura;
	private float notaAsignatura;
	public Notas(int registro, int numAlumno, String asignatura, float nota) {
		super();
		this.registro = registro;
		this.numAlumno = numAlumno;
		this.asignatura = asignatura;
		this.notaAsignatura = nota;
	}
	public Notas() {
		super();
	}
	public int getNumAlumno() {
		return numAlumno;
	}
	public void setNumAlumno(int numAlumno) {
		this.numAlumno = numAlumno;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public float getNota() {
		return notaAsignatura;
	}
	public void setNota(float nota) {
		this.notaAsignatura = nota;
	}
	public int getRegistro() {
		return registro;
	}
	public void setRegistro(int registro) {
		this.registro = registro;
	}
	
	
}
