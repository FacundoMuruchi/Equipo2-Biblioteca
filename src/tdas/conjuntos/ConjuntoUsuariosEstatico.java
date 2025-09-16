package tdas.conjuntos;

public class ConjuntoUsuariosEstatico implements ConjuntoUsuariosTDA {
    int[] a; // contenido del conjunto
    int cant; // cantidad de elementos

    @Override
    public void inicializarConjunto() {
        a = new int[100];
        cant = 0;
    }

    @Override
    public void agregar(int x) {
        if (!this.pertenece(x)){
            a[cant] = x;
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
    public void sacar(int x) {
        int i = 0;
        // tapar agujero del vector en i
        while (i < cant && a[i] != x){
            i++;
        }
        if (i < cant){ // elemento encontrado
            a[i] = a[cant-1];
            cant--;
        }
    }

    @Override
    public boolean pertenece(int x) {
        int i = 0;
        while (i<cant && a[i] != x){
            i++;
        }
        return (i<cant);
    }
}
