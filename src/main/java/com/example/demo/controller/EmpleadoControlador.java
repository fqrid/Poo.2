package controlador;

import modelo.Empleado;
import servicio.EmpleadoServicio;

import java.util.List;
import java.util.Scanner;

public class EmpleadoControlador {
    private final EmpleadoServicio servicio;
    private final Scanner scanner;

    public EmpleadoControlador() {
        this.servicio = new EmpleadoServicio();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> crearEmpleado();
                case 2 -> listarEmpleados();
                case 3 -> buscarEmpleado();
                case 4 -> actualizarEmpleado();
                case 5 -> eliminarEmpleado();
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menú de Empleados ---");
        System.out.println("1. Registrar nuevo empleado");
        System.out.println("2. Listar empleados");
        System.out.println("3. Buscar empleado por ID");
        System.out.println("4. Actualizar empleado");
        System.out.println("5. Eliminar empleado");
        System.out.println("0. Salir");
    }

    private void crearEmpleado() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();

        Empleado empleado = new Empleado(id, nombre, edad, cargo);
        servicio.registrarEmpleado(empleado);
        System.out.println("Empleado registrado exitosamente.");
    }

    private void listarEmpleados() {
        List<Empleado> empleados = servicio.listarEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            empleados.forEach(System.out::println);
        }
    }

    private void buscarEmpleado() {
        System.out.print("Ingrese el ID del empleado: ");
        int id = scanner.nextInt();
        Empleado empleado = servicio.obtenerEmpleadoPorId(id);
        if (empleado != null) {
            System.out.println(empleado);
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private void actualizarEmpleado() {
        System.out.print("ID del empleado a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Empleado existente = servicio.obtenerEmpleadoPorId(id);
        if (existente == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo cargo: ");
        String cargo = scanner.nextLine();

        Empleado actualizado = new Empleado(id, nombre, edad, cargo);
        if (servicio.modificarEmpleado(actualizado)) {
            System.out.println("Empleado actualizado con éxito.");
        } else {
            System.out.println("Error al actualizar el empleado.");
        }
    }

    private void eliminarEmpleado() {
        System.out.print("Ingrese el ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        if (servicio.eliminarEmpleado(id)) {
            System.out.println("Empleado eliminado.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }
}
