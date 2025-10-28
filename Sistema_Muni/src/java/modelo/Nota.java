package modelo;

import java.sql.Date;

public class Nota {
    private int ID_Nota;
    private int ID_Persona;
    private Date Fecha_Entrega;
    private String Detalles;
    private int Estado_Actual;
    private byte[] Nota; // se usa byte[] para almacenar archivos binarios (BLOB)

    public Nota() {}

    public Nota(int ID_Nota, int ID_Persona, Date Fecha_Entrega, String Detalles, int Estado_Actual, byte[] Nota) {
        this.ID_Nota = ID_Nota;
        this.ID_Persona = ID_Persona;
        this.Fecha_Entrega = Fecha_Entrega;
        this.Detalles = Detalles;
        this.Estado_Actual = Estado_Actual;
        this.Nota = Nota;
    }

    // Getters y Setters
    public int getID_Nota() { return ID_Nota; }
    public void setID_Nota(int ID_Nota) { this.ID_Nota = ID_Nota; }

    public int getID_Persona() { return ID_Persona; }
    public void setID_Persona(int ID_Persona) { this.ID_Persona = ID_Persona; }

    public Date getFecha_Entrega() { return Fecha_Entrega; }
    public void setFecha_Entrega(Date Fecha_Entrega) { this.Fecha_Entrega = Fecha_Entrega; }

    public String getDetalles() { return Detalles; }
    public void setDetalles(String Detalles) { this.Detalles = Detalles; }

    public int getEstado_Actual() { return Estado_Actual; }
    public void setEstado_Actual(int Estado_Actual) { this.Estado_Actual = Estado_Actual; }

    public byte[] getNota() { return Nota; }
    public void setNota(byte[] Nota) { this.Nota = Nota; }
}
