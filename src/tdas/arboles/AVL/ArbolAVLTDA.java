package tdas.arboles.AVL;

import entidades.Usuario;

public interface ArbolAVLTDA {
    void inicializarArbol();
    void insertar(Usuario dato);
    Usuario buscar(int dato);
    NodoTDA raiz();
}