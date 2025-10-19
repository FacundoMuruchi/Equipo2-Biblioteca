package tdas.arboles.AVL;

import entidades.Usuario;
import tdas.arboles.AVL.ArbolUsuariosAVL.Nodo;

public interface ArbolAVLTDA {
    // El método insertar ahora inserta un dato usuario
    void insertarUsuario(Usuario usuario);
    // El método buscar ahora es por el apellido (String)
    Nodo buscar(String apellido); 
    void recorridoInorden();
}