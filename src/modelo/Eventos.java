package modelo;

import java.util.Date;

public abstract class Eventos {

    private Date Fecha;
    private String Nombre;
    //TODO private Contenido[] contenido;

    public Eventos(Date dia, String nom) {
        Fecha = dia;
        Nombre = nom;
    }

}
