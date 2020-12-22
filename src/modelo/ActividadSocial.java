package modelo;

import gui.VistaEvento;

import java.util.Date;
import java.util.List;

public class ActividadSocial extends Evento {

    private String Lugar;

    public ActividadSocial(Date dia, String nom,Usuario dueno,  String lugar) {
        super(dia, nom, dueno);
        BD bd = new BD();
        bd.Insert("INSERT INTO ActividadSocial ('" + nom + "', '" + lugar + "');");
        this.Lugar = lugar;
    }

    public ActividadSocial(String nombre) {
        super(nombre);
        BD bd = new BD();
        List<Object[]> actividadList = bd.Select("SELECT lugar FROM ActividadSocial WHERE nombre = '" + nombre + "';");

        if (actividadList.size() > 0) {
            Object[] actividad = actividadList.get(0);
            Lugar = (String)actividad[0];
        } else {
            throw new ErrorBD("No se ha encontrado una actividad social con nombre " + nombre);
        }
    }


}
