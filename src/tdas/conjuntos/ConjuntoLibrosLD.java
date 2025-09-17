package tdas.conjuntos;

import entidades.Libro;

// ALMACENAR LIBROS

public class ConjuntoLibrosLD implements ConjuntoLibrosTDA {
    private class Nodo {
        Libro info;
        Nodo sig;
    }

    private Nodo c;

    @Override
    public void inicializarConjunto() {
        c = null;
    }

    @Override
    public void agregar(Libro libro) {
        if (!pertenece(libro.getIsbn())) {
            Nodo nuevo = new Nodo();
            nuevo.info = libro;
            nuevo.sig = c;
            c = nuevo;
        }
    }

    @Override
    public Libro elegir() {
        return c.info; // elegimos el primero aunque puede ser cualquiera
    }

    @Override
    public boolean conjuntoVacio() {
        return (c==null);
    }

    @Override
    public void sacar(int isbn) {
        if (c != null) {
            if (c.info.getIsbn() == isbn) { // es el primero
                c = c.sig;
            } else { // es algun otro, lo buscamos
                Nodo aux = c;
                while (aux.sig != null && aux.sig.info.getIsbn() != isbn) {
                    aux = aux.sig;
                }
                if (aux.sig != null) { // encontrado
                    aux.sig = aux.sig.sig;
                }
            }
        }
    }

    @Override
    public boolean pertenece(int isbn) {
        Nodo aux = c;
        while (aux != null && aux.info.getIsbn() != isbn) {
            aux = aux.sig;
        }
        return (aux != null);
    }

    @Override
    public Libro buscar(int isbn) {
        if (c != null) {
            if (c.info.getIsbn() == isbn) { // es el primero
                return c.info;
            } else { // es algun otro, lo buscamos
                Nodo aux = c;
                while (aux.sig != null && aux.sig.info.getIsbn() != isbn) {
                    aux = aux.sig;
                }
                if (aux.sig != null) { // encontrado
                    return aux.sig.info;
                }
            }
        }
        return null;
    }

    @Override
    public void mostrar() {
        Nodo aux = c;
        while (aux != null) {
            System.out.println("Titulo: " + aux.info.getTitulo() + ", Autor: " + aux.info.getAutor() + ", ISBN: " + aux.info.getIsbn() + ", Copias: " + aux.info.getCopiasDisponibles());
            aux = aux.sig;
        }
    }
}
