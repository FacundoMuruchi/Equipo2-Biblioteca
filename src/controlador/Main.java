package controlador;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import java.util.Scanner;

public class Main {
    private Sistema sys;
    private Scanner scanner;

    public Main() {
        this.sys = new Sistema();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("─────────────── SISTEMA DE GESTIÓN BIBLIOTECARIA ───────────────");

        boolean continuar = true;
        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            continuar = procesarOpcion(opcion);
        }

        System.out.println("\n¡Saliendo...!");
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n┌─────────────── MENÚ PRINCIPAL ───────────────┐");
        System.out.println("1.  Gestión de Libros");
        System.out.println("2.  Gestión de Usuarios");
        System.out.println("3.  Gestión de Préstamos");
        System.out.println("4.  Consultas y Reportes");
        System.out.println("5.  Visualizar Estructuras de Datos");
        System.out.println("0.  Salir");
        System.out.println("└──────────────────────────────────────────────┘");
        System.out.print("Seleccione una opción: ");
    }

    private boolean procesarOpcion(int opcion) {
        System.out.println();
        switch (opcion) {
            case 1:
                menuLibros();
                break;
            case 2:
                menuUsuarios();
                break;
            case 3:
                menuPrestamos();
                break;
            case 4:
                menuConsultas();
                break;
            case 5:
                menuEstructuras();
                break;
            case 0:
                return false;
            default:
                System.out.println("⚠ Opción inválida. Intente nuevamente.");
        }
        return true;
    }

    private void menuLibros() {
        System.out.println("┌────── GESTIÓN DE LIBROS ──────┐");
        System.out.println("1. Agregar libro");
        System.out.println("2. Listar todos los libros");
        System.out.println("3. Buscar libro por ISBN");
        System.out.println("4. Mostrar libros ordenados");
        System.out.println("0. Volver al menú principal");
        System.out.println("└───────────────────────────────┘");
        System.out.print("Seleccione una opción: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1:
                agregarLibro();
                break;
            case 2:
                sys.listarLibros();
                break;
            case 3:
                buscarLibro();
                break;
            case 4:
                sys.mostrarLibrosOrdenados();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void menuUsuarios() {
        System.out.println("┌────── GESTIÓN DE USUARIOS ──────┐");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Listar todos los usuarios");
        System.out.println("3. Buscar usuario por DNI");
        System.out.println("4. Mostrar usuarios ordenados");
        System.out.println("0. Volver al menú principal");
        System.out.println("└─────────────────────────────────┘");
        System.out.print("Seleccione una opción: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1:
                registrarUsuario();
                break;
            case 2:
                sys.listarUsuarios();
                break;
            case 3:
                buscarUsuario();
                break;
            case 4:
                sys.listarUsuariosOrdenados();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void menuPrestamos() {
        System.out.println("┌────── GESTIÓN DE PRÉSTAMOS ──────┐");
        System.out.println("1. Solicitar préstamo");
        System.out.println("2. Realizar devolución");
        System.out.println("3. Listar todos los préstamos");
        System.out.println("4. Devoluciones pendientes");
        System.out.println("5. Ver cola de espera");
        System.out.println("0. Volver al menú principal");
        System.out.println("└──────────────────────────────────┘");
        System.out.print("Seleccione una opción: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1:
                solicitarPrestamo();
                break;
            case 2:
                realizarDevolucion();
                break;
            case 3:
                sys.listarTodosPrestamos();
                break;
            case 4:
                sys.listarDevolucionesPendientes();
                break;
            case 5:
                sys.mostrarColaDeEspera();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void menuConsultas() {
        System.out.println("┌────── CONSULTAS Y REPORTES ──────┐");
        System.out.println("1. Listar todos los libros");
        System.out.println("2. Listar todos los usuarios");
        System.out.println("3. Listar todos los préstamos");
        System.out.println("4. Libros ordenados por ISBN");
        System.out.println("5. Usuarios ordenados por DNI");
        System.out.println("0. Volver al menú principal");
        System.out.println("└──────────────────────────────────┘");
        System.out.print("Seleccione una opción: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1:
                sys.listarLibros();
                break;
            case 2:
                sys.listarUsuarios();
                break;
            case 3:
                sys.listarTodosPrestamos();
                break;
            case 4:
                sys.mostrarLibrosOrdenados();
                break;
            case 5:
                sys.listarUsuariosOrdenados();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void menuEstructuras() {
        System.out.println("┌────── ESTRUCTURAS DE DATOS ──────┐");
        System.out.println("1. Imprimir árbol de usuarios");
        System.out.println("2. Imprimir árbol de libros");
        System.out.println("0. Volver al menú principal");
        System.out.println("└──────────────────────────────────┘");
        System.out.print("Seleccione una opción: ");

        int opcion = leerOpcion();
        System.out.println();

        switch (opcion) {
            case 1:
                sys.imprimirArbolUsuarios();
                break;
            case 2:
                sys.imprimirArbolLibros();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    // Métodos auxiliares para operaciones específicas

    private void agregarLibro() {
        System.out.println("═══ AGREGAR NUEVO LIBRO ═══");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        int isbn = leerEntero();
        System.out.print("Cantidad de copias: ");
        int copias = leerEntero();

        Libro libro = sys.agregarLibro(titulo, autor, isbn, copias);
        if (libro != null) {
            System.out.println("Libro agregado exitosamente.");
        }
    }

    private void buscarLibro() {
        System.out.print("Ingrese el ISBN del libro: ");
        int isbn = leerEntero();
        sys.buscarLibro(isbn);
    }

    private void registrarUsuario() {
        System.out.println("═══ REGISTRAR NUEVO USUARIO ═══");
        System.out.print("DNI: ");
        int dni = leerEntero();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Teléfono: ");
        int telefono = leerEntero();

        Usuario usuario = sys.registrarUsuario(dni, nombre, apellido, domicilio, telefono);
        if (usuario != null) {
            System.out.println("Usuario registrado exitosamente.");
        }
    }

    private void buscarUsuario() {
        System.out.print("Ingrese el DNI del usuario: ");
        int dni = leerEntero();
        sys.buscarUsuario(dni);
    }

    private void solicitarPrestamo() {
        System.out.println("═══ SOLICITAR PRÉSTAMO ═══");
        System.out.print("ISBN del libro: ");
        int isbn = leerEntero();
        Libro libroEncontrado = sys.buscarLibro(isbn);
        if (libroEncontrado == null)
            return;
        System.out.print("DNI del usuario: ");
        int dni = leerEntero();
        Usuario usuarioEncontrado = sys.buscarUsuario(dni);
        if (usuarioEncontrado == null)
            return;

        sys.solicitarPrestamo(libroEncontrado, usuarioEncontrado);
        System.out.println("Prestamo solicitado exitosamente, tienes 30 dias para realizar la devolucion");
    }

    private void realizarDevolucion() {
        System.out.println("═══ REALIZAR DEVOLUCIÓN ═══");
        System.out.print("Codigo del prestamo: ");
        int codigo = leerEntero();
        Prestamo prestamo = sys.buscarPrestamo(codigo);
        if (prestamo == null)
            return;
        sys.realizarDevolucion(prestamo);
        System.out.println("Devolucion realizada exitosamente");
    }

    // Métodos de utilidad

    private int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
            return opcion;
        } catch (Exception e) {
            scanner.nextLine(); // limpiar buffer
            return -1;
        }
    }

    private int leerEntero() {
        try {
            int valor = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
            return valor;
        } catch (Exception e) {
            scanner.nextLine(); // limpiar buffer
            System.out.println("Valor inválido. Intente nuevamente.");
            return leerEntero();
        }
    }

    public static void main(String[] args) {
        Main interfaz = new Main();
        interfaz.iniciar();
    }
}