package modelo;

import java.util.ArrayList;

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
        Object[] usr = bd.Select("SELECT * FROM Usuario WHERE correo = '" + cor + "';").get(0);

        correo = cor;
        nombreUsuario = (String) usr[1];
        contra = (String) usr[2];
        actividad = new ArrayList<>();
    }

    public void modificarInformacion(String cor, String nombr, String contr){
        // Hacer sentencia SQL "UPDATE..."
        BD bd = new BD();
        bd.Update("UPDATE Usuario SET correo = '" + cor + "', nombreUsuario = '" + nombr + "', contra = '" + contr + "';");
        correo = cor;
        nombreUsuario = nombr;
        contra = contr;
    }

    public void eliminarCuenta(){
        // Hacer sentencia SQL "DELETE..."
        BD bd = new BD();
        bd.Delete("DELETE FROM Usuario WHERE correo = '" + correo + "';");
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
}
