package model;

import java.io.Serializable;
import java.sql.Date;

public class Empleado implements Serializable {
	int emp_no;
	String apellido;
	String oficio;
	int dir;
	Date fecha_alt;
	float salario;
	float comision;
	int dep;
	
	public Empleado() {
		super();
	}
	public Empleado(int emp_no, String apellido, String oficio, int dir, Date fecha_alt, float salario, float comision,
			int dep) {
		super();
		this.emp_no = emp_no;
		this.apellido = apellido;
		this.oficio = oficio;
		this.dir = dir;
		this.fecha_alt = fecha_alt;
		this.salario = salario;
		this.comision = comision;
		this.dep = dep;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getOficio() {
		return oficio;
	}
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public Date getFecha_alt() {
		return fecha_alt;
	}
	public void setFecha_alt(Date fecha_alt) {
		this.fecha_alt = fecha_alt;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}
	public int getDep() {
		return dep;
	}
	public void setDep(int dep) {
		this.dep = dep;
	}
	
	
}