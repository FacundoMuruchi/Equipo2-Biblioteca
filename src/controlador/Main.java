package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;

public class Main {
    public static void main(String[] args) {
        Sistema sys = new Sistema();
        Libro principito = sys.agregarLibro("el principito", "antoine", 123, 50);
        Usuario facu = sys.agregarUsuario(47307150, "facu", "bera", 1125037150);
        Prestamo pres1 = sys.agregarPrestamo(principito, facu, "12-12-25");
    }
}