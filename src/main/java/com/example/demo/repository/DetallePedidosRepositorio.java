package repositorio;

import modelo.DetallePedidos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidosRepositorio {
    private final String url = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private final String usuario = "root";
    private final String contraseña = "tu_contraseña";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, contraseña);
    }

    // Crear (INSERT)
    public void guardar(DetallePedidos detalle) {
        String sql = "INSERT INTO detalle_pedidos (idPedido, producto, cantidad, precioUnitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdPedido());
            stmt.setString(2, detalle.getProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setDouble(4, detalle.getPrecioUnitario());
            stmt.setDouble(5, detalle.getSubtotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer (SELECT por ID)
    public DetallePedidos buscarPorId(int id) {
        String sql = "SELECT * FROM detalle_pedidos WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DetallePedidos(
                    rs.getInt("id"),
                    rs.getInt("idPedido"),
                    rs.getString("producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precioUnitario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leer todos
    public List<DetallePedidos> obtenerTodos() {
        List<DetallePedidos> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_pedidos";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DetallePedidos detalle = new DetallePedidos(
                    rs.getInt("id"),
                    rs.getInt("idPedido"),
                    rs.getString("producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precioUnitario")
                );
                lista.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizar (UPDATE)
    public void actualizar(DetallePedidos detalle) {
        String sql = "UPDATE detalle_pedidos SET idPedido = ?, producto = ?, cantidad = ?, precioUnitario = ?, subtotal = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detalle.getIdPedido());
            stmt.setString(2, detalle.getProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setDouble(4, detalle.getPrecioUnitario());
            stmt.setDouble(5, detalle.getSubtotal());
            stmt.setInt(6, detalle.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar (DELETE)
    public void eliminar(int id) {
        String sql = "DELETE FROM detalle_pedidos WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
