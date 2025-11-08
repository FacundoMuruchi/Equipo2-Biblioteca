package tdas.arboles;

import entidades.Libro;

public class ABBLibros implements ABBLibrosTDA {
    private class Nodo {
        Libro dato;
        ABBLibrosTDA subIzq;
        ABBLibrosTDA subDer;
    }

    Nodo raiz;

    @Override
    public void inicializarArbol() {
        raiz = null;
    }

    @Override
    public Libro raiz() {
        return raiz.dato;
    }

    @Override
    public ABBLibrosTDA hijoIzq() {
        return raiz.subIzq;
    }

    @Override
    public ABBLibrosTDA hijoDer() {
        return raiz.subDer;
    }

    @Override
    public boolean arbolVacio() {
        return raiz == null;
    }

    @Override
    public void agregarElem(Libro x) { // los elementos siempre se agrergan como hoja
        if (raiz == null) { // caso base
            raiz = new Nodo();
            raiz.dato = x;
            raiz.subIzq = new ABBLibros();
            raiz.subIzq.inicializarArbol();
            raiz.subDer = new ABBLibros();
            raiz.subDer.inicializarArbol();

            // casos recursivos
        } else if (raiz.dato.getIsbn() > x.getIsbn()) { // si es menor
            raiz.subIzq.agregarElem(x);
        } else if (raiz.dato.getIsbn() < x.getIsbn()) { // si es mayor
            raiz.subDer.agregarElem(x);
        }
    }

    private Libro mayor (ABBLibrosTDA a) {
        if (a.hijoDer().arbolVacio())
            return a.raiz();
        else
            return mayor(a.hijoDer());
    }

    private Libro menor (ABBLibrosTDA a) {
        if (a.hijoIzq().arbolVacio())
            return a.raiz();
        else
            return menor(a.hijoIzq());
    }

    @Override
    public void eliminarElem(Libro x) {
        if (raiz != null) {
            if (raiz.dato == x && raiz.subDer.arbolVacio() && raiz.subIzq.arbolVacio()) { // si es hoja
                raiz = null;
            } else if (raiz.dato == x && !raiz.subIzq.arbolVacio()) { // si hay subarbol izquierdo
                raiz.dato = this.mayor(raiz.subIzq); // tomo el mayor de la izquierda
                raiz.subIzq.eliminarElem(raiz.dato);
            } else if (raiz.dato == x & raiz.subIzq.arbolVacio()) { // si NO hay subarbol izquierdo
                raiz.dato = this.menor(raiz.subDer); // tomo el menor de la derecha
                raiz.subDer.eliminarElem(raiz.dato);
                // buscar raiz de x
            } else if (raiz.dato.getIsbn() < x.getIsbn()) { // si x es mayor que la raiz
                raiz.subDer.eliminarElem(x);
            } else { // si es menor que la raiz
                raiz.subIzq.eliminarElem(x);
            }
        }
    }
    @Override
    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        imprimirHorizontal(raiz, "", true);
    }

/**
 * Imprime el árbol horizontalmente (de izquierda a derecha)
 * Ejemplo:
 * 50
 * ├── 30
 * │   ├── 20
 * │   │   └── 10
 * │   └── 40
 * └── 70
 *     ├── 60
 *     └── 80
 */
    private void imprimirHorizontal(Nodo nodo, String prefijo, boolean esUltimo) {
    if (nodo == null) {
        return;
    }

    // Dibujar el prefijo antes del nodo
    System.out.print(prefijo);

    // Mostrar la rama correspondiente
    if (esUltimo) {
        System.out.print("└── ");
    } else {
        System.out.print("├── ");
    }

    // Mostrar el valor (ISBN del libro)
    System.out.println(nodo.dato.getIsbn());

    // Construir el nuevo prefijo para los hijos
    String nuevoPrefijo;
    if (esUltimo) {
        nuevoPrefijo = prefijo + "    ";
    } else {
        nuevoPrefijo = prefijo + "│   ";
    }

    // Obtener referencias a los subárboles izquierdo y derecho
    ABBLibros izq = (ABBLibros) nodo.subIzq;
    ABBLibros der = (ABBLibros) nodo.subDer;

    // Verificar si existen subárboles antes de seguir
    boolean hayIzquierdo = !izq.arbolVacio();
    boolean hayDerecho = !der.arbolVacio();

    // Llamadas recursivas
    if (hayIzquierdo || hayDerecho) {
        if (hayIzquierdo) {
            imprimirHorizontal(izq.raiz, nuevoPrefijo, !hayDerecho);
        }
        if (hayDerecho) {
            imprimirHorizontal(der.raiz, nuevoPrefijo, true);
        }
    }
}
}

