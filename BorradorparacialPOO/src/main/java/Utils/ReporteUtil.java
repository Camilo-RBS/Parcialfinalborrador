package utils;

import controllers.TransaccionController;
import controllers.TarjetaController;
import models.Transaccion;
import models.Tarjeta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReporteUtil {

    public static void generarReporteA(int clienteId, String fechaInicio, String fechaFin) {
        TransaccionController transaccionController = new TransaccionController();
        List<Transaccion> transacciones = transaccionController.obtenerTransaccionesPorCliente(clienteId, fechaInicio, fechaFin);

        try (FileWriter writer = new FileWriter("Reportes/ReporteA_" + System.currentTimeMillis() + ".txt")) {
            for (Transaccion transaccion : transacciones) {
                writer.write("ID: " + transaccion.getId() + ", Fecha: " + transaccion.getFecha() + ", Monto: " + transaccion.getMonto() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Métodos para generar los otros reportes B, C y D
}
