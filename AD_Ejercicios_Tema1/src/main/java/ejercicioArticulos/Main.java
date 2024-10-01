package ejercicioArticulos;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear el contexto de JAXB con la clase raíz VentasArticulos
            JAXBContext jaxbContext = JAXBContext.newInstance(VentasArticulos.class);

            // Crear el Unmarshaller para deserializar el archivo XML
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Leer el archivo XML
            File archivoXML = new File(".\\ventasarticulos.xml");
            VentasArticulos ventasArticulos = (VentasArticulos) jaxbUnmarshaller.unmarshal(archivoXML);

            // Mostrar los datos del artículo
            Articulo articulo = ventasArticulos.getArticulo();
            System.out.println("Artículo:");
            System.out.println("Código: " + articulo.getCodigo());
            System.out.println("Denominación: " + articulo.getDenominacion());
            System.out.println("Stock: " + articulo.getStock());
            System.out.println("Precio: " + articulo.getPrecio());

            // Mostrar los datos de las ventas
            System.out.println("\nVentas:");
            for (Venta venta : ventasArticulos.getVentas().getVentas()) {
                System.out.println("Número de Venta: " + venta.getNumventa());
                System.out.println("Unidades: " + venta.getUnidades());
                System.out.println("Nombre del Cliente: " + venta.getNombrecliente());
                System.out.println("Fecha: " + venta.getFecha());
                System.out.println("----------------------------");
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
