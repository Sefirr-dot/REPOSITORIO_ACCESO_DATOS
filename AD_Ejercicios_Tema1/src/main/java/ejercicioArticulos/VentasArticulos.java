package ejercicioArticulos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ventasarticulos")
public class VentasArticulos {
    private Articulo articulo;
    private Ventas ventas;  // Debe reflejar el contenedor "ventas"

    @XmlElement(name = "articulo")
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @XmlElement(name = "ventas")  // Asegúrate de que mapeamos "ventas"
    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }
}
