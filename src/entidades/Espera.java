package entidades;

public class Espera {
    Usuario usuario;
    Libro libro;

    public Espera(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
