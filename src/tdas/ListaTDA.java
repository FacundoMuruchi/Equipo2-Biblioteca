package tdas;

import entidades.Prestamo;

public interface ListaTDA {
    public void InicializarLista();
    public void AgregarI(Prestamo c);
    public void AgregarF(Prestamo c);
    public void AgregarO(Prestamo c);
    public void Eliminar(int dni);
    public Prestamo Buscar(int dni);
    public void Mostrar();
    public void Ordenar(); // Opcional
}