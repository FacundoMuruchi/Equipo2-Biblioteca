package tdas.arboles;

import tdas.arboles.ArbolBinarioTDA;

public class ABB implements ArbolBinarioTDA{
    private class Nodo {
        int dato;
        ArbolBinarioTDA subIzq;
        ArbolBinarioTDA subDer;
    }

    Nodo raiz;

    @Override
    public void inicializarArbol() {
        raiz = null;
    }

    @Override
    public int raiz() {
        return raiz.dato;
    }

    @Override
    public ArbolBinarioTDA hijoIzq() {
        return raiz.subIzq;
    }

    @Override
    public ArbolBinarioTDA hijoDer() {
        return raiz.subDer;
    }

    @Override
    public boolean arbolVacio() {
        return raiz == null;
    }

    @Override
    public void agregarElem(int x) { // los elementos siempre se agrergan como hoja
        if (raiz == null) { // caso base
            raiz = new Nodo();
            raiz.dato = x;
            raiz.subIzq = new ABB();
            raiz.subIzq.inicializarArbol();
            raiz.subDer = new ABB();
            raiz.subDer.inicializarArbol();

            // casos recursivos
        } else if (raiz.dato > x) { // si es menor
            raiz.subIzq.agregarElem(x);
        } else if (raiz.dato < x) { // si es mayor
            raiz.subDer.agregarElem(x);
        }
    }

    private int mayor (ArbolBinarioTDA a) {
        if (a.hijoDer().arbolVacio())
            return a.raiz();
        else
            return mayor(a.hijoDer());
    }

    private int menor (ArbolBinarioTDA a) {
        if (a.hijoIzq().arbolVacio())
            return a.raiz();
        else
            return mayor(a.hijoIzq());
    }

    @Override
    public void eliminarElem(int x) {
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
            } else if (raiz.dato < x) { // si x es mayor que la raiz
                raiz.subDer.eliminarElem(x);
            } else { // si es menor que la raiz
                raiz.subIzq.eliminarElem(x);
            }
        }
    }


}
