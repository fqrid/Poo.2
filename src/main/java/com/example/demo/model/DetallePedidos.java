package com.example.demo.model;

public class DetallePedido {

    private Long id;
    private Long pedidoId;
    private Long productoId;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private double descuento;
    private String observaciones;

    public DetallePedido() {}

    public DetallePedido(Long id, Long pedidoId, Long productoId, int cantidad, double precioUnitario, double descuento, String observaciones) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.observaciones = observaciones;
        this.subtotal = calcularSubtotal();
    }

    private double calcularSubtotal() {
        return (precioUnitario * cantidad) - descuento;
    }// podemos actualizar automaticamente el valor del subtotal

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
        this.subtotal = calcularSubtotal();
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
// Nota: tanto como pedidoId y productoId son llaves foraneas.