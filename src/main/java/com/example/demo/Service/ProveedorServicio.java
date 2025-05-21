package servicio;

import modelo.Proveedor;
import repositorio.ProveedorRepositorio;

import java.util.List;

public class ProveedorServicio {
    private ProveedorRepositorio repositorio = new ProveedorRepositorio();

    public void registrarProveedor(Proveedor proveedor) {
        repositorio.guardar(proveedor);
    }

    public List<Proveedor> listarProveedores() {
        return repositorio.obtenerTodos();
    }

    public Proveedor buscarProveedorPorId(int id) {
        return repositorio.obtenerPorId(id);
    }

    public void actualizarProveedor(Proveedor proveedor) {
        repositorio.actualizar(proveedor);
    }

    public void eliminarProveedor(int id) {
        repositorio.eliminar(id);
    }
}
