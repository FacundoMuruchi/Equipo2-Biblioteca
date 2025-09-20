package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;

public class Main {
    public static void main(String[] args) {
        Sistema sys = new Sistema();

        // AÑADIR LIBROS
        Libro frankenstein = sys.agregarLibro("Frankenstein", "Mary Shelley", 123, 50);
        Libro principito = sys.agregarLibro("El principito", "Antoine de Saint-Exupéry", 5234, 100);
        Libro harryPotter = sys.agregarLibro("Harry Potter", "J.K. Rowling", 111, 1);
//        Libro señorAnillos = sys.agregarLibro("El Señor de los Anillos", "J.R.R. Tolkien", 123, 500); // no se añade porque ya existe isbn

        // REGISTRAR USUARIOS
        Usuario facu = sys.registrarUsuario(47307150, "facu", "bera", 1125037150);
        Usuario juan = sys.registrarUsuario(52307157, "juan", "quilmes", 1199037540);
        Usuario fede = sys.registrarUsuario(49872632, "fede", "ranelagh", 1169373462);
//        Usuario carlos = sys.registrarUsuario(49872632, "carlitos", "caba", 634735475); // no se añade porque ya existe dni

        // LISTAR LIBROS Y USUARIOS REGISTRADOS
        sys.listarLibros();
        sys.listarUsuarios();

        // SOLICITAR PRESTAMOS
        Prestamo pres1 = sys.solicitarPrestamo(harryPotter, juan, "12-12-25");
        Prestamo pres2 = sys.solicitarPrestamo(frankenstein, fede, "4-12-25");
        Prestamo pres3 = sys.solicitarPrestamo(principito, facu, "23-12-25");
        Prestamo pres4 = sys.solicitarPrestamo(harryPotter, facu, "10-12-25");
//        Prestamo pres5 = sys.solicitarPrestamo(harryPotter, carlos, "10-12-25"); // no se realiza el prestamo porque carlos no esta registrado

//        sys.realizarDevolucion(pres2);

        // LISTAR PRESTAMOS, DEVOLUCIONES PENDIENTES Y COLA DE ESPERA
        sys.listarTodosPrestamos();
        sys.listarDevolucionesPendientes();
        sys.mostrarColaDeEspera();

        // BUSACR LIBRO POR ISBN
        sys.buscarLibro(11111);
        sys.buscarLibro(5234);
    }
}