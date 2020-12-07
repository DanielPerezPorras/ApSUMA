package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Tutor extends Usuario {
    private String correoUMA;
    private ArrayList<Evento> propuesto;
    private ArrayList<Usuario> usuario;
    private String contra;

    public Tutor(String cUMA, String correo, String DNI, String nombre, String apellido, String contr){
        super(correo, DNI, nombre, apellido, contr);
        correoUMA = cUMA;
    }

    public void crearCurso(Date dia, String nombre, int numClases, int duracion){
        boolean esValido = true;
        // Comprobar si el nombre del curso es válido
        if (esValido){
            Curso curso = new Curso(dia, nombre, this, numClases, duracion);
        }
    }

    public void eliminarCurso(Curso curso){
        propuesto.remove(curso);
        curso.eliminarEvento();
    }
}
