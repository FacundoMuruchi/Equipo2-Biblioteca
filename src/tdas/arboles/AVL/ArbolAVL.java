package tdas.arboles.AVL;

public class ArbolAVL implements ArbolAVLTDA {
    class Nodo {
        int dato;
        Nodo izquierdo;
        Nodo derecho;
        int altura;

        Nodo(int dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
            this.altura = 1; // La altura de un nodo hoja es 1
        }
    }
    private Nodo raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    @Override
    public void insertar(int dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private Nodo insertarRecursivo(Nodo nodo, int dato) {
        // Paso 1: Realizar la inserción normal (como en un árbol binario de búsqueda)
        if (nodo == null) {
            return new Nodo(dato);
        }

        if (dato < nodo.dato) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, dato);
        } else if (dato > nodo.dato) {
            nodo.derecho = insertarRecursivo(nodo.derecho, dato);
        } else {
            return nodo; // No se permiten duplicados
        }

        // Paso 2: Actualizar la altura del nodo actual
        nodo.altura = (1 + Math.max(getAltura(nodo.izquierdo), getAltura(nodo.derecho)));

        // Paso 3: Obtener el balance (factor de equilibrio)
        int balance = getBalance(nodo);

        // Paso 4: Balancear el árbol si es necesario (4 casos)
        // Rotación izquierda
        if (balance > 1 && dato < nodo.izquierdo.dato) {
            return rotacionDerecha(nodo);
        }

        // Rotación derecha
        if (balance < -1 && dato > nodo.derecho.dato) {
            return rotacionIzquierda(nodo);
        }

        // Rotación doble izquierda-derecha
        if (balance > 1 && dato > nodo.izquierdo.derecho.dato) {
            nodo.izquierdo = (rotacionIzquierda(nodo.izquierdo));
            return rotacionDerecha(nodo);
        }

        // Rotación doble derecha-izquierda
        if (balance < -1 && dato < nodo.derecho.izquierdo.dato) {
            nodo.derecho = (rotacionDerecha(nodo.derecho));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private int getAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    private int getBalance(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return getAltura(nodo.izquierdo) - getAltura(nodo.derecho);
    }

    private Nodo rotacionIzquierda(Nodo y) {
        Nodo x = y.derecho;
        Nodo T2 = x.izquierdo;

        // Rotar
        x.izquierdo = y;
        y.derecho = T2;

        // Actualizar alturas
        y.altura = (Math.max(getAltura(y.izquierdo), getAltura(y.derecho)) + 1);
        x.altura = (Math.max(getAltura(x.izquierdo), getAltura(x.derecho)) + 1);

        return x;
    }

    private Nodo rotacionDerecha(Nodo x) {
        Nodo y = x.izquierdo;
        Nodo T2 = y.derecho;

        // Rotar
        y.derecho = x;
        x.izquierdo = T2;

        // Actualizar alturas
        x.altura = (Math.max(getAltura(x.izquierdo), getAltura(x.derecho)) + 1);
        y.altura = (Math.max(getAltura(y.izquierdo), getAltura(y.derecho)) + 1);

        return y;
    }

    @Override
    public Nodo buscar(int dato) {
        return buscarRecursivo(raiz, dato);
    }

    private Nodo buscarRecursivo(Nodo nodo, int dato) {
        if (nodo == null || nodo.dato == dato) {
            return nodo;
        }
        if (dato < nodo.dato) {
            return buscarRecursivo(nodo.izquierdo, dato);
        } else {
            return buscarRecursivo(nodo.derecho, dato);
        }
    }

    @Override
    public void recorridoInorden() {
        recorridoInordenRecursivo(raiz);
    }

    private void recorridoInordenRecursivo(Nodo nodo) {
        if (nodo != null) {
            recorridoInordenRecursivo(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            recorridoInordenRecursivo(nodo.derecho);
        }
    }
}