package tdas.arboles.AVL;

import entidades.Usuario;

public class ArbolAVL implements ArbolAVLTDA {
	
    private NodoTDA raiz;

    @Override
    public void inicializarArbol() {
        raiz = null;
    }

    @Override
    public void insertar(Usuario dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private NodoTDA insertarRecursivo(NodoTDA nodo, Usuario dato) {
        // Paso 1: Realizar la inserción normal (como en un árbol binario de búsqueda)
        if (nodo == null) {
            return new Nodo(dato);
        }

        if (dato.getDni() < nodo.getDato().getDni()) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), dato));
        } else if (dato.getDni() > nodo.getDato().getDni()) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), dato));
        } else {
            return nodo; // No se permiten duplicados
        }

        // Paso 2: Actualizar la altura del nodo actual
        nodo.setAltura(1 + Math.max(getAltura(nodo.getIzquierdo()), getAltura(nodo.getDerecho())));

        // Paso 3: Obtener el balance (factor de equilibrio)
        int balance = getBalance(nodo);

        // Paso 4: Balancear el árbol si es necesario (4 casos)
        // Rotación izquierda
        if (balance > 1 && dato.getDni() < ((Nodo) nodo.getIzquierdo()).getDato().getDni()) {
            return rotacionDerecha(nodo);
        }

        // Rotación derecha
        if (balance < -1 && dato.getDni() > ((Nodo) nodo.getDerecho()).getDato().getDni()) {
            return rotacionIzquierda(nodo);
        }

        // Rotación doble izquierda-derecha
        if (balance > 1 && dato.getDni() > ((Nodo) nodo.getIzquierdo()).getDerecho().getDato().getDni()) {
            nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            return rotacionDerecha(nodo);
        }

        // Rotación doble derecha-izquierda
        if (balance < -1 && dato.getDni() < ((Nodo) nodo.getDerecho()).getIzquierdo().getDato().getDni()) {
            nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private int getAltura(NodoTDA nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getAltura();
    }

    private int getBalance(NodoTDA nodo) {
        if (nodo == null) {
            return 0;
        }
        return getAltura(nodo.getIzquierdo()) - getAltura(nodo.getDerecho());
    }

    private NodoTDA rotacionIzquierda(NodoTDA y) {
        NodoTDA x = y.getDerecho();
        NodoTDA T2 = x.getIzquierdo();

        // Rotar
        x.setIzquierdo(y);
        y.setDerecho(T2);

        // Actualizar alturas
        y.setAltura(Math.max(getAltura(y.getIzquierdo()), getAltura(y.getDerecho())) + 1);
        x.setAltura(Math.max(getAltura(x.getIzquierdo()), getAltura(x.getDerecho())) + 1);

        return x;
    }

    private NodoTDA rotacionDerecha(NodoTDA x) {
        NodoTDA y = x.getIzquierdo();
        NodoTDA T2 = y.getDerecho();

        // Rotar
        y.setDerecho(x);
        x.setIzquierdo(T2);

        // Actualizar alturas
        x.setAltura(Math.max(getAltura(x.getIzquierdo()), getAltura(x.getDerecho())) + 1);
        y.setAltura(Math.max(getAltura(y.getIzquierdo()), getAltura(y.getDerecho())) + 1);

        return y;
    }

    @Override
    public Usuario buscar(int dato) {
        return buscarRecursivo(raiz, dato);
    }

    private Usuario buscarRecursivo(NodoTDA nodo, int dato) {
        if (nodo == null || nodo.getDato().getDni() == dato) {
            return nodo.getDato();
        }
        if (dato < nodo.getDato().getDni()) {
            return buscarRecursivo(nodo.getIzquierdo(), dato);
        } else {
            return buscarRecursivo(nodo.getDerecho(), dato);
        }
    }

    @Override
    public NodoTDA raiz() {
        return raiz;
    }
}