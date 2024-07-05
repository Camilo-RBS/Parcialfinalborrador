package controllers;

import models.Cliente;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    // Método para obtener la lista de todos los clientes
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clientes;
    }

    // Método para crear un nuevo cliente
    public boolean crearCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombre, direccion, telefono) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método para actualizar un cliente existente
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, direccion = ?, telefono = ? WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setInt(4, cliente.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método para eliminar un cliente por su ID
    public boolean eliminarCliente(int clienteId) {
        String sql = "DELETE FROM Cliente WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clienteId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
