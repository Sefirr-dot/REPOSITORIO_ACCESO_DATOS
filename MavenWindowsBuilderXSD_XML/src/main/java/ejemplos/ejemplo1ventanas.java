package ejemplos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
public class ejemplo1ventanas extends JFrame 
implements ActionListener{
	private static final long serialVersionUID = 1L; // esto sale automático
	private JButton b1; // definimos botón1
	private JButton b2; // definimos botón2
	private JTextField t; // definimos campo de texto
	public ejemplo1ventanas() {
		setTitle("Prueba de ventana."); 
		setLayout(null);
		setSize(600, 250);
		setVisible(true);

		b1 = new JButton("Púlsame 1"); // Se crea el botón 
		b1.setBounds(100, 50, 100, 20); // indicamos la posición y la dimensión
		add(b1); // se añade el botón a la ventana

		b2 = new JButton("Púlsame 2"); // Se crea el botón 
		b2.setBounds(100, 80, 100, 20); // indicamos la posición y la dimensión
		add(b2); // se añade el botón a la ventana

		t = new JTextField(20); // Se crea el campo de texto
		add(t); // se añade a la ventana
		t.setBounds(210, 50, 200, 50);

		// Se le dice al botón qué tiene que hacer cuando lo pulsemos.
		// eso se hace con addActionListener
		b1.addActionListener(this);
		b2.addActionListener(this); 
		// Se le dice a la ventana que termine el programa cuando se la cierre
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) //acción cuando pulsamos botones 
	{ 
		if (e.getSource() == b1) 
			t.setText (" SE HA PULSADO EL BOTÓN 1."); 

		if (e.getSource() == b2) 
			t.setText (" SE HA PULSADO EL BOTÓN 2."); 
	}
	public static void main(String[] args) {
		new ejemplo1ventanas();
	}//fin main

}//fin class 

