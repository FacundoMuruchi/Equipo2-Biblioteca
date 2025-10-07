package tdas.arboles;

public interface ArbolBinarioTDA {
    void inicializarArbol();
    int raiz(); // pre: inicializado y no vacio
    ArbolBinarioTDA hijoIzq(); // pre: inicializado y no vacio
    ArbolBinarioTDA hijoDer(); // pre: inicializado y no vacio
    boolean arbolVacio(); // pre: inicializado
    void agregarElem(int x); // pre: inicializado
    void eliminarElem(int x); // pre: inicializado
}
