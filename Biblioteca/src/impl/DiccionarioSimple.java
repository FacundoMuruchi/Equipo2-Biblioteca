package impl;

import tdas.DiccionarioSimpleTDA;

public class DiccionarioSimple implements DiccionarioSimpleTDA {
    private class Elemento {
        int clave;
        Object valor;
    }

    private Elemento[] elementos;
    private int cant;

    public void InicializarDiccionario() {
        elementos = new Elemento[100]; // capacidad fija (se puede mejorar con redimensionamiento)
        cant = 0;
    }

    public void Agregar(int clave, Object valor) {
        int pos = -1;
        for (int i = 0; i < cant; i++) {
            if (elementos[i].clave == clave) {
                pos = i;
                break;
            }
        }

        if (pos == -1) { // no existe → lo agrego
            Elemento nuevo = new Elemento();
            nuevo.clave = clave;
            nuevo.valor = valor;
            elementos[cant] = nuevo;
            cant++;
        } else { // ya existe → lo actualizo
            elementos[pos].valor = valor;
        }
    }

    public void Eliminar(int clave) {
        int pos = -1;
        for (int i = 0; i < cant; i++) {
            if (elementos[i].clave == clave) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            elementos[pos] = elementos[cant - 1]; // reemplazo con el último
            cant--;
        }
    }

    public Object Recuperar(int clave) {
        for (int i = 0; i < cant; i++) {
            if (elementos[i].clave == clave) {
                return elementos[i].valor;
            }
        }
        return null;
    }

    public boolean ClaveExiste(int clave) {
        for (int i = 0; i < cant; i++) {
            if (elementos[i].clave == clave) {
                return true;
            }
        }
        return false;
    }
}
