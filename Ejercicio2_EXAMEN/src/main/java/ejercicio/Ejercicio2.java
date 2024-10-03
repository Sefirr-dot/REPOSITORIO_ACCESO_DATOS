package ejercicio;

import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class Ejercicio2 {
//4 CODE EMPRESA
//+60 NOMBRE EMPRESA
//+60 Direccion empresa
//+4 Numero empleado
//+4 Media salario 
//+60Nombre Director 
	
	
	public static int POSICIONEMPRESA = 192;
	public static ArrayList<Empleados> listaEmpleados;

	public static void main(String[] args) throws JAXBException {


		JAXBContext context = JAXBContext.newInstance(ListaEmpresas.class);
		
		


	}

}
