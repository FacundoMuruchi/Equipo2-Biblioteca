package tdas.conjuntos;

public interface ConjuntoUsuariosTDA {
    void inicializarConjunto(); // pre: no aplica
    void agregar(int dni); // pre: conjunto inicializado
    int elegir(); // pre: conjunto inicializado y no vacio
    boolean conjuntoVacio(); // pre: conjunto inicializado
    void sacar(int dni); // pre: conjunto inicializado y no vacio
    boolean pertenece(int dni); // pre: conjunto inicializado
}
