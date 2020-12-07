package modelo;

import java.util.Date;

public abstract class Evento {

    private Date Fecha;
    private String Nombre;
    //TODO private Contenido[] contenido;

    public Evento(Date dia, String nom) {
        Fecha = dia;
        Nombre = nom;
    }

}
