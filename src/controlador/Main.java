package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;

public class Main {
    public static void main(String[] args) {
        Sistema sys = new Sistema();

        Libro principito = sys.agregarLibro("el principito", "antoine", 123, 50);
        Libro aladin = sys.agregarLibro("aladin", "disney", 5234, 100);
        Libro pocoyo = sys.agregarLibro("pocoyo", "nose", 111, 2);
        Libro trols = sys.agregarLibro("trols", "sss", 123, 2); // no se añade porque ya existe isbn
        Libro xd = sys.agregarLibro("xd", "dsdsds", 111, 100); // no se añade porque ya existe isbn

        Usuario facu = sys.registrarUsuario(47307150, "facu", "bera", 1125037150);
        Usuario juan = sys.registrarUsuario(52307157, "juan", "quilmes", 1199037540);
        Usuario fede = sys.registrarUsuario(49872632, "fede", "ranelagh", 1169373462);
        Usuario carlos = sys.registrarUsuario(49872632, "carlitos", "ranelagh", 634735475); // no se añade porque ya existe dni

        Prestamo pres1 = sys.solicitarPrestamo(pocoyo, carlos, "10-12-25"); // no se realiza el prestamo porque carlos no esta registrado
        Prestamo pres2 = sys.solicitarPrestamo(pocoyo, juan, "10-12-25");
        Prestamo pres3 = sys.solicitarPrestamo(pocoyo, fede, "10-12-25");
        Prestamo pres4 = sys.solicitarPrestamo(pocoyo, facu, "10-12-25");

        sys.realizarDevolucion(pres1);

        sys.listarLibros();
        sys.listarLibros();
        sys.listarUsuarios();
        sys.listarUsuarios();
//        sys.listarTodosPrestamos();
//        sys.listarDevolucionesPendientes();
//        sys.mostrarListaDeEspera();
//
        sys.buscarLibro2(11111);
        sys.buscarLibro2(5234);
    }
}