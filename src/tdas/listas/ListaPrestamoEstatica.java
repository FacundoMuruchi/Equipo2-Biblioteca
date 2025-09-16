package tdas.listas;

import entidades.Prestamo;
import entidades.Usuario;

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
    public void eliminar(Prestamo prestamo) {
        int indx = buscar(prestamo);
        if (indx != -1) { // si la clave existe
            arr[indx] = arr[indice - 1]; // reemplaza con el ultimo elemento de la lista
            arr[indice - 1] = null;
            indice--;
        }
    }

    @Override
    public int buscar(Prestamo prestamo) {
        int i = indice - 1; // ultimo elemento
        while (i >= 0 && arr[i] != prestamo)
            i--;
        return i; // indice (-1 si no lo encuentra)
    }

    public ListaPrestamoTDA filtrarPorUsuario(Usuario usuario) {
        ListaPrestamoTDA listaPersonal = new ListaPrestamoEstatica();
        listaPersonal.inicializarLista();

        for (int i = 0; i < indice; i++) {
            if (arr[i].getUsuario() == usuario) {
                listaPersonal.agregarF(arr[i]);
            }
        }

        return listaPersonal;
    }

    @Override
    public void mostrar() {
        System.out.println("--- PRESTAMOS ---");
        int i;
        for (i = 0; i < indice; i++) {
            System.out.println("Libro: " + arr[i].getLibro().getTitulo() + ", Usuario: " + arr[i].getUsuario().getNombre() + ", Devolucion: " + arr[i].getFechaDevolucion());
        }
    }

    @Override
    public String toString() {
        return "ListaPrestamoEstatica{" +
                "arr=" + Arrays.toString(arr) +
                ", indice=" + indice +
                '}';
    }
}
