package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Estudiante {
    private int codestudiante;
    private String nombre;
    private String direccion;
    private String tlf;
    private Date fechaalta;
    private List<Participa> participaen = new ArrayList<Participa>(); // Lista de participaciones del estudiante

    // Constructor
    public Estudiante(int codestudiante, String nombre, String direccion, String tlf, Date fechaalta) {
        this.codestudiante = codestudiante;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tlf = tlf;
        this.fechaalta = fechaalta;
        this.participaen = new ArrayList<>();
    }

    // Getters y Setters
    public int getCodestudiante() {
        return codestudiante;
    }

    public void setCodestudiante(int codestudiante) {
        this.codestudiante = codestudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public List<Participa> getParticipaen() {
        return participaen;
    }

    public void setParticipaen(List<Participa> participaen) {
        this.participaen = participaen;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "codestudiante=" + codestudiante +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tlf='" + tlf + '\'' +
                ", fechaalta=" + fechaalta +
                '}';
    }
}