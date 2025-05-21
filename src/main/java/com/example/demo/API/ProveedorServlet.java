package com.example.demo.api;

import modelo.Proveedor;
import servicio.ProveedorServicio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/proveedores")
public class ProveedorServlet extends HttpServlet {
    private final ProveedorServicio servicio = new ProveedorServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Proveedor proveedor = servicio.buscarProveedorPorId(id);
            if (proveedor != null) {
                out.println("{ \"id\": " + proveedor.getId()
                        + ", \"nombre\": \"" + proveedor.getNombre()
                        + "\", \"telefono\": \"" + proveedor.getTelefono()
                        + "\", \"correo\": \"" + proveedor.getCorreo() + "\" }");
            } else {
                out.println("{ \"mensaje\": \"Proveedor no encontrado\" }");
            }
        } else {
            List<Proveedor> lista = servicio.listarProveedores();
            out.println("[");
            for (int i = 0; i < lista.size(); i++) {
                Proveedor p = lista.get(i);
                out.println("  { \"id\": " + p.getId()
                        + ", \"nombre\": \"" + p.getNombre()
                        + "\", \"telefono\": \"" + p.getTelefono()
                        + "\", \"correo\": \"" + p.getCorreo() + "\" }"
                        + (i < lista.size() - 1 ? "," : ""));
            }
            out.println("]");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Proveedor nuevo = new Proveedor();
        nuevo.setNombre(request.getParameter("nombre"));
        nuevo.setTelefono(request.getParameter("telefono"));
        nuevo.setCorreo(request.getParameter("correo"));

        servicio.registrarProveedor(nuevo);
        response.getWriter().println("Proveedor registrado correctamente.");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Proveedor actualizado = new Proveedor();
        actualizado.setId(id);
        actualizado.setNombre(request.getParameter("nombre"));
        actualizado.setTelefono(request.getParameter("telefono"));
        actualizado.setCorreo(request.getParameter("correo"));

        servicio.actualizarProveedor(actualizado);
        response.getWriter().println("Proveedor actualizado correctamente.");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        servicio.eliminarProveedor(id);
        response.getWriter().println("Proveedor eliminado correctamente.");
    }
}
