package com.example.demo;

import com.example.demo.controller.DetallePedidoController;
import com.example.demo.model.DetallePedido;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DetallePedidoController controller = new DetallePedidoController();

        // Crear detalles
        DetallePedido detalle1 = new DetallePedido();
        detalle1.setDescripcion("Primer detalle");
        detalle1.setCantidad(2);
        controller.crear(detalle1);

        DetallePedido detalle2 = new DetallePedido();
        detalle2.setDescripcion("Segundo detalle");
        detalle2.setCantidad(5);
        controller.crear(detalle2);

        // Mostrar todos los detalles
        System.out.println("Lista de detalles:");
        List<DetallePedido> todos = controller.obtenerTodos();
        for (DetallePedido d : todos) {
            System.out.println(d.getId() + " - " + d.getDescripcion() + " - Cantidad: " + d.getCantidad());
        }

        // Obtener uno por ID
        System.out.println("\nDetalle con ID 1:");
        DetallePedido obtenido = controller.obtenerPorId(1L);
        if (obtenido != null) {
            System.out.println(obtenido.getDescripcion());
        }

        // Actualizar
        DetallePedido actualizado = new DetallePedido();
        actualizado.setDescripcion("Detalle actualizado");
        actualizado.setCantidad(10);
        controller.actualizar(1L, actualizado);

        // Mostrar después de actualizar
        System.out.println("\nDespués de actualizar el ID 1:");
        DetallePedido actualizado2 = controller.obtenerPorId(1L);
        System.out.println(actualizado2.getDescripcion() + " - Cantidad: " + actualizado2.getCantidad());

        // Eliminar
        controller.eliminar(2L);
        System.out.println("\nLista después de eliminar ID 2:");
        for (DetallePedido d : controller.obtenerTodos()) {
            System.out.println(d.getId() + " - " + d.getDescripcion());
        }
    }
}
