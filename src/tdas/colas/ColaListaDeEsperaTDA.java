package tdas.colas;

import entidades.Prestamo;

public interface ColaListaDeEsperaTDA {
    void inicializarCola(); // pre: no aplica
    void acolar(Prestamo x); // pre: cola inicializada
    void desacolar(); // pre: cola inicializada y no vacia
    boolean colaVacia(); // pre: cola inicializada
    Prestamo primero(); // pre: pila inicializada y no vacia
}
