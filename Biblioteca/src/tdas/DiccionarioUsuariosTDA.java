package tdas;

import entidades.Usuario;
import entidades.Prestamo;

public interface DiccionarioUsuariosTDA {
    void inicializarDiccionario();
    void agregarUsuario(int dni, String nombre);
    void eliminarUsuario(int dni);
    boolean usuarioExiste(int dni);
    Usuario obtenerUsuario(int dni);

    // préstamos
    void agregarPrestamo(int dni, Prestamo p);
    void eliminarPrestamo(int dni, Prestamo p);
    Prestamo[] obtenerPrestamos(int dni);
}
