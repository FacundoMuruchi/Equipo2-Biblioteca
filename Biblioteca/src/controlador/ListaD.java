package controlador;

import entidades.Contacto;
import entidades.Prestamo;
import tdas.ListaTDA;

public class ListaD implements ListaTDA {

    // Clase interna Nodo
    private class Nodo {
        Contacto info;
        Nodo sig;
    }

    private Nodo inicio;

    @Override
    public void InicializarLista() {
        inicio = null;
    }

    @Override
    public void AgregarI(Contacto c) {
        Nodo nuevo = new Nodo();
        nuevo.info = c;
        nuevo.sig = inicio;
        inicio = nuevo;
    }

    public void AgregarF(Contacto c) {
        Nodo nuevo = new Nodo();
        nuevo.info = c;
        nuevo.sig = null;

        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo aux = inicio;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }
    }

    public void AgregarO(Contacto c) {
        Nodo nuevo = new Nodo();
        nuevo.info = c;

        if (inicio == null || c.getDni() < inicio.info.getDni()) {
            nuevo.sig = inicio;
            inicio = nuevo;
        } else {
            Nodo aux = inicio;
            while (aux.sig != null && aux.sig.info.getDni() < c.getDni()) {
                aux = aux.sig;
            }
            nuevo.sig = aux.sig;
            aux.sig = nuevo;
        }
    }

    @Override
    public void Eliminar(int dni) {
        if (inicio == null) return;

        if (inicio.info.getDni() == dni) {
            inicio = inicio.sig;
        } else {
            Nodo aux = inicio;
            while (aux.sig != null && aux.sig.info.getDni() != dni) {
                aux = aux.sig;
            }
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;
            }
        }
    }

    @Override
    public Contacto Buscar(int dni) {
        Nodo aux = inicio;
        while (aux != null) {
            if (aux.info.getDni() == dni) {
                return aux.info;
            }
            aux = aux.sig;
        }
        return null;
    }

    public ListaTDA BuscarEdad(int edad) {
        ListaD listaEdad = new ListaD();
        listaEdad.InicializarLista();

        Nodo aux = inicio;
        while (aux != null) {
            if (aux.info.getEdad() == edad) {
                listaEdad.AgregarF(aux.info);
            }
            aux = aux.sig;
        }
        return listaEdad;
    }

    // Método opcional para depurar
    public void Imprimir() {
        Nodo aux = inicio;
        while (aux != null) {
            System.out.println(aux.info);
            aux = aux.sig;
        }
    }

	@Override
	public void AgregarI(Prestamo c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AgregarF(Prestamo c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AgregarO(Prestamo c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Mostrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Ordenar() {
		// TODO Auto-generated method stub
		
	}
}
