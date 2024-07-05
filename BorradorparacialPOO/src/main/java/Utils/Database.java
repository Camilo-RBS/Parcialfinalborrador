package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection conn;

    // Método para conectar a la base de datos
    public static Connection connect() {
        if (conn == null) {
            try {
                // Cambia esto según tu base de datos y credenciales
                String url = "jdbc:mysql://localhost:3306/tu_base_de_datos";
                String user = "tu_usuario";
                String password = "tu_contraseña";

                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión a la base de datos establecida.");
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        }
        return conn;
    }

    // Método para desconectar de la base de datos
    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión a la base de datos cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
        }
    }
}
