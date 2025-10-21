package tdas.arboles.AVL;

import entidades.Usuario;

public class Nodo implements NodoTDA {
	
    private Usuario dato;
    private NodoTDA izquierdo;
    private NodoTDA derecho;
    private int altura;

    public Nodo(Usuario dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 0;
    }

    // Getters y setters
    public Usuario getDato() {
        return dato;
    }

    public void setDato(Usuario dato) {
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