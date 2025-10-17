package tdas.conjuntos;

// ALMACENAR DNI DE USUARIOS

public class ConjuntoUsuariosEstatico implements ConjuntoUsuariosTDA {
    int[] a; // contenido del conjunto
    int cant; // cantidad de elementos

    @Override
    public void inicializarConjunto() {
        a = new int[100];
        cant = 0;
    }

    @Override
    public void agregar(int dni) {
        if (!this.pertenece(dni)){
            a[cant] = dni;
            cant++;
        }
    }

    @Override
    public int elegir() {
        return a[cant-1];
    }

    @Override
    public boolean conjuntoVacio() {
        return (cant == 0);
    }

    @Override
    public void sacar(int dni) {
        int i = 0;
        // tapar agujero del vector en i
        while (i < cant && a[i] != dni){
            i++;
        }
        if (i < cant){ // elemento encontrado
            a[i] = a[cant-1];
            cant--;
        }
    }

    @Override
    public boolean pertenece(int dni) {
        int i = 0;
        while (i<cant && a[i] != dni){
            i++;
        }
        return (i<cant);
    }
}
