package modelo;

import gui.VistaPrincipal;

/**
 * Información sobre la sesión abierta actualmente.
 */
public class Sesion {
	private static int permisos = 0; // 0 Admin, 1 Tutor o Colaborador, 2 Estudiante, 3 Invitado
	
    private static Usuario usuarioLogueado = null;

    private static VistaPrincipal vista;

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
    public static boolean logueadoComoAdmin() {
        return usuarioLogueado instanceof Administrador;
    }

    public static void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
        if (usuario != null) {
            if (usuarioLogueado instanceof Administrador) {
                setPermisos(0);
            } else if (usuarioLogueado instanceof Tutor || usuarioLogueado instanceof Colaborador) {
                setPermisos(1);
            } else {
                setPermisos(2);
            }
        } else {
            setPermisos(3);
        }
    }

    public static void setPermisos (int num) {
    	permisos = num;
    	System.out.println("Nivel de permisos actual: " + permisos);
    }
    public static int getPermisos () {
    	return permisos;
    }

    public static boolean puedoCrearEventos() {
        return permisos < 2;
    }

    public static void setVistaPrincipal(VistaPrincipal vista) { Sesion.vista = vista; }
    public static VistaPrincipal getVistaPrincipal() { return vista; }
}
