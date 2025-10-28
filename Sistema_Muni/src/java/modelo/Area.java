package modelo;

public class Area {
    private int ID_Area;
    private String Nombre;
    private int Usuario_Area;

    public Area() {}

    public Area(int ID_Area, String Nombre, int Usuario_Area) {
        this.ID_Area = ID_Area;
        this.Nombre = Nombre;
        this.Usuario_Area = Usuario_Area;
    }

    public int getID_Area() {
        return ID_Area;
    }

    public void setID_Area(int ID_Area) {
        this.ID_Area = ID_Area;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getUsuario_Area() {
        return Usuario_Area;
    }

    public void setUsuario_Area(int Usuario_Area) {
        this.Usuario_Area = Usuario_Area;
    }
}
