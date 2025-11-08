package dao;

import modelo.Nota;
import java.sql.*;
import java.util.*;
import utils.Conexion;

public class NotaDAO {

    public List<Nota> listar() {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT ID_Nota, ID_Persona, Fecha_Entrega, Detalles, Estado_Actual, (CASE WHEN Nota IS NOT NULL THEN 1 ELSE 0 END) AS TieneArchivo FROM nota";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Nota n = new Nota();
                n.setIdNota(rs.getInt("ID_Nota"));
                n.setIdPersona(rs.getInt("ID_Persona"));
                n.setFechaEntrega(rs.getDate("Fecha_Entrega"));
                n.setDetalles(rs.getString("Detalles"));
                n.setEstadoActual(rs.getInt("Estado_Actual"));
                
                // Si TieneArchivo es 1, creamos un arreglo dummy de 1 byte para indicar que existe.
                if (rs.getInt("TieneArchivo") == 1) {
                    n.setArchivoNota(new byte[1]); 
                } else {
                    n.setArchivoNota(null);
                }
                
                lista.add(n);
            }

        } catch (Exception e) {
            System.out.println("Error al listar notas: " + e.getMessage());
        }

        return lista;
    }

    public boolean agregar(Nota n) {
        String sql = "INSERT INTO nota (ID_Persona, Fecha_Entrega, Detalles, Estado_Actual, Nota) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, n.getIdPersona());
            ps.setDate(2, n.getFechaEntrega());
            ps.setString(3, n.getDetalles());
            ps.setInt(4, n.getEstadoActual());
            ps.setBytes(5, n.getArchivoNota());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar nota: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Nota n) {
        String sql = "UPDATE nota SET ID_Persona = ?, Fecha_Entrega = ?, Detalles = ?, Estado_Actual = ?, Nota = ? WHERE ID_Nota = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, n.getIdPersona());
            ps.setDate(2, n.getFechaEntrega());
            ps.setString(3, n.getDetalles());
            ps.setInt(4, n.getEstadoActual());
            ps.setBytes(5, n.getArchivoNota());
            ps.setInt(6, n.getIdNota());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al actualizar nota: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM nota WHERE ID_Nota = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar nota: " + e.getMessage());
            return false;
        }
    }

    public Nota buscarPorId(int id) {
        Nota n = null;
        String sql = "SELECT * FROM nota WHERE ID_Nota = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                n = new Nota();
                n.setIdNota(rs.getInt("ID_Nota"));
                n.setIdPersona(rs.getInt("ID_Persona"));
                n.setFechaEntrega(rs.getDate("Fecha_Entrega"));
                n.setDetalles(rs.getString("Detalles"));
                n.setEstadoActual(rs.getInt("Estado_Actual"));
                n.setArchivoNota(rs.getBytes("Nota")); // Se carga el BLOB aquí
            }

        } catch (Exception e) {
            System.out.println("Error al buscar nota: " + e.getMessage());
        }

        return n;
    }
}