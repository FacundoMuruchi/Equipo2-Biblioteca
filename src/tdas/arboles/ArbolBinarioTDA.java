package tdas.arboles;

import entidades.Libro;

public interface ArbolBinarioTDA {
    void inicializarArbol();
    Libro raiz(); // pre: inicializado y no vacio
    ArbolBinarioTDA hijoIzq(); // pre: inicializado y no vacio
    ArbolBinarioTDA hijoDer(); // pre: inicializado y no vacio
    boolean arbolVacio(); // pre: inicializado
    void agregarElem(Libro x); // pre: inicializado
    void eliminarElem(Libro x); // pre: inicializado
}
