package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;

public class Main {
    public static void main(String[] args) {
        Sistema sys = new Sistema();
        Libro principito = sys.agregarLibro("el principito", "antoine", 123, 50);
        Libro aladin = sys.agregarLibro("aladin", "disney", 5234, 100);
        Usuario facu = sys.agregarUsuario(47307150, "facu", "bera", 1125037150);
        Usuario juan = sys.agregarUsuario(52307157, "juan", "quilmes", 1199037540);
        Usuario fede = sys.agregarUsuario(49872632, "fede", "ranelag", 1169373462);
        Prestamo pres1 = sys.agregarPrestamo(principito, facu, "12-12-25");
        Prestamo pres2 = sys.agregarPrestamo(aladin, fede, "10-12-25");
        Prestamo pres3 = sys.agregarPrestamo(principito, juan, "4-12-25");
        sys.listarPrestamos();
    }
}