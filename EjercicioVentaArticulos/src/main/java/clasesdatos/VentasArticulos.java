package clasesdatos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "ventasarticulos")
public class VentasArticulos {
	private ArrayList<Articulo> articulos;

	public VentasArticulos(ArrayList<Articulo> articulos) {
		super();
		this.articulos = articulos;
	}

	public VentasArticulos() {
		super();
	}

	@XmlElement(name = "articulo")
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	
}
