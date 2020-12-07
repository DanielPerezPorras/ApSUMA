package modelo;

import java.util.ArrayList;
import java.util.Date;

public abstract class Evento {

    private Date Fecha;
    private String Nombre;
    private Tutor profesor;
    private Colaborador colaborador;
    private Administrador administrador;
    private ArrayList<Usuario> participa;
    //TODO private Contenido[] contenido;

    public Evento(Date dia, String nom) {
        Fecha = dia;
        Nombre = nom;
    }

    public void eliminarEvento(){

    }
}
