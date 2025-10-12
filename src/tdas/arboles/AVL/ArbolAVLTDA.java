package tdas.arboles.AVL;

public interface ArbolAVLTDA {
	
    void insertar(int dato);
    NodoTDA buscar(int dato);
    void recorridoInorden();
    void recorridoPreorden();
    void recorridoPostorden();
}