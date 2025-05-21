package com.example.demo.API;

import modelo.DetallePedidos;
import servicio.DetallePedidosServicio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/api/detallepedidos")
public class DetallePedidosServlet extends HttpServlet {

    private DetallePedidosServicio servicio;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        servicio = new DetallePedidosServicio();
    }

    // Obtener todos los detalles (GET)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<DetallePedidos> lista = servicio.obtenerTodos();
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(lista));
    }

    // Crear un nuevo detalle (POST)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        DetallePedidos detalle = gson.fromJson(reader, DetallePedidos.class);
        servicio.crearDetalle(detalle.getIdPedido(), detalle.getProducto(), detalle.getCantidad(), detalle.getPrecioUnitario());
        resp.getWriter().write("{\"mensaje\": \"Detalle creado correctamente\"}");
    }

    // Actualizar un detalle (PUT)
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        DetallePedidos detalle = gson.fromJson(reader, DetallePedidos.class);
        servicio.actualizarDetalle(detalle.getId(), detalle.getIdPedido(), detalle.getProducto(), detalle.getCantidad(), detalle.getPrecioUnitario());
        resp.getWriter().write("{\"mensaje\": \"Detalle actualizado correctamente\"}");
    }

    // Eliminar un detalle (DELETE)
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        servicio.eliminarDetalle(id);
        resp.getWriter().write("{\"mensaje\": \"Detalle eliminado correctamente\"}");
    }
}
