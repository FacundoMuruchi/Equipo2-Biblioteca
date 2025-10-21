package tdas.arboles.AVL;

import entidades.Usuario;

public interface NodoTDA {
	
    Usuario getDato();
    void setDato(Usuario dato);
    NodoTDA getIzquierdo();
    void setIzquierdo(NodoTDA izquierdo);
    NodoTDA getDerecho();
    void setDerecho(NodoTDA derecho);
    int getAltura();
    void setAltura(int altura);
}