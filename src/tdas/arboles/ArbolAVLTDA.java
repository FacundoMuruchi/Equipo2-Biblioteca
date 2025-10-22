package tdas.arboles;

import entidades.Usuario;

public interface ArbolAVLTDA {
    void inicializarArbol();
    void insertar(Usuario dato);
    Usuario buscar(int dato);
    NodoAVL raiz();
}