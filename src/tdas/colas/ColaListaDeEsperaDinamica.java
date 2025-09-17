package tdas.colas;

import entidades.Prestamo;

public class ColaListaDeEsperaDinamica implements ColaListaDeEsperaTDA {
    private class Nodo {
        Prestamo info;
        Nodo sig;
    }

    private Nodo primero;
    private Nodo ultimo;

    @Override
    public void inicializarCola() {
       primero = null;
       ultimo = null;
    }

    @Override
    public void acolar(Prestamo x) {
        Nodo nuevo = new Nodo();
        nuevo.info = x;
        nuevo.sig = null;
        if (ultimo != null) { // cola no vacia
            ultimo.sig = nuevo;
        }
        ultimo = nuevo;
        if (primero == null) { // cola vacia
            primero = ultimo;
        }
    }

    @Override
    public void desacolar() {
        primero = primero.sig;
        if (primero == null) {
            ultimo = null;
        }
    }

    @Override
    public boolean colaVacia() {
        return (ultimo == null);
    }

    @Override
    public Prestamo primero() {
        return primero.info;
    }
}
