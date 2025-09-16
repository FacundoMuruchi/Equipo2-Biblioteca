package tdas.conjuntos;

import entidades.Libro;

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
    public void agregar(Libro x) {
        if (!pertenece(x.getIsbn())) {
            Nodo nuevo = new Nodo();
            nuevo.info = x;
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
    public void sacar(int x) {
        if (c != null) {
            if (c.info.getIsbn() == x) { // es el primero
                c = c.sig;
            } else { // es algun otro, lo buscamos
                Nodo aux = c;
                while (aux.sig != null && aux.sig.info.getIsbn() != x) {
                    aux = aux.sig;
                }
                if (aux.sig != null) { // encontrado
                    aux.sig = aux.sig.sig;
                }
            }
        }
    }

    @Override
    public boolean pertenece(int x) {
        Nodo aux = c;
        while (aux != null && aux.info.getIsbn() != x) {
            aux = aux.sig;
        }
        return (aux != null);
    }
}
