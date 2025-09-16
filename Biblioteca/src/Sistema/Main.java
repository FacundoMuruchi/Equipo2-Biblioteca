package Sistema;

import entidades.Libro;
import entidades.Usuario;

public class Main {
    public static void main(String[] args) {
        // Crear biblioteca con capacidad para 10 libros y 10 usuarios
        Biblioteca biblioteca = new Biblioteca(10, 10);

        // === 1. Agregar libros ===
        Libro l1 = new Libro("El Quijote", "Cervantes", 1001, 2);
        Libro l2 = new Libro("Cien Años de Soledad", "García Márquez", 1002, 1);
        Libro l3 = new Libro("1984", "George Orwell", 1003, 0); // sin copias

        biblioteca.agregarLibro(l1);
        biblioteca.agregarLibro(l2);
        biblioteca.agregarLibro(l3);

        // === 2. Registrar usuarios ===
        Usuario u1 = new Usuario(123, "Ana", "Calle Falsa 123", 5551111);
        Usuario u2 = new Usuario(456, "Luis", "Av. Siempreviva 742", 5552222);
        Usuario u3 = new Usuario(789, "Maria", "Calle Luna 55", 5553333);

        biblioteca.registrarUsuario(u1);
        biblioteca.registrarUsuario(u2);
        biblioteca.registrarUsuario(u3);

        // === 3. Solicitar préstamos ===
        biblioteca.prestarLibro(1001, 123, "2025-09-20"); // Ana pide El Quijote (OK)
        biblioteca.prestarLibro(1001, 456, "2025-09-22"); // Luis pide El Quijote (OK, última copia)
        biblioteca.prestarLibro(1001, 789, "2025-09-25"); // María pide El Quijote (cola de espera)

        biblioteca.prestarLibro(1002, 123, "2025-09-21"); // Ana pide Cien Años (OK)
        biblioteca.prestarLibro(1002, 456, "2025-09-23"); // Luis pide Cien Años (cola de espera)

        biblioteca.prestarLibro(1003, 789, "2025-09-24"); // María pide 1984 (ya no hay copias → espera)

        // === 4. Devoluciones ===
        biblioteca.devolverLibro(1001, 123); // Ana devuelve El Quijote
        biblioteca.devolverLibro(1002, 123); // Ana devuelve Cien Años

        // === 5. Reportes ===
        biblioteca.reporteLibros();   // estado de libros
        biblioteca.reporteUsuarios(); // estado de usuarios + préstamos
    }
}
