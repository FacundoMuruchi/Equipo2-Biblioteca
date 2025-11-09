package tdas.arboles;

import entidades.Libro;

public interface ABBLibrosTDA {
    void inicializarArbol();
    Libro raiz(); // pre: inicializado y no vacio
    ABBLibrosTDA hijoIzq(); // pre: inicializado y no vacio
    ABBLibrosTDA hijoDer(); // pre: inicializado y no vacio
    boolean arbolVacio(); // pre: inicializado
    void agregarElem(Libro x); // pre: inicializado
    void eliminarElem(Libro x); // pre: inicializado
    void imprimirArbol(); // pre: inicializado
}
