package controlador;
import tdas.ColaPrioridadTDA;

public class ColaPrioridadLD implements ColaPrioridadTDA {
    class NodoPrioridad { // la célula de la estructura
        int info; // el valor almacenado
        int prioridad; // la prioridad
        NodoPrioridad sig; // la referencia al siguiente nodo
    }

    NodoPrioridad primero; // primer elemento (prioritario)

    public void InicializarCola() {
        primero = null;
    }

    public void AcolarPrioridad(int x, int prioridad) {
        NodoPrioridad nuevo = new NodoPrioridad(); // el nuevo nodo que se acolará
        nuevo.info = x;
        nuevo.prioridad = prioridad;

        if (primero == null || primero.prioridad > prioridad) {
            nuevo.sig = primero; // el nodo debe ir al principio
            primero = nuevo;
        } else {
            NodoPrioridad aux = primero; // buscamos la posición
            while (aux.sig != null && aux.sig.prioridad <= prioridad) {
                aux = aux.sig;
            }
            nuevo.sig = aux.sig;
            aux.sig = nuevo;
        }
    }

    public void Desacolar() {
        primero = primero.sig; // nueva referencia a la estructura
    }

    public boolean ColaVacia() {
        return (primero == null);
    }

    public int Primero() {
        return primero.info;
    }

    public int Prioridad() {
        return primero.prioridad;
    }
}