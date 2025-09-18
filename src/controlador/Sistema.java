package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import tdas.colas.ColaListaDeEsperaDinamica;
import tdas.colas.ColaListaDeEsperaTDA;
import tdas.conjuntos.ConjuntoLibrosLD;
import tdas.conjuntos.ConjuntoLibrosTDA;
import tdas.conjuntos.ConjuntoUsuariosEstatico;
import tdas.conjuntos.ConjuntoUsuariosTDA;
import tdas.diccionarios.DiccionarioSimpleUsuariosEstatico;
import tdas.diccionarios.DiccionarioSimpleUsuariosTDA;
import tdas.listas.ListaPrestamoEstatica;
import tdas.listas.ListaPrestamoTDA;

import java.util.Scanner;

public class Sistema {
    ConjuntoLibrosTDA libros;
    ConjuntoUsuariosTDA dnis;
    DiccionarioSimpleUsuariosTDA usuarios;
    ListaPrestamoTDA prestamosActivos, prestamosTotales;
    ColaListaDeEsperaTDA pendientes;

    /**
     * constructor: inicializa estructuras
     */
    public Sistema() {
        libros = new ConjuntoLibrosLD();
        libros.inicializarConjunto();

        dnis = new ConjuntoUsuariosEstatico();
        dnis.inicializarConjunto();

        usuarios = new DiccionarioSimpleUsuariosEstatico();
        usuarios.inicializarDiccionario();

        prestamosActivos = new ListaPrestamoEstatica();
        prestamosActivos.inicializarLista();

        prestamosTotales = new ListaPrestamoEstatica();
        prestamosTotales.inicializarLista();

        pendientes = new ColaListaDeEsperaDinamica();
        pendientes.inicializarCola();
    }

    /**
     * registrar libro nuevo y añadirlo al conjunto de libros
     */
    public Libro agregarLibro(String titulo, String autor, int isbn, int copiasDisponibles) {
        Libro libro = new Libro(titulo, autor, isbn, copiasDisponibles);
        if (libros.pertenece(isbn)) {
            System.out.println("Ya hay un libro registrado con ISBN: " + isbn + "\n");
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
            System.out.println("Ya hay un usuario registrado con DNI: " + dni + "\n");
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
            if (libro.getCopiasDisponibles() > 0) {
                prestamosActivos.agregarF(prestamo);
                prestamosTotales.agregarF(prestamo);
                libro.bajarCantCopias();
            } else {
                pendientes.acolar(prestamo);
            }
        return prestamo;
    }

    public void listarDevolucionesPendientes() {
        System.out.println("--- DEVOLUCIONES PENDIENTES ---");
        prestamosActivos.mostrar();
        System.out.println();
    }

    public void listarLibros() {
        System.out.println("--- LIBROS ---");
        libros.mostrar();
        System.out.println();
    }

    public void listarUsuarios() {
        System.out.println("--- USUARIOS ---");
        usuarios.mostrar();
        System.out.println();
    }

    public void mostrarListaDeEspera() {
        ColaListaDeEsperaTDA aux = new ColaListaDeEsperaDinamica();
        aux.inicializarCola();
        aux = pendientes;
        System.out.println("--- COLA DE ESPERA ---");
        System.out.println("(Primero arriba)");
        while (!aux.colaVacia()){
            System.out.println("Nombre: " + aux.primero().getUsuario().getNombre() + ", Libro deseado: " + aux.primero().getLibro().getTitulo());
            aux.desacolar();
        }
        System.out.println();
    }

    /**
     * elimina el prestamo de la lista de prestamos activos,
     * luego agrega a la lista de prestamos al primer prestamo de la cola de pendientes
     */
    public void realizarDevolucion(Prestamo prestamo) {
        prestamosActivos.eliminar(prestamo);
        if (!pendientes.colaVacia()) { // si la cola de pendientes no esta vacia
            prestamosActivos.agregarF(pendientes.primero());
            pendientes.desacolar();
        }
        prestamo.getLibro().subirCantCopias();
        System.out.println("Devolucion: " + prestamo.getUsuario().getNombre() + " ha devuelto el libro: " + prestamo.getLibro().getTitulo() + "\n");
    }

    public void listarTodosPrestamos() {
        System.out.println("--- TODOS LOS PRESTAMOS ---");
        prestamosTotales.mostrar();
        System.out.println();
    }

    /**
     * buscar existencia de un libro en el conjunto de libros segun su ISBN
     * @param isbn
     */
    public void buscarLibro(int isbn) {
        Libro libroEncontrado = libros.buscar(isbn);
        if (libroEncontrado != null) {
            System.out.println("Libro encontrado: " + libroEncontrado.getTitulo() + ", ISBN: " + libroEncontrado.getIsbn());
        } else {
            System.out.println("Libro no encontrado con ISBN: " + isbn);
        }
    }
}
