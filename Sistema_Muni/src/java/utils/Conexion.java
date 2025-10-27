package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection conexion;

    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        if (conexion == null || conexion.isClosed()) {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_db", "root", "");
        }
        return conexion;
    }
}
