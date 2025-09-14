package tdas;

public interface ColaPrioridadTDA {
    void InicializarCola(); // pre: no aplica.
    void AcolarPrioridad(int x, int prioridad); // pre: cola inicializada.
    void Desacolar(); // pre: cola inicializada y no vacía.
    boolean ColaVacia(); // pre: cola inicializada.
    int Primero(); // pre: cola inicializada y no vacía.
    int Prioridad(); // pre: cola inicializada y no vacía.
}
