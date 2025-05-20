package servicio;

import modelo.Empleado;
import repositorio.EmpleadoRepositorio;

import java.util.List;

public class EmpleadoServicio {
    private final EmpleadoRepositorio repositorio;

    public EmpleadoServicio() {
        this.repositorio = new EmpleadoRepositorio();
    }

    // Crear
    public void registrarEmpleado(Empleado empleado) {
        repositorio.agregarEmpleado(empleado);
    }

    // Leer todos
    public List<Empleado> listarEmpleados() {
        return repositorio.obtenerTodos();
    }

    // Leer uno
    public Empleado obtenerEmpleadoPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    // Actualizar
    public boolean modificarEmpleado(Empleado empleado) {
        return repositorio.actualizarEmpleado(empleado);
    }

    // Eliminar
    public boolean eliminarEmpleado(int id) {
        return repositorio.eliminarEmpleado(id);
    }
}
