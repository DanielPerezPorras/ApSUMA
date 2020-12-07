package modelo;

import java.util.Date;

public class Curso extends Evento {

    private int clases;
    private int duracion;

    public Curso(Date dia, String nom, int num, int duracion) {
        super(dia, nom);
        clases = num;
        this.duracion = duracion;
    }
}
