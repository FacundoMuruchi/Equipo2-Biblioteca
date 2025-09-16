package tdas.diccionarios;

import entidades.Usuario;
import tdas.conjuntos.ConjuntoUsuariosEstatico;
import tdas.conjuntos.ConjuntoUsuariosTDA;

public class DiccionarioSimpleUsuariosEstatico implements DiccionarioSimpleUsuariosTDA {
    private class Elemento {
        int clave;
        Usuario valor;
    }
	private Elemento[] elementos;
	private int cant;

	public void inicializarDiccionario() {
		elementos = new Elemento[100];
		cant = 0;
	}

	public void agregar(int clave, Usuario valor) {
		int pos = Clave2Ind(clave);
		if (pos == -1) { // La clave no existe
			pos = cant; // Nueva entrada
			elementos[pos] = new Elemento();
			elementos[pos].clave = clave;
			cant++;
		}
		// elementos[pos].valor = valor;
		Elemento elem;
		elem= elementos[pos];
		elem.valor= valor;
	}

	private int Clave2Ind(int clave) {
		int i = cant - 1;
		while (i >= 0 && elementos[i].clave != clave)
			i--;
		return i;
	}

	public void eliminar(int clave) {
		int pos = Clave2Ind(clave);
		if (pos != -1) { // La clave existe
			elementos[pos] = elementos[cant - 1];
			cant--;
		}
	}

	public Usuario recuperar(int clave) {
		int pos = Clave2Ind(clave);
		return elementos[pos].valor;
	}

	public ConjuntoUsuariosTDA claves() {
		ConjuntoUsuariosTDA c = new ConjuntoUsuariosEstatico();
		c.inicializarConjunto();
		for (int i = 0; i < cant; i++)
			c.agregar(elementos[i].clave);
		return c;
	}
}
