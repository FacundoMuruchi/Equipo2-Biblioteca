package tdas.arboles.AVL;

public interface ArbolAVLTDA {
	
    void insertar(int dato);
    ArbolAVL.Nodo buscar(int dato);
    void recorridoInorden();
}