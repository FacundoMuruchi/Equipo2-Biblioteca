package entidades;

import tdas.ListaTDA;
import implementaciones.ListaDinamica;

public class Usuario {
    private int dni;
    private String nombre;
    private String direccion;
    private int telefono;
    private ListaTDA prestamos; // lista dinámica

    public Usuario(int dni, String nombre, String direccion, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.prestamos = new ListaDinamica();
        this.prestamos.InicializarLista();
    }

    public String getDni() {
        return String.valueOf(dni);
    }

    public String getTelefono() {
        return String.valueOf(telefono);
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public ListaTDA getPrestamos() {
        return prestamos;
    }
}
