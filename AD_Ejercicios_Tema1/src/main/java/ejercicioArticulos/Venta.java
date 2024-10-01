package ejercicioArticulos;
import javax.xml.bind.annotation.XmlElement;

public class Venta {
    private int numventa;
    private int unidades;
    private String nombrecliente;
    private String fecha;

    @XmlElement
    public int getNumventa() {
        return numventa;
    }

    public void setNumventa(int numventa) {
        this.numventa = numventa;
    }

    @XmlElement
    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    @XmlElement
    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    @XmlElement
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
