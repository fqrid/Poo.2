package com.example.demo.Service;

import modelo.DetallePedidos;
import repositorio.DetallePedidosRepositorio;

import java.util.List;

public class DetallePedidosServicio {
    private DetallePedidosRepositorio repositorio;

    public DetallePedidosServicio() {
        this.repositorio = new DetallePedidosRepositorio();
    }

    // Crear un nuevo detalle con lógica de negocio si aplica
    public void crearDetalle(int idPedido, String producto, int cantidad, double precioUnitario) {
        if (cantidad <= 0 || precioUnitario <= 0) {
            throw new IllegalArgumentException("❌ La cantidad y el precio deben ser mayores que cero.");
        }

        DetallePedidos detalle = new DetallePedidos(0, idPedido, producto, cantidad, precioUnitario);
        repositorio.guardar(detalle);
    }

    // Obtener detalle por ID
    public DetallePedidos obtenerPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    // Obtener todos los detalles
    public List<DetallePedidos> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    // Actualizar un detalle
    public void actualizarDetalle(int id, int idPedido, String producto, int cantidad, double precioUnitario) {
        DetallePedidos detalle = new DetallePedidos(id, idPedido, producto, cantidad, precioUnitario);
        repositorio.actualizar(detalle);
    }

    // Eliminar un detalle por ID
    public void eliminarDetalle(int id) {
        repositorio.eliminar(id);
    }
}
