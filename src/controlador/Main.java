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
        Usuario fede = sys.registrarUsuario(49872632, "fede", "ranelagh", 1169373462);

        Prestamo pres5 = sys.solicitarPrestamo(pocoyo, juan, "10-12-25");
        Prestamo pres6 = sys.solicitarPrestamo(pocoyo, facu, "10-12-25");
        Prestamo pres7 = sys.solicitarPrestamo(pocoyo, fede, "10-12-25");

//        sys.listarLibros();
//        sys.listarUsuarios();
//
//        sys.buscarLibro(11111);
//        sys.buscarLibro(5234);

        sys.realizarDevolucion(juan,pres5);
        sys.listarPendientes();
    }
}