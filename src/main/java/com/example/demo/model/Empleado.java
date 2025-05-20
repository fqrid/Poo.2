package modelo;

public class Empleado {
    private int id;
    private String nombre;
    private int edad;
    private String cargo;

    public Empleado() {}

    public Empleado(int id, String nombre, int edad, String cargo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.cargo = cargo;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCargo() {
        return cargo;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", edad=" + edad +
               ", cargo='" + cargo + '\'' +
               '}';
    }
}
