package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;

public class Main {
    
    public static void main(String[] args) {
        Sistema biblioteca = new Sistema();
        
        System.out.println("--- INICIANDO PRUEBAS DEL SISTEMA ---\n");
        
        // 1. REGISTRO DE LIBROS
        System.out.println("1. REGISTRO DE LIBROS:");
        Libro l1 = biblioteca.agregarLibro("Cien años de soledad", "García Márquez", 9786, 2);
        Libro l2 = biblioteca.agregarLibro("El señor de los anillos", "Tolkien", 1234, 1);
        Libro l3 = biblioteca.agregarLibro("Rayuela", "Cortázar", 5678, 3);
        System.out.println();
        
        // 2. REGISTRO DE USUARIOS (Diccionario por DNI + AVL por Apellido)
        // Se insertan usuarios con apellidos desordenados para verificar el AVL.
        System.out.println("2. REGISTRO DE USUARIOS:");
        Usuario u1 = biblioteca.registrarUsuario(111, "Ana", "Zalazar", "Calle A", 1111);
        Usuario u2 = biblioteca.registrarUsuario(222, "Carlos", "Perez", "Calle B", 2222);
        Usuario u3 = biblioteca.registrarUsuario(333, "Beto", "Martinez", "Calle C", 3333);
        Usuario u4 = biblioteca.registrarUsuario(444, "Diana", "Alvarez", "Calle D", 4444);
        
        // Intento de registrar un usuario con DNI duplicado (debe fallar la inserción en ambas TDAs)
        biblioteca.registrarUsuario(111, "Error", "Duplicado", "Calle X", 0000);
        System.out.println();
        
        // 3. PRUEBAS DE BÚSQUEDA DE USUARIOS
        System.out.println("3. PRUEBAS DE BÚSQUEDA:");
        
        // Búsqueda en el Diccionario por DNI
        biblioteca.buscarUsuarioPorDni(333);
        
        // Búsqueda en el AVL por Apellido
        biblioteca.buscarUsuarioPorApellido("Perez");
        biblioteca.buscarUsuarioPorApellido("Flores"); // No existente
        System.out.println();

        // 4. PRUEBA DE LISTADO ORDENADO (Usando AVL)
        // Debe salir en orden alfabético: Alvarez, Martinez, Perez, Zalazar
        System.out.println("4. LISTADO DE USUARIOS ORDENADO POR APELLIDO:");
        biblioteca.listarUsuariosOrdenados();
        
        // 5. PRUEBA DE PRÉSTAMOS Y COLA DE ESPERA
        System.out.println("5. PRUEBA DE PRÉSTAMOS:");
        
        // Préstamo 1 (Disponible)
        Prestamo p1 = biblioteca.solicitarPrestamo(l2, u2, "2025-11-01"); 
        
        // Préstamo 2 (Disponible)
        Prestamo p2 = biblioteca.solicitarPrestamo(l1, u1, "2025-11-05");
        
        // Préstamo 3 (Disponible, agota l2)
        Prestamo p3 = biblioteca.solicitarPrestamo(l2, u3, "2025-11-10"); 
        
        // Préstamo 4 (A Cola de Espera: l2 no tiene copias)
        Prestamo p4 = biblioteca.solicitarPrestamo(l2, u4, "2025-11-15"); 
        System.out.println();
        
        // 6. PRUEBA DE DEVOLUCIÓN Y COLA DE ESPERA
        System.out.println("6. PRUEBA DE DEVOLUCIÓN Y COLA DE ESPERA:");
        biblioteca.mostrarColaDeEspera();
        
        // Devolución de l2 (u3) - Esto libera la copia y se presta a u4
        biblioteca.realizarDevolucion(p3);
        
        biblioteca.mostrarColaDeEspera();
        biblioteca.listarDevolucionesPendientes();
        
        System.out.println("--- PRUEBAS DEL SISTEMA FINALIZADAS ---");
    }
}