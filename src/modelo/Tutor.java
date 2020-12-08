package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Tutor extends Usuario {
    private String correoUMA;
    private ArrayList<Evento> propuesto;
    private ArrayList<Usuario> usuario;

    public Tutor(String cUMA, String correo, String usr, String contr){
        super(correo, usr, contr);
        correoUMA = cUMA;
    }

    public void crearCurso(Date dia, String nombre, int numClases, int duracion){
        boolean esValido = true;
        // Comprobar si el nombre del curso es válido
        if (esValido){
            Curso curso = new Curso(dia, nombre, this, numClases, duracion);
        }
    }

    public void modificarInformacionTutor(String corUMA, String cor, String nombr, String contr){
        this.modificarInformacion(cor, nombr, contr);
        BD bd = new BD();
        bd.Update("UPDATE Tutor SET correo = '" + cor + "', correoUMA = '" + corUMA + "';");
        correoUMA = corUMA;
    }

    public void eliminarCuentaTutor(){
        this.eliminarCuenta();
        BD bd = new BD();
        bd.Delete("DELETE FROM Tutor WHERE correo = '" + this.getCorreo() + "';");
        correoUMA = null;
    }

    public void eliminarCurso(Curso curso){
        propuesto.remove(curso);
        curso.eliminarEvento();
    }

    public String getCorreoUMA() {
        return correoUMA;
    }

    public ArrayList<Evento> getPropuesto() {
        return propuesto;
    }

    public ArrayList<Usuario> getUsuario() {
        return usuario;
    }
}
