package entidades;

public class Usuario {
    private int dni;
    private String nombre;

    // lista dinámica de préstamos asociados a este usuario
    private NodoPrestamo prestamos;

    private class NodoPrestamo {
        Prestamo prestamo;
        NodoPrestamo sig;
    }

    public Usuario(int dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.prestamos = null;
    }

    public int getDni() { return dni; }
    public String getNombre() { return nombre; }

    // ---- gestión de préstamos ----
    public void agregarPrestamo(Prestamo p) {
        NodoPrestamo nuevo = new NodoPrestamo();
        nuevo.prestamo = p;
        nuevo.sig = prestamos;
        prestamos = nuevo;
    }

    public void eliminarPrestamo(Prestamo p) {
        if (prestamos != null && prestamos.prestamo.equals(p)) {
            prestamos = prestamos.sig;
        } else {
            NodoPrestamo aux = prestamos;
            while (aux != null && aux.sig != null && !aux.sig.prestamo.equals(p)) {
                aux = aux.sig;
            }
            if (aux != null && aux.sig != null) {
                aux.sig = aux.sig.sig;
            }
        }
    }

    public Prestamo[] getPrestamos() {
        int count = 0;
        NodoPrestamo aux = prestamos;
        while (aux != null) {
            count++;
            aux = aux.sig;
        }

        Prestamo[] arr = new Prestamo[count];
        aux = prestamos;
        int i = 0;
        while (aux != null) {
            arr[i++] = aux.prestamo;
            aux = aux.sig;
        }
        return arr;
    }
}
