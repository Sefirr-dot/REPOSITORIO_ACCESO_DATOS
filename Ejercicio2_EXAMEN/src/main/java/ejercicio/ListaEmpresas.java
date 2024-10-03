package ejercicio;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Lista Empresas")
public class ListaEmpresas {

	private ArrayList<Empresas> arrayEmpresas;

	public ListaEmpresas(ArrayList<Empresas> arrayEmpresas) {
		super();
		this.arrayEmpresas = arrayEmpresas;
	}

	public ListaEmpresas() {
		super();
	}

	public ArrayList<Empresas> getArrayEmpresas() {
		return arrayEmpresas;
	}

	public void setArrayEmpresas(ArrayList<Empresas> arrayEmpresas) {
		this.arrayEmpresas = arrayEmpresas;
	}
	
	
}
