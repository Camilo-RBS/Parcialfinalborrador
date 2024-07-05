package controllers;

import models.Tarjeta;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarjetaController {

    // Método para obtener las tarjetas de un cliente por su ID
    public List<Tarjeta> obtenerTarjetasPorCliente(int clienteId) {
        List<Tarjeta> tarjetas = new ArrayList<>();
        String sql = "SELECT * FROM Tarjeta WHERE cliente_id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clienteId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setId(rs.getInt("id"));
                tarjeta.setNumero(rs.getString("numero"));
                tarjeta.setFechaExpiracion(rs.getString("fecha_expiracion"));
                tarjeta.setFacilitador(rs.getString("facilitador"));
                tarjeta.setTipo(rs.getString("tipo"));
                tarjeta.setClienteId(clienteId);
                tarjetas.add(tarjeta);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tarjetas;
    }

    // Método para crear una nueva tarjeta
    public boolean crearTarjeta(Tarjeta tarjeta) {
        String sql = "INSERT INTO Tarjeta (numero, fecha_expiracion, facilitador, tipo, cliente_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tarjeta.getNumero());
            pstmt.setString(2, tarjeta.getFechaExpiracion());
            pstmt.setString(3, tarjeta.getFacilitador());
            pstmt.setString(4, tarjeta.getTipo());
            pstmt.setInt(5, tarjeta.getClienteId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método para actualizar una tarjeta existente
    public boolean actualizarTarjeta(Tarjeta tarjeta) {
        String sql = "UPDATE Tarjeta SET numero = ?, fecha_expiracion = ?, facilitador = ?, tipo = ? WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tarjeta.getNumero());
            pstmt.setString(2, tarjeta.getFechaExpiracion());
            pstmt.setString(3, tarjeta.getFacilitador());
            pstmt.setString(4, tarjeta.getTipo());
            pstmt.setInt(5, tarjeta.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método para eliminar una tarjeta por su ID
    public boolean eliminarTarjeta(int tarjetaId) {
        String sql = "DELETE FROM Tarjeta WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, tarjetaId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
