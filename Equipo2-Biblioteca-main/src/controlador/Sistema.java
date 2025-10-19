package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import tdas.arboles.ABB;
import tdas.arboles.ArbolBinarioTDA;
import tdas.arboles.AVL.ArbolUsuariosAVL;
import tdas.arboles.AVL.ArbolUsuariosAVL.Nodo;
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
    private ArbolUsuariosAVL indiceUsuarios;

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

        // Inicialización de AVL. No se llama a 'inicializarArbol' en un TDA basado en nodos.
        indiceUsuarios = new ArbolUsuariosAVL();
    }

    /**
     * registrar libro nuevo y añadirlo al conjunto de libros
     */
    public Libro agregarLibro(String titulo, String autor, int isbn, int copiasDisponibles) { // O(n)
        Libro libro = new Libro(titulo, autor, isbn, copiasDisponibles);
        if (libros.pertenece(isbn)) { // O(n) o similar, dependiendo de la implementación de Conjunto
            System.out.println("No se puede añadir el libro '" + titulo + "' porque ya hay uno registrado con ISBN: " + isbn + "\n");
            return null; // Devolver null si no se agrega
        }
        libros.agregar(libro);
        arbolLibros.agregarElem(libro);
        return libro;
    }

    /**
     * registrar usuario nuevo y agregarlo al diccionario (por DNI) y al AVL (por Apellido).
     */
    public Usuario registrarUsuario(int dni, String nombre, String apellido, String direccion, int telefono) { // O(log n) + O(n)
        Usuario usuario = new Usuario(dni, nombre, apellido, direccion, telefono);
        
        // 1. Verificar unicidad por DNI en el Diccionario (O(n) o más rápido, dependiendo de la TDA)
        if (usuarios.claves().pertenece(dni)) { 
            System.out.println("No se puede añadir al usuario '" + nombre + "' porque ya hay uno registrado con DNI: " + dni + "\n");
        } else {
            // 2. Insertar en el Diccionario (registro principal por DNI)
            usuarios.agregar(dni, usuario); 
            
            // 3. Insertar en el Árbol AVL (índice ordenado por Apellido)
            // Asumo que el método en ArbolUsuariosAVL se llama 'insertarUsuario'
            indiceUsuarios.insertarUsuario(usuario); 
            System.out.println("Usuario " + nombre + " " + apellido + " registrado con éxito.");
        }
        return usuario;
    }

    /**
     * Genera solicitud de prestamo. si está disponible, se agrega a la lista de prestamos activos y totales,
     * sino se acola en la cola de prestamos pendientes
     */
    public Prestamo solicitarPrestamo(Libro libro, Usuario usuario, String fechaDevolucion) { // O(n)
        Prestamo prestamo = new Prestamo(libro, usuario, fechaDevolucion);
        
        // Verifica si el usuario existe en el Diccionario por DNI.
        // Se asume que DiccionarioSimpleUsuariosEstatico.recuperar() devuelve null si no existe
        if (usuarios.recuperar(usuario.getDni()) != null) { // O(n)
            if (libro.getCopiasDisponibles() > 0) {
                prestamosActivos.agregarF(prestamo); // O(1)
                prestamosTotales.agregarF(prestamo);
                libro.bajarCantCopias();
                System.out.println("Préstamo concedido a " + usuario.getNombre() + " para el libro " + libro.getTitulo() + ".");
            } else {
                pendientes.acolar(prestamo); // O(1)
                System.out.println("Libro no disponible. Solicitud de " + usuario.getNombre() + " agregada a la cola de espera.");
            }
        } else {
            System.out.println("No puede solicitarse el prestamo porque '" + usuario.getNombre() + "' no está registrado \n");
            return null;
        }
        return prestamo;
    }

    /**
     * Elimina el prestamo de la lista de prestamos activos,
     * luego agrega a la lista de prestamos al primer prestamo de la cola de pendientes
     */
    public void realizarDevolucion(Prestamo prestamo) { // O(n)
        if (prestamo != null) { // si el prestamo existe
            prestamosActivos.eliminar(prestamo); // O(n)
            prestamo.getLibro().subirCantCopias();
            
            if (!pendientes.colaVacia()) { // si la cola de pendientes no esta vacia O(1)
                Prestamo nuevoPrestamo = pendientes.primero();
                pendientes.desacolar(); // O(1)
                
                // Se registra el nuevo préstamo activo (del usuario en espera)
                prestamosActivos.agregarF(nuevoPrestamo); // O(1)
                prestamosTotales.agregarF(nuevoPrestamo);
                nuevoPrestamo.getLibro().bajarCantCopias();
                
                System.out.println("Devolucion de " + prestamo.getUsuario().getNombre() + " realizada. ");
                System.out.println("Préstamo inmediato concedido a " + nuevoPrestamo.getUsuario().getNombre() + " (de la cola de espera). \n");
            } else {
                 System.out.println("Devolucion: " + prestamo.getUsuario().getNombre() + " ha devuelto el libro: " + prestamo.getLibro().getTitulo() + "\n");
            }
        } else {
            System.out.println("No puede realizarse la devolucion porque no existe ese prestamo\n");
        }
    }

    /**
     * Busca libro en base a su isbn
     * @param isbn ISBN a buscar
     */
    public void buscarLibro(int isbn) { // O(log n) - Asumiendo que buscarLibroRecursivo es log(n) en un ABB
        if (libros.pertenece(isbn)) { // O(n) o similar
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
    
    /**
     * Busca un usuario por su DNI (Acceso rápido por clave en el Diccionario).
     */
    public Usuario buscarUsuarioPorDni(int dni) {
        Usuario usuario = usuarios.recuperar(dni);
        if (usuario != null) {
            System.out.println("Usuario encontrado por DNI: " + usuario.getNombre() + " " + usuario.getApellido());
        } else {
            System.out.println("Usuario NO encontrado con DNI: " + dni);
        }
        return usuario;
    }
    
    /**
     * Busca un usuario por su Apellido (Búsqueda O(log n) en el Árbol AVL).
     */
    public Usuario buscarUsuarioPorApellido(String apellido) {
        // Asumo que el método en ArbolUsuariosAVL se llama 'buscar' y retorna un Nodo
        Nodo nodoEncontrado = indiceUsuarios.buscar(apellido);
        
        if (nodoEncontrado != null) {
            Usuario usuario = nodoEncontrado.usuario;
            System.out.println("Usuario encontrado por Apellido: " + usuario.getNombre() + " " + usuario.getApellido());
            return usuario;
        } else {
            System.out.println("Usuario NO encontrado con apellido: " + apellido);
            return null;
        }
    }

    // LISTAR ESTRUCTURAS
    
    public void listarUsuariosOrdenados() {
        System.out.println("--- USUARIOS ORDENADOS POR APELLIDO (Usando AVL) ---");
        indiceUsuarios.recorridoInorden(); // Llamo al método recorridoInorden de la clase AVL
        System.out.println();
    }
    
    // El método listarUsuarios() original ya no lista ordenado
    public void listarUsuarios() {
        System.out.println("--- USUARIOS (Sin Orden Específico) ---");
        ConjuntoUsuariosTDA dnisAux = usuarios.claves(); // Obtiene el conjunto de DNI's del diccionario

        while (!dnisAux.conjuntoVacio()) {

            int dniAzar = dnisAux.elegir(); // elegir clave al azar
            Usuario usuarioAzar = usuarios.recuperar(dniAzar); // elegir usuario de la respectiva clave

            System.out.println("DNI: " + dniAzar +
                    ", Nombre: " + usuarioAzar.getNombre() +
                    ", Apellido: " + usuarioAzar.getApellido() +
                    ", Direccion: " + usuarioAzar.getDireccion() +
                    ", Telefono: " + usuarioAzar.getTelefono());

            dnisAux.sacar(dniAzar);
        }
        System.out.println();
    }

    public void listarDevolucionesPendientes() {
        System.out.println("--- DEVOLUCIONES PENDIENTES ---");
        prestamosActivos.mostrar();
        System.out.println();
    }

    public void listarLibros() {
        System.out.println("--- LIBROS ---");
        // Se mantiene la lógica original de copiar el conjunto para listar sin modificar el original.
        ConjuntoLibrosTDA aux = copiarConjuntoLibros(); 

        while (!aux.conjuntoVacio()) {
            Libro libroAzar = aux.elegir(); // se elige un libro al azar
            System.out.println("Titulo: " + libroAzar.getTitulo() + ", Autor: " + libroAzar.getAutor() + ", ISBN: " + libroAzar.getIsbn() + ", Copias: " + libroAzar.getCopiasDisponibles());
            aux.sacar(libroAzar.getIsbn());
        }
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