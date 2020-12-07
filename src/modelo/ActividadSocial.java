package modelo;
import java.util.Date;

public class ActividadSocial extends Evento {
    private String Lugar;


    public ActividadSocial(int identificador,Date dia, String nom,Usuario dueno,  String lugar) {
        super(identificador,dia,nom, dueno);
        this.Lugar = lugar;
    }

    public ActividadSocial(int identificador) {
        super(identificador);
    }


}
