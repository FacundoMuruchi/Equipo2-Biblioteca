package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
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
    DiccionarioSimpleUsuariosTDA usuarios;
    ListaPrestamoTDA prestamosActivos, prestamosTotales;
    ColaListaDeEsperaTDA pendientes;

    /**
     * constructor: inicializa estructuras
     */
    public Sistema() {
        libros = new ConjuntoLibrosLD();
        libros.inicializarConjunto();

        usuarios = new DiccionarioSimpleUsuariosEstatico();
        usuarios.inicializarDiccionario();

        prestamosActivos = new ListaPrestamoEstatica();
        prestamosActivos.inicializarLista();

        prestamosTotales = new ListaPrestamoEstatica();
        prestamosTotales.inicializarLista();

        pendientes = new ColaListaDeEsperaDinamica();
        pendientes.inicializarCola();
    }

    // METODOS DE LA BIBLIOTECA
    /**
     * registrar libro nuevo y añadirlo al conjunto de libros
     */
    public Libro agregarLibro(String titulo, String autor, int isbn, int copiasDisponibles) {
        Libro libro = new Libro(titulo, autor, isbn, copiasDisponibles);
        if (libros.pertenece(isbn)) {
            System.out.println("No se puede añadir el libro '" + titulo + "' porque ya hay uno registrado con ISBN: " + isbn + "\n");
        }
        libros.agregar(libro);
        return libro;
    }

    /**
     * registrar usuario nuevo y agregarlo al diccionario
     */
    public Usuario registrarUsuario(int dni, String nombre, String direccion, int telefono) {
        Usuario usuario = new Usuario(dni, nombre, direccion, telefono);
        if (usuarios.claves().pertenece(dni)) {
            System.out.println("No se puede añadir al usuario '" + nombre + "' porque ya hay uno registrado con DNI: " + dni + "\n");
        } else {
            usuarios.agregar(dni, usuario);
        }
        return usuario;
    }

    /**
     * generar solicitud de prestamo. si está disponible, se agrega a la lista de prestamos y a la lista personal del usuario correspondiente,
     * sino se acola en la cola de prestamos pendientes
     */
    public Prestamo solicitarPrestamo(Libro libro, Usuario usuario, String fechaDevolucion) {
        Prestamo prestamo = new Prestamo(libro, usuario, fechaDevolucion);
        if (usuarios.recuperar(usuario.getDni()) == usuario) {
            if (libro.getCopiasDisponibles() > 0) {
                prestamosActivos.agregarF(prestamo);
                prestamosTotales.agregarF(prestamo);
                libro.bajarCantCopias();
            } else {
                pendientes.acolar(prestamo);
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
    public void realizarDevolucion(Prestamo prestamo) {
        if (prestamo != null) { // si el prestamo existe
            prestamosActivos.eliminar(prestamo);
            if (!pendientes.colaVacia()) { // si la cola de pendientes no esta vacia
                prestamosActivos.agregarF(pendientes.primero());
                prestamosTotales.agregarF(pendientes.primero());
                pendientes.desacolar();
            }
            prestamo.getLibro().subirCantCopias();
            System.out.println("Devolucion: " + prestamo.getUsuario().getNombre() + " ha devuelto el libro: " + prestamo.getLibro().getTitulo() + "\n");
        } else {
            System.out.println("No puede realizarse la devolucion porque no existe ese prestamo\n");
        }
    }

    /**
     * buscar libro en base a su isbn
     * @param isbn
     */
    public void buscarLibro(int isbn) {
        if (libros.pertenece(isbn)) {
            ConjuntoLibrosTDA aux = libros;
            Libro libroAzar = aux.elegir(); // libro elegido al azar
            while (libroAzar.getIsbn() != isbn) {
                aux.sacar(libroAzar.getIsbn());
                libroAzar = aux.elegir();
            }
            System.out.println("Libro encontrado: " + libroAzar.getTitulo() + ", ISBN: " + libroAzar.getIsbn());

        } else {
            System.out.println("Libro no encontrado con ISBN: " + isbn);
        }
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
                    ", Nombre: " + usuarioAzar.getNombre() +
                    ", Direccion: " + usuarioAzar.getDireccion() +
                    ", Telefono: " + usuarioAzar.getTelefono());

            dnisAux.sacar(dniAzar);
        }
        System.out.println();
    }

    public void mostrarColaDeEspera() {
        ColaListaDeEsperaTDA aux = copiarCola();

        System.out.println("--- COLA DE ESPERA ---");
        System.out.println("(Primero arriba)");

        while (!aux.colaVacia()){
            System.out.println("Nombre: " + aux.primero().getUsuario().getNombre() + ", Libro deseado: " + aux.primero().getLibro().getTitulo());
            aux.desacolar();
        }
        System.out.println();
    }

    public void listarTodosPrestamos() {
        System.out.println("--- TODOS LOS PRESTAMOS ---");
        prestamosTotales.mostrar();
        System.out.println();
    }

    // COPIAR ESTRUCTURAS
    private ConjuntoLibrosTDA copiarConjuntoLibros() {
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
    }

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
    }
}
