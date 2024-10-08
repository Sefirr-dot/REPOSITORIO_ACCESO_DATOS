package clasesdatos;

import javax.xml.bind.annotation.XmlElement;

public class Artic {

	private String codigo;
	private String denominacion;
	private int stock;
	private double precio;
	public Artic(String codigo, String denominacion, int stock, double precio) {
		super();
		this.codigo = codigo;
		this.denominacion = denominacion;
		this.stock = stock;
		this.precio = precio;
	}
	public Artic() {
		super();
	}
	@XmlElement(name = "codigo")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@XmlElement(name ="denominacion")
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	@XmlElement(name = "stock")
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@XmlElement(name="precio")
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
