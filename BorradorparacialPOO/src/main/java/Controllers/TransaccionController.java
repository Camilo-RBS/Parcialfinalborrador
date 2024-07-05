package controllers;

import models.Transaccion;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionController {

    // Método para obtener transacciones por cliente en un rango de fechas
    public List<Transaccion> obtenerTransaccionesPorCliente(int clienteId, String fechaInicio, String fechaFin) {
        List<Transaccion> transacciones = new ArrayList<>();
        String sql = "SELECT t.* FROM Transaccion t "
                + "JOIN Tarjeta tar ON t.tarjeta_id = tar.id "
                + "WHERE tar.cliente_id = ? AND t.fecha BETWEEN ? AND ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clienteId);
            pstmt.setString(2, fechaInicio);
            pstmt.setString(3, fechaFin);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setId(rs.getInt("id"));
                transaccion.setFecha(rs.getString("fecha"));
                transaccion.setMonto(rs.getDouble("monto"));
                transaccion.setDescripcion(rs.getString("descripcion"));
                transaccion.setTarjetaId(rs.getInt("tarjeta_id"));
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transacciones;
    }

    // Método para crear una nueva transacción
    public boolean crearTransaccion(Transaccion transaccion) {
        String sql = "INSERT INTO Transaccion (fecha, monto, descripcion, tarjeta_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, transaccion.getFecha());
            pstmt.setDouble(2, transaccion.getMonto());
            pstmt.setString(3, transaccion.getDescripcion());
            pstmt.setInt(4, transaccion.getTarjetaId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método para actualizar una transacción existente
    public boolean actualizarTransaccion(Transaccion transaccion) {
        String sql = "UPDATE Transaccion SET fecha = ?, monto = ?, descripcion = ?, tarjeta_id = ? WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, transaccion.getFecha());
            pstmt.setDouble(2, transaccion.getMonto());
            pstmt.setString(3, transaccion.getDescripcion());
            pstmt.setInt(4, transaccion.getTarjetaId());
            pstmt.setInt(5, transaccion.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método para eliminar una transacción por su ID
    public boolean eliminarTransaccion(int transaccionId) {
        String sql = "DELETE FROM Transaccion WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transaccionId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
