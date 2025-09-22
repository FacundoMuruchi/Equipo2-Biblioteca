package tdas.listas;

import entidades.Prestamo;

import java.util.Arrays;

public class ListaPrestamoEstatica implements ListaPrestamoTDA {
    Prestamo[] arr;
    int indice;

    @Override
    public void inicializarLista() {
        arr = new Prestamo[100];
        indice = 0;
    }

    @Override
    public void agregarF(Prestamo c) { // agregar al final
        arr[indice] = c;
        indice++;
    }

    @Override
    public void eliminar(Prestamo prestamo) { // O(n)
        int indx = buscar(prestamo);
        if (indx != -1) { // si la clave existe
            arr[indx] = arr[indice - 1]; // reemplaza con el ultimo elemento de la lista
            arr[indice - 1] = null;
            indice--;
        }
    }

    @Override
    public int buscar(Prestamo prestamo) { // O(n)
        int i = indice - 1; // ultimo elemento
        while (i >= 0 && arr[i] != prestamo)
            i--;
        return i; // indice (-1 si no lo encuentra)
    }

    @Override
    public void mostrar() {
        int i;
        for (i = 0; i < indice; i++) {
            System.out.println("Usuario: " + arr[i].getUsuario().getNombre() + ", Libro: " + arr[i].getLibro().getTitulo() +  ", Devolucion: " + arr[i].getFechaDevolucion());
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
