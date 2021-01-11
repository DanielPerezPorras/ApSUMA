package modelo;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class ActividadSocial extends Evento {

    private String lugar;

    public ActividadSocial(Date dia, String nom,Usuario dueno,  String lugar) {
        super(dia, nom, dueno);
        BD bd = new BD();
        bd.Insert("INSERT INTO ActividadSocial VALUES('" + nom + "', '" + lugar + "');");
        this.lugar = lugar;
    }

    public ActividadSocial(String nombre) {
        super(nombre);
        BD bd = new BD();
        List<Object[]> actividadList = bd.Select("SELECT lugar FROM ActividadSocial WHERE nombre = '" + nombre + "';");

        if (actividadList.size() > 0) {
            Object[] actividad = actividadList.get(0);
            lugar = (String)actividad[0];
        } else {
            throw new ErrorBD("No se ha encontrado una actividad social con nombre " + nombre);
        }
    }

    public String getLugar() { return lugar; }

    public void modificar(Date fecha, String nombre, String lugar) {
        super.modificar(fecha, nombre);
        BD bd = new BD();
        bd.Update("UPDATE ActividadSocial SET lugar='" + lugar + "' WHERE nombre='" + getNombre() + "'");
        this.lugar = lugar;
    }

    @Override
    public JLabel getSubtituloPaginaEvento() {
        return new JLabel("[" + formatoFecha.format(Fecha) + "]" + " Lugar: " + lugar);
    }

}
