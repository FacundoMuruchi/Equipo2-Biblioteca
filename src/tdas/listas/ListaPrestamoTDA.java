package tdas.listas;

import entidades.Prestamo;
import entidades.Usuario;

public interface ListaPrestamoTDA {
    public void inicializarLista();

    public void agregarF(Prestamo c);

    public void eliminar(Prestamo prestamo);

    public int buscar(Prestamo prestamo);

    public void mostrar();

    public ListaPrestamoTDA filtrarPorUsuario(Usuario usuario);
}