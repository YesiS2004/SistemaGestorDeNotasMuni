package dao;

import modelo.Nota;
import java.sql.*;
import java.util.*;
import utils.Conexion;

public class NotaDAO {

    public List<Nota> listar() {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM Nota";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setID_Nota(rs.getInt("ID_Nota"));
                nota.setID_Persona(rs.getInt("ID_Persona"));
                nota.setFecha_Entrega(rs.getDate("Fecha_Entrega"));
                nota.setDetalles(rs.getString("Detalles"));
                nota.setEstado_Actual(rs.getInt("Estado_Actual"));
                nota.setNota(rs.getBytes("Nota"));
                lista.add(nota);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar notas: " + e.getMessage());
        }

        return lista;
    }

    public Nota buscarPorId(int id) {
        Nota nota = null;
        String sql = "SELECT * FROM Nota WHERE ID_Nota = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nota = new Nota();
                nota.setID_Nota(rs.getInt("ID_Nota"));
                nota.setID_Persona(rs.getInt("ID_Persona"));
                nota.setFecha_Entrega(rs.getDate("Fecha_Entrega"));
                nota.setDetalles(rs.getString("Detalles"));
                nota.setEstado_Actual(rs.getInt("Estado_Actual"));
                nota.setNota(rs.getBytes("Nota"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar nota: " + e.getMessage());
        }

        return nota;
    }

    public boolean agregar(Nota nota) {
        String sql = "INSERT INTO Nota (ID_Persona, Fecha_Entrega, Detalles, Estado_Actual, Nota) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nota.getID_Persona());
            ps.setDate(2, nota.getFecha_Entrega());
            ps.setString(3, nota.getDetalles());
            ps.setInt(4, nota.getEstado_Actual());
            ps.setBytes(5, nota.getNota());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al agregar nota: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Nota nota) {
        String sql = "UPDATE Nota SET ID_Persona = ?, Fecha_Entrega = ?, Detalles = ?, Estado_Actual = ?, Nota = ? WHERE ID_Nota = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nota.getID_Persona());
            ps.setDate(2, nota.getFecha_Entrega());
            ps.setString(3, nota.getDetalles());
            ps.setInt(4, nota.getEstado_Actual());
            ps.setBytes(5, nota.getNota());
            ps.setInt(6, nota.getID_Nota());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar nota: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Nota WHERE ID_Nota = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar nota: " + e.getMessage());
            return false;
        }
    }
}
