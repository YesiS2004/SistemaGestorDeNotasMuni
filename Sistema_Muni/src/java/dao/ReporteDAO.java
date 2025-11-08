package dao;

import java.sql.*;
import java.util.*;
import utils.Conexion;

public class ReporteDAO {

    public ReporteDAO() {
    }

    // Reporte 1: Total de Notas 
    public int totalNotas() {
        String sql = "SELECT COUNT(*) FROM nota";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR ReporteDAO.totalNotas: " + e.getMessage());
        }
        return 0;
    }

    // Reporte 2: Total de Derivaciones
    public int totalDerivaciones() {
        String sql = "SELECT COUNT(*) FROM derivacion";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR ReporteDAO.totalDerivaciones: " + e.getMessage());
        }
        return 0;
    }
    
    // Reporte 3: Total de Notas Recibidas 
    public int totalNotasRecibidas() {
        String sql = "SELECT COUNT(*) FROM recepcion";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR ReporteDAO.totalNotasRecibidas: " + e.getMessage());
        }
        return 0;
    }

    // Reporte 4: Conteo mensual de Notas entregadas 
    public List<Map<String, Object>> notasEntregadasMensual() {
        String sql = "SELECT MONTH(Fecha_Entrega) AS Mes, COUNT(ID_Nota) AS Cantidad " +
                     "FROM nota " +
                     "WHERE YEAR(Fecha_Entrega) = YEAR(CURDATE()) " +
                     "GROUP BY Mes " +
                     "ORDER BY Mes ASC";
        
        List<Map<String, Object>> lista = new ArrayList<>();

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("mes", rs.getInt("Mes"));
                fila.put("cantidad", rs.getInt("Cantidad"));
                lista.add(fila);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR ReporteDAO.notasEntregadasMensual: " + e.getMessage());
        }
        return lista;
    }

    // Reporte 5: Notas por estado Gráfico de Torta
    public List<Map<String, Object>> notasPorEstado() {
        String sql = "SELECT e.Estado, COUNT(n.ID_Nota) AS cantidad " +
                     "FROM nota n JOIN estado e ON n.Estado_Actual = e.ID_Estado " +
                     "GROUP BY e.Estado ORDER BY cantidad DESC";
        
        List<Map<String, Object>> lista = new ArrayList<>();

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("estado", rs.getString("Estado"));
                fila.put("cantidad", rs.getInt("cantidad"));
                lista.add(fila);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR ReporteDAO.notasPorEstado: " + e.getMessage());
        }
        return lista;
    }

    // Reporte 6: Notas por área de derivación  Tabla JSTL
    public List<Map<String, Object>> notasPorArea() {
        String sql = "SELECT a.Nombre AS area, COUNT(d.ID_Nota) AS cantidad " +
                     "FROM derivacion d JOIN area a ON d.ID_Area = a.ID_Area " +
                     "GROUP BY a.Nombre ORDER BY cantidad DESC";
        
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("area", rs.getString("area")); 
                fila.put("cantidad", rs.getInt("cantidad")); 
                lista.add(fila);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR ReporteDAO.notasPorArea: " + e.getMessage());
        }
        return lista;
    }

    // Reporte 7: Notas por secretaría Tabla JSTL
    public List<Map<String, Object>> notasPorSecretaria() {
        String sql = "SELECT d.ID_Secretaria AS secretaria, COUNT(d.ID) AS cantidad " +
                     "FROM derivacion d GROUP BY d.ID_Secretaria ORDER BY cantidad DESC";
        
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("secretaria", rs.getInt("secretaria"));
                fila.put("cantidad", rs.getInt("cantidad"));
                lista.add(fila);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR ReporteDAO.notasPorSecretaria: " + e.getMessage());
        }
        return lista;
    }
}