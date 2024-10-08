package formularios;

import java.awt.EventQueue;
//import FRMVisualizarXML.java;
//import FRMInsertarVenta.java;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PruebaWindowBuilderXMLXSD {

	private JFrame frmVentanaDeInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaWindowBuilderXMLXSD window = new PruebaWindowBuilderXMLXSD();
					window.frmVentanaDeInicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PruebaWindowBuilderXMLXSD() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVentanaDeInicio = new JFrame();
		frmVentanaDeInicio.setTitle("VENTANA DE INICIO");
		frmVentanaDeInicio.setBounds(100, 100, 450, 300);
		frmVentanaDeInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaDeInicio.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnNewButton.setBounds(29, 181, 76, 43);
		frmVentanaDeInicio.getContentPane().add(btnNewButton);
		
		JMenuBar menuBar = new JMenuBar();
		frmVentanaDeInicio.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Opciones");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Visualizar XML y Objeto Java");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FRMVisualizarXML frame = new FRMVisualizarXML();
					frame.setVisible(true);
				} catch (Exception c) {
					c.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Insertar Venta");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FRMInsertarVenta frame = new FRMInsertarVenta();
					frame.setVisible(true);
				} catch (Exception c) {
					c.printStackTrace();
				}
			}
		});
		
	}
}
