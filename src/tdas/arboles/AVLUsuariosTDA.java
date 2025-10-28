package tdas.arboles;

import entidades.Usuario;

public interface AVLUsuariosTDA {
    void inicializarArbol();
    void insertar(Usuario dato);
    Usuario buscar(int dato);
    NodoAVL raiz();
}