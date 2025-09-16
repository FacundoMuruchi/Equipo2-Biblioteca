package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
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


    public Sistema() {
        libros = new ConjuntoLibrosLD();
        libros.inicializarConjunto();

        dnis = new ConjuntoUsuariosEstatico();
        dnis.inicializarConjunto();

        usuarios = new DiccionarioSimpleUsuariosEstatico();
        usuarios.inicializarDiccionario();

        prestamos = new ListaPrestamoEstatica();
        prestamos.inicializarLista();
    }

    public Libro agregarLibro(String titulo, String autor, int isbn, int copiasDisponibles) {
        Libro libro = new Libro(titulo, autor, isbn, copiasDisponibles);
        libros.agregar(libro);
        return libro;
    }

    public Usuario registrarUsuario(int dni, String nombre, String direccion, int telefono) {
        Usuario usuario = new Usuario(dni, nombre, direccion, telefono);
        usuarios.agregar(usuario.getDni(), usuario);
        return usuario;
    }

    public Prestamo solicitarPrestamo(Libro libro, Usuario usuario, String fechaDevolucion) {
        Prestamo prestamo = new Prestamo(libro, usuario, fechaDevolucion);
        prestamos.agregarF(prestamo);
        ListaPrestamoTDA listaPersonal = prestamos.filtrarPorUsuario(usuario);
        usuario.setPrestamos(listaPersonal);
        libro.bajarCantCopias();
        return prestamo;
    }

    public void listarPrestamos() {
        prestamos.mostrar();
    }

    public void listarLibros() {
        libros.mostrar();
    }

    public void listarUsuarios() {
        usuarios.mostrar();
    }

    public void realizarDevolucion(Usuario usuario, Prestamo prestamo) {
        usuario.getPrestamos().eliminar(prestamo);
        prestamos.eliminar(prestamo);
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
