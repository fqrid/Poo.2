package com.example.demo.repository;

import modelo.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoRepositorio {
    private final List<Empleado> listaEmpleados = new ArrayList<>();

    // Crear
    public void agregarEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
    }

    // Leer todos
    public List<Empleado> obtenerTodos() {
        return listaEmpleados;
    }

    // Leer por ID
    public Empleado buscarPorId(int id) {
        Optional<Empleado> empleado = listaEmpleados.stream()
            .filter(e -> e.getId() == id)
            .findFirst();
        return empleado.orElse(null);
    }

    // Actualizar
    public boolean actualizarEmpleado(Empleado empleadoActualizado) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleado e = listaEmpleados.get(i);
            if (e.getId() == empleadoActualizado.getId()) {
                listaEmpleados.set(i, empleadoActualizado);
                return true;
            }
        }
        return false;
    }

    // Eliminar
    public boolean eliminarEmpleado(int id) {
        return listaEmpleados.removeIf(e -> e.getId() == id);
    }
}
