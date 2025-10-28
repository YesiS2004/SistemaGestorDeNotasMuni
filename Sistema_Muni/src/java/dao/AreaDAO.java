package dao;

import modelo.Area;
import java.sql.*;
import java.util.*;
import utils.Conexion;

public class AreaDAO {

    public List<Area> listar() {
        List<Area> lista = new ArrayList<>();
        String sql = "SELECT * FROM Area";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Area area = new Area();
                area.setID_Area(rs.getInt("ID_Area"));
                area.setNombre(rs.getString("Nombre"));
                area.setUsuario_Area(rs.getInt("Usuario_Area"));
                lista.add(area);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar áreas: " + e.getMessage());
        }
        
        return lista;
    }

    public boolean agregar(Area area) {
        String sql = "INSERT INTO Area (Nombre, Usuario_Area) VALUES (?, ?)";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, area.getNombre());
            ps.setInt(2, area.getUsuario_Area());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error al agregar área: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Area area) {
        String sql = "UPDATE Area SET Nombre = ?, Usuario_Area = ? WHERE ID_Area = ?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, area.getNombre());
            ps.setInt(2, area.getUsuario_Area());
            ps.setInt(3, area.getID_Area());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar área: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Area WHERE ID_Area = ?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar área: " + e.getMessage());
            return false;
        }
    }

    public Area buscarPorId(int id) {
        Area area = null;
        String sql = "SELECT * FROM Area WHERE ID_Area = ?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                area = new Area();
                area.setID_Area(rs.getInt("ID_Area"));
                area.setNombre(rs.getString("Nombre"));
                area.setUsuario_Area(rs.getInt("Usuario_Area"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar área: " + e.getMessage());
        }
        
        return area;
    }
}
