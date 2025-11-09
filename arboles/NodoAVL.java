package tdas.arboles;

import entidades.Usuario;

public class NodoAVL {
    public Usuario dato;
    public NodoAVL subIzq;
    public NodoAVL subDer;
    int altura;

    NodoAVL(Usuario dato){
        this.dato = dato;
        this.altura = 0;
    }
}
