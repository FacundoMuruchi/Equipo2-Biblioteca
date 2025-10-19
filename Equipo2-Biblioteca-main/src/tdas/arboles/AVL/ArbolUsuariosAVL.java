package tdas.arboles.AVL;

import entidades.Usuario;

public class ArbolUsuariosAVL implements ArbolAVLTDA {

    
    public class Nodo {
        public Usuario usuario; // Dato almacenado
        Nodo izquierdo;
        Nodo derecho;
        int altura;

        Nodo(Usuario usuario) {
            this.usuario = usuario;
            this.izquierdo = null;
            this.derecho = null;
            this.altura = 1; // altura inicial
        }
    }

    
    private Nodo raiz;

    
    public ArbolUsuariosAVL() {
        this.raiz = null;
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        raiz = insertarRecursivo(raiz, usuario);
    }

    private Nodo insertarRecursivo(Nodo nodo, Usuario usuario) {
        // Caso base: si el nodo es nulo, creamos uno nuevo
        if (nodo == null) {
            return new Nodo(usuario);
        }

        //Comparar apellidos (alfabéticamente, sin usar compareTo)
        String apellidoNuevo = usuario.getApellido().toLowerCase();
        String apellidoNodo = nodo.usuario.getApellido().toLowerCase();

        // Comparación carácter a carácter
        int i = 0;
        while (i < apellidoNuevo.length() && i < apellidoNodo.length() &&
                       apellidoNuevo.charAt(i) == apellidoNodo.charAt(i)) {
            i++;
        }

        boolean esMenor = false;
        boolean esMayor = false;

        if (i < apellidoNuevo.length() && i < apellidoNodo.length()) {
            if (apellidoNuevo.charAt(i) < apellidoNodo.charAt(i)) {
                esMenor = true;
            } else if (apellidoNuevo.charAt(i) > apellidoNodo.charAt(i)) {
                esMayor = true;
            }
        } else if (apellidoNuevo.length() < apellidoNodo.length()) {
            esMenor = true; // ejemplo: “paz” < “pazos”
        } else if (apellidoNuevo.length() > apellidoNodo.length()) {
            esMayor = true;
        }

        // Insertar recursivamente
        if (esMenor) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, usuario);
        } else if (esMayor) {
            nodo.derecho = insertarRecursivo(nodo.derecho, usuario);
        } else {
            // Si el apellido es igual, no se inserta (para evitar duplicados)
            return nodo;
        }

        // Actualizar altura del nodo actual
        nodo.altura = 1 + Math.max(getAltura(nodo.izquierdo), getAltura(nodo.derecho));

        //  Calcular balance y aplicar rotaciones si es necesario
        int balance = getBalance(nodo);

        // Caso Izquierda-Izquierda
        if (balance > 1 && esMenor) {
            return rotacionDerecha(nodo);
        }

        // Caso Derecha-Derecha
        if (balance < -1 && esMayor) {
            return rotacionIzquierda(nodo);
        }

        // Caso Izquierda-Derecha
        if (balance > 1 && esMayor) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }

        // Caso Derecha-Izquierda
        if (balance < -1 && esMenor) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo; // sin cambios
    }

    // Implementación del método buscar(String apellido) de la interfaz ArbolAVLTDA
    @Override
    public Nodo buscar(String apellido) {
        return buscarRecursivo(raiz, apellido.toLowerCase());
    }

    private Nodo buscarRecursivo(Nodo nodo, String apellidoBuscado) {
        if (nodo == null) {
            return null;
        }

        String apellidoNodo = nodo.usuario.getApellido().toLowerCase();

        if (apellidoBuscado.equals(apellidoNodo)) {
            return nodo;
        }

        // comparar sin usar compareTo
        int i = 0;
        while (i < apellidoBuscado.length() && i < apellidoNodo.length() &&
                       apellidoBuscado.charAt(i) == apellidoNodo.charAt(i)) {
            i++;
        }

        if (i < apellidoBuscado.length() && i < apellidoNodo.length()) {
            if (apellidoBuscado.charAt(i) < apellidoNodo.charAt(i)) {
                return buscarRecursivo(nodo.izquierdo, apellidoBuscado);
            } else {
                return buscarRecursivo(nodo.derecho, apellidoBuscado);
            }
        } else if (apellidoBuscado.length() < apellidoNodo.length()) {
            return buscarRecursivo(nodo.izquierdo, apellidoBuscado);
        } else if (apellidoBuscado.length() > apellidoNodo.length()) {
            return buscarRecursivo(nodo.derecho, apellidoBuscado);
        }

        return null;
    }


    @Override
    public void recorridoInorden() {
        recorridoInordenRecursivo(raiz);
    }

    private void recorridoInordenRecursivo(Nodo nodo) {
        if (nodo != null) {
            recorridoInordenRecursivo(nodo.izquierdo);
            System.out.println("Apellido: " + nodo.usuario.getApellido() + ", DNI: " + nodo.usuario.getDni());
            recorridoInordenRecursivo(nodo.derecho);
        }
    }

    private int getAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            return nodo.altura;
        }
    }

    private int getBalance(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            return getAltura(nodo.izquierdo) - getAltura(nodo.derecho);
        }
    }

    private Nodo rotacionDerecha(Nodo y) {
        Nodo x = y.izquierdo;
        Nodo T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        y.altura = Math.max(getAltura(y.izquierdo), getAltura(y.derecho)) + 1;
        x.altura = Math.max(getAltura(x.izquierdo), getAltura(x.derecho)) + 1;

        return x;
    }

    private Nodo rotacionIzquierda(Nodo x) {
        Nodo y = x.derecho;
        Nodo T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        x.altura = Math.max(getAltura(x.izquierdo), getAltura(x.derecho)) + 1;
        y.altura = Math.max(getAltura(y.izquierdo), getAltura(y.derecho)) + 1;

        return y;
    }

    // Método auxiliar público para buscar por DNI (no está en la interfaz)

    public Usuario buscarPorDni(int dni) {
        Nodo resultado = buscarPorDniRecursivo(raiz, dni);
        if (resultado != null) {
            return resultado.usuario;
        } else {
            return null;
        }
    }

    private Nodo buscarPorDniRecursivo(Nodo nodo, int dni) {
        if (nodo == null) {
            return null;
        }
        
        if (dni == nodo.usuario.getDni()) {
            return nodo;
        }
        
        // Recorre subárbol izquierdo
        Nodo encontrado = buscarPorDniRecursivo(nodo.izquierdo, dni);
        if (encontrado != null) {
            return encontrado;
        }
        
        // Recorre subárbol derecho
        return buscarPorDniRecursivo(nodo.derecho, dni);
    }
}