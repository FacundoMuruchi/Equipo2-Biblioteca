package controlador;

import entidades.Libro;

public class Main {
    public static void main(String[] args) {

        // Crear el sistema principal que contiene el ABB de libros
        Sistema sys = new Sistema();

        // ======== AGREGAR VARIOS LIBROS AL SISTEMA ========
        Libro l1 = sys.agregarLibro("Frankenstein", "Mary Shelley", 123, 50);
        Libro l2 = sys.agregarLibro("El Principito", "Antoine de Saint-Exupéry", 5234, 100);
        Libro l3 = sys.agregarLibro("Harry Potter", "J.K. Rowling", 111, 10);
        Libro l4 = sys.agregarLibro("Superman", "Marvel", 30, 100);
        Libro l5 = sys.agregarLibro("Cien Años de Soledad", "Gabriel García Márquez", 900, 40);
        Libro l6 = sys.agregarLibro("1984", "George Orwell", 450, 70);
        Libro l7 = sys.agregarLibro("Moby Dick", "Herman Melville", 2000, 60);
        Libro l8 = sys.agregarLibro("Don Quijote de la Mancha", "Miguel de Cervantes", 750, 90);
        Libro l9 = sys.agregarLibro("Orgullo y Prejuicio", "Jane Austen", 320, 30);
        Libro l10 = sys.agregarLibro("Drácula", "Bram Stoker", 50, 80);
        Libro l11 = sys.agregarLibro("El Hobbit", "J.R.R. Tolkien", 1500, 120);
        Libro l12 = sys.agregarLibro("La Odisea", "Homero", 10, 25);

        // ======== IMPRIMIR EL ÁRBOL BINARIO DE BÚSQUEDA ========
        System.out.println("\n===== ÁRBOL BINARIO DE BÚSQUEDA DE LIBROS =====\n");
        sys.arbolLibros.imprimirArbol();
    }
}
