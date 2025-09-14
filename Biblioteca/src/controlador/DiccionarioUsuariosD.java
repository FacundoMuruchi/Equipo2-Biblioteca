package controlador;

import entidades.Usuario;
import entidades.Prestamo;
import tdas.DiccionarioUsuariosTDA;

public class DiccionarioUsuariosD implements DiccionarioUsuariosTDA {

    private class NodoClave {
        int dni;
        Usuario usuario;
        NodoClave sig;
    }

    private NodoClave primero;

    @Override
    public void inicializarDiccionario() {
        primero = null;
    }

    @Override
    public void agregarUsuario(int dni, String nombre) {
        if (!usuarioExiste(dni)) {
            NodoClave nuevo = new NodoClave();
            nuevo.dni = dni;
            nuevo.usuario = new Usuario(dni, nombre);
            nuevo.sig = primero;
            primero = nuevo;
        }
    }

    @Override
    public void eliminarUsuario(int dni) {
        if (primero != null) {
            if (primero.dni == dni) {
                primero = primero.sig;
            } else {
                NodoClave aux = primero;
                while (aux.sig != null && aux.sig.dni != dni) {
                    aux = aux.sig;
                }
                if (aux.sig != null) {
                    aux.sig = aux.sig.sig;
                }
            }
        }
    }

    @Override
    public boolean usuarioExiste(int dni) {
        return buscarNodo(dni) != null;
    }

    @Override
    public Usuario obtenerUsuario(int dni) {
        NodoClave nodo = buscarNodo(dni);
        return (nodo != null) ? nodo.usuario : null;
    }

    @Override
    public void agregarPrestamo(int dni, Prestamo p) {
        Usuario u = obtenerUsuario(dni);
        if (u != null) {
            u.agregarPrestamo(p);
        }
    }

    @Override
    public void eliminarPrestamo(int dni, Prestamo p) {
        Usuario u = obtenerUsuario(dni);
        if (u != null) {
            u.eliminarPrestamo(p);
        }
    }

    @Override
    public Prestamo[] obtenerPrestamos(int dni) {
        Usuario u = obtenerUsuario(dni);
        return (u != null) ? u.getPrestamos() : new Prestamo[0];
    }

    // ---- auxiliar ----
    private NodoClave buscarNodo(int dni) {
        NodoClave aux = primero;
        while (aux != null && aux.dni != dni) {
            aux = aux.sig;
        }
        return aux;
    }
}
