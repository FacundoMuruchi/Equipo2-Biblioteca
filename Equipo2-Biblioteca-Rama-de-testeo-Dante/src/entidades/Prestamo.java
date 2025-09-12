package entidades;

import tdas.ColaPrioridadD;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private String fechaDevolucion;
    private ColaPrioridadD listaEspera;

    public Prestamo(Libro libro, Usuario usuario, String fechaDevolucion) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaDevolucion = fechaDevolucion;
        this.listaEspera = new ColaPrioridadD();
        this.listaEspera.InicializarCola();
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void agregarAListaEspera(Usuario u, int prioridad) {
        listaEspera.AcolarPrioridad(u.getDniInt(), prioridad);
    }

    public ColaPrioridadD getListaEspera() {
        return listaEspera;
    }
}
