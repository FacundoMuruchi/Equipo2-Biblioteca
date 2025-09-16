package tdas.listas;

import entidades.Prestamo;

public interface ListaPrestamoTDA {
    public void inicializarLista();
    public void agregarF(Prestamo c);
    public void eliminar(Prestamo prestamo);
    public int buscar(Prestamo prestamo);
    public void mostrar();
}