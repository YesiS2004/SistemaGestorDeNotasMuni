package dao;

import modelo.Area;
import java.sql.*;
import java.util.*;

public class AreaDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/todocolor";
    private String jdbcUser = "root";
    private String jdbcPass = "";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
    }

    public List<Area> listar() {
        List<Area> lista = new ArrayList<>();
        String sql = "SELECT * FROM areas";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Area(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Area obtenerPorId(int id) {
        Area area = null;
        String sql = "SELECT * FROM areas WHERE id=?";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                area = new Area(rs.getInt("id"), rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return area;
    }

    public void actualizar(Area area) {
        String sql = "UPDATE areas SET nombre=? WHERE id=?";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, area.getNombre());
            ps.setInt(2, area.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
