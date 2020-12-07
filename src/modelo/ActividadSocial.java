package modelo;
import java.util.Date;

public class ActividadSocial extends Evento {
    private String Lugar;


    public ActividadSocial(Date dia, String nom, Usuario dueno, String lugar) {
        super(dia, nom, dueno);
        this.Lugar = lugar;
    }

    public ActividadSocial(int identificador) {
        super(identificador);
    }


}
