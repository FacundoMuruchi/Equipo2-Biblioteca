package controlador;

import entidades.Libro;
import tdas.conjuntos.ConjuntoLibrosLD;
import tdas.conjuntos.ConjuntoLibrosTDA;

public class Sistema {
    ConjuntoLibrosTDA libros;

    public Sistema() {
        libros = new ConjuntoLibrosLD();
        libros.inicializarConjunto();
    }

    public void agregarLibro(String titulo, String autor, int isbn, int copiasDisponibles) {
        Libro libro = new Libro(titulo, autor, isbn, copiasDisponibles);
        libros.agregar(libro);
    }
}
