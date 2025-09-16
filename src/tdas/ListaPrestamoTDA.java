package tdas;

import entidades.Prestamo;

public interface ListaPrestamoTDA {
    public void InicializarLista();
    public void AgregarF(Prestamo c);
    public void Eliminar(int dni);
    public Prestamo Buscar(int dni);
    public void Mostrar();
    public void Ordenar(); // Opcional
}