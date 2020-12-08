package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {

    private String correo;
    private String nombreUsuario;
    private String contra;
    private ArrayList<Evento> actividad;

    // Constructor para crear un nuevo usuario
    public Usuario(String cor, String nombreUs, String contr){
        BD bd = new BD();
        bd.Insert("INSERT INTO Usuario ('" + cor + "', '" + nombreUs + "', '" + contr + "');");
        correo = cor;
        nombreUsuario = nombreUs;
        contra = contr;
        actividad = new ArrayList<>();
    }

    // Constructor para recuperar los datos de un usuario ya existente
    public Usuario(String cor) {

        BD bd = new BD();
        List<Object[]> userList = bd.Select("SELECT * FROM Usuario WHERE correo = '" + cor + "';");

        if (userList.size() > 0) {
            Object[] user = userList.get(0);
            correo = cor;
            nombreUsuario = (String)user[1];
            contra = (String)user[2];
            actividad = null;
        } else {
            throw new ErrorBD("No se ha encontrado un usuario con correo " + cor);
        }

    }

    public void modificarInformacion(String cor, String nombr, String contr){
        // Hacer sentencia SQL "UPDATE..."
        correo = cor;
        nombreUsuario = nombr;
        contra = contr;
    }

    public void eliminarCuenta(){
        // Hacer sentencia SQL "DELETE..."
        correo = null;
        nombreUsuario = null;
        contra = null;
    }

    public void darseAltaEvento(Evento evento){
        actividad.add(evento);
    }

    public void darseBajaEvento(Evento evento){
        actividad.remove(evento);
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public ArrayList<Evento> getActividad() {
        return actividad;
    }

    public static Usuario buscarUsuario(String correo) {
        Usuario usuario = null;
        try {
            usuario = new Estudiante(correo);
        } catch (ErrorBD ex1) {
            try {
                usuario = new Tutor(correo);
            } catch (ErrorBD ex2) {
                try {
                    usuario = new Colaborador(correo);
                } catch (ErrorBD ex3) {
                    try {
                        usuario = new Administrador(correo);
                    } catch (ErrorBD ignored) { }
                }
            }
        }
        return usuario;
    }

}
