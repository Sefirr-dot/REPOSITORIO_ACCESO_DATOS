package ejemplos;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ejemplo2ventanas extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JTextField num=new JTextField(10);
	JTextField nombre=new JTextField(25);
	JTextField direc=new JTextField(25);
	JTextField tlf=new JTextField(15);
	JTextField dep=new JTextField(5);
	JLabel mensaje=new JLabel(" ----------------------------- ");
	JLabel titulo=new JLabel ("ENTRADA DE DATOS DE EMPLEADOS:");
	JLabel lnum = new JLabel ("NUMERO EMPLEADO:");
	JLabel lnom = new JLabel ("NOMBRE EMPLEADO:");
	JLabel ldir = new JLabel ("DIRECCION EMPLEADO:");
	JLabel ltlf = new JLabel ("TELÉFONO EMPLEADO:");
	JLabel ldep = new JLabel ("N DEPARTAMENTO:");
	JButton balta= new JButton("Alta empleado");
	JButton breset=new JButton("Limpiar datos");
	JButton fin=new JButton("CERRAR");
	Color c; //para poner colores
	// WHITE,LIGHTGRAY,GRAY,DARKGRAY,BLUE,BLACK,RED,MAGENTA,PINK,ORANGE,CYAN,GREEN,YELLOW
	public ejemplo2ventanas ()
	{ 
		setTitle("ENTRADA DE EMPLEADOS. TRES.");

		JPanel p0 = new JPanel();
		c = Color.CYAN;
		p0.add(titulo);
		p0.setBackground(c);
		JPanel p1 = new JPanel();
		p1.setLayout (new FlowLayout());
		p1.add(lnum);
		p1.add(num);
		JPanel p2 = new JPanel();
		p2.setLayout (new FlowLayout());
		p2.add(lnom);
		p2.add(nombre);
		JPanel p3 = new JPanel();
		p3.setLayout (new FlowLayout());
		p3.add(ldir);
		p3.add(direc);
		JPanel p4 = new JPanel();
		p4.setLayout (new FlowLayout());
		p4.add(ltlf);
		p4.add(tlf);
		JPanel p5 = new JPanel();
		p5.setLayout (new FlowLayout());
		p5.add(ldep);
		p5.add(dep);
		JPanel p6 = new JPanel();
		p6.setLayout (new FlowLayout());
		c = Color.YELLOW;;
		p6.add(balta); p6.add(breset);
		p6.add(fin);
		p6.setBackground(c);
		JPanel p7 = new JPanel();
		p7.setLayout (new FlowLayout());
		p7.add(mensaje);
		// para ver la ventana y colocar los panel verticalmente
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 
		// añadir los panel al Jframe
		add(p0);add(p1);add(p2);add(p3);add(p4);add(p5);add(p6);add(p7);
		pack(); //hace que se coloquen alineados los elementos de cada JPanel
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Se ejecuta ActionListener al pulsar los botones
		balta.addActionListener(this);
		breset.addActionListener(this);
		fin.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == balta) { //SE PULSA EL BOTON 
			String nom=nombre.getText();
			String dir=direc.getText();
			mensaje.setText("Has pulsado el botón alta. " +
					"El nombre tecleado es : " + nom + 
					", Su direccion es: " + dir); 
		}
		if (e.getSource() == fin) { //SE PULSA EL BOTON 
			System.exit(0); //sale de la aplicación
			//dispose(); //descarga la ventana actual
		}
		if (e.getSource() == breset) { //SE PULSA EL BOTON 
			mensaje.setText(" has pulsado el boton limpiar.."); 
			num.setText(" ");nombre.setText(" ");
			direc.setText(" ");tlf.setText(" ");dep.setText(" ");
		}
	}
	public static void main(String[] args) {
		ejemplo2ventanas v = new ejemplo2ventanas ();
		v.setVisible(true);

	}//fin main
}//fin clase
