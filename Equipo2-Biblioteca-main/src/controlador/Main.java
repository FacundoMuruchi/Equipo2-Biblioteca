package controlador;

public class Main {
    public static void main(String[] args) {
        Sistema sys = new Sistema();

        // REGISTRAR USUARIOS (insertan automáticamente en el AVL)
        sys.registrarUsuario(47307150, "Facundo", "Muruchi", "Berazategui", 1125037150);
        sys.registrarUsuario(52307157, "Juan", "Gonzales", "Quilmes", 1199037540);
        sys.registrarUsuario(49872632, "Federico", "Mestre", "Ranelagh", 1169373462);
        sys.registrarUsuario(42305432, "Carlos", "Tevez", "CABA", 634735475);
        sys.registrarUsuario(50503112, "Santiago", "Alvarez", "Avellaneda", 1147373838);

        // MOSTRAR USUARIOS ORDENADOS POR APELLIDO (función del árbol AVL)
        System.out.println("\n USUARIOS ORDENADOS POR APELLIDO (AVL):");
        sys.listarUsuariosOrdenados();
    }
}
