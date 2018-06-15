package Objetos;

public class Requerimiento {
    private int id;
    private int gerencia;
    private int departamento;
    private int encargado;
    private int asignar;
    private String descripcion;
    private String estado;

    public int getAsignar() {
        return asignar;
    }

    public void setAsignar(int asignar) {
        this.encargado=0;
        this.asignar = asignar;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGerencia() {
        return gerencia;
    }

    public void setGerencia(int gerencia) {
        this.departamento=0;
        this.gerencia = gerencia;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getEncargado() {
        return encargado;
    }

    public void setEncargado(int encargado) {
        this.encargado = encargado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
