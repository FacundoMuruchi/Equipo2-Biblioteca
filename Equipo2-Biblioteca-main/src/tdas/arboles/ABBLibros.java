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
    public void inicializarArbol() { // O(1)
        raiz = null;
    }

    @Override
    public Libro raiz() { // O(1)
        return raiz.dato;
    }

    @Override
    public ABBLibrosTDA hijoIzq() { // O(1)
        return raiz.subIzq;
    }

    @Override
    public ABBLibrosTDA hijoDer() { // O(1)
        return raiz.subDer;
    }

    @Override
    public boolean arbolVacio() { // O(1)
        return raiz == null;
    }

    @Override
    public void agregarElem(Libro x) { 
        // Costo temporal: Depende de la altura (h) del árbol.
        // MEJOR CASO (balanceado): O(log N). PEOR CASO (degenerado/desbalanceado): O(N).
        
        if (raiz == null) { // caso base // O(1)
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
        // Costo temporal: O(h), donde h es la altura de la rama derecha.
        // MEJOR CASO: O(log N). PEOR CASO: O(N).
        if (a.hijoDer().arbolVacio())
            return a.raiz();
        else
            return mayor(a.hijoDer());
    }

    private Libro menor (ABBLibrosTDA a) {
        // Costo temporal: O(h), donde h es la altura de la rama izquierda.
        // MEJOR CASO: O(log N). PEOR CASO: O(N).
        if (a.hijoIzq().arbolVacio())
            return a.raiz();
        else
            return mayor(a.hijoIzq());
    }

    @Override
    public void eliminarElem(Libro x) {
        // Costo temporal: O(h). Incluye la búsqueda y el manejo del reemplazo ('mayor' o 'menor').
        // MEJOR CASO: O(log N). PEOR CASO: O(N).
        
        if (raiz != null) {
            if (raiz.dato == x && raiz.subDer.arbolVacio() && raiz.subIzq.arbolVacio()) { // si es hoja // O(1)
                raiz = null;
            } else if (raiz.dato == x && !raiz.subIzq.arbolVacio()) { // si hay subarbol izquierdo
                raiz.dato = this.mayor(raiz.subIzq); // O(h)
                raiz.subIzq.eliminarElem(raiz.dato); // O(h)
            } else if (raiz.dato == x & raiz.subIzq.arbolVacio()) { // si NO hay subarbol izquierdo
                raiz.dato = this.menor(raiz.subDer); // O(h)
                raiz.subDer.eliminarElem(raiz.dato); // O(h)
                // buscar raiz de x
            } else if (raiz.dato.getIsbn() < x.getIsbn()) { // si x es mayor que la raiz
                raiz.subDer.eliminarElem(x);
            } else { // si es menor que la raiz
                raiz.subIzq.eliminarElem(x);
            }
        }
    }
}