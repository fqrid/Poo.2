package repositorio;

import modelo.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepositorio {
    private final String url = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private final String usuario = "tu_usuario";
    private final String clave = "tu_contraseña";

    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(url, usuario, clave);
    }

    public void guardar(Factura factura) {
        String sql = "INSERT INTO facturas (descripcion, monto, fecha) VALUES (?, ?, ?)";
        try (Connection conn = obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, factura.getDescripcion());
            stmt.setDouble(2, factura.getMonto());
            stmt.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Factura> obtenerTodas() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas";

        try (Connection conn = obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Factura f = new Factura();
                f.setId(rs.getInt("id"));
                f.setDescripcion(rs.getString("descripcion"));
                f.setMonto(rs.getDouble("monto"));
                f.setFecha(rs.getDate("fecha"));
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Factura obtenerPorId(int id) {
        String sql = "SELECT * FROM facturas WHERE id = ?";
        Factura factura = null;

        try (Connection conn = obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                factura = new Factura();
                factura.setId(rs.getInt("id"));
                factura.setDescripcion(rs.getString("descripcion"));
                factura.setMonto(rs.getDouble("monto"));
                factura.setFecha(rs.getDate("fecha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return factura;
    }

    public void actualizar(Factura factura) {
        String sql = "UPDATE facturas SET descripcion = ?, monto = ?, fecha = ? WHERE id = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, factura.getDescripcion());
            stmt.setDouble(2, factura.getMonto());
            stmt.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            stmt.setInt(4, factura.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM facturas WHERE id = ?";
        try (Connection conn = obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
