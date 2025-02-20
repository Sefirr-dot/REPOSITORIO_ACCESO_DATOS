package model;

import java.sql.Date;

public class Empleado {
	
	private int dep_no;
	private String apellido;
	private String oficio;
	private int dir;
	private Date fechaAlt;
	private float slario;
	private float comsion;
	private float dept_no;
	
	public Empleado() {
		super();
	}

	public Empleado(int dep_no, String apellido, String oficio, int dir, Date fechaAlt, float slario, float comsion,
			float dept_no) {
		super();
		this.dep_no = dep_no;
		this.apellido = apellido;
		this.oficio = oficio;
		this.dir = dir;
		this.fechaAlt = fechaAlt;
		this.slario = slario;
		this.comsion = comsion;
		this.dept_no = dept_no;
	}

	public int getDep_no() {
		return dep_no;
	}

	public void setDep_no(int dep_no) {
		this.dep_no = dep_no;
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

	public Date getFechaAlt() {
		return fechaAlt;
	}

	public void setFechaAlt(Date fechaAlt) {
		this.fechaAlt = fechaAlt;
	}

	public float getSlario() {
		return slario;
	}

	public void setSlario(float slario) {
		this.slario = slario;
	}

	public float getComsion() {
		return comsion;
	}

	public void setComsion(float comsion) {
		this.comsion = comsion;
	}

	public float getDept_no() {
		return dept_no;
	}

	public void setDept_no(float dept_no) {
		this.dept_no = dept_no;
	}
	
	
	
	
}
