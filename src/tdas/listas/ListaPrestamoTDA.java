package tdas.listas;

import entidades.Prestamo;
import entidades.Usuario;

public interface ListaPrestamoTDA {
    void inicializarLista();
    void agregarF(Prestamo c);
    void eliminar(Prestamo prestamo);
    int buscar(Prestamo prestamo);
    void mostrar();
    ListaPrestamoTDA filtrarPorUsuario(Usuario usuario);
}