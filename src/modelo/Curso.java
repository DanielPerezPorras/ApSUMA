package modelo;

import java.util.Date;

public class Curso extends Evento {

    private int clases;
    private int duracion;


    public Curso(int identificador,Date dia, String nom, Usuario dueno, int num, int duracion) {
        super(identificador,dia, nom, dueno);
        clases = num;
        this.duracion = duracion;
    }

    public Curso(int identificador) {
        super(identificador);
    }
}
