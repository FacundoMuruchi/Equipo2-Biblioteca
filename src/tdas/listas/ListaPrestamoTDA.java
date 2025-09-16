package tdas.listas;

import entidades.Prestamo;

public interface ListaPrestamoTDA {
    public void inicializarLista();
    public void agregarF(Prestamo c);
    public void eliminar(int dni);
    public Prestamo buscar(int dni);
    public void mostrar();
}