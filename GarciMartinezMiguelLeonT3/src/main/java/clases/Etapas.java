package clases;
// Generated 8 ene 2025, 15:57:46 by Hibernate Tools 5.6.15.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Etapas generated by hbm2java
 */
public class Etapas implements java.io.Serializable {

	private int codigoetapa;
	private Ciclistas ciclistas;
	private String tipoetapa;
	private String fechasalida;
	private String pobsalida;
	private String pobllegada;
	private float km;
	private Set llevas = new HashSet(0);
	private Set tramospuertoses = new HashSet(0);

	public Etapas() {
	}

	public Etapas(int codigoetapa, Ciclistas ciclistas, String tipoetapa, String fechasalida, String pobsalida,
			String pobllegada, float km) {
		this.codigoetapa = codigoetapa;
		this.ciclistas = ciclistas;
		this.tipoetapa = tipoetapa;
		this.fechasalida = fechasalida;
		this.pobsalida = pobsalida;
		this.pobllegada = pobllegada;
		this.km = km;
	}

	public Etapas(int codigoetapa, Ciclistas ciclistas, String tipoetapa, String fechasalida, String pobsalida,
			String pobllegada, float km, Set llevas, Set tramospuertoses) {
		this.codigoetapa = codigoetapa;
		this.ciclistas = ciclistas;
		this.tipoetapa = tipoetapa;
		this.fechasalida = fechasalida;
		this.pobsalida = pobsalida;
		this.pobllegada = pobllegada;
		this.km = km;
		this.llevas = llevas;
		this.tramospuertoses = tramospuertoses;
	}

	public int getCodigoetapa() {
		return this.codigoetapa;
	}

	public void setCodigoetapa(int codigoetapa) {
		this.codigoetapa = codigoetapa;
	}

	public Ciclistas getCiclistas() {
		return this.ciclistas;
	}

	public void setCiclistas(Ciclistas ciclistas) {
		this.ciclistas = ciclistas;
	}

	public String getTipoetapa() {
		return this.tipoetapa;
	}

	public void setTipoetapa(String tipoetapa) {
		this.tipoetapa = tipoetapa;
	}

	public String getFechasalida() {
		return this.fechasalida;
	}

	public void setFechasalida(String fechasalida) {
		this.fechasalida = fechasalida;
	}

	public String getPobsalida() {
		return this.pobsalida;
	}

	public void setPobsalida(String pobsalida) {
		this.pobsalida = pobsalida;
	}

	public String getPobllegada() {
		return this.pobllegada;
	}

	public void setPobllegada(String pobllegada) {
		this.pobllegada = pobllegada;
	}

	public float getKm() {
		return this.km;
	}

	public void setKm(float km) {
		this.km = km;
	}

	public Set getLlevas() {
		return this.llevas;
	}

	public void setLlevas(Set llevas) {
		this.llevas = llevas;
	}

	public Set getTramospuertoses() {
		return this.tramospuertoses;
	}

	public void setTramospuertoses(Set tramospuertoses) {
		this.tramospuertoses = tramospuertoses;
	}

}
