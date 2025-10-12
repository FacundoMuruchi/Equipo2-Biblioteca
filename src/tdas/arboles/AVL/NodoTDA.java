package tdas.arboles.AVL;

public interface NodoTDA {
	
    int getDato();
    void setDato(int dato);
    NodoTDA getIzquierdo();
    void setIzquierdo(NodoTDA izquierdo);
    NodoTDA getDerecho();
    void setDerecho(NodoTDA derecho);
    int getAltura();
    void setAltura(int altura);
}