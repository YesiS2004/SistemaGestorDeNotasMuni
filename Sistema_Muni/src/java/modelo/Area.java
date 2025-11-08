package modelo;

public class Area {
    private int idArea;
    private String nombre; 
    private int usuarioArea;

    public Area() {}

    public Area(int idArea, String nombre, int usuarioArea) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.usuarioArea = usuarioArea;
    }

    // Getters y Setters 
    
    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUsuarioArea() {
        return usuarioArea;
    }

    public void setUsuarioArea(int usuarioArea) {
        this.usuarioArea = usuarioArea;
    }
}