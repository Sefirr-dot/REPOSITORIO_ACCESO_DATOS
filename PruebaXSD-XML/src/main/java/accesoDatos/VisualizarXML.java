package accesoDatos;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class VisualizarXML {

	public static void main(String[] args) throws FileNotFoundException, JAXBException {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		
		do {
			mostrarMenu();
			opcion = sc.nextInt();
			switch(opcion) {
			case 1:
				visualizarxml();
				break;
			case 2:
				System.out.println("Di el  numero de venta");
				int numVenta = sc.nextInt();
				System.out.println("Di el nombre del cliente");
				String nomCli=sc.next();
				System.out.println("Di el numero de unidades");
				int unidades= sc.nextInt();
				System.out.println("Di la fecha");
				String fechaString =sc.next();
				insertarventa(numVenta, nomCli, unidades, fechaString);
				break;
			case 3:
				System.out.println("Di el  numero de venta");
				int numVenta1 = sc.nextInt();
				eliminarVenta(numVenta1);
				break;
			case 4:
				System.out.println("Di el numero venta");
				int numVenta2 = sc.nextInt();
				System.out.println("Di las unidades");
				int unidades2 = sc.nextInt();
				modificarUnidades(numVenta2,unidades2);
			case 0:
				System.out.println("Adios!!");
				break;
			}
			}while(opcion != 0);
		
		sc.close();
	}
	
	private static void modificarUnidades(int numVenta2, int unidades) throws JAXBException, FileNotFoundException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);    
		Unmarshaller u = jaxbContext.createUnmarshaller();     
		JAXBElement jaxbElement = (JAXBElement)u.unmarshal(new FileInputStream("./ventasarticulos.xml")); 
		VentasType miventa = (VentasType) jaxbElement.getValue();   
		//Obtenemos una instancia para obtener todas las ventas      
		Ventas vent = miventa.getVentas();    
		// Guardamos las ventas en la lista  
		List listaVentas = new ArrayList();  
		listaVentas = vent.getVenta(); 
		for (int i = 0; i < listaVentas.size(); i++) {   
			Ventas.Venta ve = (Ventas.Venta) listaVentas.get(i);
			if(ve.getNumventa().intValue()==numVenta2) {
				ve.setUnidades(numVenta2);
			}
		Marshaller m = jaxbContext.createMarshaller();   
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);      
		m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));
			            
		}}

	private static void mostrarMenu() {
		System.out.println("1. Visualizar XML.");
		System.out.println("2. Insertar Venta.");
		System.out.println("3. Eliminar Venta.");
		System.out.println("4. Modificar unidades");
		System.out.println("0. Salir.");
		
	}

	public static void visualizarxml() {   
	System.out.println("------------------------------ ");  
	System.out.println("-------VISUALIZAR XML--------- ");  
	System.out.println("------------------------------ "); 
	try {       //Creamos el contexto    
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class); 
		Unmarshaller u = jaxbContext.createUnmarshaller();   
		JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));  
		Marshaller m = jaxbContext.createMarshaller();   
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);   
		// Visualiza por consola 
		m.marshal(jaxbElement, System.out);              
		//Cargamos ahora el documento en los tipos  
		VentasType miventa = (VentasType) jaxbElement.getValue();   
		//Obtenemos una instancia para obtener todas las ventas      
		Ventas vent = miventa.getVentas();    
		// Guardamos las ventas en la lista  
		List listaVentas = new ArrayList();  
		listaVentas = vent.getVenta();   
		System.out.println("---------------------------- ");  
		System.out.println("---VISUALIZAR LOS OBJETOS--- ");  
		System.out.println("---------------------------- ");  
		// Cargamos los datos del artículo  
		DatosArtic miartic = (DatosArtic) miventa.getArticulo();  
		System.out.println("Nombre art: " +  miartic.getDenominacion());  
		System.out.println("Coodigo art: " + miartic.getCodigo());  
		System.out.println("Ventas  del artículo , hay: " +listaVentas.size());       
		//Visualizamos las ventas del artículo  
		for (int i = 0; i < listaVentas.size(); i++) {   
			Ventas.Venta ve = (Ventas.Venta) listaVentas.get(i);   
			System.out.println("Número de venta: " + ve.getNumventa() + ". Nombre cliente: " +ve.getNombrecliente() + 
					", unidades: " +ve.getUnidades() + ", fecha: " + ve.getFecha());        }    
		} catch (JAXBException je) {    System.out.println(je.getCause());    
		} catch (IOException ioe) {    
			System.out.println(ioe.getMessage());} 
	} 
	
	private static void insertarventa (int numeventa, String nomcli, int uni, String fecha) {  
		System.out.println("---------------------------- ");  
		System.out.println("-------AÑADIR VENTA--------- "); 
		System.out.println("---------------------------- ");  
		try {     
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);    
		Unmarshaller u = jaxbContext.createUnmarshaller();     
		JAXBElement jaxbElement = (JAXBElement)       u.unmarshal(new FileInputStream("./ventasarticulos.xml"));      
		VentasType miventa = (VentasType) jaxbElement.getValue();     
		Ventas vent = miventa.getVentas();     
		List listaVentas = new ArrayList();  
		listaVentas = vent.getVenta();        // comprobar si existe el número de venta,      
		// reccorriendo el arraylist       
		int existe = 0;        
		for (int i = 0; i < listaVentas.size(); i++) {   
			Ventas.Venta ve = (Ventas.Venta) listaVentas.get(i);  
			if (ve.getNumventa().intValue() == numeventa) { 
				existe = 1; break;}    
		}       if (existe == 0) {      
			// Crear el objeto Ventas.Venta  
			Ventas.Venta ve = new Ventas.Venta();     
			ve.setNombrecliente(nomcli);  
			ve.setFecha(fecha); ve.setUnidades(uni);      
			BigInteger nume = BigInteger.valueOf(numeventa);     


			ve.setNumventa(nume);        // Se añade la venta a la lista   
			listaVentas.add(ve);        //Se crea el Marshaller, volcar la lista al fichero XML    
			Marshaller m = jaxbContext.createMarshaller();   
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);      
			m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));  
			System.out.println("Venta añadida: " + numeventa);  
		} else  
			System.out.println("En número de venta ya existe: " + numeventa);   
		} catch (JAXBException je) {
			System.out.println(je.getCause()); 
		} catch (IOException ioe) { 
			System.out.println(ioe.getMessage());
			}  
		}
	public static void eliminarVenta(int numVenta) throws JAXBException, FileNotFoundException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);    
		Unmarshaller u = jaxbContext.createUnmarshaller();     
		JAXBElement jaxbElement = (JAXBElement)u.unmarshal(new FileInputStream("./ventasarticulos.xml")); 
		VentasType miventa = (VentasType) jaxbElement.getValue();   
		//Obtenemos una instancia para obtener todas las ventas      
		Ventas vent = miventa.getVentas();    
		// Guardamos las ventas en la lista  
		List listaVentas = new ArrayList();  
		listaVentas = vent.getVenta(); 
		for (int i = 0; i < listaVentas.size(); i++) {   
			Ventas.Venta ve = (Ventas.Venta) listaVentas.get(i);
			if(ve.getNumventa().intValue()==numVenta) {
				System.out.println("Venta "+ve.getNumventa()+" eliminada.");
				listaVentas.remove(i);
			}
			}
		Marshaller m = jaxbContext.createMarshaller();   
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);      
		m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));
			            
		}
	

}


