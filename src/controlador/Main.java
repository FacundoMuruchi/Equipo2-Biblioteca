package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;

public class Main {
    public static void main(String[] args) {
        Sistema sys = new Sistema();

        Libro principito = sys.agregarLibro("el principito", "antoine", 123, 50);
        Libro aladin = sys.agregarLibro("aladin", "disney", 5234, 100);
        Libro pocoyo = sys.agregarLibro("pocoyo", "nose", 111, 1);

        Usuario facu = sys.registrarUsuario(47307150, "facu", "bera", 1125037150);
        Usuario juan = sys.registrarUsuario(52307157, "juan", "quilmes", 1199037540);
        Usuario fede = sys.registrarUsuario(49872632, "fede", "ranelag", 1169373462);

        Prestamo pres1 = sys.solicitarPrestamo(principito, facu, "12-12-25");
        Prestamo pres4 = sys.solicitarPrestamo(aladin, facu, "30-12-25");
        Prestamo pres3 = sys.solicitarPrestamo(principito, juan, "4-12-25");
        Prestamo pres2 = sys.solicitarPrestamo(aladin, fede, "10-12-25");
        Prestamo pres5 = sys.solicitarPrestamo(pocoyo, fede, "10-12-25");
        Prestamo pres6 = sys.solicitarPrestamo(pocoyo, facu, "10-12-25");

        sys.listarPrestamos();
        sys.listarLibros();
        sys.listarUsuarios();

        sys.realizarDevolucion(facu, pres1);

        sys.listarPrestamos();
        sys.listarLibros();
        sys.listarUsuarios();

        sys.buscarLibro(11111);
        sys.buscarLibro(17451);
        sys.buscarLibro(123);
        sys.buscarLibro(5234);

        sys.listarPendientes();
    }
}