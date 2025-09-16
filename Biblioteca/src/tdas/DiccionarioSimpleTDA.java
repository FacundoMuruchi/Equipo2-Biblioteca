package tdas;

public interface DiccionarioSimpleTDA {
    void InicializarDiccionario();
    void Agregar(int clave, Object valor); 
    void Eliminar(int clave);
    Object Recuperar(int clave);
    boolean ClaveExiste(int clave);
}
