package tdas.colas;

import entidades.Espera;

public interface ColaListaDeEsperaTDA {
    void inicializarCola(); // pre: no aplica
    void acolar(Espera x); // pre: cola inicializada
    void desacolar(); // pre: cola inicializada y no vacia
    boolean colaVacia(); // pre: cola inicializada
    Espera primero(); // pre: pila inicializada y no vacia
}
