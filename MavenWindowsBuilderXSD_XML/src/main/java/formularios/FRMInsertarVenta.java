package formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import clasesdatos.generated.ObjectFactory;
import clasesdatos.generated.Ventas;
import clasesdatos.generated.VentasType;
import javax.swing.JTextPane;

public class FRMInsertarVenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNumVenta;
	private JTextField textFieldNomCli;
	private JTextField textFieldUnidades;
	private JTextField textFieldFecha;
	private JLabel lblNumVenta;
	private JLabel lblNomCli;
	private JLabel lblUnidades;
	private JLabel lblFecha;
	private JButton btnGuardarVenta;
	private JButton btnCancVenta;
	private JLabel lblMensaje ;

	public static final String nombrefichero = "./ventasarticulos.xml";
	static Scanner teclado = new Scanner(System.in);


	/**
	 * Create the frame.
	 */

	public FRMInsertarVenta() {
		setTitle("Insertar Venta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNumVenta = new JLabel("Numero de Venta");
		lblNumVenta.setBounds(26, 11, 110, 14);
		contentPane.add(lblNumVenta);

		lblNomCli = new JLabel("Nombre de Cliente");
		lblNomCli.setBounds(26, 36, 110, 14);
		contentPane.add(lblNomCli);

		lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(26, 61, 110, 14);
		contentPane.add(lblUnidades);

		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(26, 86, 110, 14);
		contentPane.add(lblFecha);
		
		lblMensaje = new JLabel("");
		lblMensaje.setBounds(26, 111, 329, 20);
		contentPane.add(lblMensaje);

		textFieldNumVenta = new JTextField();
		textFieldNumVenta.setBounds(146, 5, 208, 20);
		contentPane.add(textFieldNumVenta);
		textFieldNumVenta.setColumns(10);

		textFieldNomCli = new JTextField();
		textFieldNomCli.setBounds(146, 30, 208, 20);
		textFieldNomCli.setColumns(10);
		contentPane.add(textFieldNomCli);
		textFieldNomCli.setVisible(true);

		textFieldUnidades = new JTextField();
		textFieldUnidades.setBounds(146, 55, 208, 20);
		textFieldUnidades.setColumns(10);
		contentPane.add(textFieldUnidades);
		textFieldUnidades.setVisible(true);

		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(146, 80, 208, 20);
		textFieldFecha.setColumns(10);
		contentPane.add(textFieldFecha);
		textFieldFecha.setVisible(true);

		btnGuardarVenta = new JButton("Guardar Venta");
		btnGuardarVenta.setVisible(true);
		btnGuardarVenta.setBounds(26, 171, 175, 23);
		contentPane.add(btnGuardarVenta);

		btnCancVenta = new JButton("Cancelar Venta");
		btnCancVenta.setBounds(219, 171, 135, 23);
		contentPane.add(btnCancVenta);
		
		btnCancVenta.setVisible(true);
		btnCancVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
			}
		});

		btnGuardarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
					Unmarshaller u = jaxbContext.createUnmarshaller();
					JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream(nombrefichero));

					VentasType miventa = (VentasType) jaxbElement.getValue();
					Ventas vent = miventa.getVentas();
					List listaVentas = new ArrayList<>();
					listaVentas = vent.getVenta();
					boolean existe = false;
					// comprobar si existe el número de venta, recorriendo el arraylist
					for (int i = 0; i < listaVentas.size(); i++) {
						Ventas.Venta ve = (Ventas.Venta) listaVentas.get(i);
						if (ve.getNumventa().intValue() == Integer.parseInt(textFieldNumVenta.getText())) {
							existe = true;
							break;
						}
					}
					if (existe) {
						lblMensaje.setText("En número de venta ya existe: " + textFieldNumVenta.getText());
						limpiarDatos();
					} else {

						Ventas.Venta ve = new Ventas.Venta();
						BigInteger nume = new BigInteger(textFieldNumVenta.getText());
						ve.setNumventa(nume);
						String nombre = textFieldNomCli.getText();
						ve.setNombrecliente(nombre);
						String fecha = textFieldFecha.getText();
						ve.setFecha(fecha);
						int unidades = Integer.parseInt(textFieldUnidades.getText());
						ve.setUnidades(unidades);
						// Se añade la venta a la lista
						listaVentas.add(ve);
						// Se crea el Marshaller, volcar la lista al fichero XML
						Marshaller m = jaxbContext.createMarshaller();
						m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
						m.marshal(jaxbElement, new FileOutputStream(nombrefichero));
						//lblMensaje.setText("Venta añadida: " + textFieldNumVenta.getText());
						JOptionPane.showMessageDialog(rootPane, "Venta añadida: " + textFieldNumVenta.getText());
						limpiarDatos();
					}
				} catch (JAXBException je) {
					System.out.println(je.getCause());
				} catch (IOException ioe) {
					System.out.println(ioe.getMessage());
				}
			}
		});
	}
	
	public void limpiarDatos() {
		textFieldNumVenta.setText("");
		textFieldNomCli.setText("");
		textFieldUnidades.setText("");
		textFieldFecha.setText("");
		//lblMensaje.setText("");
	}
}
