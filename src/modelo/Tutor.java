package modelo;

import gui.VistaPrincipalTutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tutor extends Usuario {
    private String correoUMA;
    private ArrayList<Evento> propuesto;
    private ArrayList<Usuario> usuario;

    public Tutor(String cUMA, String correo, String usr, String contr){
        super(correo, usr, contr);
        BD bd = new BD();
        bd.Insert("INSERT INTO Tutor VALUES ('" + correo + "', '" + cUMA + "');");
        correoUMA = cUMA;
        propuesto = null;
        usuario = null;
    }

    public Tutor(String correo) {
        super(correo);
        BD bd = new BD();
        List<Object[]> userList = bd.Select("SELECT correoUMA FROM Tutor WHERE correo = '" + correo + "';");

        if (userList.size() > 0) {
            Object[] user = userList.get(0);
            correoUMA = (String)user[0];
            propuesto = null;
            usuario = null;
        } else {
            throw new ErrorBD("No se ha encontrado un tutor con correo " + correo);
        }
    }

    @Override
    public void abrirVentanaPrincipal() {
        VistaPrincipalTutor.abrirVentana();
    }

    public void crearCurso(Date dia, String nombre, int numClases, int duracion){
        Curso curso = new Curso(dia, nombre, this, numClases, duracion);
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
        curso.eliminarCurso();
    }

    public String getCorreoUMA() {
        return correoUMA;
    }

    public ArrayList<Evento> getPropuesto() {
        if (propuesto == null) {
            propuesto = new ArrayList<>();
            BD bd = new BD();
            List<Object[]> resultados = bd.Select("SELECT nombre FROM Evento WHERE correo='" + getCorreo() + "'");
            for (Object[] tupla : resultados) {
                propuesto.add(Evento.buscarEvento((String)tupla[0]));
            }
        }
        System.out.println(propuesto.size());
        return propuesto;
    }

    public ArrayList<Usuario> getUsuario() {
        return usuario;
    }
}
