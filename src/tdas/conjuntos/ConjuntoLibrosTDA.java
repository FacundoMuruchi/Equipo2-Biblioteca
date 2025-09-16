package tdas.conjuntos;

import entidades.Libro;

public interface ConjuntoLibrosTDA {
    void inicializarConjunto(); // pre: no aplica
    void agregar(Libro x); // pre: conjunto inicializado
    Libro elegir(); // pre: conjunto inicializado y no vacio
    boolean conjuntoVacio(); // pre: conjunto inicializado
    void sacar(int x); // pre: conjunto inicializado y no vacio
    boolean pertenece(int x); // pre: conjunto inicializado
}
