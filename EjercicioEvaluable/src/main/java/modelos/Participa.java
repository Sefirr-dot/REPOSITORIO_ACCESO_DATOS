package modelos;

public class Participa {
    private int codparticipacion;
    private Estudiante estudiante; // Objeto Estudiante
    private Proyecto proyecto; // Objeto Proyecto
    private String tipoparticipacion;
    private int numaportaciones;

    // Constructor
    public Participa(int codparticipacion, Estudiante estudiante, Proyecto proyecto, String tipoparticipacion, int numaportaciones) {
        this.codparticipacion = codparticipacion;
        this.estudiante = estudiante;
        this.proyecto = proyecto;
        this.tipoparticipacion = tipoparticipacion;
        this.numaportaciones = numaportaciones;
    }

    // Getters y Setters
    public int getCodparticipacion() {
        return codparticipacion;
    }

    public void setCodparticipacion(int codparticipacion) {
        this.codparticipacion = codparticipacion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getTipoparticipacion() {
        return tipoparticipacion;
    }

    public void setTipoparticipacion(String tipoparticipacion) {
        this.tipoparticipacion = tipoparticipacion;
    }

    public int getNumaportaciones() {
        return numaportaciones;
    }

    public void setNumaportaciones(int numaportaciones) {
        this.numaportaciones = numaportaciones;
    }

    @Override
    public String toString() {
        return "Participa{" +
                "codparticipacion=" + codparticipacion +
                ", estudiante=" + estudiante +
                ", proyecto=" + proyecto +
                ", tipoparticipacion='" + tipoparticipacion + '\'' +
                ", numaportaciones=" + numaportaciones +
                '}';
    }
}