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
        Libro superman = sys.agregarLibro("Superman", "Marvel", 30, 100);
//        Libro señorAnillos = sys.agregarLibro("El Señor de los Anillos", "J.R.R. Tolkien", 123, 500); // no se añade porque ya existe isbn

        // REGISTRAR USUARIOS
        Usuario facu = sys.registrarUsuario(47307150, "facu", "muruchi", "bera", 1125037150);
        Usuario juan = sys.registrarUsuario(52307157, "juan", "gonzales", "quilmes", 1199037540);
        Usuario fede = sys.registrarUsuario(49872632, "fede", "mestre", "ranelagh", 1169373462);
        Usuario alma = sys.registrarUsuario(47862369, "alma", "velazco", "solano", 1164576457);
        Usuario roman = sys.registrarUsuario(45812331, "roman", "bramanti", "ezpeleta", 1106789877);
        Usuario carlos = sys.registrarUsuario(49872632, "carlitos", "tevez", "caba", 634735475); // no se añade porque ya existe dni

        // LISTAR LIBROS Y USUARIOS REGISTRADOS
        sys.listarLibros();
        sys.listarUsuarios();

        // SOLICITAR PRESTAMOS
        Prestamo pres1 = sys.solicitarPrestamo(harryPotter, juan, "12-12-25");
        Prestamo pres2 = sys.solicitarPrestamo(frankenstein, fede, "4-12-25");
        Prestamo pres3 = sys.solicitarPrestamo(principito, facu, "23-12-25");
        Prestamo pres4 = sys.solicitarPrestamo(harryPotter, facu, "10-12-25");
        Prestamo pres5 = sys.solicitarPrestamo(harryPotter, fede, "15-12-25");
//        Prestamo pres6 = sys.solicitarPrestamo(frankenstein, carlos, "10-12-25"); // no se realiza el prestamo porque carlos no esta registrado

//        sys.realizarDevolucion(pres1);

        // LISTAR PRESTAMOS, DEVOLUCIONES PENDIENTES Y COLA DE ESPERA
        sys.listarTodosPrestamos();
        sys.listarDevolucionesPendientes();
        sys.mostrarColaDeEspera();

        // LISTAR LIBROS ORDENADOS
        sys.mostrarLibrosOrdenados();

        // BUSCAR LIBRO POR ISBN
        sys.buscarLibro(11191);
        sys.buscarLibro(30);
        sys.buscarLibro(600);
        sys.buscarLibro(111);
        System.out.println();

        // LISTAR USUARIOS ORDENADOS
        sys.listarUsuariosOrdenados();

        // BUSCAR USUARIO POR DNI
        sys.buscarUsuario(49872632);
        sys.buscarUsuario(47679632);
        sys.buscarUsuario(45812331);
        sys.buscarUsuario(47862369);
    }
}