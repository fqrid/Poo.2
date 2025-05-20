package controlador;

import modelo.DetallePedidos;
import repositorio.DetallePedidosRepositorio;

import java.util.List;

public class DetallePedidosControlador {
    private DetallePedidosRepositorio repositorio;

    public DetallePedidosControlador() {
        this.repositorio = new DetallePedidosRepositorio();
    }

    // Crear un nuevo detalle de pedido
    public void crearDetalle(int idPedido, String producto, int cantidad, double precioUnitario) {
        DetallePedidos detalle = new DetallePedidos(0, idPedido, producto, cantidad, precioUnitario);
        repositorio.guardar(detalle);
        System.out.println("✅ Detalle de pedido creado exitosamente.");
    }

    // Obtener detalle por ID
    public void obtenerDetallePorId(int id) {
        DetallePedidos detalle = repositorio.buscarPorId(id);
        if (detalle != null) {
            mostrarDetalle(detalle);
        } else {
            System.out.println("❌ Detalle no encontrado con ID: " + id);
        }
    }

    // Listar todos los detalles
    public void listarDetalles() {
        List<DetallePedidos> lista = repositorio.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("❌ No hay detalles de pedidos registrados.");
        } else {
            for (DetallePedidos d : lista) {
                mostrarDetalle(d);
            }
        }
    }

    // Actualizar un detalle existente
    public void actualizarDetalle(int id, int idPedido, String producto, int cantidad, double precioUnitario) {
        DetallePedidos detalle = new DetallePedidos(id, idPedido, producto, cantidad, precioUnitario);
        repositorio.actualizar(detalle);
        System.out.println("🔄 Detalle de pedido actualizado correctamente.");
    }

    // Eliminar un detalle por ID
    public void eliminarDetalle(int id) {
        repositorio.eliminar(id);
        System.out.println("🗑️ Detalle de pedido eliminado correctamente.");
    }

    // Método auxiliar para mostrar detalles
    private void mostrarDetalle(DetallePedidos d) {
        System.out.println("📦 Detalle ID: " + d.getId());
        System.out.println("   Pedido ID: " + d.getIdPedido());
        System.out.println("   Producto: " + d.getProducto());
        System.out.println("   Cantidad: " + d.getCantidad());
        System.out.println("   Precio Unitario: $" + d.getPrecioUnitario());
        System.out.println("   Subtotal: $" + d.getSubtotal());
        System.out.println("-----------------------------");
    }
}
