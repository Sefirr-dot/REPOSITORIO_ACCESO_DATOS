package datos;

public class Alummno {

	private int numAlumno;
	private String nombre;
	private String localidad;
	private int numAsignaturas;
	private float notaMedia;
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

	public int getNumAlumno() {
		return numAlumno;
	}
	public void setNumAlumno(int numAlumno) {
		this.numAlumno = numAlumno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public int getNumAsignaturas() {
		return numAsignaturas;
	}
	public void setNumAsignaturas(int numAsignaturas) {
		this.numAsignaturas = numAsignaturas;
	}
	public float getNotaMedia() {
		return notaMedia;
	}
	public void setNotaMedia(float notaMedia) {
		this.notaMedia = notaMedia;
	}
	
	
}
