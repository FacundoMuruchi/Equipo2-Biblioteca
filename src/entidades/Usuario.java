package entidades;

public class Usuario {
    private String nombre;
    private String direccion;
    private int edad;
    private int telefono;

    public Usuario(String nombre, String direccion, int edad, int telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.edad = edad;
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
