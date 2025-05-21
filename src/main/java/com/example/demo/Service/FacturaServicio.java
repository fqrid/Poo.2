package servicio;

import modelo.Factura;
import repositorio.FacturaRepositorio;

import java.util.List;

public class FacturaServicio {
    private FacturaRepositorio repositorio = new FacturaRepositorio();

    public void registrarFactura(Factura factura) {
        repositorio.guardar(factura);
    }

    public List<Factura> listarFacturas() {
        return repositorio.obtenerTodas();
    }

    public Factura buscarFacturaPorId(int id) {
        return repositorio.obtenerPorId(id);
    }

    public void actualizarFactura(Factura factura) {
        repositorio.actualizar(factura);
    }

    public void eliminarFactura(int id) {
        repositorio.eliminar(id);
    }
}

