package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Proyecto {
    private int codigoproyecto;
    private String nombre;
    private Date fechainicio;
    private Date fechafin;
    private float presupuesto;
    private float extraaportacion;
    private List<Participa> participantes; // Lista de participaciones en el proyecto

    // Constructor
    public Proyecto(int codigoproyecto, String nombre, Date fechainicio, Date fechafin, float presupuesto, float extraaportacion) {
        this.codigoproyecto = codigoproyecto;
        this.nombre = nombre;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.presupuesto = presupuesto;
        this.extraaportacion = extraaportacion;
        this.participantes = new ArrayList<>();
    }

    // Getters y Setters
    public int getCodigoproyecto() {
        return codigoproyecto;
    }

    public void setCodigoproyecto(int codigoproyecto) {
        this.codigoproyecto = codigoproyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public float getExtraaportacion() {
        return extraaportacion;
    }

    public void setExtraaportacion(float extraaportacion) {
        this.extraaportacion = extraaportacion;
    }

    public List<Participa> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participa> participantes) {
        this.participantes = participantes;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "codigoproyecto=" + codigoproyecto +
                ", nombre='" + nombre + '\'' +
                ", fechainicio=" + fechainicio +
                ", fechafin=" + fechafin +
                ", presupuesto=" + presupuesto +
                ", extraaportacion=" + extraaportacion +
                '}';
    }
}