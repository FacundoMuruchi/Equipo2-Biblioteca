package tdas.arboles;

import entidades.Usuario;

public class ArbolAVL implements ArbolAVLTDA {
	
    private NodoAVL raiz;

    @Override
    public void inicializarArbol() {
        raiz = null;
    }

    @Override
    public void insertar(Usuario dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    public NodoAVL insertarRecursivo(NodoAVL nodo, Usuario dato) {
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
        actualizarAltura(nodo);

        // Paso 3: Obtener el balance (factor de equilibrio)
        int balance = getBalance(nodo);

        // Paso 4: Balancear el árbol si es necesario (4 casos)
        // Left-Left
        if (balance > 1 && dato.getDni() < nodo.subIzq.dato.getDni()) {
            return rotacionDerecha(nodo);
        }

        // Right-Right
        if (balance < -1 && dato.getDni() > nodo.subDer.dato.getDni()) {
            return rotacionIzquierda(nodo);
        }

        // Left-Right
        if (balance > 1 && dato.getDni() > nodo.subIzq.subDer.dato.getDni()) {
            nodo.subIzq = rotacionIzquierda(nodo.subIzq);
            return rotacionDerecha(nodo);
        }

        // Right-Left
        if (balance < -1 && dato.getDni() < nodo.subDer.subIzq.dato.getDni()) {
            nodo.subDer = rotacionDerecha(nodo.subDer);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private int getAltura(NodoAVL nodo) {
        if (nodo == null) {
            return -1;
        }
        return nodo.altura;
    }

    private void actualizarAltura(NodoAVL nodo) {
        nodo.altura = Math.max(getAltura(nodo.subIzq), getAltura(nodo.subDer)) + 1;
    }

    private int getBalance(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return getAltura(nodo.subIzq) - getAltura(nodo.subDer);
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.subDer;
        NodoAVL T2 = y.subIzq;

        // Rotar
        y.subIzq = x;
        x.subDer = T2;

        // Actualizar alturas
        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
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
        return buscarRecursivo(raiz, dato);
    }

    private Usuario buscarRecursivo(NodoAVL nodo, int dato) {
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
    public NodoAVL raiz() {
        return raiz;
    }
}