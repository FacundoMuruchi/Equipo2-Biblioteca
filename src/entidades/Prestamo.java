package entidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private int codigo;
    private static int contadorCodigo = 0;

    public Prestamo(Libro libro, Usuario usuario) {
        this.codigo = ++contadorCodigo;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = this.fechaPrestamo.plusDays(30);
    }

    public Libro getLibro() {
        return libro;
    }

    public int getCodigo() {
        return codigo;
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

    public String getFechaPrestamo() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fechaPrestamo.format(formato);
    }

    public String getFechaDevolucion() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fechaDevolucion.format(formato);
    }

    public int diasRestantes() {
        return LocalDate.now().until(fechaDevolucion).getDays();
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + ", Usuario: " + usuario.getNombre() + " " + usuario.getApellido() +
                ", Libro: " + libro.getTitulo() + ", Fecha: " + this.getFechaPrestamo() + ", Devolucion: "
                + this.getFechaDevolucion() + ", Dias restantes: " + this.diasRestantes();
    }
}
