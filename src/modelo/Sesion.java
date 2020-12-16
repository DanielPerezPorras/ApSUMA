package modelo;

/**
 * Información sobre la sesión abierta actualmente.
 */
public class Sesion {
	private static int permisos = 0; // 0 Admin, 1 Tutor, 2 Estudiante, 3 Invitado
	
    private static Usuario usuarioLogueado = null;

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
    public static boolean logueadoComoAdmin() {
        return usuarioLogueado instanceof Administrador;
    }

    public static void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
    }
    
    public static void setPermisos (int num) {
    	permisos = num;
    }
    
    public static int getPermisos () {
    	return permisos;
    }


}
