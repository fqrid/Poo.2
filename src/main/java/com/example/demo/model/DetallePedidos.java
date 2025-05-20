package modelo;

public class DetallePedidos {
    private int id;
    private int idPedido;
    private String producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetallePedidos() {
    }

    public DetallePedidos(int id, int idPedido, String producto, int cantidad, double precioUnitario) {
        this.id = id;
        this.idPedido = idPedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        actualizarSubtotal();
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        actualizarSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    private void actualizarSubtotal() {
        this.subtotal = this.cantidad * this.precioUnitario;
    }
}
