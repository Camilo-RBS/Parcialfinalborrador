package models;

public class Tarjeta {
    private int id;
    private String numero;
    private String fechaExpiracion;
    private String facilitador;
    private String tipo;
    private int clienteId;

    // Constructor, getters y setters


    public Tarjeta(int id, String numero, String fechaExpiracion, String facilitador, String tipo, int clienteId) {
        this.id = id;
        this.numero = numero;
        this.fechaExpiracion = fechaExpiracion;
        this.facilitador = facilitador;
        this.tipo = tipo;
        this.clienteId = clienteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
}
