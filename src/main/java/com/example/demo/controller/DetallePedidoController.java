package com.example.demo.controller;

import com.example.demo.model.DetallePedido;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetallePedidoController {
    private List<DetallePedido> lista = new ArrayList<>();
    private long idCounter = 1;

    // Obtener todos los detalles
    public List<DetallePedido> obtenerTodos() {
        return lista;
    }

    // Obtener un detalle por ID
    public DetallePedido obtenerPorId(Long id) {
        Optional<DetallePedido> resultado = lista.stream()
            .filter(dp -> dp.getId().equals(id))
            .findFirst();
        return resultado.orElse(null);
    }

    // Crear un nuevo detalle
    public DetallePedido crear(DetallePedido nuevoDetalle) {
        nuevoDetalle.setId(idCounter++);
        lista.add(nuevoDetalle);
        return nuevoDetalle;
    }

    // Actualizar un detalle existente
    public DetallePedido actualizar(Long id, DetallePedido actualizado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(id)) {
                actualizado.setId(id);
                lista.set(i, actualizado);
                return actualizado;
            }
        }
        return null;
    }

    // Eliminar un detalle por ID
    public void eliminar(Long id) {
        lista.removeIf(dp -> dp.getId().equals(id));
    }
}
