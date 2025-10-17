package controlador;

import entidades.Usuario;
import tdas.arboles.AVL.ArbolUsuariosAVL;

public class Main {
    public static void main(String[] args) {
        ArbolUsuariosAVL arbol = new ArbolUsuariosAVL();
        arbol.inicializarArbol();

        // Insertar algunos usuarios
        arbol.insertar(new Usuario(12345678, "Juan Pérez", "Calle A 123", null, 0));
        arbol.insertar(new Usuario(23456789, "Ana Gómez", "Calle B 456", null, 0));
        arbol.insertar(new Usuario(34567890, "Luis Fernández", "Calle C 789", null, 0));
        arbol.insertar(new Usuario(45678901, "María López", "Calle D 321", null, 0));

        // Mostrar ordenados
        arbol.listarUsuariosOrdenados();

        // Buscar por apellido
        String apellidoBuscado = "Pérez";
        Usuario encontrado = arbol.buscarPorApellido(apellidoBuscado);

        if (encontrado != null)
            System.out.println("\n Usuario encontrado: " + encontrado.getNombre() + " (DNI: " + encontrado.getDni() + ")");
        else
            System.out.println("\n No se encontró el apellido " + apellidoBuscado);
    }
}
