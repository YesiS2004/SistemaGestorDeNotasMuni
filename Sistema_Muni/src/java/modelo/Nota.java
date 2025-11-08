package modelo;

import java.sql.Date;

public class Nota {
    private int idNota;
    private int idPersona;
    private Date fechaEntrega;
    private String detalles;
    private int estadoActual;
    private byte[] archivoNota; 

    public Nota() {}

    public Nota(int idNota, int idPersona, Date fechaEntrega, String detalles, int estadoActual, byte[] archivoNota) {
        this.idNota = idNota;
        this.idPersona = idPersona;
        this.fechaEntrega = fechaEntrega;
        this.detalles = detalles;
        this.estadoActual = estadoActual;
        this.archivoNota = archivoNota;
    }
    
    // Getters y Setters 
    public int getIdNota() { return idNota; }
    public void setIdNota(int idNota) { this.idNota = idNota; }

    public int getIdPersona() { return idPersona; }
    public void setIdPersona(int idPersona) { this.idPersona = idPersona; }

    public Date getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(Date fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public String getDetalles() { return detalles; }
    public void setDetalles(String detalles) { this.detalles = detalles; }

    public int getEstadoActual() { return estadoActual; }
    public void setEstadoActual(int estadoActual) { this.estadoActual = estadoActual; }

    public byte[] getArchivoNota() { return archivoNota; }
    public void setArchivoNota(byte[] archivoNota) { this.archivoNota = archivoNota; }
}