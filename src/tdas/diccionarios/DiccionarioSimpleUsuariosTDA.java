package tdas.diccionarios;

import entidades.Usuario;
import tdas.conjuntos.ConjuntoUsuariosTDA;

public interface DiccionarioSimpleUsuariosTDA {
    void inicializarDiccionario(); // pre: no aplica
    void agregar(int clave, Usuario valor); // pre: diccionario inicializado
    void eliminar(int clave); // pre: diccionario iniciaizado
    Usuario recuperar(int clave); // pre: diccionario inicializado y no vacio
    ConjuntoUsuariosTDA claves(); // pre: diccionario inicializado
    void mostrar();
}
