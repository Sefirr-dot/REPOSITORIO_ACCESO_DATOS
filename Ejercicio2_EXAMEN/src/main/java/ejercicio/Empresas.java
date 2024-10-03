package ejercicio;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class Empresas {
	private int codeEmpresa;
	private String direccionString;
	private Empleados directorString;
	private ArrayList<Empleados> listaEmpleados;
	public Empresas(int codeEmpresa, String direccionString, Empleados directorString,
			ArrayList<Empleados> listaEmpleados) {
		super();
		this.codeEmpresa = codeEmpresa;
		this.direccionString = direccionString;
		this.directorString = directorString;
		this.listaEmpleados = listaEmpleados;
	}
	
	public Empresas() {
		super();
	}
	@XmlElement
	public int getCodeEmpresa() {
		return codeEmpresa;
	}
	public void setCodeEmpresa(int codeEmpresa) {
		this.codeEmpresa = codeEmpresa;
	}
	@XmlElement
	public String getDireccionString() {
		return direccionString;
	}
	public void setDireccionString(String direccionString) {
		this.direccionString = direccionString;
	}
	@XmlElement
	public Empleados getDirectorString() {
		return directorString;
	}
	public void setDirectorString(Empleados directorString) {
		this.directorString = directorString;
	}
	@XmlElement
	public ArrayList<Empleados> getListaEmpleados() {
		return listaEmpleados;
	}
	public void setListaEmpleados(ArrayList<Empleados> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	
	
}
