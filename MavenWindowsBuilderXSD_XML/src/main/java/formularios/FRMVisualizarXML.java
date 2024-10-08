package formularios;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import clasesdatos.generated.DatosArtic;
import clasesdatos.generated.ObjectFactory;
import clasesdatos.generated.Ventas;
import clasesdatos.generated.VentasType;

public class FRMVisualizarXML extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField t;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JTextArea textArea;
	public static final String nombrefichero = "./ventasarticulos.xml";
	static Scanner teclado = new Scanner(System.in);
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMVisualizarXML frame = new FRMVisualizarXML();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public FRMVisualizarXML() {
		
		setTitle("Visualizar XML");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setBounds(100, 100, 550, 350);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Objetos");
		lblNewLabel.setBounds(95, 15, 131, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 400, 250);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 250));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 500, 250);
		panel.add(textArea);
		
		System.out.println("-----------------------Con UNMARSHALLING----------------- ");
		System.out.println("-------------------- LEEMOS EL ARCHIVO XML Y ------------ ");
		System.out.println("-LO CONVERTIMOS EN UN OBJETO JAVA GENERICO 'jaxbElement'- ");
		try {
			// Creamos el contexto
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);

			Unmarshaller u = jaxbContext.createUnmarshaller();

			JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream(nombrefichero));
			System.out.println("-------------------Con MARSHALLING------------------ ");
			System.out.println("- CONVERTIMOS EL OBJETO JAVA 'jaxbElement' A XML Y - ");
			System.out.println("------------LO VISUALIZAMOS POR CONSOLA------------- ");

			Marshaller m = jaxbContext.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Visualiza por consola
			m.marshal(jaxbElement, System.out);

			System.out.println("----------------------------------------- ------------------ ");
			System.out.println("- GUARDAMOS EL OBJETO JAVA 'jaxbElement' EN VARIABLES PARA - ");
			System.out.println("-----------------VISUALIZARLO POR CONSOLA------------------- ");

			// Asignamos a la variable miventa, de tipo VentasType, el objeto contenido en
			// jaxbElement.
			VentasType miventa = (VentasType) jaxbElement.getValue();

			// Creamos una instancia para obtener todas las ventas en la variable vent
			Ventas vent = miventa.getVentas();

			// Guardamos las ventas en la lista listaVentas
			List listaVentas = new ArrayList();
			listaVentas = vent.getVenta();

			// Asignamos a la variable miartic, del tipo DatosArtic, los datos del artículo
			DatosArtic miartic = (DatosArtic) miventa.getArticulo();

			System.out.println("---------------------------- ");
			System.out.println("---VISUALIZAR LOS OBJETOS--- ");
			System.out.println("---------------------------- ");
			
			textArea.setText("Nombre art: " + miartic.getDenominacion()+"\n"); 
			textArea.append("Codigo art: " + miartic.getCodigo()+"\n");
			textArea.append("Ventas del artículo , hay: " + listaVentas.size()+"\n");

			// Visualizamos las ventas del artículo
			for (int i = 0; i < listaVentas.size(); i++) {
				Ventas.Venta ve = (Ventas.Venta) listaVentas.get(i);
				textArea.append("Número de venta: " + ve.getNumventa() + ". Nombre cliente: " + ve.getNombrecliente()
						+ ", unidades: " + ve.getUnidades() + ", fecha: " + ve.getFecha()+"\n");
			}
		} catch (JAXBException je) {
			System.out.println(je.getCause());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}		
}


