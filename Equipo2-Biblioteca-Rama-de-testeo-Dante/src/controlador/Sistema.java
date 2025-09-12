package controlador;

import entidades.Libro;
import tdas.ConjuntoLibroLD;
import tdas.ConjuntoTDA;

public class Sistema {
    ConjuntoTDA libros;

    public Sistema() {
        libros = new ConjuntoLibroLD();
        libros.inicializarConjunto();
    }

    public void agregarLibro(String titulo, String autor, int isbn, int copiasDisponibles) {
        Libro libro = new Libro(titulo, autor, isbn, copiasDisponibles);
        libros.agregar(libro);
    }
}
