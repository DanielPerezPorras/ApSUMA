package modelo;

import java.util.Date;

public class ActividadSocial extends Evento {
    private String Lugar;

    public ActividadSocial(Date dia, String nom, String lugar) {
        super(dia,nom);
        this.Lugar = lugar;
    }

}
