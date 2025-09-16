package entidades;

import tdas.listas.ListaPrestamoTDA;

public class Usuario {
    private int dni;
    private String nombre;
    private String direccion;
    private int telefono;
    private ListaPrestamoTDA prestamos;

    public Usuario(int dni, String nombre, String direccion, int telefono, ListaPrestamoTDA prestamos) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.prestamos = prestamos;
    }

    public int getDni() {
        return dni;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public ListaPrestamoTDA getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(ListaPrestamoTDA prestamos) {
        this.prestamos = prestamos;
    }
}
