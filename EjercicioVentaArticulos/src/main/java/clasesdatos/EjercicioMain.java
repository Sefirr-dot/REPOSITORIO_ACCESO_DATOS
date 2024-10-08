package clasesdatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

;

public class EjercicioMain {

	public static ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(VentasArticulos.class);
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		VentasArticulos ventasArticulos1 = (VentasArticulos) unmarshaller.unmarshal(new FileReader(".\\ventasarticulosdos.xml"));
		
		ArrayList<Articulo> articulos = ventasArticulos1.getArticulos();
		
		System.out.println("Numero de articulos: "+articulos.size());
		for (Articulo articulo : articulos) {
			MostrarArticulo(articulo);
			MostrarVenta(articulo);
		}
		
	}
	private static void MostrarArticulo(Articulo a) {
		
	    System.out.println("Código: " + a.getArtic().getCodigo() 
	        + "      Nombre: " + a.getArtic().getDenominacion() 
	        + "      PVP: " + a.getArtic().getPrecio());
	    System.out.println("NUM VENTA  FECHA VENTA  NOM-CLIENTE                UNIDADES     IMPORTE");
	    System.out.println("---------  -----------  ------------------         --------  ----------");
	}

	private static void MostrarVenta(Articulo a) {
	    double totalUnidades = 0;
	    double totalImporte = 0;
	    
	    for (Venta vent : a.getVentas().getVentas()) {
	        int numVenta = vent.getNumventa();
	        String fechaVenta = vent.getFechaString();
	        String nombreCliente = String.format("%-20s", vent.getNombreCliente());
	        int unidades = vent.getUnidades();
	        double importe = unidades * a.getArtic().getPrecio();
	        
	        // Acumular totales
	        totalUnidades += unidades;
	        totalImporte += importe;

	        // Formato de salida para cada venta
	        System.out.printf("%9d   %10s  %-25s  %8d  %10.2f\n", numVenta, fechaVenta, nombreCliente, unidades, importe);
	        
	    }

	    // Separador final
	    System.out.println("---------  -----------  ------------------         --------  ----------");
	    // Mostrar totales
	    System.out.printf("  TOTALES                                           %7.0f  %9.2f\n\n", totalUnidades, totalImporte);
	}

}
