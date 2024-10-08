package clasesdatos;

import javax.xml.bind.annotation.XmlElement;

public class Venta {

	private int numventa;
	private int unidades;
	private String nombreCliente;
	private String fechaString;
	public Venta(int numventa, int unidades, String nombreCliente, String fechaString) {
		super();
		this.numventa = numventa;
		this.unidades = unidades;
		this.nombreCliente = nombreCliente;
		this.fechaString = fechaString;
	}
	public Venta() {
		super();
	}
	@XmlElement(name = "numventa")
	public int getNumventa() {
		return numventa;
	}
	public void setNumventa(int numventa) {
		this.numventa = numventa;
	}
	@XmlElement(name = "unidades")
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	@XmlElement(name = "nombrecliente")
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	@XmlElement(name = "fecha")
	public String getFechaString() {
		return fechaString;
	}
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}
	
}
