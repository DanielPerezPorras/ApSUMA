package modelo;

import gui.VistaCurso;

import java.util.Date;
import java.util.List;

public class Curso extends Evento {

    private int clases;
    private int duracion;

    public Curso(Date dia, String nom, Usuario dueno, int num, int duracion) {
        super(dia, nom, dueno);
        BD bd = new BD();
        bd.Insert("INSERT INTO Curso ('" + nom + "', " + num + ", " + duracion + ");");
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

    @Override
    public void abrirEvento() {
        VistaCurso.abrirVentana();
    }

    public void eliminarCurso(){
        this.eliminarEvento();
        BD bd = new BD();
        bd.Delete("DELETE FROM Curso WHERE nombre = '" + this.getNombre() + "';");
        clases = 0;
        duracion = 0;
    }
}
