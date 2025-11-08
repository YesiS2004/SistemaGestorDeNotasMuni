package dao;

import modelo.Area;
import java.sql.*;
import java.util.*;
import utils.Conexion;

public class AreaDAO {

    // LISTAR ÁREAS
    public List<Area> listar() {
        List<Area> lista = new ArrayList<>();
        String sql = "SELECT ID_Area AS idArea, Nombre AS nombre, Usuario_Area AS usuarioArea FROM Area";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Area area = new Area();
                area.setIdArea(rs.getInt("idArea"));
                area.setNombre(rs.getString("nombre"));
                area.setUsuarioArea(rs.getInt("usuarioArea"));
                lista.add(area);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al listar áreas: " + e.getMessage());
        }

        return lista;
    }

    // AGREGAR ÁREA
    public boolean agregar(Area area) {
        String sql = "INSERT INTO Area (Nombre, Usuario_Area) VALUES (?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, area.getNombre());
            ps.setInt(2, area.getUsuarioArea());
            ps.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al agregar área: " + e.getMessage());
            return false;
        }
    }

    // ACTUALIZAR ÁREA
    public boolean actualizar(Area area) {
        String sql = "UPDATE Area SET Nombre = ?, Usuario_Area = ? WHERE ID_Area = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, area.getNombre());
            ps.setInt(2, area.getUsuarioArea());
            ps.setInt(3, area.getIdArea());
            ps.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al actualizar área: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR ÁREA
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Area WHERE ID_Area = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al eliminar área: " + e.getMessage());
            return false;
        }
    }

    // BUSCAR ÁREA POR ID
    public Area buscarPorId(int id) {
        Area area = null;
        String sql = "SELECT ID_Area AS idArea, Nombre AS nombre, Usuario_Area AS usuarioArea FROM Area WHERE ID_Area = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    area = new Area();
                    area.setIdArea(rs.getInt("idArea"));
                    area.setNombre(rs.getString("nombre"));
                    area.setUsuarioArea(rs.getInt("usuarioArea"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al buscar área: " + e.getMessage());
        }

        return area;
    }
}
