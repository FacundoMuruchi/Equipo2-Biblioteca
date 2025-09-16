package entidades;

public class Usuario {
    private int dni;
    private String nombre;
    private String direccion;
    private int telefono;

    // En vez de ListaTDA, usamos un arreglo fijo de préstamos
    private Prestamo[] prestamos;
    private int cantPrestamos;

    public Usuario(int dni, String nombre, String direccion, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;

        this.prestamos = new Prestamo[50]; // máximo 50 préstamos por usuario
        this.cantPrestamos = 0;
    }

    // --- Getters y Setters básicos ---
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    // --- Manejo de préstamos ---
    public void agregarPrestamo(Prestamo p) {
        if (cantPrestamos < prestamos.length) {
            prestamos[cantPrestamos] = p;
            cantPrestamos++;
        } else {
            System.out.println("El usuario alcanzó el límite de préstamos.");
        }
    }

    public Prestamo[] getPrestamos() {
        return prestamos;
    }

    public int getCantPrestamos() {
        return cantPrestamos;
    }

    public void mostrarPrestamos() {
        if (cantPrestamos == 0) {
            System.out.println("El usuario no tiene préstamos activos.");
        } else {
            for (int i = 0; i < cantPrestamos; i++) {
                Prestamo p = prestamos[i];
                System.out.println("- Libro: " + p.getLibro().getTitulo() + 
                                   " | Fecha devolución: " + p.getFechaDevolucion());
            }
        }
    }
}

