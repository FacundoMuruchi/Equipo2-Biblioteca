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

    public void agregarLibro(String titulo, String autor, int isbn, int copiasDisponibles) {
        Libro libro = new Libro(titulo, autor, isbn, copiasDisponibles);
        libros.agregar(libro);
    }

    public void agregarUsuario(int dni, String nombre, String direccion, int telefono) {
        Usuario usuario = new Usuario(dni, nombre, direccion, telefono);
        usuarios.agregar(usuario.getDni(), usuario);
    }

    public void agregarPrestamo(Libro libro, Usuario usuario, String fechaDevolucion) {
        Prestamo prestamo = new Prestamo(libro, usuario, fechaDevolucion);
        prestamos.agregarF(prestamo);
    }
}
