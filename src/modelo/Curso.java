package modelo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Curso extends Evento {

    private int clases;
    private int duracion;
    private ArrayList<Foro> foros;

    public Curso(Date dia, String nom, Usuario dueno, int num, int duracion) {
        super(dia, nom, dueno);
        BD bd = new BD();
        bd.Insert("INSERT INTO Curso VALUES('" + nom + "', " + num + ", " + duracion + ");");
        clases = num;
        this.duracion = duracion;
    }

    public Curso(String nombre) {
        super(nombre);
        BD bd = new BD();
        List<Object[]> cursoList = bd.Select("SELECT numClases, duracion FROM Curso WHERE nombre = '" + nombre + "';");

        if (cursoList.size() > 0) {
            Object[] curso = cursoList.get(0);
            clases = (int)curso[0];
            duracion = (int)curso[1];
        } else {
            throw new ErrorBD("No se ha encontrado un curso con nombre " + nombre);
        }
    }

    public int getNumClases() { return clases; }

    public int getDuracion() { return duracion; }

    public void modificar(Date fecha, String nombre, int numClases, int duracion) {
        super.modificar(fecha, nombre);
        BD bd = new BD();
        bd.Update("UPDATE Curso SET numClases=" + numClases + ", duracion=" + duracion + " WHERE nombre='" + getNombre() + "'");
        clases = numClases;
        this.duracion = duracion;
    }

    @Override
    public void eliminar() {
        BD bd = new BD();
        bd.Delete("DELETE FROM Curso where nombre = '" + getNombre() + "';");
        clases = 0;
        duracion = 0;
        super.eliminar();
    }

    @Override
    public JLabel getSubtituloPaginaEvento() {
        return new JLabel("[" + formatoFecha.format(Fecha) + "]" + " Número de clases: " + clases + ", duración: " + duracion + " hora(s)");
    }

}
