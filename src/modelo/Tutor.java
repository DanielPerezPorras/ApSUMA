package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Tutor extends Usuario {

    private ArrayList<Evento> propuesto;
    private ArrayList<Usuario> usuario;

    public Tutor(String cUMA, String correo, String usr, String contr){
        super(correo, usr, contr);
    }

    public void crearCurso(Date dia, String nombre, int numClases, int duracion){
        boolean esValido = true;
        // Comprobar si el nombre del curso es válido
        if (esValido){
            Curso curso = new Curso(dia, nombre, this, numClases, duracion);
        }
    }

    public void modificarInformacionTutor(String cor, String nombr, String contr){
        this.modificarInformacion(cor, nombr, contr);
    }

    public void eliminarCurso(Curso curso){
        propuesto.remove(curso);
        curso.eliminarEvento();
    }

    public ArrayList<Evento> getPropuesto() {
        return propuesto;
    }

    public ArrayList<Usuario> getUsuario() {
        return usuario;
    }
}
