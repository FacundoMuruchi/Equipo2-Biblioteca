package entidades;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private String fechaDevolucion;

    public Prestamo(Libro libro, Usuario usuario, String fechaDevolucion) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "libro=" + libro +
                ", usuario=" + usuario +
                ", fechaDevolucion='" + fechaDevolucion + '\'' +
                '}';
    }
}
