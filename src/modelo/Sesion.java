package modelo;

/**
 * Información sobre la sesión abierta actualmente.
 */
public class Sesion {

    private static Usuario usuarioLogueado = null;

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
    }


}
