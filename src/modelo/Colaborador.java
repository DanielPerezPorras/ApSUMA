package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Colaborador extends Usuario{
    private String correoCorp;
    private ArrayList<Evento> propuesto;

    public Colaborador(String correoCorporativo, String cor, String usuario, String contr) {
        super(cor, usuario, contr);
        BD bd = new BD();
        bd.Insert("INSERT INTO Colaborador VALUES ('" + cor + "', '" + correoCorporativo + "');");
        correoCorp = correoCorporativo;
        propuesto = null;
    }

    public Colaborador(String correo) {
        super(correo);
        BD bd = new BD();
        List<Object[]> userList = bd.Select("SELECT correoCorp FROM Colaborador WHERE correo = '" + correo + "';");

        if (userList.size() > 0) {
            Object[] user = userList.get(0);
            correoCorp = (String)user[0];
            propuesto = null;
        } else {
            throw new ErrorBD("No se ha encontrado un colaborador con correo " + correo);
        }
    }

    public void crearCurso(Date dia, String nombre, int numClases, int duracion){
        Curso curso = new Curso(dia, nombre, this, numClases, duracion);
        if (propuesto == null) {
            propuesto = new ArrayList<>();
        }
        propuesto.add(curso);
        super.getEventosInscritos().add(curso);
        BD bd = new BD();
        bd.Insert("INSERT INTO UsuarioEvento VALUES('" + getCorreo() + "', '" + nombre + "');");
    }

    public void crearConferencia(Date dia, String nombre, String link) {
        Conferencia conferencia = new Conferencia(dia, nombre, this, link);
        if (propuesto == null){
            propuesto = new ArrayList<>();
        }
        propuesto.add(conferencia);
        super.getEventosInscritos().add(conferencia);
        BD bd = new BD();
        bd.Insert("INSERT INTO UsuarioEvento VALUES('" + getCorreo() + "', '" + nombre + "');");
    }

    public void crearActividad(Date dia, String nombre, String lugar) {
        ActividadSocial actividad = new ActividadSocial(dia, nombre, this, lugar);
        if (propuesto == null){
            propuesto = new ArrayList<>();
        }
        propuesto.add(actividad);
        super.getEventosInscritos().add(actividad);
        BD bd = new BD();
        bd.Insert("INSERT INTO UsuarioEvento VALUES('" + getCorreo() + "', '" + nombre + "');");
    }

    public void eliminarEvento(Evento evento){
        if (!(propuesto == null)){
            propuesto.removeIf(ev -> evento.getNombre().equals(evento.getNombre()));
            super.getEventosInscritos().remove(evento);
        }
    }

    public void modificarInformacion(String cor, String usuario, String correoCorporativo){
        super.modificarInformacion(cor, usuario, null);
        BD bd = new BD();
        bd.Update("UPDATE Colaborador SET correoCorp = '" + correoCorporativo + "' WHERE correo = '" + getCorreo() + "';");
        correoCorp = correoCorporativo;

    }

    public void eliminarCuenta(){
        BD bd = new BD();
        bd.Delete("DELETE FROM Colaborador WHERE correo = '" + this.getCorreo() + "';");
        correoCorp = null;
        super.eliminarCuenta();
    }

    @Override
    public String getCorp() {
        return this.correoCorp;
    }

    public String getCorreoCorp() {
        return correoCorp;
    }

}
