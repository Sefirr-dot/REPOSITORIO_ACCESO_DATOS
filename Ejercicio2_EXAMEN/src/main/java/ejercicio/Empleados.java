package ejercicio;

import javax.xml.bind.annotation.XmlElement;

public class Empleados {

	private String nombreString;
	private double salario;
	private String puestoString;
	public Empleados(String nombreString, double salario, String puestoString) {
		super();
		this.nombreString = nombreString;
		this.salario = salario;
		this.puestoString = puestoString;
	}
	public Empleados() {
		super();
	}
	@XmlElement
	public String getNombreString() {
		return nombreString;
	}

	public void setNombreString(String nombreString) {
		this.nombreString = nombreString;
	}
	@XmlElement
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	@XmlElement
	public String getPuestoString() {
		return puestoString;
	}
	public void setPuestoString(String puestoString) {
		this.puestoString = puestoString;
	}
	
	
}
