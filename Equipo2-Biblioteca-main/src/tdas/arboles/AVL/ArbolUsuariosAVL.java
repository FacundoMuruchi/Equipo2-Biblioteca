package tdas.arboles.AVL;

import entidades.Usuario;

public class ArbolUsuariosAVL {
    private class NodoAVL {
        Usuario usuario;
        NodoAVL izquierdo;
        NodoAVL derecho;
        int altura;
    }

    private NodoAVL raiz;

    public void inicializarArbol() {
        raiz = null;
    }

    private int altura(NodoAVL n) {
        return (n == null) ? 0 : n.altura;
    }

    private int balanceFactor(NodoAVL n) {
        return (n == null) ? 0 : altura(n.izquierdo) - altura(n.derecho);
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL temp = x.derecho;

        x.derecho = y;
        y.izquierdo = temp;

        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;

        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL temp = y.izquierdo;

        y.izquierdo = x;
        x.derecho = temp;

        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;

        return y;
    }

    public void insertar(Usuario usuario) {
        raiz = insertarRec(raiz, usuario);
    }

    private NodoAVL insertarRec(NodoAVL nodo, Usuario usuario) {
        if (nodo == null) {
            NodoAVL nuevo = new NodoAVL();
            nuevo.usuario = usuario;
            nuevo.altura = 1;
            return nuevo;
        }

        // Comparación robusta por apellido
        String[] partes1 = nodo.usuario.getNombre().trim().split(" ");
        String[] partes2 = usuario.getNombre().trim().split(" ");
        String ap1 = (partes1.length > 1) ? partes1[partes1.length - 1] : partes1[0];
        String ap2 = (partes2.length > 1) ? partes2[partes2.length - 1] : partes2[0];

        int cmp = ap2.compareToIgnoreCase(ap1);

        if (cmp < 0)
            nodo.izquierdo = insertarRec(nodo.izquierdo, usuario);
        else if (cmp > 0)
            nodo.derecho = insertarRec(nodo.derecho, usuario);
        else
            return nodo; // apellido duplicado (ignorar)

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));

        int balance = balanceFactor(nodo);

        // Rotaciones necesarias
        if (balance > 1 && ap2.compareToIgnoreCase(ap1) < 0)
            return rotacionDerecha(nodo);
        if (balance < -1 && ap2.compareToIgnoreCase(ap1) > 0)
            return rotacionIzquierda(nodo);
        if (balance > 1 && ap2.compareToIgnoreCase(ap1) > 0) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        if (balance < -1 && ap2.compareToIgnoreCase(ap1) < 0) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // 🔍 Nueva funcionalidad: buscar usuario por apellido
    public Usuario buscarPorApellido(String apellido) {
        return buscarPorApellidoRec(raiz, apellido.trim().toLowerCase());
    }

    private Usuario buscarPorApellidoRec(NodoAVL nodo, String apellidoBuscado) {
        if (nodo == null) return null;

        String[] partes = nodo.usuario.getNombre().trim().split(" ");
        String apellidoNodo = (partes.length > 1) ? partes[partes.length - 1].toLowerCase() : partes[0].toLowerCase();

        int cmp = apellidoBuscado.compareTo(apellidoNodo);

        if (cmp == 0)
            return nodo.usuario;
        else if (cmp < 0)
            return buscarPorApellidoRec(nodo.izquierdo, apellidoBuscado);
        else
            return buscarPorApellidoRec(nodo.derecho, apellidoBuscado);
    }

    public void listarUsuariosOrdenados() {
        System.out.println("--- USUARIOS ORDENADOS POR APELLIDO ---");
        inOrder(raiz);
        System.out.println();
    }

    private void inOrder(NodoAVL nodo) {
        if (nodo != null) {
            inOrder(nodo.izquierdo);
            System.out.println("DNI: " + nodo.usuario.getDni() +
                    ", Nombre: " + nodo.usuario.getNombre() +
                    ", Dirección: " + nodo.usuario.getDireccion());
            inOrder(nodo.derecho);
        }
    }
}
