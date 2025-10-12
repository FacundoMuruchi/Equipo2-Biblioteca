package tdas.arboles.AVL;

public class Nodo implements NodoTDA {
	
    private int dato;
    private NodoTDA izquierdo;
    private NodoTDA derecho;
    private int altura;

    public Nodo(int dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1; // La altura de un nodo hoja es 1
    }

    // Getters y setters
    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public NodoTDA getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoTDA izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoTDA getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoTDA derecho) {
        this.derecho = derecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}