package modelo;

import java.util.ArrayList;

public abstract class Usuario {

    private String correo;
    private String nombreUsuario;
    private String contra;
    private ArrayList<Evento> actividad;
    private ArrayList<Tutor> tutor;

    // Constructor para crear un nuevo usuario
    public Usuario(String cor, String nombreUs, String contr){
        // Hacer sentencia SQL "INSERT..."
        correo = cor;
        nombreUsuario = nombreUs;
        contra = contr;
        actividad = new ArrayList<>();
        tutor = new ArrayList<>();
    }

    // Constructor para recuperar los datos de un usuario ya existente
    public Usuario(String cor) {
        // Hacer sentencia SQL "SELECT..."
        correo = cor;
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

    public ArrayList<Tutor> getTutor() {
        return tutor;
    }
}
