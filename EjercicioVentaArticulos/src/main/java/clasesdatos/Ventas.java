package clasesdatos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class Ventas {

	private ArrayList<Venta> ventas;

	public Ventas(ArrayList<Venta> ventas) {
		super();
		this.ventas = ventas;
	}

	public Ventas() {
		super();
	}
	@XmlElement(name = "venta")
	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}
	
	
}
