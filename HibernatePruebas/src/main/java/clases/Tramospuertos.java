package clases;
// Generated 8 ene 2025, 19:08:51 by Hibernate Tools 5.6.15.Final

/**
 * Tramospuertos generated by hbm2java
 */
public class Tramospuertos implements java.io.Serializable {

	private int codigotramo;
	private Ciclistas ciclistas;
	private Etapas etapas;
	private String nombretramo;
	private float km;
	private int categoria;
	private String pendiente;

	public Tramospuertos() {
	}

	public Tramospuertos(int codigotramo, Ciclistas ciclistas, Etapas etapas, String nombretramo, float km,
			int categoria, String pendiente) {
		this.codigotramo = codigotramo;
		this.ciclistas = ciclistas;
		this.etapas = etapas;
		this.nombretramo = nombretramo;
		this.km = km;
		this.categoria = categoria;
		this.pendiente = pendiente;
	}

	public int getCodigotramo() {
		return this.codigotramo;
	}

	public void setCodigotramo(int codigotramo) {
		this.codigotramo = codigotramo;
	}

	public Ciclistas getCiclistas() {
		return this.ciclistas;
	}

	public void setCiclistas(Ciclistas ciclistas) {
		this.ciclistas = ciclistas;
	}

	public Etapas getEtapas() {
		return this.etapas;
	}

	public void setEtapas(Etapas etapas) {
		this.etapas = etapas;
	}

	public String getNombretramo() {
		return this.nombretramo;
	}

	public void setNombretramo(String nombretramo) {
		this.nombretramo = nombretramo;
	}

	public float getKm() {
		return this.km;
	}

	public void setKm(float km) {
		this.km = km;
	}

	public int getCategoria() {
		return this.categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getPendiente() {
		return this.pendiente;
	}

	public void setPendiente(String pendiente) {
		this.pendiente = pendiente;
	}

}
