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

public class Sistema {
    ConjuntoLibrosTDA libros;
    ConjuntoUsuariosTDA dnis;
    DiccionarioSimpleUsuariosTDA usuarios;
    ListaPrestamoTDA prestamos;
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

        prestamos = new ListaPrestamoEstatica();
        prestamos.inicializarLista();

        pendientes = new ColaListaDeEsperaDinamica();
        pendientes.inicializarCola();
    }

    /**
     * registrar libro nuevo y añadirlo al conjunto de libros
     */
    public Libro agregarLibro(String titulo, String autor, int isbn, int copiasDisponibles) {
        Libro libro = new Libro(titulo, autor, isbn, copiasDisponibles);
        libros.agregar(libro);
        return libro;
    }

    /**
     * registrar usuario nuevo y agregarlo al diccionario
     */
    public Usuario registrarUsuario(int dni, String nombre, String direccion, int telefono) {
        Usuario usuario = new Usuario(dni, nombre, direccion, telefono);
        usuarios.agregar(usuario.getDni(), usuario);
        return usuario;
    }

    /**
     * generar solicitud de prestamo. si está disponible, se agrega a la lista de prestamos y a la lista personal del usuario correspondiente,
     * sino se acola en la cola de prestamos pendientes
     */
    public Prestamo solicitarPrestamo(Libro libro, Usuario usuario, String fechaDevolucion) {
        Prestamo prestamo = new Prestamo(libro, usuario, fechaDevolucion);
        if (libro.getCopiasDisponibles() > 0) {
            prestamos.agregarF(prestamo);
            ListaPrestamoTDA listaPersonal = prestamos.filtrarPorUsuario(usuario);
            usuario.setPrestamos(listaPersonal);
            libro.bajarCantCopias();
        } else {
            pendientes.acolar(prestamo);
        }
        return prestamo;
    }

    public void listarDevolucionesPendientes() {
        prestamos.mostrar();
    }

    public void listarLibros() {
        libros.mostrar();
    }

    public void listarUsuarios() {
        usuarios.mostrar();
    }

    public void mostrarListaDeEspera() {
        ColaListaDeEsperaTDA aux = new ColaListaDeEsperaDinamica();
        aux.inicializarCola();
        aux = pendientes;
        System.out.println("--- COLA DE ESPERA ---");
        System.out.println("(Primero arriba, ultimo abajo)");
        while (!aux.colaVacia()){
            System.out.println("Nombre: " + aux.primero().getUsuario().getNombre() + ", Libro deseado: " + aux.primero().getLibro().getTitulo());
            aux.desacolar();
        }
    }

    /**
     * elimina el prestamo de la lista personal y de la lista de prestamos,
     * luego agrega a la lista de prestamos al primer prestamo de la cola de pendientes
     */
    public void realizarDevolucion(Prestamo prestamo) {
        prestamo.getUsuario().getPrestamos().eliminar(prestamo);
        prestamos.eliminar(prestamo);
        if (!pendientes.colaVacia()) { // si la cola de pendientes no esta vacia
            prestamos.agregarF(pendientes.primero());
            pendientes.desacolar();
        }
        prestamo.getLibro().subirCantCopias();
    }

    public void buscarLibro(int isbn) {
        Libro libroEncontrado = libros.buscar(isbn);
        if (libroEncontrado != null) {
            System.out.println("Libro encontrado: " + libroEncontrado.getTitulo() + ", ISBN: " + libroEncontrado.getIsbn());
        } else {
            System.out.println("Libro no encontrado con ISBN: " + isbn);
        }
    }
}
