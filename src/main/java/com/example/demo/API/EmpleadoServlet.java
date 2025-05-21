package com.example.demo.api;

import modelo.Empleado;
import servicio.EmpleadoServicio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/empleado")
public class EmpleadoServlet extends HttpServlet {
    private final EmpleadoServicio servicio = new EmpleadoServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (accion == null || accion.equals("listar")) {
            List<Empleado> empleados = servicio.listarEmpleados();
            for (Empleado e : empleados) {
                out.println(e + "<br>");
            }
        } else if (accion.equals("buscar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Empleado empleado = servicio.obtenerEmpleadoPorId(id);
            if (empleado != null) {
                out.println(empleado);
            } else {
                out.println("Empleado no encontrado.");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada");
            return;
        }

        switch (accion) {
            case "crear" -> crearEmpleado(request, response);
            case "actualizar" -> actualizarEmpleado(request, response);
            case "eliminar" -> eliminarEmpleado(request, response);
            default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
        }
    }

    private void crearEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String cargo = request.getParameter("cargo");

        Empleado empleado = new Empleado(id, nombre, edad, cargo);
        servicio.registrarEmpleado(empleado);

        response.getWriter().println("Empleado registrado exitosamente.");
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String cargo = request.getParameter("cargo");

        Empleado actualizado = new Empleado(id, nombre, edad, cargo);
        boolean actualizadoCorrectamente = servicio.modificarEmpleado(actualizado);

        if (actualizadoCorrectamente) {
            response.getWriter().println("Empleado actualizado correctamente.");
        } else {
            response.getWriter().println("Empleado no encontrado.");
        }
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean eliminado = servicio.eliminarEmpleado(id);

        if (eliminado) {
            response.getWriter().println("Empleado eliminado correctamente.");
        } else {
            response.getWriter().println("Empleado no encontrado.");
        }
    }
}
