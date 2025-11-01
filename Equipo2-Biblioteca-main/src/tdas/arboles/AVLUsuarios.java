package tdas.arboles;

import entidades.Usuario;

public class AVLUsuarios implements AVLUsuariosTDA {
	
    private NodoAVL raiz;

    @Override
    public void inicializarArbol() { // O(1)
        raiz = null;
    }

    @Override
    public void insertar(Usuario dato) {
        // Costo temporal: O(log N). El AVL garantiza que su altura (h) siempre es logarítmica (h = O(log N)).
        raiz = insertarRecursivo(raiz, dato); 
    }

    public NodoAVL insertarRecursivo(NodoAVL nodo, Usuario dato) {
        // Costo temporal: O(log N). La inserción y el rebalanceo (rotaciones) están limitados por la altura logarítmica del árbol.
        
        // Paso 1: Realizar la inserción normal (como en un árbol binario de búsqueda)
        if (nodo == null) {
            return new NodoAVL(dato);
        }

        if (dato.getDni() < nodo.dato.getDni()) {
            nodo.subIzq = insertarRecursivo(nodo.subIzq, dato);
        } else if (dato.getDni() > nodo.dato.getDni()) {
            nodo.subDer = insertarRecursivo(nodo.subDer, dato);
        } else {
            return nodo; // No se permiten duplicados
        }

        // Paso 2: Actualizar la altura del nodo actual
        actualizarAltura(nodo); // O(1)

        // Paso 3: Obtener el balance (factor de equilibrio)
        int balance = getBalance(nodo); // O(1)

        // Paso 4: Balancear el árbol si es necesario (4 casos)
        // Todas las rotaciones son O(1)
        // Left-Left
        if (balance > 1 && dato.getDni() < nodo.subIzq.dato.getDni()) {
            return rotacionDerecha(nodo); // O(1)
        }

        // Right-Right
        if (balance < -1 && dato.getDni() > nodo.subDer.dato.getDni()) {
            return rotacionIzquierda(nodo); // O(1)
        }

        // Left-Right
        if (balance > 1 && dato.getDni() > nodo.subIzq.subDer.dato.getDni()) {
            nodo.subIzq = rotacionIzquierda(nodo.subIzq);
            return rotacionDerecha(nodo); // O(1)
        }

        // Right-Left
        if (balance < -1 && dato.getDni() < nodo.subDer.subIzq.dato.getDni()) {
            nodo.subDer = rotacionDerecha(nodo.subDer);
            return rotacionIzquierda(nodo); // O(1)
        }

        return nodo;
    }

    private int getAltura(NodoAVL nodo) { // O(1)
        if (nodo == null) {
            return -1;
        }
        return nodo.altura;
    }

    private void actualizarAltura(NodoAVL nodo) { // O(1)
        nodo.altura = Math.max(getAltura(nodo.subIzq), getAltura(nodo.subDer)) + 1;
    }

    private int getBalance(NodoAVL nodo) { // O(1)
        if (nodo == null) {
            return 0;
        }
        return getAltura(nodo.subIzq) - getAltura(nodo.subDer);
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) { // O(1)
        NodoAVL y = x.subDer;
        NodoAVL T2 = y.subDer;

        // Rotar
        y.subIzq = x;
        x.subDer = T2;

        // Actualizar alturas
        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    private NodoAVL rotacionDerecha(NodoAVL y) { // O(1)
        NodoAVL x = y.subIzq;
        NodoAVL T2 = x.subDer;

        // Rotar
        x.subDer = y;
        y.subIzq = T2;

        // Actualizar alturas
        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    @Override
    public Usuario buscar(int dato) {
        // Costo temporal: O(log N). La AVL garantiza una altura logarítmica.
        return buscarRecursivo(raiz, dato);
    }

    private Usuario buscarRecursivo(NodoAVL nodo, int dato) {
        // Costo temporal: O(log N)
        if (nodo == null || nodo.dato.getDni() == dato) {
            return nodo.dato;
        }
        if (dato < nodo.dato.getDni()) {
            return buscarRecursivo(nodo.subIzq, dato);
        } else {
            return buscarRecursivo(nodo.subDer, dato);
        }
    }

    @Override
    public NodoAVL raiz() { // O(1)
        return raiz;
    }
}