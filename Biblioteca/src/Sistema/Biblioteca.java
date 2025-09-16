package Sistema;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import impl.ColaPrioridad;
import tdas.ColaPrioridadTDA;

public class Biblioteca {
    private Libro[] libros;
    private Usuario[] usuarios;
    private int cantLibros;
    private int cantUsuarios;

    public Biblioteca(int maxLibros, int maxUsuarios) {
        this.libros = new Libro[maxLibros];
        this.usuarios = new Usuario[maxUsuarios];
        this.cantLibros = 0;
        this.cantUsuarios = 0;
    }

    // --- Gestión de libros ---
    public void agregarLibro(Libro l) {
        if (cantLibros < libros.length) {
            libros[cantLibros] = l;
            cantLibros++;
        } else {
            System.out.println("No se pueden agregar más libros.");
        }
    }

    public Libro buscarLibro(int isbn) {
        for (int i = 0; i < cantLibros; i++) {
            if (libros[i].getIsbn() == isbn) {
                return libros[i];
            }
        }
        return null;
    }

    // --- Gestión de usuarios ---
    public void registrarUsuario(Usuario u) {
        if (cantUsuarios < usuarios.length) {
            usuarios[cantUsuarios] = u;
            cantUsuarios++;
        } else {
            System.out.println("No se pueden registrar más usuarios.");
        }
    }

    public Usuario buscarUsuario(int dni) {
        for (int i = 0; i < cantUsuarios; i++) {
            if (usuarios[i].getDni() == dni) {
                return usuarios[i];
            }
        }
        return null;
    }

    // --- Gestión de préstamos ---
    public void prestarLibro(int isbn, int dni, String fecha) {
        Libro l = buscarLibro(isbn);
        Usuario u = buscarUsuario(dni);

        if (l == null || u == null) {
            System.out.println("Libro o usuario no encontrado.");
            return;
        }

        if (l.getCopiasDisponibles() > 0) {
            // Se reduce stock
            l.setCopiasDisponibles(l.getCopiasDisponibles() - 1);

            // Crear préstamo
            Prestamo p = new Prestamo(l, u, fecha);

            // CORRECCIÓN: usar método de Usuario directamente
            u.agregarPrestamo(p);

            System.out.println("✅ Préstamo realizado: " + l.getTitulo() + " a " + u.getNombre());
        } else {
            // Si no hay copias, se agrega a la cola de espera
            ColaPrioridadTDA cola = new ColaPrioridad();
            cola.InicializarCola();
            cola.AcolarPrioridad(u.getDni(), 1); // prioridad baja por defecto
            System.out.println("⚠ No hay copias disponibles. Usuario agregado a lista de espera.");
        }
    }

    // --- Devoluciones ---
    public void devolverLibro(int isbn, int dni) {
        Libro l = buscarLibro(isbn);
        Usuario u = buscarUsuario(dni);

        if (l == null || u == null) {
            System.out.println("Libro o usuario no encontrado.");
            return;
        }

        // Se incrementa stock
        l.setCopiasDisponibles(l.getCopiasDisponibles() + 1);
        System.out.println("✅ Libro devuelto: " + l.getTitulo() + " por " + u.getNombre());
    }

    // --- Reportes ---
    public void reporteUsuarios() {
        System.out.println("=== Reporte de usuarios ===");
        for (int i = 0; i < cantUsuarios; i++) {
            Usuario u = usuarios[i];
            System.out.println("- " + u.getNombre() + " (DNI: " + u.getDni() + ")");
            u.mostrarPrestamos();
        }
    }

    public void reporteLibros() {
        System.out.println("=== Reporte de libros ===");
        for (int i = 0; i < cantLibros; i++) {
            Libro l = libros[i];
            System.out.println("- " + l.getTitulo() + " | Autor: " + l.getAutor() +
                               " | Copias disponibles: " + l.getCopiasDisponibles());
        }
    }
}
