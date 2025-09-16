package entidades;

import tdas.ColaPrioridadTDA;
import impl.ColaPrioridad; // 👈 usamos la implementación, no la interfaz

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private String fechaDevolucion;
    private ColaPrioridadTDA listaEspera;

    public Prestamo(Libro libro, Usuario usuario, String fechaDevolucion) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaDevolucion = fechaDevolucion;

        // CORRECCIÓN: instanciar la implementación concreta
        this.listaEspera = new ColaPrioridad();
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
        listaEspera.AcolarPrioridad(u.getDni(), prioridad);
    }

    public ColaPrioridadTDA getListaEspera() {
        return listaEspera;
    }
}
