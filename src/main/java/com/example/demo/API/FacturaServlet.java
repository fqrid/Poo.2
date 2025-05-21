package com.ejemplo.example.demo.api;

import modelo.Factura;
import servicio.FacturaServicio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/api/facturas")
public class FacturaServlet extends HttpServlet {
    private final FacturaServicio servicio = new FacturaServicio();

    private Date parseFecha(String fechaStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
        } catch (Exception e) {
            return new Date(); // Fecha actual si falla
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Factura factura = servicio.buscarFacturaPorId(id);
            if (factura != null) {
                out.println("{ \"id\": " + factura.getId()
                        + ", \"descripcion\": \"" + factura.getDescripcion()
                        + "\", \"monto\": " + factura.getMonto()
                        + ", \"fecha\": \"" + factura.getFecha() + "\" }");
            } else {
                out.println("{ \"mensaje\": \"Factura no encontrada\" }");
            }
        } else {
            List<Factura> lista = servicio.listarFacturas();
            out.println("[");
            for (int i = 0; i < lista.size(); i++) {
                Factura f = lista.get(i);
                out.println("  { \"id\": " + f.getId()
                        + ", \"descripcion\": \"" + f.getDescripcion()
                        + "\", \"monto\": " + f.getMonto()
                        + ", \"fecha\": \"" + f.getFecha() + "\" }"
                        + (i < lista.size() - 1 ? "," : ""));
            }
            out.println("]");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factura nueva = new Factura();
        nueva.setDescripcion(request.getParameter("descripcion"));
        nueva.setMonto(Double.parseDouble(request.getParameter("monto")));
        nueva.setFecha(parseFecha(request.getParameter("fecha")));

        servicio.registrarFactura(nueva);
        response.getWriter().println("Factura registrada correctamente.");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Factura actualizada = new Factura();
        actualizada.setId(id);
        actualizada.setDescripcion(request.getParameter("descripcion"));
        actualizada.setMonto(Double.parseDouble(request.getParameter("monto")));
        actualizada.setFecha(parseFecha(request.getParameter("fecha")));

        servicio.actualizarFactura(actualizada);
        response.getWriter().println("Factura actualizada correctamente.");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        servicio.eliminarFactura(id);
        response.getWriter().println("Factura eliminada correctamente.");
    }
}
