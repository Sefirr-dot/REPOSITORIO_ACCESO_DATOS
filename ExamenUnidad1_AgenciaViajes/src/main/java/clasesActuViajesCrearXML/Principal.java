package clasesActuViajesCrearXML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Principal {
	static Scanner teclado = new Scanner(System.in);

	static String viajes = "Viajes.dat";
	static String reservas = "Reservas.dat";

	static int lonviajes = 76;
	static int lonreservas = 48;

	public static void main(String[] args) throws IOException, JAXBException {
		try {
			int op = 0;
			do {
				System.out.println("............................................................\n"
						+ ".  1 Actualizar Viajes y Crear XML. \n" 
						+ ".  2 Visualizar Viajes  \n" 
						+ ".  0 SALIR.\n"
						+ "............................................................\n");
				System.out.println("TECLEA OPERACION: ");
				op = teclado.nextInt();
				switch (op) {
				case 1: // Actualizar el fichero Viajes.dat y Crear Viajes.xml
					actualizarViajesyCrearXML();
					break;
				case 2: // visualizar todo el fichero Viajes.dat de forma secuencial
					visualizarViajes();
					break;
				} // cierra Sub menu

			} while (op != 0);

		} catch (InputMismatchException e) {
			System.out.println("ERROR. LA OPCION DEBE SER NUMERICA.\n ");
		}

	}

	private static void actualizarViajesyCrearXML() throws IOException, JAXBException {

		File zFicheroVIAJES = new File(viajes);
		RandomAccessFile ficheroViajes = new RandomAccessFile(zFicheroVIAJES, "rw");
		
		Listaviajes listaViajes = new Listaviajes();
		
		ArrayList<Viajes> listaFinalViajes = new ArrayList<Viajes>();
		ArrayList<String> situacionReservas = new ArrayList<String>();
		long posicionViajes = 0;

		while(posicionViajes<ficheroViajes.length()) {
			ficheroViajes.seek(posicionViajes);
			int codViaje = ficheroViajes.readInt();
			if(codViaje!=0) {
				char nombreViaje[] = new char[30], auxNombreViaje;
				
				for (int i = 0; i < nombreViaje.length; i++) {
					auxNombreViaje = ficheroViajes.readChar();
					nombreViaje[i] = auxNombreViaje;	
				}
				String nombreViajeFinalString = new String(nombreViaje).trim();
				
				int pvp = ficheroViajes.readInt();
				int plazas = ficheroViajes.readInt();
				
				Viajes v1 = new Viajes(codViaje,nombreViajeFinalString,pvp,plazas,0,0,null);
				
				leerListaReserva(v1);
				ficheroViajes.writeInt(v1.getNumreservas());
				listaViajes.getLista().add(v1);
				
			}
			
			posicionViajes += lonviajes;
		}
		
		ficheroViajes.close();

	    // Crea el XML file
	    File xmlFile = new File("viajes.xml");
	    JAXBContext jaxbContext = JAXBContext.newInstance(Listaviajes.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	    // Formatea la salida
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	    // Escribe el xml
	    jaxbMarshaller.marshal(listaViajes, xmlFile);
	    System.out.println("XML creado correctamente.");
		
	}


	public static void visualizarViajes() throws IOException {

		File zFicheroVIAJES = new File(viajes);
		RandomAccessFile ficheroViajes = new RandomAccessFile(zFicheroVIAJES, "rw");
		String situacionString = "";
		
		
		ArrayList<Viajes> listaFinalViajes = new ArrayList<Viajes>();
		ArrayList<String> situacionReservas = new ArrayList<String>();
		long posicionViajes = 0;
		
		 System.out.printf("%-10s %-30s %-5s %-7s %-9s %-8s %-20s%n",
			        "CodViaje", "Nombre", "PVP", "PLAZAS", "RESERVAS", "IMPORTE", "Situación");
			    System.out.println("--------------------------------------------------------------------------------------");
		while(posicionViajes<ficheroViajes.length()) {
			ficheroViajes.seek(posicionViajes);
			int codViaje = ficheroViajes.readInt();
			if(codViaje!=0) {
				char nombreViaje[] = new char[30], auxNombreViaje;
				
				for (int i = 0; i < nombreViaje.length; i++) {
					auxNombreViaje = ficheroViajes.readChar();
					nombreViaje[i] = auxNombreViaje;	
				}
				String nombreViajeFinalString = new String(nombreViaje);
				
				int pvp = ficheroViajes.readInt();
				int plazas = ficheroViajes.readInt();
				int numReservas = ficheroViajes.readInt();
				int importe = numReservas*pvp;
				if(numReservas>plazas) {
					int aux = numReservas-plazas;
					situacionString= "Reservas excedidas en "+ aux;
				} else {
					situacionString="Correcto";
				}
				System.out.printf("%-10d %-30s %-5d %-7d %-9d %-8d %-20s%n",
		                codViaje, nombreViajeFinalString, pvp, plazas, numReservas, importe, situacionString);
				
			}
			
			posicionViajes += lonviajes;
		}

	}
	
	public static void leerListaReserva(Viajes v) throws IOException {
		File zFicheroReservas = new File(reservas);
		String holaString = "";
		RandomAccessFile ficheroReservas = new RandomAccessFile(zFicheroReservas, "rw");
		int importe =0;
		long posicionReservas = 0;
		int reservasTOTALES = 0;
		ArrayList<Reservas> listaReservas = new ArrayList<Reservas>();
		while(posicionReservas<ficheroReservas.length()) {
			ficheroReservas.seek(posicionReservas);
			
			char nomCliente[] = new char[20], auxNomCliente;
			for (int i = 0; i < nomCliente.length; i++) {
				auxNomCliente = ficheroReservas.readChar();
				nomCliente[i] =auxNomCliente;
				
			}
			String nomClienteString = new String(nomCliente).trim();
			int codViaj = ficheroReservas.readInt();
			int plazasReservadas = ficheroReservas.readInt();
			Reservas r1 = new Reservas(nomClienteString ,plazasReservadas);
			if(codViaj == v.getCodviaje()) {
				listaReservas.add(r1);
				reservasTOTALES+=plazasReservadas;
			}
			posicionReservas+=lonreservas;
		}
		v.setTotalimporte(reservasTOTALES*v.getPvp());
		v.setNumreservas(reservasTOTALES);
		v.setLista(listaReservas);
		
		
	}
}
