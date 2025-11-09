package controlador;

import entidades.Libro;

public class Main {
    public static void main(String[] args) {
        Sistema sys = new Sistema();

        // AÑADIR LIBROS
        Libro frankenstein = sys.agregarLibro("Frankenstein", "Mary Shelley", 123, 50);
        Libro principito = sys.agregarLibro("El principito", "Antoine de Saint-Exupéry", 5234, 100);
        Libro harryPotter = sys.agregarLibro("Harry Potter", "J.K. Rowling", 111, 1);
        Libro superman = sys.agregarLibro("Superman", "Marvel", 30, 100);
//        Libro señorAnillos = sys.agregarLibro("El Señor de los Anillos", "J.R.R. Tolkien", 123, 500); // no se añade porque ya existe isbn

        System.out.println("===== ÁRBOL BINARIO DE BÚSQUEDA DE LIBROS =====");
       sys.arbolLibros.imprimirArbol();
    }
}
