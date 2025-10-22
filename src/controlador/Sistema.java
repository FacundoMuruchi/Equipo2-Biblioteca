package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import tdas.arboles.ABB;
import tdas.arboles.ArbolAVL;
import tdas.arboles.ArbolAVLTDA;
import tdas.arboles.NodoAVL;
import tdas.arboles.ArbolBinarioTDA;
import tdas.colas.ColaListaDeEsperaDinamica;
import tdas.colas.ColaListaDeEsperaTDA;
import tdas.conjuntos.ConjuntoLibrosLD;
import tdas.conjuntos.ConjuntoLibrosTDA;
import tdas.conjuntos.ConjuntoUsuariosTDA;
import tdas.diccionarios.DiccionarioSimpleUsuariosEstatico;
import tdas.diccionarios.DiccionarioSimpleUsuariosTDA;
import tdas.listas.ListaPrestamoEstatica;
import tdas.listas.ListaPrestamoTDA;

public class Sistema {
    ConjuntoLibrosTDA libros;
    ArbolBinarioTDA arbolLibros;
    DiccionarioSimpleUsuariosTDA usuarios;
    ListaPrestamoTDA prestamosActivos, prestamosTotales;
    ColaListaDeEsperaTDA pendientes;
    ArbolAVLTDA dnis;

    /**
     * constructor: inicializa estructuras
     */
    public Sistema() {
        libros = new ConjuntoLibrosLD();
        libros.inicializarConjunto();

        arbolLibros = new ABB();
        arbolLibros.inicializarArbol();

        usuarios = new DiccionarioSimpleUsuariosEstatico();
        usuarios.inicializarDiccionario();

        prestamosActivos = new ListaPrestamoEstatica();
        prestamosActivos.inicializarLista();

        prestamosTotales = new ListaPrestamoEstatica();
        prestamosTotales.inicializarLista();

        pendientes = new ColaListaDeEsperaDinamica();
        pendientes.inicializarCola();

        dnis = new ArbolAVL();
        dnis.inicializarArbol();
    }

    // METODOS DE LA BIBLIOTECA
    /**
     * registrar libro nuevo y añadirlo al conjunto de libros
     */
    public Libro agregarLibro(String titulo, String autor, int isbn, int copiasDisponibles) { // O(n)
        Libro libro = new Libro(titulo, autor, isbn, copiasDisponibles);
        if (libros.pertenece(isbn)) {
            System.out.println("No se puede añadir el libro '" + titulo + "' porque ya hay uno registrado con ISBN: " + isbn + "\n");
        }
        libros.agregar(libro);
        arbolLibros.agregarElem(libro);
        return libro;
    }

    /**
     * registrar usuario nuevo y agregarlo al diccionario
     */
    public Usuario registrarUsuario(int dni, String nombre, String apellido, String direccion, int telefono) { // O(n)
        Usuario usuario = new Usuario(dni, nombre, apellido, direccion, telefono);
        if (usuarios.claves().pertenece(dni)) {
            System.out.println("No se puede añadir al usuario '" + nombre + "' porque ya hay uno registrado con DNI: " + dni + "\n");
        } else {
            usuarios.agregar(dni, usuario);
            dnis.insertar(usuario);
        }
        return usuario;
    }

    /**
     * generar solicitud de prestamo. si está disponible, se agrega a la lista de prestamos activos y totales,
     * sino se acola en la cola de prestamos pendientes
     */
    public Prestamo solicitarPrestamo(Libro libro, Usuario usuario, String fechaDevolucion) { // O(n)
        Prestamo prestamo = new Prestamo(libro, usuario, fechaDevolucion);
        if (usuarios.recuperar(usuario.getDni()) == usuario) { // O(n)
            if (libro.getCopiasDisponibles() > 0) {
                prestamosActivos.agregarF(prestamo); // O(1)
                prestamosTotales.agregarF(prestamo);
                libro.bajarCantCopias();
            } else {
                pendientes.acolar(prestamo); // O(1)
            }
        } else {
            System.out.println("No puede solicitarse el prestamo porque '" + usuario.getNombre() + "' no está registrado \n");
            return null;
        }
        return prestamo;
    }

    /**
     * elimina el prestamo de la lista de prestamos activos,
     * luego agrega a la lista de prestamos al primer prestamo de la cola de pendientes
     */
    public void realizarDevolucion(Prestamo prestamo) { // O(n)
        if (prestamo != null) { // si el prestamo existe
            prestamosActivos.eliminar(prestamo); // O(n)
            if (!pendientes.colaVacia()) { // si la cola de pendientes no esta vacia O(1)
                prestamosActivos.agregarF(pendientes.primero()); // O(1)
                prestamosTotales.agregarF(pendientes.primero());
                pendientes.desacolar(); // O(1)
            }
            prestamo.getLibro().subirCantCopias();
            System.out.println("Devolucion: " + prestamo.getUsuario().getNombre() + " ha devuelto el libro: " + prestamo.getLibro().getTitulo() + "\n");
        } else {
            System.out.println("No puede realizarse la devolucion porque no existe ese prestamo\n");
        }
    }

    /**
     * buscar libro en base a su isbn
     * @param isbn ISBN a buscar
     */
    public void buscarLibro(int isbn) { // O(n)
        if (libros.pertenece(isbn)) { // O(n)
            Libro encontrado = buscarLibroRecursivo(arbolLibros, isbn);
            System.out.println("Libro encontrado: " + encontrado.getTitulo() + ", ISBN: " + encontrado.getIsbn());
        } else {
            System.out.println("Libro NO encontrado con ISBN: " + isbn);
        }
    }

    private Libro buscarLibroRecursivo(ArbolBinarioTDA a, int isbn) {
     if (a.raiz().getIsbn() == isbn)
         return a.raiz();
     else if (a.raiz().getIsbn() > isbn) {
         return buscarLibroRecursivo(a.hijoIzq(), isbn);
     } else
         return buscarLibroRecursivo(a.hijoDer(), isbn);
    }

    public void buscarUsuario(int dni) {
        if (usuarios.claves().pertenece(dni)) {
            Usuario encontrado = dnis.buscar(dni);
            System.out.println("Usuario encontrado: " + encontrado.getNombre() + " " + encontrado.getApellido() + " con DNI: " + dni);
        } else
            System.out.println("Usuario NO encontrado con DNI: " + dni);
    }

    // LISTAR LIBROS, USUARIOS, COLA DE ESPERA Y PRESTAMOS
    public void listarDevolucionesPendientes() {
        System.out.println("--- DEVOLUCIONES PENDIENTES ---");
        prestamosActivos.mostrar();
        System.out.println();
    }

    public void listarLibros() {
        System.out.println("--- LIBROS ---");


        ConjuntoLibrosTDA aux = copiarConjuntoLibros();

        while (!aux.conjuntoVacio()) {
            Libro libroAzar = aux.elegir(); // se elige un libro al azar
            System.out.println("Titulo: " + libroAzar.getTitulo() + ", Autor: " + libroAzar.getAutor() + ", ISBN: " + libroAzar.getIsbn() + ", Copias: " + libroAzar.getCopiasDisponibles());
            aux.sacar(libroAzar.getIsbn());
        }
        System.out.println();
    }

    public void listarUsuarios() {
        System.out.println("--- USUARIOS ---");
        ConjuntoUsuariosTDA dnisAux = usuarios.claves();

        while (!dnisAux.conjuntoVacio()) {

            int dniAzar = dnisAux.elegir(); // elegir clave al azar
            Usuario usuarioAzar = usuarios.recuperar(dniAzar); // elegir usuario de la respectiva clave

            System.out.println("DNI: " + dniAzar +
                    ", Usuario: " + usuarioAzar.getNombre() +
                    " " + usuarioAzar.getApellido() +
                    ", Direccion: " + usuarioAzar.getDireccion() +
                    ", Telefono: " + usuarioAzar.getTelefono());

            dnisAux.sacar(dniAzar);
        }
        System.out.println();
    }

    public void listarUsuariosOrdenados() {
        System.out.println("--- USUARIOS ORDENADOS ---");
        recorridoInordenRecursivo(dnis.raiz());
        System.out.println();
    }

    public void mostrarColaDeEspera() {
        ColaListaDeEsperaTDA aux = copiarCola();

        System.out.println("--- COLA DE ESPERA ---");
        System.out.println("(Primero arriba)");

        while (!aux.colaVacia()){
            System.out.println("Usuario: " + aux.primero().getUsuario().getNombre() + " " + aux.primero().getUsuario().getApellido() + ", Libro deseado: " + aux.primero().getLibro().getTitulo());
            aux.desacolar();
        }
        System.out.println();
    } // O(n)

    public void listarTodosPrestamos() {
        System.out.println("--- TODOS LOS PRESTAMOS ---");
        prestamosTotales.mostrar();
        System.out.println();
    }

    public void mostrarLibrosOrdenados() {
        System.out.println("--- LIBROS ORDENADOS POR ISBN ---");
        inOrder(arbolLibros);
        System.out.println();
    }

    private void inOrder(ArbolBinarioTDA a) {
        if (!a.arbolVacio()) {
            inOrder(a.hijoIzq());
            System.out.println("Titulo: " + a.raiz().getTitulo() + ", ISBN: " + a.raiz().getIsbn());
            inOrder(a.hijoDer());
        }
    }

    private void recorridoInordenRecursivo(NodoAVL nodo) {
        if (nodo != null) {
            recorridoInordenRecursivo(nodo.subIzq);
            System.out.println("Usuario: " + nodo.dato.getNombre() + " " + nodo.dato.getApellido() + ", DNI: " + nodo.dato.getDni());
            recorridoInordenRecursivo(nodo.subDer);
        }
    }

    // COPIAR ESTRUCTURAS
    private ConjuntoLibrosTDA copiarConjuntoLibros() { // O(n)
        ConjuntoLibrosTDA aux = new ConjuntoLibrosLD();
        aux.inicializarConjunto();

        ConjuntoLibrosTDA copia = new ConjuntoLibrosLD();
        copia.inicializarConjunto();

        Libro azar;

        while (!libros.conjuntoVacio()) {
            azar = libros.elegir();
            aux.agregar(azar);
            libros.sacar(azar.getIsbn());
        }

        while (!aux.conjuntoVacio()) {
            azar = aux.elegir();
            libros.agregar(azar);
            copia.agregar(azar);
            aux.sacar(azar.getIsbn());
        }
        return copia;
    } // O(n)

    private ColaListaDeEsperaTDA copiarCola() {
        ColaListaDeEsperaTDA aux = new ColaListaDeEsperaDinamica();
        aux.inicializarCola();

        ColaListaDeEsperaTDA copia = new ColaListaDeEsperaDinamica();
        copia.inicializarCola();

        Prestamo primero;

        while (!pendientes.colaVacia()) {
            primero = pendientes.primero();
            pendientes.desacolar();
            aux.acolar(primero);
        }

        while (!aux.colaVacia()) {
            primero = aux.primero();
            aux.desacolar();
            pendientes.acolar(primero);
            copia.acolar(primero);
        }
        return copia;
    } // O(n)
}
